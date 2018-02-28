// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.module;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:FAPVType interface.
 */
public interface FAPV extends DomElement {

	/**
	 * Returns the value of the Default child.
	 * @return the value of the Default child.
	 */
	@NotNull
	@SubTag("Default")
	GenericDomValue<String> getDefault();


	/**
	 * Returns the value of the Familiarize child.
	 * @return the value of the Familiarize child.
	 */
	@NotNull
	@SubTag("Familiarize")
	GenericDomValue<String> getFamiliarize();


	/**
	 * Returns the value of the Acquire child.
	 * @return the value of the Acquire child.
	 */
	@NotNull
	@SubTag("Acquire")
	GenericDomValue<String> getAcquire();


	/**
	 * Returns the value of the Practice child.
	 * @return the value of the Practice child.
	 */
	@NotNull
	@SubTag("Practice")
	GenericDomValue<String> getPractice();


	/**
	 * Returns the value of the Validate child.
	 * @return the value of the Validate child.
	 */
	@NotNull
	@SubTag("Validate")
	GenericDomValue<String> getValidate();


}
