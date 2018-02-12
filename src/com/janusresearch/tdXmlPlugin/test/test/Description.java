// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

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
	GenericDomValue<String> getTitle();


	/**
	 * Returns the value of the Objective child.
	 * @return the value of the Objective child.
	 */
	@NotNull
	GenericDomValue<String> getObjective();


	/**
	 * Returns the value of the Action child.
	 * @return the value of the Action child.
	 */
	@NotNull
	FAPV getAction();


	/**
	 * Returns the value of the Avatars child.
	 * @return the value of the Avatars child.
	 */
	@NotNull
	Avatars getAvatars();


	/**
	 * Returns the value of the Condition child.
	 * @return the value of the Condition child.
	 */
	@NotNull
	FAPV getCondition();


	/**
	 * Returns the value of the Standard child.
	 * @return the value of the Standard child.
	 */
	@NotNull
	FAPV getStandard();


	/**
	 * Returns the value of the PerformanceMeasures child.
	 * @return the value of the PerformanceMeasures child.
	 */
	@NotNull
	PerformanceMeasures getPerformanceMeasures();


	/**
	 * Returns the value of the Warnings child.
	 * @return the value of the Warnings child.
	 */
	@NotNull
	Warnings getWarnings();


	/**
	 * Returns the value of the Cautions child.
	 * @return the value of the Cautions child.
	 */
	@NotNull
	Cautions getCautions();


	/**
	 * Returns the value of the TMReferences child.
	 * @return the value of the TMReferences child.
	 */
	@NotNull
	TMReferences getTMReferences();


}
