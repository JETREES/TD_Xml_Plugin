// Generated on Tue Feb 27 23:45:49 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.config;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTag;
import com.intellij.util.xml.SubTagList;
import org.jetbrains.annotations.NotNull;

/**
 * null:GroupsType interface.
 */
public interface Groups extends DomElement {

	/**
	 * Returns the list of Group children.
	 * @return the list of Group children.
	 */
	@NotNull
	@SubTagList("Group")
	java.util.List<Group> getGroups();
	/**
	 * Adds new child to the list of Group children.
	 * @return created child
	 */
	@SubTag("Group")
	Group addGroup();


}
