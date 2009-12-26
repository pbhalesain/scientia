package org.oplm.core.service.business

import org.oplm.core.model.business.Product;

class ProductService {
	
    boolean transactional = true

    def save( params) {
		def productInstance = new Product(params)
    }
}
