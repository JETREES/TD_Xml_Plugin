// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:FAPVType interface.
 */
public interface FAPV extends DomElement {

	/**
	 * Returns the value of the Default child.
	 * @return the value of the Default child.
	 */
	@NotNull
	GenericDomValue<String> getDefault();


	/**
	 * Returns the value of the Familiarize child.
	 * @return the value of the Familiarize child.
	 */
	@NotNull
	GenericDomValue<String> getFamiliarize();


	/**
	 * Returns the value of the Acquire child.
	 * @return the value of the Acquire child.
	 */
	@NotNull
	GenericDomValue<String> getAcquire();


	/**
	 * Returns the value of the Practice child.
	 * @return the value of the Practice child.
	 */
	@NotNull
	GenericDomValue<String> getPractice();


	/**
	 * Returns the value of the Validate child.
	 * @return the value of the Validate child.
	 */
	@NotNull
	GenericDomValue<String> getValidate();


}
