// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:DescriptionType interface.
 */
public interface Description extends DomElement {

	/**
	 * Returns the value of the Title child.
	 * @return the value of the Title child.
	 */
	@NotNull
	@SubTag("Title")
	GenericDomValue<String> getTitle();


	/**
	 * Returns the value of the Objective child.
	 * @return the value of the Objective child.
	 */
	@NotNull
	@SubTag("Objective")
	GenericDomValue<String> getObjective();


	/**
	 * Returns the value of the Action child.
	 * @return the value of the Action child.
	 */
	@NotNull
	@SubTag("Action")
	FAPV getAction();


	/**
	 * Returns the value of the Avatars child.
	 * @return the value of the Avatars child.
	 */
	@NotNull
	@SubTag("Avatars")
	Avatars getAvatars();


	/**
	 * Returns the value of the Condition child.
	 * @return the value of the Condition child.
	 */
	@NotNull
	@SubTag("Condition")
	FAPV getCondition();


	/**
	 * Returns the value of the Standard child.
	 * @return the value of the Standard child.
	 */
	@NotNull
	@SubTag("Standard")
	FAPV getStandard();


	/**
	 * Returns the value of the PerformanceMeasures child.
	 * @return the value of the PerformanceMeasures child.
	 */
	@NotNull
	@SubTag("PerformanceMeasures")
	PerformanceMeasures getPerformanceMeasures();


	/**
	 * Returns the value of the Warnings child.
	 * @return the value of the Warnings child.
	 */
	@NotNull
	@SubTag("Warnings")
	Warnings getWarnings();


	/**
	 * Returns the value of the Cautions child.
	 * @return the value of the Cautions child.
	 */
	@NotNull
	@SubTag("Cautions")
	Cautions getCautions();


	/**
	 * Returns the value of the TMReferences child.
	 * @return the value of the TMReferences child.
	 */
	@NotNull
	@SubTag("TMReferences")
	TMReferences getTMReferences();


}
