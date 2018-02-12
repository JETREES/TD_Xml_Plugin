// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:ComponentFilesType interface.
 */
public interface ComponentFiles extends DomElement {

	/**
	 * Returns the list of Component children.
	 * @return the list of Component children.
	 */
	@NotNull
	java.util.List<GenericDomValue<String>> getComponents();
	/**
	 * Adds new child to the list of Component children.
	 * @return created child
	 */
	GenericDomValue<String> addComponent();


}
