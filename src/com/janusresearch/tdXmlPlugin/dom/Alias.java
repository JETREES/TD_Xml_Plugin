// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:Alias interface.
 */
public interface Alias extends DomElement {

	/**
	 * Returns the value of the typeName child.
	 * @return the value of the typeName child.
	 */
	@NotNull
	@Attribute("typeName")
	GenericAttributeValue<TypeName> getTypeName();


	/**
	 * Returns the value of the name child.
	 * @return the value of the name child.
	 */
	@NotNull
	@Required
	@SubTag("name")
	GenericDomValue<String> getName();


	/**
	 * Returns the value of the description child.
	 * @return the value of the description child.
	 */
	@NotNull
	@SubTag("description")
	GenericDomValue<String> getDescription();


	/**
	 * Returns the value of the comment child.
	 * @return the value of the comment child.
	 */
	@NotNull
	@SubTag("comment")
	GenericDomValue<String> getComment();


	/**
	 * Returns the value of the transient child.
	 * @return the value of the transient child.
	 */
	@NotNull
	@SubTag("transient")
	GenericDomValue<Boolean> getTransient();


	/**
	 * Returns the value of the source child.
	 * @return the value of the source child.
	 */
	@NotNull
	@SubTag("source")
	GenericDomValue<Boolean> getSource();


	/**
	 * Returns the value of the target child.
	 * @return the value of the target child.
	 */
	@NotNull
	@SubTag("target")
	GenericDomValue<Boolean> getTarget();


	/**
	 * Returns the value of the aliasPropertyPath child.
	 * @return the value of the aliasPropertyPath child.
	 */
	@NotNull
	@Required
	@SubTag("aliasPropertyPath")
	GenericDomValue<String> getAliasPropertyPath();


	/**
	 * Returns the value of the assetId child.
	 * @return the value of the assetId child.
	 */
	@NotNull
	@Attribute("assetId")
	GenericAttributeValue<String> getAssetId();


	/**
	 * Returns the value of the alias child.
	 * @return the value of the alias child.
	 */
	@NotNull
	@Attribute("alias")
	GenericAttributeValue<String> getAlias();


}
