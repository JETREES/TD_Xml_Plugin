// Generated on Tue Feb 27 23:45:49 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.config;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTag;
import com.intellij.util.xml.SubTagList;
import org.jetbrains.annotations.NotNull;

/**
 * null:CourseType interface.
 */
public interface Course extends DomElement {

	/**
	 * Returns the list of Type children.
	 * @return the list of Type children.
	 */
	@NotNull
	@SubTagList("Type")
	java.util.List<Type> getTypes();
	/**
	 * Adds new child to the list of Type children.
	 * @return created child
	 */
	@SubTag("Type")
	Type addType();


}
