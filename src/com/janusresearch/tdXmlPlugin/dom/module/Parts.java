// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.module;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTag;
import org.jetbrains.annotations.NotNull;

/**
 * null:PartsType interface.
 */
public interface Parts extends DomElement {

	/**
	 * Returns the list of Part children.
	 * @return the list of Part children.
	 */
	@NotNull
	@SubTag("Part")
	java.util.List<Part> getParts();
	/**
	 * Adds new child to the list of Part children.
	 * @return created child
	 */
	@SubTag("Part")
	Part addPart();


}
