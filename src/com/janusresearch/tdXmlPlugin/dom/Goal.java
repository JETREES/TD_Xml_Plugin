// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

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
	@Attribute("name")
	GenericAttributeValue<String> getName();


	/**
	 * Returns the value of the variable child.
	 * @return the value of the variable child.
	 */
	@NotNull
	@Attribute("variable")
	GenericAttributeValue<String> getVariable();


	/**
	 * Returns the value of the value child.
	 * @return the value of the value child.
	 */
	@NotNull
	@Attribute("value")
	GenericAttributeValue<String> getValue();


	/**
	 * Returns the value of the score child.
	 * @return the value of the score child.
	 */
	@NotNull
	@Attribute("score")
	GenericAttributeValue<Integer> getScore();


	/**
	 * Returns the value of the step child.
	 * @return the value of the step child.
	 */
	@NotNull
	@Attribute("step")
	GenericAttributeValue<String> getStep();


}
