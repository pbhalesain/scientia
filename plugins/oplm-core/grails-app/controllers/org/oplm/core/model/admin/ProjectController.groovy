package org.oplm.core.model.admin


import grails.converters.*

class ProjectController {
	
	// the delete, save and update actions only accept POST requests
	static allowedMethods = [delete:'POST', save:'POST', update:'POST']
	
	def index = {
        redirect(action: "list", params: params)
    }
    
    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 25,  100)
        def projectInstanceList = Project.list( params )
        def projectInstanceTotal = Project.count()
	    withFormat {
        	html { return [ projectInstanceList : projectInstanceList , projectInstanceTotal: projectInstanceTotal ] }
        	xml  { render ( [totalCount:projectInstanceTotal, projects : projectInstanceList ] as XML) }
        	json { render ( [totalCount:projectInstanceTotal, projects : projectInstanceList ] as JSON) }
        }
    }
	
	def create = {
		def projectInstance = new Project()
		projectInstance.properties = params
		withFormat {
			html {
				return ['projectInstance':projectInstance]
			}
			xml {
				render (['projectInstance':projectInstance] as XML)
			}
			json {
				render (['projectInstance':projectInstance] as JSON)
			}
		}
	}
	
	def save = {
		def projectInstance = new Project(params)
		if (saveObject(projectInstance)) {
			redirect(action: 'show', id: projectInstance.id)
		}
		else {
			render(view: 'create', model: [projectInstance: projectInstance])
		}
	}
	
    def show = {
        def projectInstance = Project.get( params.id )

        if(!projectInstance) {
            flash.message = "project.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Project not found with id ${params.id}"
            redirect(action: "list")
        } else {  
        	withFormat {
                html { return [projectInstance: projectInstance] }
                xml  { render ( [projectInstance: projectInstance]  as XML )}
                json { render ( [projectInstance: projectInstance]  as JSON )}
        	}
        }
    }
	
	def edit = {
		this.setLastReturnToAction(session, request, ["project/list","project/show"])
		if (request.post) {
			params.action='edit'
			redirect(params)
		}
		def projectInstance = Project.get(Long.parseLong(params.id.toString()))
		if(!projectInstance) {
			withFormat{
				
				html {
					flash.message = "project.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Project not found with id ${params.id}"
					redirect(action: 'list')
				}
				xml {
					response.status = 404 //Not Found
					render "Project not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Project not found with id ${params.id}"
				}
			}
		} else {
			withFormat {
				response.status = 200 // OK
				html { return [ projectInstance : projectInstance ]}
				xml  { render ([ projectInstance : projectInstance ] as XML)}
				json { render ([ projectInstance : projectInstance ] as JSON)}
			}
		}
	}
	
	def update = {
		deleteUnauthorizedParams(params)
		def projectInstance = Project.get( params.id )
		if(projectInstance) {
			if(params.version) {
				def version = params.version.toLong()
				if(projectInstance.version > version) {
					
					projectInstance.errors.rejectValue("version", "project.optimistic.locking.failure", "Another user has updated this Project while you were editing.")
					render(view:'edit',model:[projectInstance:projectInstance])
					return
				}
			}
			projectInstance.properties = params
			if(!projectInstance.hasErrors() && projectInstance.save()) {
				withFormat {
					html {
						flash.message = "project.updated"
						flash.args = [params.id]
						flash.defaultMessage = "Project ${params.id} updated"
						if (session.returnToReferer) {
							redirect(url:session.returnToReferer)
							session.removeAttribute('returnToReferer')
						} else {
							redirect(action:"list")
						}
					}
					xml {
						response.status = 200 // OK
						render projectInstance as XML
					}
					json {
						response.status = 200 // OK
						render projectInstance as XML
					}
				}
			}
			else {
				withFormat {
					html {
						render(view:'edit',model:[projectInstance:projectInstance])
					}
					xml {
						response.status = 500 //Internal Server Error
					}
					json {
						response.status = 500 //Internal Server Error
						// TODO : response.status = 500
						render "Project could not be saved due to errors:"
					}
				}
			}
		}
		else {
			withFormat {
				html {
					flash.message = "project.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Project not found with id ${params.id}"
					redirect(action: 'edit', id: params.id)
				}
				xml {
					response.status = 404 //Not Found
					render "Project not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Project not found with id ${params.id}"
				}
			}
		}
	}
	
    def delete = {
        def projectInstance = Project.get( params.id )
        if(projectInstance) {
            try {
                projectInstance.delete()
                withFormat {
                	html {
                        flash.message = "project.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Project ${params.id} deleted"
                        redirect(action: 'list')
                	}
                	xml {
                		response.status = 200 // OK
                	    render  "Project ${params.id} deleted"	
                	}
                	json {
                		response.status = 200 // OK
                		render "Project ${params.id} deleted"
                	}                		
                }
            } catch(org.springframework.dao.DataIntegrityViolationException e) {
            	withFormat{
            		html {
                        flash.message = "project.not.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Project ${params.id} could not be deleted"
                        redirect(action: 'show', id: params.id)
            		}
            		xml {
            			response.status = 500 //Internal Server Error
            			render "Project ${params.id} could not be deleted"
            		}
            		json {
            			response.status = 500 //Internal Server Error
            	        render "Project ${params.id} could not deleted due to errors:"
            		}
            	}
            }
        } else {
        	withFormat {
        		html {
                    flash.message = "project.not.found"
                    flash.args = [params.id]
                    flash.defaultMessage = "Project not found with id ${params.id}"
                    redirect(action: 'list')
        		}
        		xml {
        			response.status = 404 //Not Found
      	            render "Project not found with id ${params.id}"
        		}
        		json{
        			response.status = 404 //Not Found
      	            render "Project not found with id ${params.id}"
        		}
        	}
        }
    }
	
    def saveAndRedirectToCreate = {
        def projectInstance = new Project(params)
        if (saveObject(projectInstance)) {
            redirect(action: 'create')
        }
        else {
            render(view: 'create', model: [projectInstance: projectInstance])
        }
    }

    /**
     * Save the given Domain-Object.
     */
    private saveObject(def projectInstance) {
        def flash = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getFlashScope()
        setDefaultsForUnauthorizedProperties (projectInstance)
        if (!projectInstance.hasErrors() && projectInstance.save()) {
        	withFormat {
        		html {
                    flash.message = "project.created"
                    flash.args = [projectInstance.id]
                    flash.defaultMessage = "Project ${projectInstance.id} created"
                    return true
        		}
        		xml {
       			    response.status = 201 // Created
 		            render projectInstance as XML
        		}
        		json {
       			    esponse.status = 201 // Created
 		            render projectInstance as XML
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
        def permissionField = Project.getMetaClass().getMetaProperty("needsPermission")
        if (permissionField && java.lang.reflect.Modifier.isStatic(permissionField.getModifiers())) {
            def session = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getSession()
            def authorizationService = org.codehaus.groovy.grails.commons.ApplicationHolder.application.mainContext.getBean("authorizationService")

            Project.needsPermission.each { key, closure ->
                if (closure && !authorizationService.hasPermission("Project.${key}", "write", session)) {
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
