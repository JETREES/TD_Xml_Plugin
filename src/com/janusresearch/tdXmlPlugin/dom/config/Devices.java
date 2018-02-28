// Generated on Tue Feb 27 23:45:49 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.config;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTag;
import com.intellij.util.xml.SubTagList;
import org.jetbrains.annotations.NotNull;

/**
 * null:DevicesType interface.
 */
public interface Devices extends DomElement {

	/**
	 * Returns the list of Device children.
	 * @return the list of Device children.
	 */
	@NotNull
	@SubTagList("Device")
	java.util.List<Device> getDevices();
	/**
	 * Adds new child to the list of Device children.
	 * @return created child
	 */
	@SubTag("Device")
	Device addDevice();


}
