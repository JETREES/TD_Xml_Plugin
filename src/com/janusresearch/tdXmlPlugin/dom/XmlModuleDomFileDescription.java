/*
* This class is not currently used. It's was created in effort to implement
* the DomFileDescription object and register it as an extension point since
* the method registerFileDescription is deprecated. However, with no luck
* with this route the current method used to get the plugin working is
* the deprecated on mentioned here.
*/

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.DomFileDescription;
import com.intellij.util.xml.highlighting.DomElementsAnnotator;
import com.janusresearch.tdXmlPlugin.xml.highlighting.XmlAnnotator;
import org.jetbrains.annotations.Nullable;

public class XmlModuleDomFileDescription extends DomFileDescription<Module> {
    private static final String ROOT_TAG = "Module";

    public XmlModuleDomFileDescription() {
        super(Module.class, ROOT_TAG);

    }

    @Nullable
    @Override
    public DomElementsAnnotator createAnnotator() {
        return new XmlAnnotator();
    }
}