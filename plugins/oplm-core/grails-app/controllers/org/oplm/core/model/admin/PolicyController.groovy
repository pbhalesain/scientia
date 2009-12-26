package org.oplm.core.model.admin


import grails.converters.*

class PolicyController {
	
	// the delete, save and update actions only accept POST requests
	static allowedMethods = [delete:'POST', save:'POST', update:'POST']
	
	def index = {
        redirect(action: "list", params: params)
    }
    
    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 25,  100)
        def policyInstanceList = Policy.list( params )
        def policyInstanceTotal = Policy.count()
	    withFormat {
        	html { return [ policyInstanceList : policyInstanceList , policyInstanceTotal: policyInstanceTotal ] }
        	xml  { render ( [totalCount:policyInstanceTotal, policys : policyInstanceList ] as XML) }
        	json { render ( [totalCount:policyInstanceTotal, policys : policyInstanceList ] as JSON) }
        }
    }
	
	def create = {
		def policyInstance = new Policy()
		policyInstance.properties = params
		withFormat {
			html {
				return ['policyInstance':policyInstance]
			}
			xml {
				render (['policyInstance':policyInstance] as XML)
			}
			json {
				render (['policyInstance':policyInstance] as JSON)
			}
		}
	}
	
	def save = {
		def policyInstance = new Policy(params)
		if (saveObject(policyInstance)) {
			redirect(action: 'show', id: policyInstance.id)
		}
		else {
			render(view: 'create', model: [policyInstance: policyInstance])
		}
	}
	
    def show = {
        def policyInstance = Policy.get( params.id )

        if(!policyInstance) {
            flash.message = "policy.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Policy not found with id ${params.id}"
            redirect(action: "list")
        } else {  
        	withFormat {
                html { return [policyInstance: policyInstance] }
                xml  { render ( [policyInstance: policyInstance]  as XML )}
                json { render ( [policyInstance: policyInstance]  as JSON )}
        	}
        }
    }
	
	def edit = {
		this.setLastReturnToAction(session, request, ["policy/list","policy/show"])
		if (request.post) {
			params.action='edit'
			redirect(params)
		}
		def policyInstance = Policy.get(Long.parseLong(params.id.toString()))
		if(!policyInstance) {
			withFormat{
				
				html {
					flash.message = "policy.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Policy not found with id ${params.id}"
					redirect(action: 'list')
				}
				xml {
					response.status = 404 //Not Found
					render "Policy not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Policy not found with id ${params.id}"
				}
			}
		} else {
			withFormat {
				response.status = 200 // OK
				html { return [ policyInstance : policyInstance ]}
				xml  { render ([ policyInstance : policyInstance ] as XML)}
				json { render ([ policyInstance : policyInstance ] as JSON)}
			}
		}
	}
	
	def update = {
		deleteUnauthorizedParams(params)
		def policyInstance = Policy.get( params.id )
		if(policyInstance) {
			if(params.version) {
				def version = params.version.toLong()
				if(policyInstance.version > version) {
					
					policyInstance.errors.rejectValue("version", "policy.optimistic.locking.failure", "Another user has updated this Policy while you were editing.")
					render(view:'edit',model:[policyInstance:policyInstance])
					return
				}
			}
			policyInstance.properties = params
			if(!policyInstance.hasErrors() && policyInstance.save()) {
				withFormat {
					html {
						flash.message = "policy.updated"
						flash.args = [params.id]
						flash.defaultMessage = "Policy ${params.id} updated"
						if (session.returnToReferer) {
							redirect(url:session.returnToReferer)
							session.removeAttribute('returnToReferer')
						} else {
							redirect(action:"list")
						}
					}
					xml {
						response.status = 200 // OK
						render policyInstance as XML
					}
					json {
						response.status = 200 // OK
						render policyInstance as XML
					}
				}
			}
			else {
				withFormat {
					html {
						render(view:'edit',model:[policyInstance:policyInstance])
					}
					xml {
						response.status = 500 //Internal Server Error
					}
					json {
						response.status = 500 //Internal Server Error
						// TODO : response.status = 500
						render "Policy could not be saved due to errors:"
					}
				}
			}
		}
		else {
			withFormat {
				html {
					flash.message = "policy.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Policy not found with id ${params.id}"
					redirect(action: 'edit', id: params.id)
				}
				xml {
					response.status = 404 //Not Found
					render "Policy not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Policy not found with id ${params.id}"
				}
			}
		}
	}
	
    def delete = {
        def policyInstance = Policy.get( params.id )
        if(policyInstance) {
            try {
                policyInstance.delete()
                withFormat {
                	html {
                        flash.message = "policy.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Policy ${params.id} deleted"
                        redirect(action: 'list')
                	}
                	xml {
                		response.status = 200 // OK
                	    render  "Policy ${params.id} deleted"	
                	}
                	json {
                		response.status = 200 // OK
                		render "Policy ${params.id} deleted"
                	}                		
                }
            } catch(org.springframework.dao.DataIntegrityViolationException e) {
            	withFormat{
            		html {
                        flash.message = "policy.not.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Policy ${params.id} could not be deleted"
                        redirect(action: 'show', id: params.id)
            		}
            		xml {
            			response.status = 500 //Internal Server Error
            			render "Policy ${params.id} could not be deleted"
            		}
            		json {
            			response.status = 500 //Internal Server Error
            	        render "Policy ${params.id} could not deleted due to errors:"
            		}
            	}
            }
        } else {
        	withFormat {
        		html {
                    flash.message = "policy.not.found"
                    flash.args = [params.id]
                    flash.defaultMessage = "Policy not found with id ${params.id}"
                    redirect(action: 'list')
        		}
        		xml {
        			response.status = 404 //Not Found
      	            render "Policy not found with id ${params.id}"
        		}
        		json{
        			response.status = 404 //Not Found
      	            render "Policy not found with id ${params.id}"
        		}
        	}
        }
    }
	
    def saveAndRedirectToCreate = {
        def policyInstance = new Policy(params)
        if (saveObject(policyInstance)) {
            redirect(action: 'create')
        }
        else {
            render(view: 'create', model: [policyInstance: policyInstance])
        }
    }

    /**
     * Save the given Domain-Object.
     */
    private saveObject(def policyInstance) {
        def flash = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getFlashScope()
        setDefaultsForUnauthorizedProperties (policyInstance)
        if (!policyInstance.hasErrors() && policyInstance.save()) {
        	withFormat {
        		html {
                    flash.message = "policy.created"
                    flash.args = [policyInstance.id]
                    flash.defaultMessage = "Policy ${policyInstance.id} created"
                    return true
        		}
        		xml {
       			    response.status = 201 // Created
 		            render policyInstance as XML
        		}
        		json {
       			    esponse.status = 201 // Created
 		            render policyInstance as XML
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
        def permissionField = Policy.getMetaClass().getMetaProperty("needsPermission")
        if (permissionField && java.lang.reflect.Modifier.isStatic(permissionField.getModifiers())) {
            def session = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getSession()
            def authorizationService = org.codehaus.groovy.grails.commons.ApplicationHolder.application.mainContext.getBean("authorizationService")

            Policy.needsPermission.each { key, closure ->
                if (closure && !authorizationService.hasPermission("Policy.${key}", "write", session)) {
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
