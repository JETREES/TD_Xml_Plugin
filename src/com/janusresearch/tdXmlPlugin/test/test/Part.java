// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:PartType interface.
 */
public interface Part extends DomElement {

	/**
	 * Returns the value of the name child.
	 * @return the value of the name child.
	 */
	@NotNull
	GenericAttributeValue<String> getName();


	/**
	 * Returns the value of the Camera child.
	 * @return the value of the Camera child.
	 */
	@NotNull
	GenericDomValue<String> getCamera();


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
	 * Returns the list of Reference children.
	 * @return the list of Reference children.
	 */
	@NotNull
	java.util.List<GenericDomValue<String>> getReferences();
	/**
	 * Adds new child to the list of Reference children.
	 * @return created child
	 */
	GenericDomValue<String> addReference();


	/**
	 * Returns the value of the Description child.
	 * @return the value of the Description child.
	 */
	@NotNull
	GenericDomValue<String> getDescription();


}
