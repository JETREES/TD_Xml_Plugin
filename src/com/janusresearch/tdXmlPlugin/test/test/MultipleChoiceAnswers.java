// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:MultipleChoiceAnswersType interface.
 */
public interface MultipleChoiceAnswers extends DomElement {

	/**
	 * Returns the value of the A child.
	 * @return the value of the A child.
	 */
	@NotNull
	GenericDomValue<String> getA();


	/**
	 * Returns the value of the B child.
	 * @return the value of the B child.
	 */
	@NotNull
	GenericDomValue<String> getB();


	/**
	 * Returns the value of the C child.
	 * @return the value of the C child.
	 */
	@NotNull
	GenericDomValue<String> getC();


	/**
	 * Returns the value of the D child.
	 * @return the value of the D child.
	 */
	@NotNull
	GenericDomValue<String> getD();


	/**
	 * Returns the value of the Correct child.
	 * @return the value of the Correct child.
	 */
	@NotNull
	GenericDomValue<String> getCorrect();


	/**
	 * Returns the value of the PositiveFeedback child.
	 * @return the value of the PositiveFeedback child.
	 */
	@NotNull
	GenericDomValue<String> getPositiveFeedback();


	/**
	 * Returns the value of the NegativeFeedback child.
	 * @return the value of the NegativeFeedback child.
	 */
	@NotNull
	GenericDomValue<String> getNegativeFeedback();


}
