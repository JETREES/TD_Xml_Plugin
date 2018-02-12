// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

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
	java.util.List<GenericDomValue<String>> getPages();
	/**
	 * Adds new child to the list of Page children.
	 * @return created child
	 */
	GenericDomValue<String> addPage();


	/**
	 * Returns the list of Insert children.
	 * @return the list of Insert children.
	 */
	@NotNull
	java.util.List<GenericDomValue<String>> getInserts();
	/**
	 * Adds new child to the list of Insert children.
	 * @return created child
	 */
	GenericDomValue<String> addInsert();


	/**
	 * Returns the list of Question children.
	 * @return the list of Question children.
	 */
	@NotNull
	java.util.List<MultipleChoiceQuestion> getQuestions();
	/**
	 * Adds new child to the list of Question children.
	 * @return created child
	 */
	MultipleChoiceQuestion addQuestion();


	/**
	 * Returns the list of COL children.
	 * @return the list of COL children.
	 */
	@NotNull
	java.util.List<COL> getCOLs();
	/**
	 * Adds new child to the list of COL children.
	 * @return created child
	 */
	COL addCOL();


}
