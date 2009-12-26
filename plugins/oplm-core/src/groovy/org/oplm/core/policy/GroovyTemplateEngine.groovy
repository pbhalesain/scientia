/********************************************************************************
 * Product: Open Product Lifecycle Management Solution (oplm)                   *
 * Copyright (C) 20097 Banko Software, Inc. All Rights Reserved.                *
 * This program is free software: you can redistribute it and/or modify         *
 * it under the terms of the GNU General Public License as published by         *
 * the Free Software Foundation, either version 3 of the License, or            *
 * (at your option) any later version.                                          *
 *                                                                              *
 * This program is distributed in the hope that it will be useful,              *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of               *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                *
 * GNU General Public License for more details.                                 *
 *                                                                              *
 * You should have received a copy of the GNU General Public License            *
 * along with this program.                                                     *
 * For the text or an alternative of this public license, you may reach us      *
 * contact information provided at http://www.oplm.org/contact                  *
 * or via info@oplm.org or http://www.oplm.org/license                          *
 ********************************************************************************/
package org.oplm.core.policy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

/**
 *
 * @author Prashant Bhalesain
 */

class GroovyTemplateEngine extends DefaultListableBeanFactory implements
		ApplicationContextAware {
	
	def applicationContext
	
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
			
    def registerBean(String packageName, String beanName, String code){
		GroovyClassLoader gcl = new GroovyClassLoader();
		Class clazz = gcl.parseClass(code);
		AbstractBeanDefinition beanDef =
		BeanDefinitionReaderUtils.createBeanDefinition( "parentBean", packageName + "." + beanName, gcl);
		DefaultListableBeanFactory factory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
		factory.registerBeanDefinition(className, beanDef);
		def bean = factory.createBean(clazz, AUTOWIRE_BY_NAME, false);		
		return bean;
    }
			

}
