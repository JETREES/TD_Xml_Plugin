// Generated on Tue Feb 27 23:45:49 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.config;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:ModuleType interface.
 */
public interface Module extends DomElement {

	/**
	 * Returns the value of the number child.
	 * @return the value of the number child.
	 */
	@NotNull
    @Attribute("number")
	GenericAttributeValue<String> getNumber();


	/**
	 * Returns the value of the fapv child.
	 * @return the value of the fapv child.
	 */
	@NotNull
    @Attribute("fapv")
	GenericAttributeValue<FAPV> getFapv();


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


}
