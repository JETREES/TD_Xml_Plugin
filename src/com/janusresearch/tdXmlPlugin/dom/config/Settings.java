// Generated on Tue Feb 27 23:45:49 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.config;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:SettingsType interface.
 */
public interface Settings extends DomElement {

	/**
	 * Returns the value of the ShowStepTreeOnPracticeValidate child.
	 * @return the value of the ShowStepTreeOnPracticeValidate child.
	 */
	@NotNull
	@SubTag("ShowStepTreeOnPracticeValidate")
	GenericDomValue<com.janusresearch.tdXmlPlugin.dom.config.Boolean> getShowStepTreeOnPracticeValidate();


	/**
	 * Returns the value of the RandomColAtEnd child.
	 * @return the value of the RandomColAtEnd child.
	 */
	@NotNull
	@SubTag("RandomColAtEnd")
	GenericDomValue<com.janusresearch.tdXmlPlugin.dom.config.Boolean> getRandomColAtEnd();


	/**
	 * Returns the value of the HighlightHotspots child.
	 * @return the value of the HighlightHotspots child.
	 */
	@NotNull
	@SubTag("HighlightHotspots")
	GenericDomValue<com.janusresearch.tdXmlPlugin.dom.config.Boolean> getHighlightHotspots();


	/**
	 * Returns the value of the IntroMovies child.
	 * @return the value of the IntroMovies child.
	 */
	@NotNull
	@SubTag("IntroMovies")
	GenericDomValue<String> getIntroMovies();


	/**
	 * Returns the value of the DisableDefaultLessonReference child.
	 * @return the value of the DisableDefaultLessonReference child.
	 */
	@NotNull
	@SubTag("DisableDefaultLessonReference")
	GenericDomValue<com.janusresearch.tdXmlPlugin.dom.config.Boolean> getDisableDefaultLessonReference();


	/**
	 * Returns the value of the FamiliarizeString child.
	 * @return the value of the FamiliarizeString child.
	 */
	@NotNull
	@SubTag("FamiliarizeString")
	GenericDomValue<String> getFamiliarizeString();


	/**
	 * Returns the value of the AcquireString child.
	 * @return the value of the AcquireString child.
	 */
	@NotNull
	@SubTag("AcquireString")
	GenericDomValue<String> getAcquireString();


	/**
	 * Returns the value of the PracticeString child.
	 * @return the value of the PracticeString child.
	 */
	@NotNull
	@SubTag("PracticeString")
	GenericDomValue<String> getPracticeString();


	/**
	 * Returns the value of the ValidateString child.
	 * @return the value of the ValidateString child.
	 */
	@NotNull
	@SubTag("ValidateString")
	GenericDomValue<String> getValidateString();


	/**
	 * Returns the value of the HintString child.
	 * @return the value of the HintString child.
	 */
	@NotNull
	@SubTag("HintString")
	GenericDomValue<String> getHintString();


}
