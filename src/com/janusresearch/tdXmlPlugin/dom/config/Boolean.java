// Generated on Tue Feb 27 23:45:49 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.config;

/**
 * null:BooleanType enumeration.
 */
public enum Boolean implements com.intellij.util.xml.NamedEnum {
	Boolean_0 ("0"),
	Boolean_1 ("1"),
	FALSE_0 ("FALSE"),
	FALSE_1 ("False"),
	FALSE_2 ("false"),
	TRUE_0 ("TRUE"),
	TRUE_1 ("True"),
	TRUE_2 ("true"),
	NO_0 ("NO"),
	NO_1 ("No"),
	NO_2 ("no"),
	YES_0 ("YES"),
	YES_1 ("Yes"),
	YES_2 ("yes");

	private final String value;
	Boolean(String value) { this.value = value; }
	public String getValue() { return value; }

}
