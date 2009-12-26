package org.oplm.core.services.session

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.oplm.core.model.admin.User

class SessionStorageService {

    boolean transactional = false
	
    static scope = "singleton"
	
	def getUser(){
		getSession().user
	}

    def setUser(User user){
		getSession().user = user
	}
	private HttpSession getSession(){
		return RequestContextHolder.currentRequestAttributes().getSession()
	}
}
