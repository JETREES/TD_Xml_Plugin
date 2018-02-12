// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

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
	java.util.List<GenericDomValue<String>> getCautions();
	/**
	 * Adds new child to the list of Caution children.
	 * @return created child
	 */
	GenericDomValue<String> addCaution();


}
