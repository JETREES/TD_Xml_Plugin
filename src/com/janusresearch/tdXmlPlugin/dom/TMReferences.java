// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:TMReferencesType interface.
 */
public interface TMReferences extends DomElement {

	/**
	 * Returns the list of Reference children.
	 * @return the list of Reference children.
	 */
	@NotNull
	@SubTag("Reference")
	java.util.List<GenericDomValue<String>> getReferences();
	/**
	 * Adds new child to the list of Reference children.
	 * @return created child
	 */
	@SubTag("Reference")
	GenericDomValue<String> addReference();


}
