// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:COLType interface.
 */
public interface COL extends DomElement {

	/**
	 * Returns the value of the Type child.
	 * @return the value of the Type child.
	 */
	@NotNull
	GenericDomValue<String> getType();


	/**
	 * Returns the value of the Questions child.
	 * @return the value of the Questions child.
	 */
	@NotNull
	Questions getQuestions();


	/**
	 * Returns the value of the Answers child.
	 * @return the value of the Answers child.
	 */
	@NotNull
	Answers getAnswers();


	/**
	 * Returns the value of the Feedback child.
	 * @return the value of the Feedback child.
	 */
	@NotNull
	Feedback getFeedback();


	/**
	 * Returns the value of the InsertAfter child.
	 * @return the value of the InsertAfter child.
	 */
	@NotNull
	GenericDomValue<String> getInsertAfter();


	/**
	 * Returns the value of the Review child.
	 * @return the value of the Review child.
	 */
	@NotNull
	GenericDomValue<String> getReview();


}
