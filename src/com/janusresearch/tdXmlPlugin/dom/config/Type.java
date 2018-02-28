// Generated on Tue Feb 27 23:45:49 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.config;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:TypeType interface.
 */
public interface Type extends DomElement {

	/**
	 * Returns the value of the name child.
	 * @return the value of the name child.
	 */
	@NotNull
    @Attribute("name")
	GenericAttributeValue<String> getName();


	/**
	 * Returns the value of the Description child.
	 * @return the value of the Description child.
	 */
	@NotNull
    @SubTag("Description")
	GenericDomValue<String> getDescription();


	/**
	 * Returns the value of the Preview child.
	 * @return the value of the Preview child.
	 */
	@NotNull
    @SubTag("Preview")
	GenericDomValue<String> getPreview();


	/**
	 * Returns the value of the Positions child.
	 * @return the value of the Positions child.
	 */
	@NotNull
    @SubTag("Positions")
	GenericDomValue<String> getPositions();


	/**
	 * Returns the value of the Groups child.
	 * @return the value of the Groups child.
	 */
	@NotNull
    @SubTag("Groups")
	Groups getGroups();


}
