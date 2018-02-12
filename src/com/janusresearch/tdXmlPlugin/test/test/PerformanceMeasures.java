// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:PerformanceMeasuresType interface.
 */
public interface PerformanceMeasures extends DomElement {

	/**
	 * Returns the list of Measure children.
	 * @return the list of Measure children.
	 */
	@NotNull
	java.util.List<GenericDomValue<String>> getMeasures();
	/**
	 * Adds new child to the list of Measure children.
	 * @return created child
	 */
	GenericDomValue<String> addMeasure();


}
