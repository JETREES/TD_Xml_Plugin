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


}
