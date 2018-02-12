// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:FeedbackType interface.
 */
public interface Feedback extends DomElement {

	/**
	 * Returns the value of the Correct child.
	 * @return the value of the Correct child.
	 */
	@NotNull
	GenericDomValue<String> getCorrect();


	/**
	 * Returns the list of Error children.
	 * @return the list of Error children.
	 */
	@NotNull
	java.util.List<Error> getErrors();
	/**
	 * Adds new child to the list of Error children.
	 * @return created child
	 */
	Error addError();


	/**
	 * Returns the value of the NOGO child.
	 * @return the value of the NOGO child.
	 */
	@NotNull
	GenericDomValue<String> getNOGO();


}
