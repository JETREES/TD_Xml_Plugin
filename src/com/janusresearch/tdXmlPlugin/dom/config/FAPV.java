// Generated on Tue Feb 27 23:45:49 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.config;

/**
 * null:FAPVType enumeration.
 */
public enum FAPV implements com.intellij.util.xml.NamedEnum {
	FAPV_F ("f"),
	FAPV_A ("a"),
	FAPV_P ("p"),
	FAPV_V ("v"),
	AP ("ap"),
	PFP ("pfp");

	private final String value;
	FAPV(String value) { this.value = value; }
	public String getValue() { return value; }

}
