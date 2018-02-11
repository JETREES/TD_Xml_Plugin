// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:ResourcesType interface.
 */
public interface Resources extends DomElement {

	/**
	 * Returns the value of the Layers child.
	 * @return the value of the Layers child.
	 */
	@NotNull
	@SubTag("Layers")
	Layers getLayers();


	/**
	 * Returns the value of the Contexts child.
	 * @return the value of the Contexts child.
	 */
	@NotNull
	@SubTag("Contexts")
	Contexts getContexts();


}
