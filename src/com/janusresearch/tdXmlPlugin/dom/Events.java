// Generated on Fri Feb 09 21:22:29 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.SubTag;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.xml.DomElement;

/**
 * null:EventsType interface.
 */
public interface Events extends DomElement {

	/**
	 * Returns the list of Event children.
	 * @return the list of Event children.
	 */
	@NotNull
	@SubTag("Event")
	java.util.List<Event> getEvents();
	/**
	 * Adds new child to the list of Event children.
	 * @return created child
	 */
	@SubTag("Event")
	Event addEvent();
}