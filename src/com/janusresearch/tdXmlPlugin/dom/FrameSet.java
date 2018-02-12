// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.SubTag;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.xml.DomElement;

/**
 * null:FrameSetType interface.
 */
public interface FrameSet extends DomElement {

	/**
	 * Returns the list of Frame children.
	 * @return the list of Frame children.
	 */
	@NotNull
	@SubTag("Frame")
	java.util.List<Frame> getFrames();
	/**
	 * Adds new child to the list of Frame children.
	 * @return created child
	 */
	@SubTag("Frame")
	Frame addFrame();


	/**
	 * Returns the list of Question children.
	 * @return the list of Question children.
	 */
	@NotNull
	@SubTag("Question")
	java.util.List<Frame> getQuestions();
	/**
	 * Adds new child to the list of Question children.
	 * @return created child
	 */
	@SubTag("Question")
	Frame addQuestion();


	/**
	 * Returns the list of COL children.
	 * @return the list of COL children.
	 */
	@NotNull
	@SubTag("COL")
	java.util.List<COL> getCOLs();
	/**
	 * Adds new child to the list of COL children.
	 * @return created child
	 */
	@SubTag("COL")
	COL addCOL();


}
