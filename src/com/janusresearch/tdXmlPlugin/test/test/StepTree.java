// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:StepTreeType interface.
 */
public interface StepTree extends DomElement {

	/**
	 * Returns the list of node children.
	 * @return the list of node children.
	 */
	@NotNull
	java.util.List<StepTreeNode> getNodes();
	/**
	 * Adds new child to the list of node children.
	 * @return created child
	 */
	StepTreeNode addNode();


}
