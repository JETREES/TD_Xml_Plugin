// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:PopupsType interface.
 */
public interface Popups extends DomElement {

	/**
	 * Returns the list of POPUP children.
	 * @return the list of POPUP children.
	 */
	@NotNull
	java.util.List<GenericDomValue<String>> getPOPUPs();
	/**
	 * Adds new child to the list of POPUP children.
	 * @return created child
	 */
	GenericDomValue<String> addPOPUP();


}
