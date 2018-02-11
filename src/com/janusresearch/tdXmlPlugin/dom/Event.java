// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:EventType interface.
 */
public interface Event extends DomElement {

	/**
	 * Returns the value of the get child.
	 * @return the value of the get child.
	 */
	@NotNull
	@Attribute("get")
	GenericAttributeValue<String> getGet();


	/**
	 * Returns the value of the nextid child.
	 * @return the value of the nextid child.
	 */
	@NotNull
	@Attribute("nextid")
	GenericAttributeValue<String> getNextid();


	/**
	 * Returns the value of the send child.
	 * @return the value of the send child.
	 */
	@NotNull
	@Attribute("send")
	GenericAttributeValue<String> getSend();


	/**
	 * Returns the value of the varcheck child.
	 * @return the value of the varcheck child.
	 */
	@NotNull
	@Attribute("varcheck")
	GenericAttributeValue<String> getVarcheck();


	/**
	 * Returns the value of the input child.
	 * @return the value of the input child.
	 */
	@NotNull
	@Attribute("input")
	GenericAttributeValue<String> getInput();

}
