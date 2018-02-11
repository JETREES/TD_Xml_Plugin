// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:PreloadCommandsType interface.
 */
public interface PreloadCommands extends DomElement {

	/**
	 * Returns the list of Preload children.
	 * @return the list of Preload children.
	 */
	@NotNull
	@SubTag("Preload")
	java.util.List<Preload> getPreloads();
	/**
	 * Adds new child to the list of Preload children.
	 * @return created child
	 */
	@SubTag("Preload")
	Preload addPreload();


}
