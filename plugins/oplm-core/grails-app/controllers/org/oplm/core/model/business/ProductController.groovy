package org.oplm.core.model.business


import grails.converters.*

class ProductController {
	
	// the delete, save and update actions only accept POST requests
	static allowedMethods = [delete:'POST', save:'POST', update:'POST']

	//Injecting CRUD service
	def productService
	                         
	def index = {
        redirect(action: "list", params: params)
    }
    
    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 25,  100)
        def productInstanceList = productService.list( params )
        def productInstanceTotal = productInstanceList.count();
	    withFormat {
        	html { return [ productInstanceList : productInstanceList , productInstanceTotal: productInstanceTotal ] }
        	xml  { render ( [totalCount:productInstanceTotal, products : productInstanceList ] as XML) }
        	json { render ( [totalCount:productInstanceTotal, products : productInstanceList ] as JSON) }
        }
    }
	
	def create = {
		def productInstance = new Product()
		productInstance.properties = params
		withFormat {
			html {
				return ['productInstance':productInstance]
			}
			xml {
				render (['productInstance':productInstance] as XML)
			}
			json {
				render (['productInstance':productInstance] as JSON)
			}
		}
	}
	
	def save = {
		def productInstance = new Product(params)
		if (productService.saveObject(productInstance)) {
			redirect(action: 'show', id: productInstance.id)
		}
		else {
			render(view: 'create', model: [productInstance: productInstance])
		}
	}
	
    def show = {
        def productInstance = productService.get("Product", params.id )

        if(!productInstance) {
            flash.message = "product.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Product not found with id ${params.id}"
            redirect(action: "list")
        } else {  
        	withFormat {
                html { return [productInstance: productInstance] }
                xml  { render ( [productInstance: productInstance]  as XML )}
                json { render ( [productInstance: productInstance]  as JSON )}
        	}
        }
    }
	
	def edit = {
		this.setLastReturnToAction(session, request, ["product/list","product/show"])
		if (request.post) {
			params.action='edit'
			redirect(params)
		}
		def productInstance = productService.get("Product",Long.parseLong(params.id.toString()))
		if(!productInstance) {
			withFormat{
				
				html {
					flash.message = "product.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Product not found with id ${params.id}"
					redirect(action: 'list')
				}
				xml {
					response.status = 404 //Not Found
					render "Product not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Product not found with id ${params.id}"
				}
			}
		} else {
			withFormat {
				response.status = 200 // OK
				html { return [ productInstance : productInstance ]}
				xml  { render ([ productInstance : productInstance ] as XML)}
				json { render ([ productInstance : productInstance ] as JSON)}
			}
		}
	}
	
	def update = {
		deleteUnauthorizedParams(params)
		def productInstance = productService.get( params.id )
		if(productInstance) {
			if(params.version) {
				def version = params.version.toLong()
				if(productInstance.version > version) {
					
					productInstance.errors.rejectValue("version", "product.optimistic.locking.failure", "Another user has updated this Product while you were editing.")
					render(view:'edit',model:[productInstance:productInstance])
					return
				}
			}
			productInstance.properties = params
			if(!productInstance.hasErrors() && productInstance.save()) {
				withFormat {
					html {
						flash.message = "product.updated"
						flash.args = [params.id]
						flash.defaultMessage = "Product ${params.id} updated"
						if (session.returnToReferer) {
							redirect(url:session.returnToReferer)
							session.removeAttribute('returnToReferer')
						} else {
							redirect(action:"list")
						}
					}
					xml {
						response.status = 200 // OK
						render productInstance as XML
					}
					json {
						response.status = 200 // OK
						render productInstance as XML
					}
				}
			}
			else {
				withFormat {
					html {
						render(view:'edit',model:[productInstance:productInstance])
					}
					xml {
						response.status = 500 //Internal Server Error
					}
					json {
						response.status = 500 //Internal Server Error
						// TODO : response.status = 500
						render "Product could not be saved due to errors:"
					}
				}
			}
		}
		else {
			withFormat {
				html {
					flash.message = "product.not.found"
					flash.args = [params.id]
					flash.defaultMessage = "Product not found with id ${params.id}"
					redirect(action: 'edit', id: params.id)
				}
				xml {
					response.status = 404 //Not Found
					render "Product not found with id ${params.id}"
				}
				json {
					response.status = 404 //Not Found
					render "Product not found with id ${params.id}"
				}
			}
		}
	}
	
    def delete = {
        def productInstance = productService.get( params.id )
        if(productInstance) {
            try {
                productInstance.delete()
                withFormat {
                	html {
                        flash.message = "product.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Product ${params.id} deleted"
                        redirect(action: 'list')
                	}
                	xml {
                		response.status = 200 // OK
                	    render  "Product ${params.id} deleted"	
                	}
                	json {
                		response.status = 200 // OK
                		render "Product ${params.id} deleted"
                	}                		
                }
            } catch(org.springframework.dao.DataIntegrityViolationException e) {
            	withFormat{
            		html {
                        flash.message = "product.not.deleted"
                        flash.args = [params.id]
                        flash.defaultMessage = "Product ${params.id} could not be deleted"
                        redirect(action: 'show', id: params.id)
            		}
            		xml {
            			response.status = 500 //Internal Server Error
            			render "Product ${params.id} could not be deleted"
            		}
            		json {
            			response.status = 500 //Internal Server Error
            	        render "Product ${params.id} could not deleted due to errors:"
            		}
            	}
            }
        } else {
        	withFormat {
        		html {
                    flash.message = "product.not.found"
                    flash.args = [params.id]
                    flash.defaultMessage = "Product not found with id ${params.id}"
                    redirect(action: 'list')
        		}
        		xml {
        			response.status = 404 //Not Found
      	            render "Product not found with id ${params.id}"
        		}
        		json{
        			response.status = 404 //Not Found
      	            render "Product not found with id ${params.id}"
        		}
        	}
        }
    }
	
    def saveAndRedirectToCreate = {
        def productInstance = new Product(params)
        if (productService.saveObject(productInstance)) {
            redirect(action: 'create')
        }
        else {
            render(view: 'create', model: [productInstance: productInstance])
        }
    }

    /**
     * Save the given Domain-Object.
     */
    private saveObject(def productInstance) {
        def flash = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getFlashScope()
        setDefaultsForUnauthorizedProperties (productInstance)
        if (!productInstance.hasErrors() && productInstance.save()) {
        	withFormat {
        		html {
                    flash.message = "product.created"
                    flash.args = [productInstance.id]
                    flash.defaultMessage = "Product ${productInstance.id} created"
                    return true
        		}
        		xml {
       			    response.status = 201 // Created
 		            render productInstance as XML
        		}
        		json {
       			    esponse.status = 201 // Created
 		            render productInstance as XML
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
        def permissionField = Product.getMetaClass().getMetaProperty("needsPermission")
        if (permissionField && java.lang.reflect.Modifier.isStatic(permissionField.getModifiers())) {
            def session = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getSession()
            def authorizationService = org.codehaus.groovy.grails.commons.ApplicationHolder.application.mainContext.getBean("authorizationService")

            Product.needsPermission.each { key, closure ->
                if (closure && !authorizationService.hasPermission("Product.${key}", "write", session)) {
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
