// Generated on Tue Feb 27 23:45:49 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.config;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTag;
import com.intellij.util.xml.SubTagList;
import org.jetbrains.annotations.NotNull;

/**
 * null:UserclassesType interface.
 */
public interface Userclasses extends DomElement {

	/**
	 * Returns the list of Userclass children.
	 * @return the list of Userclass children.
	 */
	@NotNull
	@SubTagList("Userclass")
	java.util.List<Userclass> getUserclasses();
	/**
	 * Adds new child to the list of Userclass children.
	 * @return created child
	 */
	@SubTag("Userclass")
	Userclass addUserclass();


}
