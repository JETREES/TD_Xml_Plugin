// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.module;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:LessonReferenceFileType interface.
 */
public interface LessonReferenceFile extends DomElement {

	/**
	 * Returns the value of the name child.
	 * @return the value of the name child.
	 */
	@NotNull
	@Attribute("name")
	GenericAttributeValue<String> getName();


	/**
	 * Returns the value of the label child.
	 * @return the value of the label child.
	 */
	@NotNull
	@Attribute("label")
	GenericAttributeValue<String> getLabel();


}
