// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.module;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:GoalsType interface.
 */
public interface Goals extends DomElement {

	/**
	 * Returns the list of Goal children.
	 * @return the list of Goal children.
	 */
	@NotNull
	@SubTag("Goal")
	java.util.List<Goal> getGoals();
	/**
	 * Adds new child to the list of Goal children.
	 * @return created child
	 */
	@SubTag("Goal")
	Goal addGoal();


}
