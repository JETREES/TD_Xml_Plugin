// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:FrameType interface.
 */
public interface Frame extends DomElement {

	/**
	 * Returns the value of the id child.
	 * @return the value of the id child.
	 */
	@NotNull
	GenericAttributeValue<String> getId();


	/**
	 * Returns the value of the node child.
	 * @return the value of the node child.
	 */
	@NotNull
	GenericAttributeValue<String> getNode();


	/**
	 * Returns the value of the weight child.
	 * @return the value of the weight child.
	 */
	@NotNull
	GenericAttributeValue<String> getWeight();


	/**
	 * Returns the value of the steps child.
	 * @return the value of the steps child.
	 */
	@NotNull
	GenericAttributeValue<String> getSteps();


	/**
	 * Returns the value of the score child.
	 * @return the value of the score child.
	 */
	@NotNull
	GenericAttributeValue<Integer> getScore();


	/**
	 * Returns the value of the status child.
	 * @return the value of the status child.
	 */
	@NotNull
	GenericAttributeValue<String> getStatus();


	/**
	 * Returns the value of the Events child.
	 * @return the value of the Events child.
	 */
	@NotNull
	Events getEvents();


	/**
	 * Returns the value of the Commands child.
	 * @return the value of the Commands child.
	 */
	@NotNull
	Commands getCommands();


	/**
	 * Returns the value of the Commands2 child.
	 * @return the value of the Commands2 child.
	 */
	@NotNull
	Commands getCommands2();


	/**
	 * Returns the value of the InfoText child.
	 * @return the value of the InfoText child.
	 */
	@NotNull
	GenericDomValue<String> getInfoText();


	/**
	 * Returns the value of the Text child.
	 * @return the value of the Text child.
	 */
	@NotNull
	GenericDomValue<String> getText();


	/**
	 * Returns the value of the Text2 child.
	 * @return the value of the Text2 child.
	 */
	@NotNull
	GenericDomValue<String> getText2();


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
