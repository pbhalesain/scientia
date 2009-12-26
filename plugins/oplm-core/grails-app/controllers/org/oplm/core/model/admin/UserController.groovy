package org.oplm.core.model.admin


import grails.converters.*

class UserController {
	
	// the delete, save and update actions only accept POST requests
	static allowedMethods = [delete:'POST', save:'POST', update:'POST']
	
	def index = {
        redirect(action: "list", params: params)
    }
    
    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 25,  100)
        def userInstanceList = User.list( params )
        def userInstanceTotal = User.count()
	    withFormat {
        	html { return [ userInstanceList : userInstanceList , userInstanceTotal: userInstanceTotal ] }
        	xml  { render ( [totalCount:userInstanceTotal, users : userInstanceList ] as XML) }
        	json { render ( [totalCount:userInstanceTotal, users : userInstanceList ] as JSON) }
        }
    }
	
	def create = {
		def userInstance = new User()
		userInstance.properties = params
		withFormat {
			html {
				return ['userInstance':userInstance]
			}
			xml {
				render (['userInstance':userInstance] as XML)
			}
			json {
				render (['userInstance':userInstance] as JSON)
			}
		}
	}
	
	def save = {
		def userInstance = new User(params)
		if (saveObject(userInstance)) {
			redirect(action: 'show', id: userInstance.id)
		}
		else {
			render(view: 'create', model: [userInstance: userInstance])
		}
	}
	
    def show = {
        def userInstance = User.get( params.id )

        if(!userInstance) {
            flash.message = "user.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "User not found with id ${params.id}"
            redirect(action: "list")
        } else {  
        	withFormat {
                html { return [userInstance: userInstance] }
                xml  { render ( [userInstance: userInstance]  as XML )}
                json { render ( [userInstance: userInstance]  as JSON )}
        	}
        }
    }
	
	def edit = {
		this.setLastReturnToAction(session, request, ["user/list","user/show"])
		if (request.post) {
			params.action='edit'
			redirect(params)
		}
		def userInstance = User.get(Long.parseLong(params.id.toString()))
		if(!userInstance) {
			withFormat{
				
				html {
					flash.message = "user.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "User not found with id ${params.id}"
					redirect(action: 'list')
				}
				xml {
					response.status = 404 //Not Found
					render "User not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "User not found with id ${params.id}"
				}
			}
		} else {
			withFormat {
				response.status = 200 // OK
				html { return [ userInstance : userInstance ]}
				xml  { render ([ userInstance : userInstance ] as XML)}
				json { render ([ userInstance : userInstance ] as JSON)}
			}
		}
	}
	
	def update = {
		deleteUnauthorizedParams(params)
		def userInstance = User.get( params.id )
		if(userInstance) {
			if(params.version) {
				def version = params.version.toLong()
				if(userInstance.version > version) {
					
					userInstance.errors.rejectValue("version", "user.optimistic.locking.failure", "Another user has updated this User while you were editing.")
					render(view:'edit',model:[userInstance:userInstance])
					return
				}
			}
			userInstance.properties = params
			if(!userInstance.hasErrors() && userInstance.save()) {
				withFormat {
					html {
						flash.message = "user.updated"
						flash.args = [params.id]
						flash.defaultMessage = "User ${params.id} updated"
						if (session.returnToReferer) {
							redirect(url:session.returnToReferer)
							session.removeAttribute('returnToReferer')
						} else {
							redirect(action:"list")
						}
					}
					xml {
						response.status = 200 // OK
						render userInstance as XML
					}
					json {
						response.status = 200 // OK
						render userInstance as XML
					}
				}
			}
			else {
				withFormat {
					html {
						render(view:'edit',model:[userInstance:userInstance])
					}
					xml {
						response.status = 500 //Internal Server Error
					}
					json {
						response.status = 500 //Internal Server Error
						// TODO : response.status = 500
						render "User could not be saved due to errors:"
					}
				}
			}
		}
		else {
			withFormat {
				html {
					flash.message = "user.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "User not found with id ${params.id}"
					redirect(action: 'edit', id: params.id)
				}
				xml {
					response.status = 404 //Not Found
					render "User not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "User not found with id ${params.id}"
				}
			}
		}
	}
	
    def delete = {
        def userInstance = User.get( params.id )
        if(userInstance) {
            try {
                userInstance.delete()
                withFormat {
                	html {
                        flash.message = "user.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "User ${params.id} deleted"
                        redirect(action: 'list')
                	}
                	xml {
                		response.status = 200 // OK
                	    render  "User ${params.id} deleted"	
                	}
                	json {
                		response.status = 200 // OK
                		render "User ${params.id} deleted"
                	}                		
                }
            } catch(org.springframework.dao.DataIntegrityViolationException e) {
            	withFormat{
            		html {
                        flash.message = "user.not.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "User ${params.id} could not be deleted"
                        redirect(action: 'show', id: params.id)
            		}
            		xml {
            			response.status = 500 //Internal Server Error
            			render "User ${params.id} could not be deleted"
            		}
            		json {
            			response.status = 500 //Internal Server Error
            	        render "User ${params.id} could not deleted due to errors:"
            		}
            	}
            }
        } else {
        	withFormat {
        		html {
                    flash.message = "user.not.found"
                    flash.args = [params.id]
                    flash.defaultMessage = "User not found with id ${params.id}"
                    redirect(action: 'list')
        		}
        		xml {
        			response.status = 404 //Not Found
      	            render "User not found with id ${params.id}"
        		}
        		json{
        			response.status = 404 //Not Found
      	            render "User not found with id ${params.id}"
        		}
        	}
        }
    }
	
    def saveAndRedirectToCreate = {
        def userInstance = new User(params)
        if (saveObject(userInstance)) {
            redirect(action: 'create')
        }
        else {
            render(view: 'create', model: [userInstance: userInstance])
        }
    }

    /**
     * Save the given Domain-Object.
     */
    private saveObject(def userInstance) {
        def flash = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getFlashScope()
        setDefaultsForUnauthorizedProperties (userInstance)
        if (!userInstance.hasErrors() && userInstance.save()) {
        	withFormat {
        		html {
                    flash.message = "user.created"
                    flash.args = [userInstance.id]
                    flash.defaultMessage = "User ${userInstance.id} created"
                    return true
        		}
        		xml {
       			    response.status = 201 // Created
 		            render userInstance as XML
        		}
        		json {
       			    esponse.status = 201 // Created
 		            render userInstance as XML
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
        def permissionField = User.getMetaClass().getMetaProperty("needsPermission")
        if (permissionField && java.lang.reflect.Modifier.isStatic(permissionField.getModifiers())) {
            def session = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getSession()
            def authorizationService = org.codehaus.groovy.grails.commons.ApplicationHolder.application.mainContext.getBean("authorizationService")

            User.needsPermission.each { key, closure ->
                if (closure && !authorizationService.hasPermission("User.${key}", "write", session)) {
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
