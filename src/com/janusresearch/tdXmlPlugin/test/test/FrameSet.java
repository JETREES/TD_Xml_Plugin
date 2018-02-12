// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:FrameSetType interface.
 */
public interface FrameSet extends DomElement {

	/**
	 * Returns the list of Frame children.
	 * @return the list of Frame children.
	 */
	@NotNull
	java.util.List<Frame> getFrames();
	/**
	 * Adds new child to the list of Frame children.
	 * @return created child
	 */
	Frame addFrame();


	/**
	 * Returns the list of Question children.
	 * @return the list of Question children.
	 */
	@NotNull
	java.util.List<Frame> getQuestions();
	/**
	 * Adds new child to the list of Question children.
	 * @return created child
	 */
	Frame addQuestion();


	/**
	 * Returns the list of COL children.
	 * @return the list of COL children.
	 */
	@NotNull
	java.util.List<COL> getCOLs();
	/**
	 * Adds new child to the list of COL children.
	 * @return created child
	 */
	COL addCOL();


}
