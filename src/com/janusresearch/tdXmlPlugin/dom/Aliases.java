// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:AliasesType interface.
 */
public interface Aliases extends DomElement {

	/**
	 * Returns the list of Alias children.
	 * @return the list of Alias children.
	 */
	@NotNull
    @SubTag("Alias")
	java.util.List<Alias> getAliases();
	/**
	 * Adds new child to the list of Alias children.
	 * @return created child
	 */
	@SubTag("Alias")
	Alias addAlias();


}
