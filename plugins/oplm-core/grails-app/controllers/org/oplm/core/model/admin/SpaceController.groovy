package org.oplm.core.model.admin


import grails.converters.*

class SpaceController {
	
	// the delete, save and update actions only accept POST requests
	static allowedMethods = [delete:'POST', save:'POST', update:'POST']
	
	def index = {
        redirect(action: "list", params: params)
    }
    
    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 25,  100)
        def spaceInstanceList = Space.list( params )
        def spaceInstanceTotal = Space.count()
	    withFormat {
        	html { return [ spaceInstanceList : spaceInstanceList , spaceInstanceTotal: spaceInstanceTotal ] }
        	xml  { render ( [totalCount:spaceInstanceTotal, spaces : spaceInstanceList ] as XML) }
        	json { render ( [totalCount:spaceInstanceTotal, spaces : spaceInstanceList ] as JSON) }
        }
    }
	
	def create = {
		def spaceInstance = new Space()
		spaceInstance.properties = params
		withFormat {
			html {
				return ['spaceInstance':spaceInstance]
			}
			xml {
				render (['spaceInstance':spaceInstance] as XML)
			}
			json {
				render (['spaceInstance':spaceInstance] as JSON)
			}
		}
	}
	
	def save = {
		def spaceInstance = new Space(params)
		if (saveObject(spaceInstance)) {
			redirect(action: 'show', id: spaceInstance.id)
		}
		else {
			render(view: 'create', model: [spaceInstance: spaceInstance])
		}
	}
	
    def show = {
        def spaceInstance = Space.get( params.id )

        if(!spaceInstance) {
            flash.message = "space.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Space not found with id ${params.id}"
            redirect(action: "list")
        } else {  
        	withFormat {
                html { return [spaceInstance: spaceInstance] }
                xml  { render ( [spaceInstance: spaceInstance]  as XML )}
                json { render ( [spaceInstance: spaceInstance]  as JSON )}
        	}
        }
    }
	
	def edit = {
		this.setLastReturnToAction(session, request, ["space/list","space/show"])
		if (request.post) {
			params.action='edit'
			redirect(params)
		}
		def spaceInstance = Space.get(Long.parseLong(params.id.toString()))
		if(!spaceInstance) {
			withFormat{
				
				html {
					flash.message = "space.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Space not found with id ${params.id}"
					redirect(action: 'list')
				}
				xml {
					response.status = 404 //Not Found
					render "Space not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Space not found with id ${params.id}"
				}
			}
		} else {
			withFormat {
				response.status = 200 // OK
				html { return [ spaceInstance : spaceInstance ]}
				xml  { render ([ spaceInstance : spaceInstance ] as XML)}
				json { render ([ spaceInstance : spaceInstance ] as JSON)}
			}
		}
	}
	
	def update = {
		deleteUnauthorizedParams(params)
		def spaceInstance = Space.get( params.id )
		if(spaceInstance) {
			if(params.version) {
				def version = params.version.toLong()
				if(spaceInstance.version > version) {
					
					spaceInstance.errors.rejectValue("version", "space.optimistic.locking.failure", "Another user has updated this Space while you were editing.")
					render(view:'edit',model:[spaceInstance:spaceInstance])
					return
				}
			}
			spaceInstance.properties = params
			if(!spaceInstance.hasErrors() && spaceInstance.save()) {
				withFormat {
					html {
						flash.message = "space.updated"
						flash.args = [params.id]
						flash.defaultMessage = "Space ${params.id} updated"
						if (session.returnToReferer) {
							redirect(url:session.returnToReferer)
							session.removeAttribute('returnToReferer')
						} else {
							redirect(action:"list")
						}
					}
					xml {
						response.status = 200 // OK
						render spaceInstance as XML
					}
					json {
						response.status = 200 // OK
						render spaceInstance as XML
					}
				}
			}
			else {
				withFormat {
					html {
						render(view:'edit',model:[spaceInstance:spaceInstance])
					}
					xml {
						response.status = 500 //Internal Server Error
					}
					json {
						response.status = 500 //Internal Server Error
						// TODO : response.status = 500
						render "Space could not be saved due to errors:"
					}
				}
			}
		}
		else {
			withFormat {
				html {
					flash.message = "space.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Space not found with id ${params.id}"
					redirect(action: 'edit', id: params.id)
				}
				xml {
					response.status = 404 //Not Found
					render "Space not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Space not found with id ${params.id}"
				}
			}
		}
	}
	
    def delete = {
        def spaceInstance = Space.get( params.id )
        if(spaceInstance) {
            try {
                spaceInstance.delete()
                withFormat {
                	html {
                        flash.message = "space.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Space ${params.id} deleted"
                        redirect(action: 'list')
                	}
                	xml {
                		response.status = 200 // OK
                	    render  "Space ${params.id} deleted"	
                	}
                	json {
                		response.status = 200 // OK
                		render "Space ${params.id} deleted"
                	}                		
                }
            } catch(org.springframework.dao.DataIntegrityViolationException e) {
            	withFormat{
            		html {
                        flash.message = "space.not.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Space ${params.id} could not be deleted"
                        redirect(action: 'show', id: params.id)
            		}
            		xml {
            			response.status = 500 //Internal Server Error
            			render "Space ${params.id} could not be deleted"
            		}
            		json {
            			response.status = 500 //Internal Server Error
            	        render "Space ${params.id} could not deleted due to errors:"
            		}
            	}
            }
        } else {
        	withFormat {
        		html {
                    flash.message = "space.not.found"
                    flash.args = [params.id]
                    flash.defaultMessage = "Space not found with id ${params.id}"
                    redirect(action: 'list')
        		}
        		xml {
        			response.status = 404 //Not Found
      	            render "Space not found with id ${params.id}"
        		}
        		json{
        			response.status = 404 //Not Found
      	            render "Space not found with id ${params.id}"
        		}
        	}
        }
    }
	
    def saveAndRedirectToCreate = {
        def spaceInstance = new Space(params)
        if (saveObject(spaceInstance)) {
            redirect(action: 'create')
        }
        else {
            render(view: 'create', model: [spaceInstance: spaceInstance])
        }
    }

    /**
     * Save the given Domain-Object.
     */
    private saveObject(def spaceInstance) {
        def flash = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getFlashScope()
        setDefaultsForUnauthorizedProperties (spaceInstance)
        if (!spaceInstance.hasErrors() && spaceInstance.save()) {
        	withFormat {
        		html {
                    flash.message = "space.created"
                    flash.args = [spaceInstance.id]
                    flash.defaultMessage = "Space ${spaceInstance.id} created"
                    return true
        		}
        		xml {
       			    response.status = 201 // Created
 		            render spaceInstance as XML
        		}
        		json {
       			    esponse.status = 201 // Created
 		            render spaceInstance as XML
        		}
        	}
        }
        return false
    }

    /**
     * Removes parameter values from request that are restricted due to the
     * current user role. The actual checking is done by the underlying private
     * method handleUnauthorizedProperties.
     *
     * @param params The params to check and clear for restricted values.
     */
    def deleteUnauthorizedParams(def params) {
        def action = {map, key, closure, session ->
            map.remove(key)
        }
        handleUnauthorizedProperties (params, action)
    }


    /**
     * Sets domain object properties to default values that have not been set by the view
     * due to the current user role restriction. The actual default value is gathered
     * by the underlying private method handleUnauthorizedProperties.
     *
     * @param domainObject The domain object to set default values for.
     */
    def setDefaultsForUnauthorizedProperties (def domainObject) {
        def action = { object, key, closure, session ->
            def defaultValue = closure(session, object)
            object."${key}" = defaultValue
        }
        handleUnauthorizedProperties (domainObject, action)
    }

    /**
     * Checks and handles restricted write-only object properties. If the current user is not
     * authorized to update a property a closure is called to handle the problem.
     * For each restricted property a static map is defined on the domain object.
     * In that map a closure is defined to gather the default value for the property.
     *
     * @param object The domain object to handle unautherized properties for.
     * @param action A closure which is called in case a properties needs to be handled.
     */
    private handleUnauthorizedProperties (def object, def action) {
        def permissionField = Space.getMetaClass().getMetaProperty("needsPermission")
        if (permissionField && java.lang.reflect.Modifier.isStatic(permissionField.getModifiers())) {
            def session = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getSession()
            def authorizationService = org.codehaus.groovy.grails.commons.ApplicationHolder.application.mainContext.getBean("authorizationService")

            Space.needsPermission.each { key, closure ->
                if (closure && !authorizationService.hasPermission("Space.${key}", "write", session)) {
                    action(object, key, closure, session)
                }
            }
        }
    }
    
    /**
     * Saves request.referer information into the session to remember return to pages.
     * Useful to trigger an e.g. edit page from multiple locations.
     */
    void setLastReturnToAction(def session, def request, def allowedReferers) {
        if (!session.returnToReferer) {
            def referer = request.getHeader("referer")
            if (!referer) {
                return
            }
            def allowed = true
            if (allowedReferers) {
                allowed = false
                allowedReferers.each {
                    if (referer.contains(it)) { allowed = true }
                }
            }
            if (allowed) {
                session.returnToReferer = request.getHeader("referer")
            }
        }
    }
        
}
