// Generated on Tue Feb 27 23:45:49 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.config;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:UserclassType interface.
 */
public interface Userclass extends DomElement {

	/**
	 * Returns the value of the position child.
	 * @return the value of the position child.
	 */
	@NotNull
    @Attribute("position")
	GenericAttributeValue<String> getPosition();


	/**
	 * Returns the value of the Title child.
	 * @return the value of the Title child.
	 */
	@NotNull
    @SubTag("Title")
	GenericDomValue<String> getTitle();


	/**
	 * Returns the value of the Description child.
	 * @return the value of the Description child.
	 */
	@NotNull
    @SubTag("Description")
	GenericDomValue<String> getDescription();


	/**
	 * Returns the value of the Preview child.
	 * @return the value of the Preview child.
	 */
	@NotNull
    @SubTag("Preview")
	GenericDomValue<String> getPreview();


}
