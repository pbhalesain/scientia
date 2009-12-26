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
package org.oplm.exception.service;

import org.oplm.exception.OplmException;

/**
 * @author Prashant Bhalesain
 */
public class CheckinCheckOutServiceException extends OplmException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9202628194888162600L;

	/**
	 * 
	 */
	public CheckinCheckOutServiceException() {
		super();
	}

	/**
	 * 
	 * @param arg0
	 * @param arg1
	 */
	public CheckinCheckOutServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * 
	 * @param arg0
	 */
	public CheckinCheckOutServiceException(String arg0) {
		super(arg0);
	}

	/**
	 * 
	 * @param arg0
	 */
	public CheckinCheckOutServiceException(Throwable arg0) {
		super(arg0);
	}
}
