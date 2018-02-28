// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.module;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

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


	/**
	 * Returns the list of Question children.
	 * @return the list of Question children.
	 */
	@NotNull
	@SubTag("Question")
	java.util.List<MultipleChoiceQuestion> getQuestions();
	/**
	 * Adds new child to the list of Question children.
	 * @return created child
	 */
	@SubTag("Question")
	MultipleChoiceQuestion addQuestion();


	/**
	 * Returns the list of COL children.
	 * @return the list of COL children.
	 */
	@NotNull
	@SubTag("COL")
	java.util.List<COL> getCOLs();
	/**
	 * Adds new child to the list of COL children.
	 * @return created child
	 */
	@SubTag("COL")
	COL addCOL();
}
