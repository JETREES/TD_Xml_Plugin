// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:EventType interface.
 */
public interface Event extends DomElement {

	/**
	 * Returns the value of the get child.
	 * @return the value of the get child.
	 */
	@NotNull
	GenericAttributeValue<String> getGet();


	/**
	 * Returns the value of the nextid child.
	 * @return the value of the nextid child.
	 */
	@NotNull
	GenericAttributeValue<String> getNextid();


	/**
	 * Returns the value of the send child.
	 * @return the value of the send child.
	 */
	@NotNull
	GenericAttributeValue<String> getSend();


	/**
	 * Returns the value of the varcheck child.
	 * @return the value of the varcheck child.
	 */
	@NotNull
	GenericAttributeValue<String> getVarcheck();


	/**
	 * Returns the value of the input child.
	 * @return the value of the input child.
	 */
	@NotNull
	GenericAttributeValue<String> getInput();


}
