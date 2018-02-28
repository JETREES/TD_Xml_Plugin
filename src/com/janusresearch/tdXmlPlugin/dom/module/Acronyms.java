package com.janusresearch.tdXmlPlugin.dom.module;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;
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
    @SubTagList("acronym")
    java.util.List<Acronym> getAcronyms();
    /**
     * Adds new child to the list of Acronym children.
     * @return created child
     */
    @SubTagList("acronym")
    Acronym addAcronym();
}
