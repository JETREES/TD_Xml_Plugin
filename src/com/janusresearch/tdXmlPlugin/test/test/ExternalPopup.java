// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:ExternalPopupType interface.
 */
public interface ExternalPopup extends DomElement {

	/**
	 * Returns the list of Popup children.
	 * @return the list of Popup children.
	 */
	@NotNull
	java.util.List<GenericDomValue<String>> getPopups();
	/**
	 * Adds new child to the list of Popup children.
	 * @return created child
	 */
	GenericDomValue<String> addPopup();


}
