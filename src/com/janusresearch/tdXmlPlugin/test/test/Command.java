// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:CommandType interface.
 */
public interface Command extends DomElement {

	/**
	 * Returns the value of the send child.
	 * @return the value of the send child.
	 */
	@NotNull
	GenericAttributeValue<String> getSend();


	/**
	 * Returns the value of the wait child.
	 * @return the value of the wait child.
	 */
	@NotNull
	GenericAttributeValue<String> getWait();


	/**
	 * Returns the value of the repeatid child.
	 * @return the value of the repeatid child.
	 */
	@NotNull
	GenericAttributeValue<String> getRepeatid();


	/**
	 * Returns the value of the repeatwait child.
	 * @return the value of the repeatwait child.
	 */
	@NotNull
	GenericAttributeValue<String> getRepeatwait();


	/**
	 * Returns the value of the repeatsend child.
	 * @return the value of the repeatsend child.
	 */
	@NotNull
	GenericAttributeValue<String> getRepeatsend();


}
