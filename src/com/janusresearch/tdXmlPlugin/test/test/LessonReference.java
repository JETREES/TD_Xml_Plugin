// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

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
	java.util.List<LessonReferenceFile> getFiles();
	/**
	 * Adds new child to the list of File children.
	 * @return created child
	 */
	LessonReferenceFile addFile();


}
