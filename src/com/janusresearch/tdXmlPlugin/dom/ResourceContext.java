// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:ResourceContextType interface.
 */
public interface ResourceContext extends DomElement {

	/**
	 * Returns the value of the id child.
	 * @return the value of the id child.
	 */
	@NotNull
    @Attribute("id")
	GenericAttributeValue<String> getId();


	/**
	 * Returns the value of the Aliases child.
	 * @return the value of the Aliases child.
	 */
	@NotNull
    @SubTag("Alias")
	Aliases getAliases();


}
