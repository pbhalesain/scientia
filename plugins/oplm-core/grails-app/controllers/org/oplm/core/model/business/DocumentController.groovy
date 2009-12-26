package org.oplm.core.model.business

import grails.converters.*

class DocumentController {
	
	// the delete, save and update actions only accept POST requests
	static allowedMethods = [delete:'POST', save:'POST', update:'POST']
	
	def index = {
        redirect(action: "list", params: params)
    }
    
    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 25,  100)
        def documentInstanceList = Document.list( params )
        def documentInstanceTotal = Document.count()
	    withFormat {
        	html { return [ documentInstanceList : documentInstanceList , documentInstanceTotal: documentInstanceTotal ] }
        	xml  { render ( [totalCount:documentInstanceTotal, documents : documentInstanceList ] as XML) }
        	json { render ( [totalCount:documentInstanceTotal, documents : documentInstanceList ] as JSON) }
        }
    }
	
	def create = {
		def documentInstance = new Document()
		documentInstance.properties = params
		withFormat {
			html {
				return ['documentInstance':documentInstance]
			}
			xml {
				render (['documentInstance':documentInstance] as XML)
			}
			json {
				render (['documentInstance':documentInstance] as JSON)
			}
		}
	}
	
	def save = {
		def documentInstance = new Document(params)
		if (saveObject(documentInstance)) {
			redirect(action: 'show', id: documentInstance.id)
		}
		else {
			render(view: 'create', model: [documentInstance: documentInstance])
		}
	}
	
    def show = {
        def documentInstance = Document.get( params.id )

        if(!documentInstance) {
            flash.message = "document.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Document not found with id ${params.id}"
            redirect(action: "list")
        } else {  
        	withFormat {
                html { return [documentInstance: documentInstance] }
                xml  { render ( [documentInstance: documentInstance]  as XML )}
                json { render ( [documentInstance: documentInstance]  as JSON )}
        	}
        }
    }
	
	def edit = {
		this.setLastReturnToAction(session, request, ["document/list","document/show"])
		if (request.post) {
			params.action='edit'
			redirect(params)
		}
		def documentInstance = Document.get(Long.parseLong(params.id.toString()))
		if(!documentInstance) {
			withFormat{
				
				html {
					flash.message = "document.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Document not found with id ${params.id}"
					redirect(action: 'list')
				}
				xml {
					response.status = 404 //Not Found
					render "Document not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Document not found with id ${params.id}"
				}
			}
		} else {
			withFormat {
				response.status = 200 // OK
				html { return [ documentInstance : documentInstance ]}
				xml  { render ([ documentInstance : documentInstance ] as XML)}
				json { render ([ documentInstance : documentInstance ] as JSON)}
			}
		}
	}
	
	def update = {
		deleteUnauthorizedParams(params)
		def documentInstance = Document.get( params.id )
		if(documentInstance) {
			if(params.version) {
				def version = params.version.toLong()
				if(documentInstance.version > version) {
					
					documentInstance.errors.rejectValue("version", "document.optimistic.locking.failure", "Another user has updated this Document while you were editing.")
					render(view:'edit',model:[documentInstance:documentInstance])
					return
				}
			}
			documentInstance.properties = params
			if(!documentInstance.hasErrors() && documentInstance.save()) {
				withFormat {
					html {
						flash.message = "document.updated"
						flash.args = [params.id]
						flash.defaultMessage = "Document ${params.id} updated"
						if (session.returnToReferer) {
							redirect(url:session.returnToReferer)
							session.removeAttribute('returnToReferer')
						} else {
							redirect(action:"list")
						}
					}
					xml {
						response.status = 200 // OK
						render documentInstance as XML
					}
					json {
						response.status = 200 // OK
						render documentInstance as XML
					}
				}
			}
			else {
				withFormat {
					html {
						render(view:'edit',model:[documentInstance:documentInstance])
					}
					xml {
						response.status = 500 //Internal Server Error
					}
					json {
						response.status = 500 //Internal Server Error
						// TODO : response.status = 500
						render "Document could not be saved due to errors:"
					}
				}
			}
		}
		else {
			withFormat {
				html {
					flash.message = "document.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Document not found with id ${params.id}"
					redirect(action: 'edit', id: params.id)
				}
				xml {
					response.status = 404 //Not Found
					render "Document not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Document not found with id ${params.id}"
				}
			}
		}
	}
	
    def delete = {
        def documentInstance = Document.get( params.id )
        if(documentInstance) {
            try {
                documentInstance.delete()
                withFormat {
                	html {
                        flash.message = "document.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Document ${params.id} deleted"
                        redirect(action: 'list')
                	}
                	xml {
                		response.status = 200 // OK
                	    render  "Document ${params.id} deleted"	
                	}
                	json {
                		response.status = 200 // OK
                		render "Document ${params.id} deleted"
                	}                		
                }
            } catch(org.springframework.dao.DataIntegrityViolationException e) {
            	withFormat{
            		html {
                        flash.message = "document.not.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Document ${params.id} could not be deleted"
                        redirect(action: 'show', id: params.id)
            		}
            		xml {
            			response.status = 500 //Internal Server Error
            			render "Document ${params.id} could not be deleted"
            		}
            		json {
            			response.status = 500 //Internal Server Error
            	        render "Document ${params.id} could not deleted due to errors:"
            		}
            	}
            }
        } else {
        	withFormat {
        		html {
                    flash.message = "document.not.found"
                    flash.args = [params.id]
                    flash.defaultMessage = "Document not found with id ${params.id}"
                    redirect(action: 'list')
        		}
        		xml {
        			response.status = 404 //Not Found
      	            render "Document not found with id ${params.id}"
        		}
        		json{
        			response.status = 404 //Not Found
      	            render "Document not found with id ${params.id}"
        		}
        	}
        }
    }
	
    def saveAndRedirectToCreate = {
        def documentInstance = new Document(params)
        if (saveObject(documentInstance)) {
            redirect(action: 'create')
        }
        else {
            render(view: 'create', model: [documentInstance: documentInstance])
        }
    }

    /**
     * Save the given Domain-Object.
     */
    private saveObject(def documentInstance) {
        def flash = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getFlashScope()
        setDefaultsForUnauthorizedProperties (documentInstance)
        if (!documentInstance.hasErrors() && documentInstance.save()) {
        	withFormat {
        		html {
                    flash.message = "document.created"
                    flash.args = [documentInstance.id]
                    flash.defaultMessage = "Document ${documentInstance.id} created"
                    return true
        		}
        		xml {
       			    response.status = 201 // Created
 		            render documentInstance as XML
        		}
        		json {
       			    esponse.status = 201 // Created
 		            render documentInstance as XML
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
        def permissionField = Document.getMetaClass().getMetaProperty("needsPermission")
        if (permissionField && java.lang.reflect.Modifier.isStatic(permissionField.getModifiers())) {
            def session = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getSession()
            def authorizationService = org.codehaus.groovy.grails.commons.ApplicationHolder.application.mainContext.getBean("authorizationService")

            Document.needsPermission.each { key, closure ->
                if (closure && !authorizationService.hasPermission("Document.${key}", "write", session)) {
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
