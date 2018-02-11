// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

/**
 * null:LayerLoadType enumeration.
 */
public enum LayerLoad implements com.intellij.util.xml.NamedEnum {
	BUILD ("Build"),
	BUNDLE ("Bundle"),
	PREFAB ("Prefab");

	private final String value;
	private LayerLoad(String value) { this.value = value; }
	public String getValue() { return value; }

}
