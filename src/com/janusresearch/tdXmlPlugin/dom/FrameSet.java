// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.SubTagList;
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
    @SubTagList("Frame")
	java.util.List<Frame> getFrames();
	/**
	 * Adds new child to the list of Frame children.
	 * @return created child
	 */
    @SubTagList("Frame")
	Frame addFrame();


	/**
	 * Returns the list of Question children.
	 * @return the list of Question children.
	 */
	@NotNull
	@SubTagList("Question")
	java.util.List<Frame> getQuestions();
	/**
	 * Adds new child to the list of Question children.
	 * @return created child
	 */
	@SubTagList("Question")
	Frame addQuestion();


	/**
	 * Returns the list of COL children.
	 * @return the list of COL children.
	 */
	@NotNull
	@SubTagList("COL")
	java.util.List<COL> getCOLs();
	/**
	 * Adds new child to the list of COL children.
	 * @return created child
	 */
	@SubTagList("COL")
	COL addCOL();


}
