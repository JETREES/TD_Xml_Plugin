// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

/**
 * null:typeNameAttrType enumeration.
 */
public enum TypeName implements com.intellij.util.xml.NamedEnum {
	OBJECT ("Object"),
	STRING ("String"),
	BOOLEAN ("boolean"),
	FLOAT ("float"),
	INT ("int"),
	LONG ("long");

	private final String value;
	private TypeName(String value) { this.value = value; }
	public String getValue() { return value; }

}
