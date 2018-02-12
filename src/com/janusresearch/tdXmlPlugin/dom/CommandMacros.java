// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.SubTag;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.xml.DomElement;

/**
 * null:CommandMacrosType interface.
 */
public interface CommandMacros extends DomElement {

	/**
	 * Returns the list of Macro children.
	 * @return the list of Macro children.
	 */
	@NotNull
	@SubTag("Macro")
	java.util.List<Macro> getMacros();
	/**
	 * Adds new child to the list of Macro children.
	 * @return created child
	 */
	@SubTag("Macro")
	Macro addMacro();


}