// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:CommandMacrosType interface.
 */
public interface CommandMacros extends DomElement {

	/**
	 * Returns the list of Macro children.
	 * @return the list of Macro children.
	 */
	@NotNull
	java.util.List<Macro> getMacros();
	/**
	 * Adds new child to the list of Macro children.
	 * @return created child
	 */
	Macro addMacro();


}
