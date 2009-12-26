package org.oplm.core.model.admin


import grails.converters.*

class RoleController {
	
	// the delete, save and update actions only accept POST requests
	static allowedMethods = [delete:'POST', save:'POST', update:'POST']
	
	def index = {
        redirect(action: "list", params: params)
    }
    
    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 25,  100)
        def roleInstanceList = Role.list( params )
        def roleInstanceTotal = Role.count()
	    withFormat {
        	html { return [ roleInstanceList : roleInstanceList , roleInstanceTotal: roleInstanceTotal ] }
        	xml  { render ( [totalCount:roleInstanceTotal, roles : roleInstanceList ] as XML) }
        	json { render ( [totalCount:roleInstanceTotal, roles : roleInstanceList ] as JSON) }
        }
    }
	
	def create = {
		def roleInstance = new Role()
		roleInstance.properties = params
		withFormat {
			html {
				return ['roleInstance':roleInstance]
			}
			xml {
				render (['roleInstance':roleInstance] as XML)
			}
			json {
				render (['roleInstance':roleInstance] as JSON)
			}
		}
	}
	
	def save = {
		def roleInstance = new Role(params)
		if (saveObject(roleInstance)) {
			redirect(action: 'show', id: roleInstance.id)
		}
		else {
			render(view: 'create', model: [roleInstance: roleInstance])
		}
	}
	
    def show = {
        def roleInstance = Role.get( params.id )

        if(!roleInstance) {
            flash.message = "role.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Role not found with id ${params.id}"
            redirect(action: "list")
        } else {  
        	withFormat {
                html { return [roleInstance: roleInstance] }
                xml  { render ( [roleInstance: roleInstance]  as XML )}
                json { render ( [roleInstance: roleInstance]  as JSON )}
        	}
        }
    }
	
	def edit = {
		this.setLastReturnToAction(session, request, ["role/list","role/show"])
		if (request.post) {
			params.action='edit'
			redirect(params)
		}
		def roleInstance = Role.get(Long.parseLong(params.id.toString()))
		if(!roleInstance) {
			withFormat{
				
				html {
					flash.message = "role.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Role not found with id ${params.id}"
					redirect(action: 'list')
				}
				xml {
					response.status = 404 //Not Found
					render "Role not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Role not found with id ${params.id}"
				}
			}
		} else {
			withFormat {
				response.status = 200 // OK
				html { return [ roleInstance : roleInstance ]}
				xml  { render ([ roleInstance : roleInstance ] as XML)}
				json { render ([ roleInstance : roleInstance ] as JSON)}
			}
		}
	}
	
	def update = {
		deleteUnauthorizedParams(params)
		def roleInstance = Role.get( params.id )
		if(roleInstance) {
			if(params.version) {
				def version = params.version.toLong()
				if(roleInstance.version > version) {
					
					roleInstance.errors.rejectValue("version", "role.optimistic.locking.failure", "Another user has updated this Role while you were editing.")
					render(view:'edit',model:[roleInstance:roleInstance])
					return
				}
			}
			roleInstance.properties = params
			if(!roleInstance.hasErrors() && roleInstance.save()) {
				withFormat {
					html {
						flash.message = "role.updated"
						flash.args = [params.id]
						flash.defaultMessage = "Role ${params.id} updated"
						if (session.returnToReferer) {
							redirect(url:session.returnToReferer)
							session.removeAttribute('returnToReferer')
						} else {
							redirect(action:"list")
						}
					}
					xml {
						response.status = 200 // OK
						render roleInstance as XML
					}
					json {
						response.status = 200 // OK
						render roleInstance as XML
					}
				}
			}
			else {
				withFormat {
					html {
						render(view:'edit',model:[roleInstance:roleInstance])
					}
					xml {
						response.status = 500 //Internal Server Error
					}
					json {
						response.status = 500 //Internal Server Error
						// TODO : response.status = 500
						render "Role could not be saved due to errors:"
					}
				}
			}
		}
		else {
			withFormat {
				html {
					flash.message = "role.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Role not found with id ${params.id}"
					redirect(action: 'edit', id: params.id)
				}
				xml {
					response.status = 404 //Not Found
					render "Role not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Role not found with id ${params.id}"
				}
			}
		}
	}
	
    def delete = {
        def roleInstance = Role.get( params.id )
        if(roleInstance) {
            try {
                roleInstance.delete()
                withFormat {
                	html {
                        flash.message = "role.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Role ${params.id} deleted"
                        redirect(action: 'list')
                	}
                	xml {
                		response.status = 200 // OK
                	    render  "Role ${params.id} deleted"	
                	}
                	json {
                		response.status = 200 // OK
                		render "Role ${params.id} deleted"
                	}                		
                }
            } catch(org.springframework.dao.DataIntegrityViolationException e) {
            	withFormat{
            		html {
                        flash.message = "role.not.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Role ${params.id} could not be deleted"
                        redirect(action: 'show', id: params.id)
            		}
            		xml {
            			response.status = 500 //Internal Server Error
            			render "Role ${params.id} could not be deleted"
            		}
            		json {
            			response.status = 500 //Internal Server Error
            	        render "Role ${params.id} could not deleted due to errors:"
            		}
            	}
            }
        } else {
        	withFormat {
        		html {
                    flash.message = "role.not.found"
                    flash.args = [params.id]
                    flash.defaultMessage = "Role not found with id ${params.id}"
                    redirect(action: 'list')
        		}
        		xml {
        			response.status = 404 //Not Found
      	            render "Role not found with id ${params.id}"
        		}
        		json{
        			response.status = 404 //Not Found
      	            render "Role not found with id ${params.id}"
        		}
        	}
        }
    }
	
    def saveAndRedirectToCreate = {
        def roleInstance = new Role(params)
        if (saveObject(roleInstance)) {
            redirect(action: 'create')
        }
        else {
            render(view: 'create', model: [roleInstance: roleInstance])
        }
    }

    /**
     * Save the given Domain-Object.
     */
    private saveObject(def roleInstance) {
        def flash = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getFlashScope()
        setDefaultsForUnauthorizedProperties (roleInstance)
        if (!roleInstance.hasErrors() && roleInstance.save()) {
        	withFormat {
        		html {
                    flash.message = "role.created"
                    flash.args = [roleInstance.id]
                    flash.defaultMessage = "Role ${roleInstance.id} created"
                    return true
        		}
        		xml {
       			    response.status = 201 // Created
 		            render roleInstance as XML
        		}
        		json {
       			    esponse.status = 201 // Created
 		            render roleInstance as XML
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
        def permissionField = Role.getMetaClass().getMetaProperty("needsPermission")
        if (permissionField && java.lang.reflect.Modifier.isStatic(permissionField.getModifiers())) {
            def session = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getSession()
            def authorizationService = org.codehaus.groovy.grails.commons.ApplicationHolder.application.mainContext.getBean("authorizationService")

            Role.needsPermission.each { key, closure ->
                if (closure && !authorizationService.hasPermission("Role.${key}", "write", session)) {
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
