// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:PreloadCommandsType interface.
 */
public interface PreloadCommands extends DomElement {

	/**
	 * Returns the list of Preload children.
	 * @return the list of Preload children.
	 */
	@NotNull
	java.util.List<Preload> getPreloads();
	/**
	 * Adds new child to the list of Preload children.
	 * @return created child
	 */
	Preload addPreload();


}
