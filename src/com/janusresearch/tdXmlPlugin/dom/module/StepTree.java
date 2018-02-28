// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.module;

import com.intellij.util.xml.SubTag;
import com.intellij.util.xml.SubTagList;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.xml.DomElement;

/**
 * null:StepTreeType interface.
 */
public interface StepTree extends DomElement {

	/**
	 * Returns the list of node children.
	 * @return the list of node children.
	 */
	@NotNull
	@SubTagList("node")
	java.util.List<StepTreeNode> getNodes();
	/**
	 * Adds new child to the list of node children.
	 * @return created child
	 */
	@SubTagList("node")
	StepTreeNode addNode();


}
