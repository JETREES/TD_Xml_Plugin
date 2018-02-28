// Generated on Tue Feb 27 23:45:49 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.config;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:ConfigurationType interface.
 */
public interface Configuration extends DomElement {

	/**
	 * Returns the value of the VEEVersion child.
	 * @return the value of the VEEVersion child.
	 */
	@NotNull
	@SubTag("VEEVersion")
	GenericDomValue<String> getVEEVersion();


	/**
	 * Returns the value of the Settings child.
	 * @return the value of the Settings child.
	 */
	@NotNull
	@SubTag("Settings")
	Settings getSettings();


	/**
	 * Returns the value of the Shelter child.
	 * @return the value of the Shelter child.
	 */
	@NotNull
	@SubTag("Shelter")
	Shelter getShelter();


	/**
	 * Returns the value of the Course child.
	 * @return the value of the Course child.
	 */
	@NotNull
	@SubTag("Course")
	Course getCourse();


}
