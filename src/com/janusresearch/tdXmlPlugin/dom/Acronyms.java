package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTag;
import org.jetbrains.annotations.NotNull;

/**
 * null:AcronymsType interface.
 */
public interface Acronyms extends DomElement {

    /**
     * Returns the list of Acronym children.
     * @return the list of Acronym children.
     */
    @NotNull
    @SubTag("acronym")
    java.util.List<Acronym> getAcronyms();
    /**
     * Adds new child to the list of Acronym children.
     * @return created child
     */
    @SubTag("acronym")
    Acronym addAcronym();
}
