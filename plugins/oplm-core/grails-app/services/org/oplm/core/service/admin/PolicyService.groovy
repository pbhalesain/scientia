package org.oplm.core.service.admin

import org.oplm.core.model.admin.*

class PolicyService {
	
	
	/*
	 * 
	 * 
	 * class Behaviour{
   def sayHello = {
       println "say hello"

       def anotherClosure = {
         println "another closure"
       }
     anotherClosure()
   }
}
def classname = "Behaviour"
def beforeLock = new {$className}().sayHello
beforeLock.delegate = this

beforeLock()
	 */
    boolean transactional = true
	def groovyTemplateEngine
	
	def getBehaviour(){
		String behString = "";
	}
}
