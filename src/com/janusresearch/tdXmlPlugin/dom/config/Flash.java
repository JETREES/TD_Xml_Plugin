// Generated on Tue Feb 27 23:45:49 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.config;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:FlashType interface.
 */
public interface Flash extends DomElement {

	/**
	 * Returns the value of the filename child.
	 * @return the value of the filename child.
	 */
	@NotNull
    @Attribute("filename")
	GenericAttributeValue<String> getFilename();


	/**
	 * Returns the value of the alias child.
	 * @return the value of the alias child.
	 */
	@NotNull
    @Attribute("alias")
	GenericAttributeValue<String> getAlias();


}
