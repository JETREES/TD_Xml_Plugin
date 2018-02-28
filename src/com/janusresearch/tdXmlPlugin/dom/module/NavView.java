// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.module;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:NavViewType interface.
 */
public interface NavView extends DomElement {

	/**
	 * Returns the value of the name child.
	 * @return the value of the name child.
	 */
	@NotNull
	@Attribute("name")
	GenericAttributeValue<String> getName();


	/**
	 * Returns the value of the camera child.
	 * @return the value of the camera child.
	 */
	@NotNull
	@Attribute("camera")
	GenericAttributeValue<String> getCamera();


	/**
	 * Returns the value of the remove child.
	 * @return the value of the remove child.
	 */
	@NotNull
	@Attribute("remove")
	GenericAttributeValue<String> getRemove();


}
