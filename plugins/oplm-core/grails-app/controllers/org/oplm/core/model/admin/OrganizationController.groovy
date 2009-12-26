package org.oplm.core.model.admin


import grails.converters.*

class OrganizationController {
	
	// the delete, save and update actions only accept POST requests
	static allowedMethods = [delete:'POST', save:'POST', update:'POST']
	
	def index = {
        redirect(action: "list", params: params)
    }
    
    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 25,  100)
        def organizationInstanceList = Organization.list( params )
        def organizationInstanceTotal = Organization.count()
	    withFormat {
        	html { return [ organizationInstanceList : organizationInstanceList , organizationInstanceTotal: organizationInstanceTotal ] }
        	xml  { render ( [totalCount:organizationInstanceTotal, organizations : organizationInstanceList ] as XML) }
        	json { render ( [totalCount:organizationInstanceTotal, organizations : organizationInstanceList ] as JSON) }
        }
    }
	
	def create = {
		def organizationInstance = new Organization()
		organizationInstance.properties = params
		withFormat {
			html {
				return ['organizationInstance':organizationInstance]
			}
			xml {
				render (['organizationInstance':organizationInstance] as XML)
			}
			json {
				render (['organizationInstance':organizationInstance] as JSON)
			}
		}
	}
	
	def save = {
		def organizationInstance = new Organization(params)
		if (saveObject(organizationInstance)) {
			redirect(action: 'show', id: organizationInstance.id)
		}
		else {
			render(view: 'create', model: [organizationInstance: organizationInstance])
		}
	}
	
    def show = {
        def organizationInstance = Organization.get( params.id )

        if(!organizationInstance) {
            flash.message = "organization.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Organization not found with id ${params.id}"
            redirect(action: "list")
        } else {  
        	withFormat {
                html { return [organizationInstance: organizationInstance] }
                xml  { render ( [organizationInstance: organizationInstance]  as XML )}
                json { render ( [organizationInstance: organizationInstance]  as JSON )}
        	}
        }
    }
	
	def edit = {
		this.setLastReturnToAction(session, request, ["organization/list","organization/show"])
		if (request.post) {
			params.action='edit'
			redirect(params)
		}
		def organizationInstance = Organization.get(Long.parseLong(params.id.toString()))
		if(!organizationInstance) {
			withFormat{
				
				html {
					flash.message = "organization.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Organization not found with id ${params.id}"
					redirect(action: 'list')
				}
				xml {
					response.status = 404 //Not Found
					render "Organization not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Organization not found with id ${params.id}"
				}
			}
		} else {
			withFormat {
				response.status = 200 // OK
				html { return [ organizationInstance : organizationInstance ]}
				xml  { render ([ organizationInstance : organizationInstance ] as XML)}
				json { render ([ organizationInstance : organizationInstance ] as JSON)}
			}
		}
	}
	
	def update = {
		deleteUnauthorizedParams(params)
		def organizationInstance = Organization.get( params.id )
		if(organizationInstance) {
			if(params.version) {
				def version = params.version.toLong()
				if(organizationInstance.version > version) {
					
					organizationInstance.errors.rejectValue("version", "organization.optimistic.locking.failure", "Another user has updated this Organization while you were editing.")
					render(view:'edit',model:[organizationInstance:organizationInstance])
					return
				}
			}
			organizationInstance.properties = params
			if(!organizationInstance.hasErrors() && organizationInstance.save()) {
				withFormat {
					html {
						flash.message = "organization.updated"
						flash.args = [params.id]
						flash.defaultMessage = "Organization ${params.id} updated"
						if (session.returnToReferer) {
							redirect(url:session.returnToReferer)
							session.removeAttribute('returnToReferer')
						} else {
							redirect(action:"list")
						}
					}
					xml {
						response.status = 200 // OK
						render organizationInstance as XML
					}
					json {
						response.status = 200 // OK
						render organizationInstance as XML
					}
				}
			}
			else {
				withFormat {
					html {
						render(view:'edit',model:[organizationInstance:organizationInstance])
					}
					xml {
						response.status = 500 //Internal Server Error
					}
					json {
						response.status = 500 //Internal Server Error
						// TODO : response.status = 500
						render "Organization could not be saved due to errors:"
					}
				}
			}
		}
		else {
			withFormat {
				html {
					flash.message = "organization.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Organization not found with id ${params.id}"
					redirect(action: 'edit', id: params.id)
				}
				xml {
					response.status = 404 //Not Found
					render "Organization not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Organization not found with id ${params.id}"
				}
			}
		}
	}
	
    def delete = {
        def organizationInstance = Organization.get( params.id )
        if(organizationInstance) {
            try {
                organizationInstance.delete()
                withFormat {
                	html {
                        flash.message = "organization.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Organization ${params.id} deleted"
                        redirect(action: 'list')
                	}
                	xml {
                		response.status = 200 // OK
                	    render  "Organization ${params.id} deleted"	
                	}
                	json {
                		response.status = 200 // OK
                		render "Organization ${params.id} deleted"
                	}                		
                }
            } catch(org.springframework.dao.DataIntegrityViolationException e) {
            	withFormat{
            		html {
                        flash.message = "organization.not.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Organization ${params.id} could not be deleted"
                        redirect(action: 'show', id: params.id)
            		}
            		xml {
            			response.status = 500 //Internal Server Error
            			render "Organization ${params.id} could not be deleted"
            		}
            		json {
            			response.status = 500 //Internal Server Error
            	        render "Organization ${params.id} could not deleted due to errors:"
            		}
            	}
            }
        } else {
        	withFormat {
        		html {
                    flash.message = "organization.not.found"
                    flash.args = [params.id]
                    flash.defaultMessage = "Organization not found with id ${params.id}"
                    redirect(action: 'list')
        		}
        		xml {
        			response.status = 404 //Not Found
      	            render "Organization not found with id ${params.id}"
        		}
        		json{
        			response.status = 404 //Not Found
      	            render "Organization not found with id ${params.id}"
        		}
        	}
        }
    }
	
    def saveAndRedirectToCreate = {
        def organizationInstance = new Organization(params)
        if (saveObject(organizationInstance)) {
            redirect(action: 'create')
        }
        else {
            render(view: 'create', model: [organizationInstance: organizationInstance])
        }
    }

    /**
     * Save the given Domain-Object.
     */
    private saveObject(def organizationInstance) {
        def flash = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getFlashScope()
        setDefaultsForUnauthorizedProperties (organizationInstance)
        if (!organizationInstance.hasErrors() && organizationInstance.save()) {
        	withFormat {
        		html {
                    flash.message = "organization.created"
                    flash.args = [organizationInstance.id]
                    flash.defaultMessage = "Organization ${organizationInstance.id} created"
                    return true
        		}
        		xml {
       			    response.status = 201 // Created
 		            render organizationInstance as XML
        		}
        		json {
       			    esponse.status = 201 // Created
 		            render organizationInstance as XML
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
        def permissionField = Organization.getMetaClass().getMetaProperty("needsPermission")
        if (permissionField && java.lang.reflect.Modifier.isStatic(permissionField.getModifiers())) {
            def session = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getSession()
            def authorizationService = org.codehaus.groovy.grails.commons.ApplicationHolder.application.mainContext.getBean("authorizationService")

            Organization.needsPermission.each { key, closure ->
                if (closure && !authorizationService.hasPermission("Organization.${key}", "write", session)) {
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
