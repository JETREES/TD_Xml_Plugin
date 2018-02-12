// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:PartsType interface.
 */
public interface Parts extends DomElement {

	/**
	 * Returns the list of Part children.
	 * @return the list of Part children.
	 */
	@NotNull
	java.util.List<Part> getParts();
	/**
	 * Adds new child to the list of Part children.
	 * @return created child
	 */
	Part addPart();


}
