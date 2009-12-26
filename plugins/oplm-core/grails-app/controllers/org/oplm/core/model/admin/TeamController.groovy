package org.oplm.core.model.admin


import grails.converters.*

class TeamController {
	
	// the delete, save and update actions only accept POST requests
	static allowedMethods = [delete:'POST', save:'POST', update:'POST']
	
	def index = {
        redirect(action: "list", params: params)
    }
    
    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 25,  100)
        def teamInstanceList = Team.list( params )
        def teamInstanceTotal = Team.count()
	    withFormat {
        	html { return [ teamInstanceList : teamInstanceList , teamInstanceTotal: teamInstanceTotal ] }
        	xml  { render ( [totalCount:teamInstanceTotal, teams : teamInstanceList ] as XML) }
        	json { render ( [totalCount:teamInstanceTotal, teams : teamInstanceList ] as JSON) }
        }
    }
	
	def create = {
		def teamInstance = new Team()
		teamInstance.properties = params
		withFormat {
			html {
				return ['teamInstance':teamInstance]
			}
			xml {
				render (['teamInstance':teamInstance] as XML)
			}
			json {
				render (['teamInstance':teamInstance] as JSON)
			}
		}
	}
	
	def save = {
		def teamInstance = new Team(params)
		if (saveObject(teamInstance)) {
			redirect(action: 'show', id: teamInstance.id)
		}
		else {
			render(view: 'create', model: [teamInstance: teamInstance])
		}
	}
	
    def show = {
        def teamInstance = Team.get( params.id )

        if(!teamInstance) {
            flash.message = "team.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Team not found with id ${params.id}"
            redirect(action: "list")
        } else {  
        	withFormat {
                html { return [teamInstance: teamInstance] }
                xml  { render ( [teamInstance: teamInstance]  as XML )}
                json { render ( [teamInstance: teamInstance]  as JSON )}
        	}
        }
    }
	
	def edit = {
		this.setLastReturnToAction(session, request, ["team/list","team/show"])
		if (request.post) {
			params.action='edit'
			redirect(params)
		}
		def teamInstance = Team.get(Long.parseLong(params.id.toString()))
		if(!teamInstance) {
			withFormat{
				
				html {
					flash.message = "team.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Team not found with id ${params.id}"
					redirect(action: 'list')
				}
				xml {
					response.status = 404 //Not Found
					render "Team not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Team not found with id ${params.id}"
				}
			}
		} else {
			withFormat {
				response.status = 200 // OK
				html { return [ teamInstance : teamInstance ]}
				xml  { render ([ teamInstance : teamInstance ] as XML)}
				json { render ([ teamInstance : teamInstance ] as JSON)}
			}
		}
	}
	
	def update = {
		deleteUnauthorizedParams(params)
		def teamInstance = Team.get( params.id )
		if(teamInstance) {
			if(params.version) {
				def version = params.version.toLong()
				if(teamInstance.version > version) {
					
					teamInstance.errors.rejectValue("version", "team.optimistic.locking.failure", "Another user has updated this Team while you were editing.")
					render(view:'edit',model:[teamInstance:teamInstance])
					return
				}
			}
			teamInstance.properties = params
			if(!teamInstance.hasErrors() && teamInstance.save()) {
				withFormat {
					html {
						flash.message = "team.updated"
						flash.args = [params.id]
						flash.defaultMessage = "Team ${params.id} updated"
						if (session.returnToReferer) {
							redirect(url:session.returnToReferer)
							session.removeAttribute('returnToReferer')
						} else {
							redirect(action:"list")
						}
					}
					xml {
						response.status = 200 // OK
						render teamInstance as XML
					}
					json {
						response.status = 200 // OK
						render teamInstance as XML
					}
				}
			}
			else {
				withFormat {
					html {
						render(view:'edit',model:[teamInstance:teamInstance])
					}
					xml {
						response.status = 500 //Internal Server Error
					}
					json {
						response.status = 500 //Internal Server Error
						// TODO : response.status = 500
						render "Team could not be saved due to errors:"
					}
				}
			}
		}
		else {
			withFormat {
				html {
					flash.message = "team.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Team not found with id ${params.id}"
					redirect(action: 'edit', id: params.id)
				}
				xml {
					response.status = 404 //Not Found
					render "Team not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Team not found with id ${params.id}"
				}
			}
		}
	}
	
    def delete = {
        def teamInstance = Team.get( params.id )
        if(teamInstance) {
            try {
                teamInstance.delete()
                withFormat {
                	html {
                        flash.message = "team.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Team ${params.id} deleted"
                        redirect(action: 'list')
                	}
                	xml {
                		response.status = 200 // OK
                	    render  "Team ${params.id} deleted"	
                	}
                	json {
                		response.status = 200 // OK
                		render "Team ${params.id} deleted"
                	}                		
                }
            } catch(org.springframework.dao.DataIntegrityViolationException e) {
            	withFormat{
            		html {
                        flash.message = "team.not.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Team ${params.id} could not be deleted"
                        redirect(action: 'show', id: params.id)
            		}
            		xml {
            			response.status = 500 //Internal Server Error
            			render "Team ${params.id} could not be deleted"
            		}
            		json {
            			response.status = 500 //Internal Server Error
            	        render "Team ${params.id} could not deleted due to errors:"
            		}
            	}
            }
        } else {
        	withFormat {
        		html {
                    flash.message = "team.not.found"
                    flash.args = [params.id]
                    flash.defaultMessage = "Team not found with id ${params.id}"
                    redirect(action: 'list')
        		}
        		xml {
        			response.status = 404 //Not Found
      	            render "Team not found with id ${params.id}"
        		}
        		json{
        			response.status = 404 //Not Found
      	            render "Team not found with id ${params.id}"
        		}
        	}
        }
    }
	
    def saveAndRedirectToCreate = {
        def teamInstance = new Team(params)
        if (saveObject(teamInstance)) {
            redirect(action: 'create')
        }
        else {
            render(view: 'create', model: [teamInstance: teamInstance])
        }
    }

    /**
     * Save the given Domain-Object.
     */
    private saveObject(def teamInstance) {
        def flash = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getFlashScope()
        setDefaultsForUnauthorizedProperties (teamInstance)
        if (!teamInstance.hasErrors() && teamInstance.save()) {
        	withFormat {
        		html {
                    flash.message = "team.created"
                    flash.args = [teamInstance.id]
                    flash.defaultMessage = "Team ${teamInstance.id} created"
                    return true
        		}
        		xml {
       			    response.status = 201 // Created
 		            render teamInstance as XML
        		}
        		json {
       			    esponse.status = 201 // Created
 		            render teamInstance as XML
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
        def permissionField = Team.getMetaClass().getMetaProperty("needsPermission")
        if (permissionField && java.lang.reflect.Modifier.isStatic(permissionField.getModifiers())) {
            def session = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getSession()
            def authorizationService = org.codehaus.groovy.grails.commons.ApplicationHolder.application.mainContext.getBean("authorizationService")

            Team.needsPermission.each { key, closure ->
                if (closure && !authorizationService.hasPermission("Team.${key}", "write", session)) {
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
