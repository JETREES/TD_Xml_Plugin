// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:MacroType interface.
 */
public interface Macro extends DomElement {

	/**
	 * Returns the value of the name child.
	 * @return the value of the name child.
	 */
	@NotNull
	GenericAttributeValue<String> getName();


	/**
	 * Returns the list of Command children.
	 * @return the list of Command children.
	 */
	@NotNull
	java.util.List<Command> getCommands();
	/**
	 * Adds new child to the list of Command children.
	 * @return created child
	 */
	Command addCommand();


	/**
	 * Returns the list of CommandScreen children.
	 * @return the list of CommandScreen children.
	 */
	@NotNull
	java.util.List<CommandScreen> getCommandScreens();
	/**
	 * Adds new child to the list of CommandScreen children.
	 * @return created child
	 */
	CommandScreen addCommandScreen();


}
