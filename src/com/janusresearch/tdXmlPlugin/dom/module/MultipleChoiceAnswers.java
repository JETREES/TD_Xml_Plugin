// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.module;

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
	@SubTag("A")
	GenericDomValue<String> getA();


	/**
	 * Returns the value of the B child.
	 * @return the value of the B child.
	 */
	@NotNull
	@SubTag("B")
	GenericDomValue<String> getB();


	/**
	 * Returns the value of the C child.
	 * @return the value of the C child.
	 */
	@NotNull
	@SubTag("C")
	GenericDomValue<String> getC();


	/**
	 * Returns the value of the D child.
	 * @return the value of the D child.
	 */
	@NotNull
	@SubTag("D")
	GenericDomValue<String> getD();


	/**
	 * Returns the value of the Correct child.
	 * @return the value of the Correct child.
	 */
	@NotNull
	@SubTag("Correct")
	GenericDomValue<String> getCorrect();


	/**
	 * Returns the value of the PositiveFeedback child.
	 * @return the value of the PositiveFeedback child.
	 */
	@NotNull
	@SubTag("PositiveFeedback")
	GenericDomValue<String> getPositiveFeedback();


	/**
	 * Returns the value of the NegativeFeedback child.
	 * @return the value of the NegativeFeedback child.
	 */
	@NotNull
	@SubTag("NegativeFeedback")
	GenericDomValue<String> getNegativeFeedback();


}
