package org.oplm.core.service.common

class CrudService {
	
	def list(String domainType, params){
		def domainObjectList = clazz(domainType).list(params)
	}
	
	def Object get(String domainType, id) {
		clazz(domainType).get(id)
	}
	
	def List update(String domainType, Object entity)
	throws BindException {
		entity.merge(deepValidate:false, flush:true)
		if (entity.errors.hasErrors()) {
			throw new BindException(entity.errors)
		}
		sendUpdate(domainType);
		all(domainType);
	}
	
	def List remove(String domainType, Object entity) {
		entity.delete(flush:true);
		sendUpdate(domainType);
		all(domainType);
		
	}
	private def Class clazz(className) {
		return ApplicationHolder.application.getClassForName(className);
	}
	
	private def void sendUpdate(String domainType) {
		try {
			sendPubSubJMSMessage("tpc", all(domainType), [type:domainType]);
		} catch (Exception e) {
			log.error("Sending updates failed.", e);
		}
	}
	
}
