// Generated on Tue Feb 27 23:45:49 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.config;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:DeviceType interface.
 */
public interface Device extends DomElement {

	/**
	 * Returns the value of the name child.
	 * @return the value of the name child.
	 */
	@NotNull
	@Attribute("name")
	GenericAttributeValue<String> getName();


	/**
	 * Returns the value of the lessonScene child.
	 * @return the value of the lessonScene child.
	 */
	@NotNull
	@Attribute("lessonScene")
	GenericAttributeValue<String> getLessonScene();


	/**
	 * Returns the list of Module children.
	 * @return the list of Module children.
	 */
	@NotNull
	@SubTagList("Module")
	java.util.List<Module> getModules();
	/**
	 * Adds new child to the list of Module children.
	 * @return created child
	 */
	@SubTag("Module")
	Module addModule();


}
