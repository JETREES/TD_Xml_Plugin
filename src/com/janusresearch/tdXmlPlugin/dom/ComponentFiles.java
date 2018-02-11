// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:ComponentFilesType interface.
 */
public interface ComponentFiles extends DomElement {

	/**
	 * Returns the list of Component children.
	 * @return the list of Component children.
	 */
	@NotNull
	@SubTag("Component")
	java.util.List<GenericDomValue<String>> getComponents();
	/**
	 * Adds new child to the list of Component children.
	 * @return created child
	 */
	@SubTag("Component")
	GenericDomValue<String> addComponent();


}
