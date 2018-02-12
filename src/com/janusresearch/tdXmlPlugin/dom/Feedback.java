// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;


/**
 * null:FeedbackType interface.
 */
public interface Feedback extends DomElement {

	/**
	 * Returns the value of the Correct child.
	 * @return the value of the Correct child.
	 */
	@NotNull
	@SubTag("Correct")
	GenericDomValue<String> getCorrect();


	/**
	 * Returns the list of Error children.
	 * @return the list of Error children.
	 */
	@NotNull
	@SubTag("Error")
	java.util.List<Error> getErrors();
	/**
	 * Adds new child to the list of Error children.
	 * @return created child
	 */
	@SubTag("Error")
	Error addError();


	/**
	 * Returns the value of the NOGO child.
	 * @return the value of the NOGO child.
	 */
	@NotNull
	@SubTag("NOGO")
	GenericDomValue<String> getNOGO();


}
