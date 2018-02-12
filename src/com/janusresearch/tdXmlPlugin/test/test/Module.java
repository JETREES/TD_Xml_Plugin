// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:ModuleType interface.
 */
public interface Module extends DomElement {

	/**
	 * Returns the value of the title child.
	 * @return the value of the title child.
	 */
	@NotNull
	GenericAttributeValue<String> getTitle();


	/**
	 * Returns the value of the timed child.
	 * @return the value of the timed child.
	 */
	@NotNull
	GenericAttributeValue<Boolean> getTimed();


	/**
	 * Returns the value of the estimatedtime child.
	 * @return the value of the estimatedtime child.
	 */
	@NotNull
	GenericAttributeValue<String> getEstimatedtime();


	/**
	 * Returns the value of the type child.
	 * @return the value of the type child.
	 */
	@NotNull
	GenericAttributeValue<String> getType();


	/**
	 * Returns the value of the view child.
	 * @return the value of the view child.
	 */
	@NotNull
	GenericAttributeValue<String> getView();


	/**
	 * Returns the value of the movie child.
	 * @return the value of the movie child.
	 */
	@NotNull
	GenericAttributeValue<String> getMovie();


	/**
	 * Returns the value of the lines child.
	 * @return the value of the lines child.
	 */
	@NotNull
	GenericAttributeValue<Integer> getLines();


	/**
	 * Returns the value of the version child.
	 * @return the value of the version child.
	 */
	@NotNull
	GenericAttributeValue<Integer> getVersion();


	/**
	 * Returns the value of the widescreen child.
	 * @return the value of the widescreen child.
	 */
	@NotNull
	GenericAttributeValue<String> getWidescreen();


	/**
	 * Returns the value of the w3d child.
	 * @return the value of the w3d child.
	 */
	@NotNull
	GenericAttributeValue<String> getW3d();


	/**
	 * Returns the value of the mode child.
	 * @return the value of the mode child.
	 */
	@NotNull
	GenericAttributeValue<String> getMode();


	/**
	 * Returns the value of the Description child.
	 * @return the value of the Description child.
	 */
	@NotNull
	Description getDescription();


	/**
	 * Returns the value of the PreloadCommands child.
	 * @return the value of the PreloadCommands child.
	 */
	@NotNull
	PreloadCommands getPreloadCommands();


	/**
	 * Returns the value of the ExternalCOL child.
	 * @return the value of the ExternalCOL child.
	 */
	@NotNull
	ExternalCOL getExternalCOL();


	/**
	 * Returns the value of the ExternalPopup child.
	 * @return the value of the ExternalPopup child.
	 */
	@NotNull
	ExternalPopup getExternalPopup();


	/**
	 * Returns the value of the LessonReference child.
	 * @return the value of the LessonReference child.
	 */
	@NotNull
	LessonReference getLessonReference();


	/**
	 * Returns the value of the BaseCamera child.
	 * @return the value of the BaseCamera child.
	 */
	@NotNull
	GenericDomValue<String> getBaseCamera();


	/**
	 * Returns the value of the ObjectTree child.
	 * @return the value of the ObjectTree child.
	 */
	@NotNull
	ObjectTree getObjectTree();


	/**
	 * Returns the value of the ComponentFiles child.
	 * @return the value of the ComponentFiles child.
	 */
	@NotNull
	ComponentFiles getComponentFiles();


	/**
	 * Returns the value of the StepTree child.
	 * @return the value of the StepTree child.
	 */
	@NotNull
	StepTree getStepTree();


	/**
	 * Returns the value of the GlobalEvents child.
	 * @return the value of the GlobalEvents child.
	 */
	@NotNull
	GlobalEvents getGlobalEvents();


	/**
	 * Returns the value of the CommandMacros child.
	 * @return the value of the CommandMacros child.
	 */
	@NotNull
	CommandMacros getCommandMacros();


	/**
	 * Returns the value of the Goals child.
	 * @return the value of the Goals child.
	 */
	@NotNull
	Goals getGoals();


	/**
	 * Returns the value of the CustomNavViews child.
	 * @return the value of the CustomNavViews child.
	 */
	@NotNull
	CustomNavViews getCustomNavViews();


	/**
	 * Returns the value of the FrameSetOrPages child.
	 * @return the value of the FrameSetOrPages child.
	 */
	@NotNull
	@Required
	GenericDomValue<String> getFrameSetOrPages();


}
