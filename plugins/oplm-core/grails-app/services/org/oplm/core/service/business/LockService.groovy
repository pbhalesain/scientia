package org.oplm.core.service.common


import java.util.Collection;
/*
class Behaviour{
	def sayHello = {
		println "say hello"
		
		def anotherClosure = {
			println "another closure"
		}
		anotherClosure()
	}
}

def lock() {
	this.metaClass.behaviour = new Behaviour()
	def beforeLock = behaviour.sayHello
	beforeLock.delegate = this
	beforeLock()
}

lock()

*/

class LockService {

    boolean transactional = true
	

	def lock(Object entity, LockType lockType){
		
	}
	
	def lock(Object entity, LockType lockType, int timeToExpire){
		
	}
	
	def lock(Object entity, LockType lockType, int timeToExpire, boolean lockChildren){
		
	}
	
	def lock(Collection<Object> entities, LockType lockType, int timeToExpire){
		
	}
	
	def unlock(Object entity){
		
	}
	
	def unlock(Object entity, boolean lockChildren){
		
	}
	
	def unlock(Collection<Object> entities){
		
	}
	
	def LockStatus getLockStatus(Object nodeRef){
		return null;
	}
	
	def LockStatus getLockStatus(Object nodeRef, String userName){
		return null
	}
	
	def LockType getLockType(Object object){
		
	}
	
	def checkForLock(){
		
	}
	
	/**
	 * Get all the locks for current user
	 * @generated
	 */
	
	def getLocks(){
		
	}
	
	def List<Object> getLocks(LockType lockType){
		
	}
}
