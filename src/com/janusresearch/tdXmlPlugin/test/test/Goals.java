// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:GoalsType interface.
 */
public interface Goals extends DomElement {

	/**
	 * Returns the list of Goal children.
	 * @return the list of Goal children.
	 */
	@NotNull
	java.util.List<Goal> getGoals();
	/**
	 * Adds new child to the list of Goal children.
	 * @return created child
	 */
	Goal addGoal();


}
