package com.objectgen.build

import com.objectgen.core.*
import com.objectgen.codegen.*
import com.objectgen.codegen.hibernate.*

/**
 * This Groovy script will modify all classes with stereotype Persistent.
 */
class PersistentClassBuilder extends ClassBuilder {
	private String ID_STEREOTYPE = 'id'
	private Variable serialVersionUID
	private String interfaceName
	
	void build() {
		Project project = cl.project
		def persistentFactory = PersistentFactory.getInstance(project)
		if(persistentFactory?.generateInterfaces) {
			String newInterfaceName = cl.name
			if(newInterfaceName != interfaceName) {
				if(interfaceName != null) {
					removeInterface(interfaceName)
				}
				interfaceName = newInterfaceName
				addInterface(interfaceName)
			}
		} else if(interfaceName != null) {
			removeInterface(interfaceName)
			interfaceName = null
		}
		
		addInterface("java.io.Serializable")
		
		serialVersionUID = createSerialVersionUID( cl.fullName.hashCode() )
		
		def constructor = createConstructor()
		callInitObjects(constructor)
		
		def id = cl.findVariableWithStereotype(ID_STEREOTYPE)
		
		// Create id unless a superclass already has an id.
		if(cl.superClass?.stereotype?.name == 'Persistent') {
			if(id != null) {
				cl.removeVariable(id)
			}
		} else {
			if(id == null) {
				id = cl.createVariable('Long', 'id')
				id.setStereotype(ID_STEREOTYPE)
				
				if(id.composite) {
					addTag(id, '@EmbeddedId')
				} else {
					Tag idTag = addTag(id, '@Id')
			        Tag idcolumTag = addTag(id, '@Column')
					idcolumTag.createParameter('name', (cl.name).toLowerCase()+'_id')
					
					Tag generatedTag = addTag(id, '@GeneratedValue')
					//              generatedTag.createParameter('strategy', 'IDENTITY')
				}
			} else {
				if(id.composite) {
					addTag(id, '@EmbeddedId')
					removeTag(id, '@Id')
					removeTag(id, '@GeneratedValue')
				}
			}
		}
		
		
		/* To generate version:*/
		 if(cl.findVariableWithTag('@Version') == null) {
		     println "Creating Variable version in ${cl}"
		     def version = cl.createVariable('Long', 'version')
			Tag versionTag = addTag(version, '@Version')
			Tag generatedTag = addTag(version, '@Column')
			generatedTag.createParameter('name','OPT_LOCK')
		 }
		
		if(cl.findVariable('dateCreated') == null) {
			println "Creating Variable dateCreated in ${cl}"
			def dateCreated = cl.createVariable('java.util.Date', 'dateCreated')
		}
		
		if(cl.findVariable('lastUpdated') == null) {
			println "Creating Variable lastUpdated in ${cl}"			
			def lastUpdated = cl.createVariable('java.util.Date', 'lastUpdated')
		}
		 
		 /*Create default transient operations */
		
		implementInterfaceOperations()
	}
	
	void clear() {
		if(interfaceName != null) {        
			removeInterface(interfaceName)
		}
		removeInterface("java.io.Serializable")
		
		if(serialVersionUID != null) {
			cl.removeVariable(serialVersionUID)
		}
		
		clearConstructor()
		
		def id = cl.findVariableWithStereotype(ID_STEREOTYPE)
		if(id != null) {
			cl.removeVariable(id)
		}
		
		/* To remove version:*/
		def version = cl.findVariableWithTag('@Version')
	    if(version != null) {
		    println "Removing ${version}"
		    cl.removeVariable(version)
		    version = null
	    }
		
		def dateCreated = cl.findVariable('dateCreated')
		if(dateCreated != null) {
			println "Removing ${dateCreated}"
			cl.removeVariable(dateCreated)
            dateCreated = null
		}
		
		def lastUpdated = cl.findVariable('lastUpdated')
		if(lastUpdated != null) {
			println "Removing ${lastUpdated}"
			cl.removeVariable(lastUpdated)
            lastUpdated = null
		}
		removeInterfaceOperations()
	}
}
