// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:PartType interface.
 */
public interface Part extends DomElement {

	/**
	 * Returns the value of the name child.
	 * @return the value of the name child.
	 */
	@NotNull
	@Attribute("name")
	GenericAttributeValue<String> getName();


	/**
	 * Returns the value of the Camera child.
	 * @return the value of the Camera child.
	 */
	@NotNull
	@SubTag("Camera")
	GenericDomValue<String> getCamera();


	/**
	 * Returns the list of Reference children.
	 * @return the list of Reference children.
	 */
	@NotNull
	@SubTag("Reference")
	java.util.List<GenericDomValue<String>> getReferences();
	/**
	 * Adds new child to the list of Reference children.
	 * @return created child
	 */
	@SubTag("Reference")
	GenericDomValue<String> addReference();


	/**
	 * Returns the value of the Description child.
	 * @return the value of the Description child.
	 */
	@NotNull
	@SubTag("Description")
	GenericDomValue<String> getDescription();


}
