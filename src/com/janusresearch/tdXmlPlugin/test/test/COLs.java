// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:COLsType interface.
 */
public interface COLs extends DomElement {

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
