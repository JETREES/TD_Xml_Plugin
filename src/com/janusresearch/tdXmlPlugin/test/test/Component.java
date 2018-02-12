// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:ComponentType interface.
 */
public interface Component extends DomElement {

	/**
	 * Returns the value of the name child.
	 * @return the value of the name child.
	 */
	@NotNull
	GenericAttributeValue<String> getName();


	/**
	 * Returns the value of the version child.
	 * @return the value of the version child.
	 */
	@NotNull
	GenericAttributeValue<Integer> getVersion();


	/**
	 * Returns the value of the BaseCamera child.
	 * @return the value of the BaseCamera child.
	 */
	@NotNull
	GenericDomValue<String> getBaseCamera();


	/**
	 * Returns the value of the Parts child.
	 * @return the value of the Parts child.
	 */
	@NotNull
	Parts getParts();


}
