/*
* This interface is used to access the Dom
* and register the DomFileDescription
*/
package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.DomElement;

public interface XmlRoot extends DomElement {
    GenericDomValue<String> getApplication();
}
