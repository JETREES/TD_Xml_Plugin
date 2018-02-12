// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:ErrorElemType interface.
 */
public interface Error extends DomElement {

	/**
	 * Returns the value of the simple content.
	 * @return the value of the simple content.
	 */
	@NotNull
	@Required
	@TagValue
	String getValue();
	/**
	 * Sets the value of the simple content.
	 * @param value the new value to set
	 */
	@TagValue
	void setValue(@NotNull String value);


	/**
	 * Returns the value of the count child.
	 * @return the value of the count child.
	 */
	@NotNull
	@Attribute("count")
	GenericAttributeValue<Integer> getCount();


}
