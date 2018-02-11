// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

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
	 * Returns the value of the ignore child.
	 * @return the value of the ignore child.
	 */
	@NotNull
	@Attribute("ignore")
	GenericAttributeValue<Integer> getIgnore();


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
	 * Returns the value of the PathToFrame child.
	 * @return the value of the PathToFrame child.
	 */
	@NotNull
	@SubTag("PathToFrame")
	GenericDomValue<String> getPathToFrame();


	/**
	 * Returns the value of the EntryPoint child.
	 * @return the value of the EntryPoint child.
	 */
	@NotNull
	@SubTag("EntryPoint")
	GenericDomValue<String> getEntryPoint();


}
