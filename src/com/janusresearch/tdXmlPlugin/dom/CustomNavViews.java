// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

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
	@SubTag("NavView")
	java.util.List<NavView> getNavViews();
	/**
	 * Adds new child to the list of NavView children.
	 * @return created child
	 */
	@SubTag("NavView")
	NavView addNavView();


}
