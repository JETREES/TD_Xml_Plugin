// Generated on Mon Feb 12 09:46:36 EST 2018
// DTD/Schema  :    null

package test;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:EventsType interface.
 */
public interface Events extends DomElement {

	/**
	 * Returns the list of Event children.
	 * @return the list of Event children.
	 */
	@NotNull
	java.util.List<Event> getEvents();
	/**
	 * Adds new child to the list of Event children.
	 * @return created child
	 */
	Event addEvent();


}
