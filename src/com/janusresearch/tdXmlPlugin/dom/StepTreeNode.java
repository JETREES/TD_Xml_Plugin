// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:StepTreeNodeType interface.
 */
public interface StepTreeNode extends DomElement {

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
	GenericAttributeValue<String> getParentAttr();


	/**
	 * Returns the value of the label child.
	 * @return the value of the label child.
	 */
	@NotNull
	@Attribute("label")
	GenericAttributeValue<String> getLabel();


	/**
	 * Returns the value of the id child.
	 * @return the value of the id child.
	 */
	@NotNull
	@Attribute("id")
	GenericAttributeValue<String> getId();


}
