// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.xml.DomElement;

/**
 * null:CommandType interface.
 */
public interface Command extends DomElement {

	/**
	 * Returns the value of the send child.
	 * @return the value of the send child.
	 */
	@NotNull
	@Attribute("send")
	GenericAttributeValue<String> getSend();


	/**
	 * Returns the value of the wait child.
	 * @return the value of the wait child.
	 */
	@NotNull
	@Attribute("wait")
	GenericAttributeValue<String> getWait();


	/**
	 * Returns the value of the repeatid child.
	 * @return the value of the repeatid child.
	 */
	@NotNull
	@Attribute("repeatid")
	GenericAttributeValue<String> getRepeatid();


	/**
	 * Returns the value of the repeatwait child.
	 * @return the value of the repeatwait child.
	 */
	@NotNull
	@Attribute("repeatwait")
	GenericAttributeValue<String> getRepeatwait();


	/**
	 * Returns the value of the repeatsend child.
	 * @return the value of the repeatsend child.
	 */
	@NotNull
	@Attribute("repeatsend")
	GenericAttributeValue<String> getRepeatsend();


}
