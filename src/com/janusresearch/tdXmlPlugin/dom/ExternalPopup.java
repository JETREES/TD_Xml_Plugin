// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

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
	@SubTag("Popup")
	java.util.List<GenericDomValue<String>> getPopups();
	/**
	 * Adds new child to the list of Popup children.
	 * @return created child
	 */
	@SubTag("Popup")
	GenericDomValue<String> addPopup();


}
