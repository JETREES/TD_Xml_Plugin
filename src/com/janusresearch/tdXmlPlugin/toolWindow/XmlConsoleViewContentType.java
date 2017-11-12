package com.janusresearch.tdXmlPlugin.toolWindow;

import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;

import java.awt.*;

class XmlConsoleViewContentType extends ConsoleViewContentType{
    private static final TextAttributes ELEMENT_OUTPUT_ATTR = new TextAttributes(XmlConsoleColors.ELEMENT, XmlConsoleColors.BACKING, null, null, Font.PLAIN);
    private static final TextAttributes TITLE_OUTPUT_ATTR = new TextAttributes(XmlConsoleColors.CHANGE, XmlConsoleColors.BACKING, XmlConsoleColors.CHANGE, EffectType.LINE_UNDERSCORE, Font.PLAIN);
    private static final TextAttributes VALUE_OUTPUT_ATTR = new TextAttributes(XmlConsoleColors.VALUE, XmlConsoleColors.BACKING, null, null, Font.PLAIN);
    private static final TextAttributes ATTRIBUTE_OUTPUT_ATTR = new TextAttributes(XmlConsoleColors.ATTRIBUTE, XmlConsoleColors.BACKING, null, null, Font.PLAIN);
    private static final TextAttributes CHANGE_OUTPUT_ATTR = new TextAttributes(XmlConsoleColors.CHANGE, XmlConsoleColors.BACKING, null, null, Font.PLAIN);
    static final XmlConsoleViewContentType ELEMENT_OUTPUT = new XmlConsoleViewContentType("ELEMENT_OUTPUT", ELEMENT_OUTPUT_ATTR);
    static final XmlConsoleViewContentType TITLE_OUTPUT = new XmlConsoleViewContentType("TITLE_OUTPUT", TITLE_OUTPUT_ATTR);
    static final XmlConsoleViewContentType VALUE_OUTPUT = new XmlConsoleViewContentType("VALUE_OUTPUT", VALUE_OUTPUT_ATTR);
    static final XmlConsoleViewContentType ATTRIBUTE_OUTPUT = new XmlConsoleViewContentType("ATTRIBUTE_OUTPUT", ATTRIBUTE_OUTPUT_ATTR);
    static final XmlConsoleViewContentType CHANGE_OUTPUT = new XmlConsoleViewContentType("CHANGE_OUTPUT", CHANGE_OUTPUT_ATTR);

    private XmlConsoleViewContentType(String name, TextAttributes textAttributes) {
        super(name, textAttributes);
    }
}
