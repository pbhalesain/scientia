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
package org.oplm.exception;

import org.codehaus.groovy.grails.exceptions.GrailsException;

/**
 * @author Prashant Bhalesain
 * @since Dec 24, 2009
 */
public class OplmException extends GrailsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1720334143229349619L;

	public OplmException(){
		super();
	}
	
	public OplmException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public OplmException(String arg0) {
		super(arg0);
	}

	public OplmException(Throwable arg0) {
		super(arg0);
	}
}
