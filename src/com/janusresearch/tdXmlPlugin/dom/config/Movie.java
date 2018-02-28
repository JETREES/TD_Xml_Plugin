// Generated on Tue Feb 27 23:45:49 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.config;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:MovieType interface.
 */
public interface Movie extends DomElement {

	/**
	 * Returns the value of the filename child.
	 * @return the value of the filename child.
	 */
	@NotNull
	@Attribute("filename")
	GenericAttributeValue<String> getFilename();


	/**
	 * Returns the value of the w3d child.
	 * @return the value of the w3d child.
	 */
	@NotNull
	@Attribute("w3d")
	GenericAttributeValue<String> getW3d();


	/**
	 * Returns the value of the alias child.
	 * @return the value of the alias child.
	 */
	@NotNull
	@Attribute("alias")
	GenericAttributeValue<String> getAlias();


	/**
	 * Returns the value of the default child.
	 * @return the value of the default child.
	 */
	@NotNull
	@Attribute("default")
	GenericAttributeValue<com.janusresearch.tdXmlPlugin.dom.config.Boolean> getDefault();


	/**
	 * Returns the list of Flash children.
	 * @return the list of Flash children.
	 */
	@NotNull
	@SubTagList("Flash")
	java.util.List<Flash> getFlashs();
	/**
	 * Adds new child to the list of Flash children.
	 * @return created child
	 */
	@SubTag("Flash")
	Flash addFlash();


}
