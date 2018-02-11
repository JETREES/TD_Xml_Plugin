/*
 * This class is not currently used. It's was created in effort to implement
 * the DomFileDescription object and register it as an extension point since
 * the method registerFileDescription is deprecated. However, with no luck
 * with this route the current method used to get the plugin working is
 * the deprecated on mentioned here.
 */

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.DomFileDescription;

public class XmlCOLsDomFileDescription extends DomFileDescription<COLs> {
    private static final String ROOT_TAG = "COLs";

    public XmlCOLsDomFileDescription() {
        super(COLs.class, ROOT_TAG);

    }
}