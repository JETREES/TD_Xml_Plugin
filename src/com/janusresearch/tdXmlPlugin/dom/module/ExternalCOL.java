// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.module;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:ExternalCOLType interface.
 */
public interface ExternalCOL extends DomElement {

	/**
	 * Returns the list of COL children.
	 * @return the list of COL children.
	 */
	@NotNull
	@SubTag("COL")
	java.util.List<GenericDomValue<String>> getCOLs();
	/**
	 * Adds new child to the list of COL children.
	 * @return created child
	 */
	@SubTag("COL")
	GenericDomValue<String> addCOL();


}
