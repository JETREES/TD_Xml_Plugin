// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:ObjectTreeNodeType interface.
 */
public interface ObjectTreeNode extends DomElement {

	/**
	 * Returns the value of the name child.
	 * @return the value of the name child.
	 */
	@NotNull
	GenericAttributeValue<String> getName();


	/**
	 * Returns the value of the parent child.
	 * @return the value of the parent child.
	 */
	@NotNull
	GenericAttributeValue<String> getParent();


	/**
	 * Returns the value of the label child.
	 * @return the value of the label child.
	 */
	@NotNull
	GenericAttributeValue<String> getLabel();


	/**
	 * Returns the value of the camera child.
	 * @return the value of the camera child.
	 */
	@NotNull
	GenericAttributeValue<String> getCamera();


	/**
	 * Returns the value of the objects child.
	 * @return the value of the objects child.
	 */
	@NotNull
	GenericAttributeValue<String> getObjects();


}
