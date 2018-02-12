// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

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
	java.util.List<GenericDomValue<String>> getReferences();
	/**
	 * Adds new child to the list of Reference children.
	 * @return created child
	 */
	GenericDomValue<String> addReference();


}
