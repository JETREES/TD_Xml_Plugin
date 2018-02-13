// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * null:GlobalEventsType interface.
 */
public interface GlobalEvents extends DomElement {

	/**
	 * Returns the list of Event children.
	 * @return the list of Event children.
	 */
	@NotNull
	@SubTagList("Event")
	java.util.List<Event> getEvents();
	/**
	 * Adds new child to the list of Event children.
	 * @return created child
	 */
	@SubTagList("Event")
	Event addEvent();


}
