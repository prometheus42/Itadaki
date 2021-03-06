/*
 * Copyright (C) 2006-2007
 * Matt Francis <asbel@neosheffield.co.uk>
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 */

package org.itadaki.openoffice;

import com.sun.star.text.XTextRange;


/**
 * A listener interface to be notified of changes to the region selected by an
 * OfficeSentenceProvider
 */
public interface OfficeSentenceProviderListener {

	/**
	 * Indicates that the region selected by the OfficeSentenceProvider has
	 * changed
	 *
	 * @param newTextRange The new selection. This text range should NOT be
	 *                     modified by implementing classes
	 */
	public void regionChanged (XTextRange newTextRange);

}
