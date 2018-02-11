// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:CautionsType interface.
 */
public interface Cautions extends DomElement {

	/**
	 * Returns the list of Caution children.
	 * @return the list of Caution children.
	 */
	@NotNull
	@SubTag("Caution")
	java.util.List<GenericDomValue<String>> getCautions();
	/**
	 * Adds new child to the list of Caution children.
	 * @return created child
	 */
	@SubTag("Caution")
	GenericDomValue<String> addCaution();


}
