// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:PagesType interface.
 */
public interface Pages extends DomElement {

	/**
	 * Returns the list of Page children.
	 * @return the list of Page children.
	 */
	@NotNull
	@SubTag("Page")
	java.util.List<GenericDomValue<String>> getPages();
	/**
	 * Adds new child to the list of Page children.
	 * @return created child
	 */
	@SubTag("Page")
	GenericDomValue<String> addPage();


	/**
	 * Returns the list of Insert children.
	 * @return the list of Insert children.
	 */
	@NotNull
	@SubTag("Insert")
	java.util.List<GenericDomValue<String>> getInserts();
	/**
	 * Adds new child to the list of Insert children.
	 * @return created child
	 */
	@SubTag("Insert")
	GenericDomValue<String> addInsert();
}
