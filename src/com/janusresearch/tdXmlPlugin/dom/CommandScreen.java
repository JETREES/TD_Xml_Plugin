// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:CommandScreenType interface.
 */
public interface CommandScreen extends DomElement {

	/**
	 * Returns the value of the line child.
	 * @return the value of the line child.
	 */
	@NotNull
	@Attribute("line")
	GenericAttributeValue<Integer> getLine();


	/**
	 * Returns the value of the send child.
	 * @return the value of the send child.
	 */
	@NotNull

	@Attribute("send")
	GenericAttributeValue<String> getSend();


	/**
	 * Returns the value of the screen child.
	 * @return the value of the screen child.
	 */
	@NotNull

	@Attribute("screen")
	GenericAttributeValue<String> getScreen();


	/**
	 * Returns the value of the wait child.
	 * @return the value of the wait child.
	 */
	@NotNull

	@Attribute("wait")
	GenericAttributeValue<Integer> getWait();


	/**
	 * Returns the value of the repeatid child.
	 * @return the value of the repeatid child.
	 */
	@NotNull

	@Attribute("repeatid")
	GenericAttributeValue<String> getRepeatid();


	/**
	 * Returns the value of the repeatsend child.
	 * @return the value of the repeatsend child.
	 */
	@NotNull
	@Attribute("repeatsend")
	GenericAttributeValue<String> getRepeatsend();


	/**
	 * Returns the value of the repeatwait child.
	 * @return the value of the repeatwait child.
	 */
	@NotNull
	@Attribute("repeatwait")
	GenericAttributeValue<Integer> getRepeatwait();


}
