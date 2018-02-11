// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

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
	@SubTag("Warning")
	java.util.List<GenericDomValue<String>> getWarnings();
	/**
	 * Adds new child to the list of Warning children.
	 * @return created child
	 */
	@SubTag("Warning")
	GenericDomValue<String> addWarning();


}
