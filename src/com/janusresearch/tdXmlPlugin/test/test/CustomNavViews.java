// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:CustomNavViewsType interface.
 */
public interface CustomNavViews extends DomElement {

	/**
	 * Returns the list of NavView children.
	 * @return the list of NavView children.
	 */
	@NotNull
	java.util.List<NavView> getNavViews();
	/**
	 * Adds new child to the list of NavView children.
	 * @return created child
	 */
	NavView addNavView();


}
