// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
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
    @SubTag("Type")
    GenericDomValue<String> getType();


	/**
	 * Returns the value of the Questions child.
	 * @return the value of the Questions child.
	 */
	@NotNull
	@SubTag("Questions")
	Questions getQuestions();


	/**
	 * Returns the value of the Answers child.
	 * @return the value of the Answers child.
	 */
	@NotNull
	@SubTag("Answers")
	Answers getAnswers();


	/**
	 * Returns the value of the Feedback child.
	 * @return the value of the Feedback child.
	 */
	@NotNull
	@SubTag("Feedback")
	Feedback getFeedback();


	/**
	 * Returns the value of the InsertAfter child.
	 * @return the value of the InsertAfter child.
	 */
	@NotNull
	@SubTag("InsertAfter")
	GenericDomValue<String> getInsertAfter();


	/**
	 * Returns the value of the Review child.
	 * @return the value of the Review child.
	 */
	@NotNull
	@SubTag("Review")
	GenericDomValue<String> getReview();


}
