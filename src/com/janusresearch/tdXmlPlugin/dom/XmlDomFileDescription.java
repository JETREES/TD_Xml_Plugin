/*
* This class is not currently used. It's was created in effort to implement
* the DomFileDescription object and register it as an extension point since
* the method registerFileDescription is deprecated. However, with no luck
* with this route the current method used to get the plugin working is
* the deprecated on mentioned here.
*/

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.DomFileDescription;

public class XmlDomFileDescription extends DomFileDescription<XmlRoot> {
    private static final String ROOT_TAG_NAME = "Module";

    public XmlDomFileDescription() {
        super(XmlRoot.class, ROOT_TAG_NAME);

    }
}