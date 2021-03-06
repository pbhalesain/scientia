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
package org.oplm.core.model.policy;

/**
 * Behaviour attached to entities runtime. 
 * Any entity can have various policies stored. Policies have behaviour stored as a string
 * in groovy script format. These scripts are rendered into beans inside service.   
 * @author Prashant Bhalesain
 */
public interface Behaviour {
	
}
