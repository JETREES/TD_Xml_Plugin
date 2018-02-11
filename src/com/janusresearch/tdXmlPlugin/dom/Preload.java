// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:PreloadType interface.
 */
public interface Preload extends DomElement {

	/**
	 * Returns the value of the filename child.
	 * @return the value of the filename child.
	 */
	@NotNull
	@Attribute("filename")
	GenericAttributeValue<String> getFilename();


}
