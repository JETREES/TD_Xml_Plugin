// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

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
	GenericDomValue<String> getWelcome();


	/**
	 * Returns the value of the Task child.
	 * @return the value of the Task child.
	 */
	@NotNull
	GenericDomValue<String> getTask();


	/**
	 * Returns the value of the PerformanceMeasures child.
	 * @return the value of the PerformanceMeasures child.
	 */
	@NotNull
	GenericDomValue<String> getPerformanceMeasures();


	/**
	 * Returns the value of the Warnings child.
	 * @return the value of the Warnings child.
	 */
	@NotNull
	GenericDomValue<String> getWarnings();


	/**
	 * Returns the value of the TMReferences child.
	 * @return the value of the TMReferences child.
	 */
	@NotNull
	GenericDomValue<String> getTMReferences();


	/**
	 * Returns the value of the TimeWarning child.
	 * @return the value of the TimeWarning child.
	 */
	@NotNull
	GenericDomValue<String> getTimeWarning();


}
