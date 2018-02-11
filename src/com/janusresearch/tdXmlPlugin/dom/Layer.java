// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:LayerType interface.
 */
public interface Layer extends DomElement {

	/**
	 * Returns the value of the name child.
	 * @return the value of the name child.
	 */
	@NotNull
    @Attribute("name")
	GenericAttributeValue<String> getName();


	/**
	 * Returns the value of the context child.
	 * @return the value of the context child.
	 */
	@NotNull
    @Attribute("context")
	GenericAttributeValue<String> getContext();


	/**
	 * Returns the value of the loadType child.
	 * @return the value of the loadType child.
	 */
	@NotNull
    @Attribute("loadType")
	GenericAttributeValue<LayerLoad> getLoadType();


	/**
	 * Returns the value of the disabled child.
	 * @return the value of the disabled child.
	 */
	@NotNull
    @Attribute("disabled")
	GenericAttributeValue<Boolean> getDisabled();


}
