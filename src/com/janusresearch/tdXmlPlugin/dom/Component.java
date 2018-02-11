// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:ComponentType interface.
 */
public interface Component extends DomElement {

	/**
	 * Returns the value of the name child.
	 * @return the value of the name child.
	 */
	@NotNull
	@Attribute("name")
	GenericAttributeValue<String> getName();


	/**
	 * Returns the value of the version child.
	 * @return the value of the version child.
	 */
	@NotNull
	@Attribute("version")
	GenericAttributeValue<Integer> getVersion();


	/**
	 * Returns the value of the BaseCamera child.
	 * @return the value of the BaseCamera child.
	 */
	@NotNull
	@SubTag("BaseCamera")
	GenericDomValue<String> getBaseCamera();


	/**
	 * Returns the value of the Parts child.
	 * @return the value of the Parts child.
	 */
	@NotNull
	@SubTag("Parts")
	Parts getParts();


}
