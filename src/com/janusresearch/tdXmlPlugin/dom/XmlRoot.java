/*
* This interface is used to access the Dom
* and register the DomFileDescription
*/
package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericDomValue;

public interface XmlRoot extends DomElement{
    GenericDomValue<String> getApplication();
}
