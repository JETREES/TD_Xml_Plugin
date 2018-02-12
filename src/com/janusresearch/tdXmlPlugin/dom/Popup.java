// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:PopupType interface.
 */
public interface Popup extends DomElement {

	/**
	 * Returns the value of the x child.
	 * @return the value of the x child.
	 */
	@NotNull
	@Required
	@Attribute("x")
	GenericAttributeValue<Integer> getX();


	/**
	 * Returns the value of the y child.
	 * @return the value of the y child.
	 */
	@NotNull
	@Required
	@Attribute("y")
	GenericAttributeValue<Integer> getY();


	/**
	 * Returns the value of the w child.
	 * @return the value of the w child.
	 */
	@NotNull
	@Required
	@Attribute("w")
	GenericAttributeValue<Integer> getW();


	/**
	 * Returns the value of the h child.
	 * @return the value of the h child.
	 */
	@NotNull
	@Required
	@Attribute("h")
	GenericAttributeValue<Integer> getH();


	/**
	 * Returns the value of the Id child.
	 * @return the value of the Id child.
	 */
	@NotNull
	@Required
	@SubTag("Id")
	GenericDomValue<String> getId();


	/**
	 * Returns the value of the Text child.
	 * @return the value of the Text child.
	 */
	@NotNull
	@Required
	@SubTag("Text")
	GenericDomValue<String> getText();


	/**
	 * Returns the value of the Close child.
	 * @return the value of the Close child.
	 */
	@NotNull
	@SubTag("Close")
	GenericDomValue<String> getClose();


	/**
	 * Returns the value of the Title child.
	 * @return the value of the Title child.
	 */
	@NotNull
	@SubTag("Title")
	GenericDomValue<String> getTitle();


	/**
	 * Returns the value of the Drag child.
	 * @return the value of the Drag child.
	 */
	@NotNull
	@SubTag("Drag")
	GenericDomValue<Boolean> getDrag();


}
