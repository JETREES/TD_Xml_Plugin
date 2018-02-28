// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.module;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:AvatarsType interface.
 */
public interface Avatars extends DomElement {

	/**
	 * Returns the value of the Welcome child.
	 * @return the value of the Welcome child.
	 */
	@NotNull
	@SubTag("Welcome")
	GenericDomValue<String> getWelcome();


	/**
	 * Returns the value of the Task child.
	 * @return the value of the Task child.
	 */
	@NotNull
	@SubTag("Task")
	GenericDomValue<String> getTask();


	/**
	 * Returns the value of the PerformanceMeasures child.
	 * @return the value of the PerformanceMeasures child.
	 */
	@NotNull
	@SubTag("PerformanceMeasures")
	GenericDomValue<String> getPerformanceMeasures();


	/**
	 * Returns the value of the Warnings child.
	 * @return the value of the Warnings child.
	 */
	@NotNull
	@SubTag("Warnings")
	GenericDomValue<String> getWarnings();


	/**
	 * Returns the value of the TMReferences child.
	 * @return the value of the TMReferences child.
	 */
	@NotNull
	@SubTag("TMReferences")
	GenericDomValue<String> getTMReferences();


	/**
	 * Returns the value of the TimeWarning child.
	 * @return the value of the TimeWarning child.
	 */
	@NotNull
	@SubTag("TimeWarning")
	GenericDomValue<String> getTimeWarning();


}
