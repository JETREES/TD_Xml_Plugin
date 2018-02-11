// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.SubTag;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.xml.DomElement;

/**
 * null:CommandsType interface.
 */
public interface Commands extends DomElement {

	/**
	 * Returns the list of Command children.
	 * @return the list of Command children.
	 */
	@NotNull
	@SubTag("Command")
	java.util.List<Command> getCommands();
	/**
	 * Adds new child to the list of Command children.
	 * @return created child
	 */
	@SubTag("Command")
	Command addCommand();


	/**
	 * Returns the list of CommandScreen children.
	 * @return the list of CommandScreen children.
	 */
	@NotNull
	@SubTag("CommandScreen")
	java.util.List<CommandScreen> getCommandScreens();
	/**
	 * Adds new child to the list of CommandScreen children.
	 * @return created child
	 */
	@SubTag("CommandScreen")
	CommandScreen addCommandScreen();


}
