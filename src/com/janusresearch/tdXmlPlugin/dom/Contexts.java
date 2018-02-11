// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:ContextsType interface.
 */
public interface Contexts extends DomElement {

	/**
	 * Returns the list of Context children.
	 * @return the list of Context children.
	 */
	@NotNull
    @SubTag("Contexts")
	java.util.List<ResourceContext> getContexts();
	/**
	 * Adds new child to the list of Context children.
	 * @return created child
	 */
	@SubTag("Contexts")
	ResourceContext addContext();


}
