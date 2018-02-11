// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:ObjectTreeNodeType interface.
 */
public interface ObjectTreeNode extends DomElement {

	/**
	 * Returns the value of the name child.
	 * @return the value of the name child.
	 */
	@NotNull
	@Attribute("name")
	GenericAttributeValue<String> getName();


	/**
	 * Returns the value of the parent child.
	 * @return the value of the parent child.
	 */
	@NotNull
	@Attribute("parent")
	GenericAttributeValue<String> getParent();


	/**
	 * Returns the value of the label child.
	 * @return the value of the label child.
	 */
	@NotNull
	@Attribute("label")
	GenericAttributeValue<String> getLabel();


	/**
	 * Returns the value of the camera child.
	 * @return the value of the camera child.
	 */
	@NotNull
	@Attribute("camera")
	GenericAttributeValue<String> getCamera();


	/**
	 * Returns the value of the objects child.
	 * @return the value of the objects child.
	 */
	@NotNull
	@Attribute("objects")
	GenericAttributeValue<String> getObjects();


}
