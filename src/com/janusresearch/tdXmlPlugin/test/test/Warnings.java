// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:WarningsType interface.
 */
public interface Warnings extends DomElement {

	/**
	 * Returns the list of Warning children.
	 * @return the list of Warning children.
	 */
	@NotNull
	java.util.List<GenericDomValue<String>> getWarnings();
	/**
	 * Adds new child to the list of Warning children.
	 * @return created child
	 */
	GenericDomValue<String> addWarning();


}
