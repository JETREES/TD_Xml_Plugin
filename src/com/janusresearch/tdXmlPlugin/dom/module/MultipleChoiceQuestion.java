// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.module;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:MultipleChoiceQuestionType interface.
 */
public interface MultipleChoiceQuestion extends DomElement {

	/**
	 * Returns the value of the Query child.
	 * @return the value of the Query child.
	 */
	@NotNull
	GenericDomValue<String> getQuery();


	/**
	 * Returns the value of the Answers child.
	 * @return the value of the Answers child.
	 */
	@NotNull
	MultipleChoiceAnswers getAnswers();


	/**
	 * Returns the value of the InsertAfter child.
	 * @return the value of the InsertAfter child.
	 */
	@NotNull
	GenericDomValue<String> getInsertAfter();


}
