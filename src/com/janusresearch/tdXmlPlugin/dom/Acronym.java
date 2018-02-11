package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import org.jetbrains.annotations.NotNull;

public interface Acronym extends DomElement {

    /**
     * Returns the value of the name child.
     * @return the value of the name child.
     */
    @NotNull
    @Attribute("name")
    GenericAttributeValue<String> getName();


    /**
     * Returns the value of the pronunciation child.
     * @return the value of the pronunciation child.
     */
    @NotNull
    @Attribute("pronunciation")
    GenericAttributeValue<String> getPronunciation();
}
