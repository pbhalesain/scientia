<%=packageName ? "package ${packageName}\n\n" : ''%>
import grails.converters.*

class ${className}Controller {
	
	// the delete, save and update actions only accept POST requests
	static allowedMethods = [delete:'POST', save:'POST', update:'POST']
	
	def index = {
        redirect(action: "list", params: params)
    }
    
    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 25,  100)
        def ${propertyName}List = ${className}.list( params )
        def ${propertyName}Total = ${className}.count()
	    withFormat {
        	html { return [ ${propertyName}List : ${propertyName}List , ${propertyName}Total: ${propertyName}Total ] }
        	xml  { render ( [totalCount:${propertyName}Total, ${className.toLowerCase()}s : ${propertyName}List ] as XML) }
        	json { render ( [totalCount:${propertyName}Total, ${className.toLowerCase()}s : ${propertyName}List ] as JSON) }
        }
    }
	
	def create = {
		def ${propertyName} = new ${className}()
		${propertyName}.properties = params
		withFormat {
			html {
				return ['${propertyName}':${propertyName}]
			}
			xml {
				render (['${propertyName}':${propertyName}] as XML)
			}
			json {
				render (['${propertyName}':${propertyName}] as JSON)
			}
		}
	}
	
	def save = {
		def ${propertyName} = new ${className}(params)
		if (saveObject(${propertyName})) {
			redirect(action: 'show', id: ${propertyName}.id)
		}
		else {
			render(view: 'create', model: [${propertyName}: ${propertyName}])
		}
	}
	
    def show = {
        def ${propertyName} = ${className}.get( params.id )

        if(!${propertyName}) {
            flash.message = "${domainClass.propertyName}.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "${className} not found with id \${params.id}"
            redirect(action: "list")
        } else {  
        	withFormat {
                html { return [${propertyName}: ${propertyName}] }
                xml  { render ( [${propertyName}: ${propertyName}]  as XML )}
                json { render ( [${propertyName}: ${propertyName}]  as JSON )}
        	}
        }
    }
	
	def edit = {
		this.setLastReturnToAction(session, request, ["${domainClass.propertyName}/list","${domainClass.propertyName}/show"])
		if (request.post) {
			params.action='edit'
			redirect(params)
		}
		def ${propertyName} = ${className}.get(Long.parseLong(params.id.toString()))
		if(!${propertyName}) {
			withFormat{
				
				html {
					flash.message = "${domainClass.propertyName}.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "${className} not found with id \${params.id}"
					redirect(action: 'list')
				}
				xml {
					response.status = 404 //Not Found
					render "${className} not found with id \${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "${className} not found with id \${params.id}"
				}
			}
		} else {
			withFormat {
				response.status = 200 // OK
				html { return [ ${propertyName} : ${propertyName} ]}
				xml  { render ([ ${propertyName} : ${propertyName} ] as XML)}
				json { render ([ ${propertyName} : ${propertyName} ] as JSON)}
			}
		}
	}
	
	def update = {
		deleteUnauthorizedParams(params)
		def ${propertyName} = ${className}.get( params.id )
		if(${propertyName}) {
			if(params.version) {
				def version = params.version.toLong()
				if(${propertyName}.version > version) {
					<%def lowerCaseName = grails.util.GrailsNameUtils.getPropertyName(className)%>
					${propertyName}.errors.rejectValue("version", "${lowerCaseName}.optimistic.locking.failure", "Another user has updated this ${className} while you were editing.")
					render(view:'edit',model:[${propertyName}:${propertyName}])
					return
				}
			}
			${propertyName}.properties = params
			if(!${propertyName}.hasErrors() && ${propertyName}.save()) {
				withFormat {
					html {
						flash.message = "${domainClass.propertyName}.updated"
						flash.args = [params.id]
						flash.defaultMessage = "${className} \${params.id} updated"
						if (session.returnToReferer) {
							redirect(url:session.returnToReferer)
							session.removeAttribute('returnToReferer')
						} else {
							redirect(action:"list")
						}
					}
					xml {
						response.status = 200 // OK
						render ${propertyName} as XML
					}
					json {
						response.status = 200 // OK
						render ${propertyName} as XML
					}
				}
			}
			else {
				withFormat {
					html {
						render(view:'edit',model:[${propertyName}:${propertyName}])
					}
					xml {
						response.status = 500 //Internal Server Error
					}
					json {
						response.status = 500 //Internal Server Error
						// TODO : response.status = 500
						render "${className} could not be saved due to errors:"
					}
				}
			}
		}
		else {
			withFormat {
				html {
					flash.message = "${domainClass.propertyName}.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "${className} not found with id \${params.id}"
					redirect(action: 'edit', id: params.id)
				}
				xml {
					response.status = 404 //Not Found
					render "${className} not found with id \${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "${className} not found with id \${params.id}"
				}
			}
		}
	}
	
    def delete = {
        def ${propertyName} = ${className}.get( params.id )
        if(${propertyName}) {
            try {
                ${propertyName}.delete()
                withFormat {
                	html {
                        flash.message = "${domainClass.propertyName}.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "${className} \${params.id} deleted"
                        redirect(action: 'list')
                	}
                	xml {
                		response.status = 200 // OK
                	    render  "${className} \${params.id} deleted"	
                	}
                	json {
                		response.status = 200 // OK
                		render "${className} \${params.id} deleted"
                	}                		
                }
            } catch(org.springframework.dao.DataIntegrityViolationException e) {
            	withFormat{
            		html {
                        flash.message = "${domainClass.propertyName}.not.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "${className} \${params.id} could not be deleted"
                        redirect(action: 'show', id: params.id)
            		}
            		xml {
            			response.status = 500 //Internal Server Error
            			render "${className} \${params.id} could not be deleted"
            		}
            		json {
            			response.status = 500 //Internal Server Error
            	        render "${className} \${params.id} could not deleted due to errors:"
            		}
            	}
            }
        } else {
        	withFormat {
        		html {
                    flash.message = "${domainClass.propertyName}.not.found"
                    flash.args = [params.id]
                    flash.defaultMessage = "${className} not found with id \${params.id}"
                    redirect(action: 'list')
        		}
        		xml {
        			response.status = 404 //Not Found
      	            render "${className} not found with id \${params.id}"
        		}
        		json{
        			response.status = 404 //Not Found
      	            render "${className} not found with id \${params.id}"
        		}
        	}
        }
    }
	
    def saveAndRedirectToCreate = {
        def ${propertyName} = new ${className}(params)
        if (saveObject(${propertyName})) {
            redirect(action: 'create')
        }
        else {
            render(view: 'create', model: [${propertyName}: ${propertyName}])
        }
    }

    /**
     * Save the given Domain-Object.
     */
    private saveObject(def ${propertyName}) {
        def flash = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getFlashScope()
        setDefaultsForUnauthorizedProperties (${propertyName})
        if (!${propertyName}.hasErrors() && ${propertyName}.save()) {
        	withFormat {
        		html {
                    flash.message = "${domainClass.propertyName}.created"
                    flash.args = [${propertyName}.id]
                    flash.defaultMessage = "${className} \${${propertyName}.id} created"
                    return true
        		}
        		xml {
       			    response.status = 201 // Created
 		            render ${propertyName} as XML
        		}
        		json {
       			    esponse.status = 201 // Created
 		            render ${propertyName} as XML
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
            object."\${key}" = defaultValue
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
        def permissionField = ${className}.getMetaClass().getMetaProperty("needsPermission")
        if (permissionField && java.lang.reflect.Modifier.isStatic(permissionField.getModifiers())) {
            def session = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getSession()
            def authorizationService = org.codehaus.groovy.grails.commons.ApplicationHolder.application.mainContext.getBean("authorizationService")

            ${className}.needsPermission.each { key, closure ->
                if (closure && !authorizationService.hasPermission("${className}.\${key}", "write", session)) {
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
