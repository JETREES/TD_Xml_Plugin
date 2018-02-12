// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:GoalType interface.
 */
public interface Goal extends DomElement {

	/**
	 * Returns the value of the name child.
	 * @return the value of the name child.
	 */
	@NotNull
	GenericAttributeValue<String> getName();


	/**
	 * Returns the value of the variable child.
	 * @return the value of the variable child.
	 */
	@NotNull
	GenericAttributeValue<String> getVariable();


	/**
	 * Returns the value of the value child.
	 * @return the value of the value child.
	 */
	@NotNull
	GenericAttributeValue<String> getValue();


	/**
	 * Returns the value of the score child.
	 * @return the value of the score child.
	 */
	@NotNull
	GenericAttributeValue<Integer> getScore();


	/**
	 * Returns the value of the step child.
	 * @return the value of the step child.
	 */
	@NotNull
	GenericAttributeValue<String> getStep();


}
