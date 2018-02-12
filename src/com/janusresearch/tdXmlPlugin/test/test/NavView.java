// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:NavViewType interface.
 */
public interface NavView extends DomElement {

	/**
	 * Returns the value of the name child.
	 * @return the value of the name child.
	 */
	@NotNull
	GenericAttributeValue<String> getName();


	/**
	 * Returns the value of the camera child.
	 * @return the value of the camera child.
	 */
	@NotNull
	GenericAttributeValue<String> getCamera();


	/**
	 * Returns the value of the remove child.
	 * @return the value of the remove child.
	 */
	@NotNull
	GenericAttributeValue<String> getRemove();


}
