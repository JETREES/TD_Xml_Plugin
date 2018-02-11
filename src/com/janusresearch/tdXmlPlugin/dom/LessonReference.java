// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:LessonReferenceType interface.
 */
public interface LessonReference extends DomElement {

	/**
	 * Returns the list of File children.
	 * @return the list of File children.
	 */
	@NotNull
	@SubTag("File")
	java.util.List<LessonReferenceFile> getFiles();
	/**
	 * Adds new child to the list of File children.
	 * @return created child
	 */
	@SubTag("File")
	LessonReferenceFile addFile();


}
