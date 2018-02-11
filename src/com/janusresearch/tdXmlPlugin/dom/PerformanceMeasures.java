// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

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
	@SubTag("Measure")
	java.util.List<GenericDomValue<String>> getMeasures();
	/**
	 * Adds new child to the list of Measure children.
	 * @return created child
	 */
	@SubTag("Measure")
	GenericDomValue<String> addMeasure();


}
