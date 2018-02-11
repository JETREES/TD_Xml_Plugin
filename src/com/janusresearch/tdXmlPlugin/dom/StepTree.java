// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.SubTag;
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
	@SubTag("node")
	java.util.List<StepTreeNode> getNodes();
	/**
	 * Adds new child to the list of node children.
	 * @return created child
	 */
	@SubTag("node")
	StepTreeNode addNode();


}
