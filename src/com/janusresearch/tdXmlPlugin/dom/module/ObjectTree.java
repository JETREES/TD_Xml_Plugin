// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.module;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:ObjectTreeType interface.
 */
public interface ObjectTree extends DomElement {

	/**
	 * Returns the list of node children.
	 * @return the list of node children.
	 */
	@NotNull
	@SubTag("node")
	java.util.List<ObjectTreeNode> getNodes();
	/**
	 * Adds new child to the list of node children.
	 * @return created child
	 */
	@SubTag("node")
	ObjectTreeNode addNode();


}
