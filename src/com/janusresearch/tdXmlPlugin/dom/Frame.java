// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import com.sampullara.cli.Argument;
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
	@Attribute("id")
	GenericAttributeValue<String> getId();


	/**
	 * Returns the value of the node child.
	 * @return the value of the node child.
	 */
	@NotNull
	@Attribute("node")
	GenericAttributeValue<String> getNode();


	/**
	 * Returns the value of the weight child.
	 * @return the value of the weight child.
	 */
	@NotNull
	@Attribute("weight")
	GenericAttributeValue<String> getWeight();


	/**
	 * Returns the value of the steps child.
	 * @return the value of the steps child.
	 */
	@NotNull
	@Argument("steps")
	GenericAttributeValue<String> getSteps();


	/**
	 * Returns the value of the score child.
	 * @return the value of the score child.
	 */
	@NotNull
	@Attribute("score")
	GenericAttributeValue<Integer> getScore();


	/**
	 * Returns the value of the status child.
	 * @return the value of the status child.
	 */
	@NotNull
	@Attribute("status")
	GenericAttributeValue<String> getStatus();


	/**
	 * Returns the value of the Events child.
	 * @return the value of the Events child.
	 */
	@NotNull
	@SubTag("Events")
	Events getEvents();


	/**
	 * Returns the value of the Commands child.
	 * @return the value of the Commands child.
	 */
	@NotNull
	@SubTag("Commands")
	Commands getCommands();


	/**
	 * Returns the value of the Commands2 child.
	 * @return the value of the Commands2 child.
	 */
	@NotNull
	@SubTag("Commands2")
	Commands getCommands2();


	/**
	 * Returns the value of the InfoText child.
	 * @return the value of the InfoText child.
	 */
	@NotNull
	@SubTag("InfoText")
	GenericDomValue<String> getInfoText();


	/**
	 * Returns the value of the Text child.
	 * @return the value of the Text child.
	 */
	@NotNull
	@SubTag("Text")
	GenericDomValue<String> getText();


	/**
	 * Returns the value of the Text2 child.
	 * @return the value of the Text2 child.
	 */
	@NotNull
	@SubTag("Text2")
	GenericDomValue<String> getText2();


	/**
	 * Returns the value of the Query child.
	 * @return the value of the Query child.
	 */
	@NotNull
	@SubTag("Query")
	GenericDomValue<String> getQuery();


	/**
 	* Returns the value of the Answers child.
 	* @return the value of the Answers child.
 	*/
	@NotNull
	@SubTag("Answers")
	MultipleChoiceAnswers getAnswers();


	/**
	 * Returns the value of the InsertAfter child.
	 * @return the value of the InsertAfter child.
	 */
	@NotNull
	@SubTag("InsertAfter")
	GenericDomValue<String> getInsertAfter();


}
