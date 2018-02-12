// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:PopupType interface.
 */
public interface Popup extends DomElement {

	/**
	 * Returns the value of the x child.
	 * @return the value of the x child.
	 */
	@NotNull
	@Required
	GenericAttributeValue<Integer> getX();


	/**
	 * Returns the value of the y child.
	 * @return the value of the y child.
	 */
	@NotNull
	@Required
	GenericAttributeValue<Integer> getY();


	/**
	 * Returns the value of the w child.
	 * @return the value of the w child.
	 */
	@NotNull
	@Required
	GenericAttributeValue<Integer> getW();


	/**
	 * Returns the value of the h child.
	 * @return the value of the h child.
	 */
	@NotNull
	@Required
	GenericAttributeValue<Integer> getH();


	/**
	 * Returns the value of the Id child.
	 * @return the value of the Id child.
	 */
	@NotNull
	@Required
	GenericDomValue<String> getId();


	/**
	 * Returns the value of the Text child.
	 * @return the value of the Text child.
	 */
	@NotNull
	@Required
	GenericDomValue<String> getText();


	/**
	 * Returns the value of the Close child.
	 * @return the value of the Close child.
	 */
	@NotNull
	GenericDomValue<String> getClose();


	/**
	 * Returns the value of the Title child.
	 * @return the value of the Title child.
	 */
	@NotNull
	GenericDomValue<String> getTitle();


	/**
	 * Returns the value of the Drag child.
	 * @return the value of the Drag child.
	 */
	@NotNull
	GenericDomValue<Boolean> getDrag();


}
