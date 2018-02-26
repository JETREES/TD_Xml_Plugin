package com.janusresearch.tdXmlPlugin.xml.highlighting;

import com.intellij.util.xml.highlighting.BasicDomElementsInspection;
import org.jetbrains.annotations.NotNull;

public class testbasicdom extends BasicDomElementsInspection {
    public testbasicdom(@NotNull Class domClass, Class[] additionalClasses) {
        super(domClass, additionalClasses);
    }
}
