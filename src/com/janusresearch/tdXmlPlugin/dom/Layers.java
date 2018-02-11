// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:LayersType interface.
 */
public interface Layers extends DomElement {

	/**
	 * Returns the list of Layer children.
	 * @return the list of Layer children.
	 */
	@NotNull
    @SubTag("Layer")
	java.util.List<Layer> getLayers();
	/**
	 * Adds new child to the list of Layer children.
	 * @return created child
	 */
	@SubTag("Layer")
	Layer addLayer();


}
