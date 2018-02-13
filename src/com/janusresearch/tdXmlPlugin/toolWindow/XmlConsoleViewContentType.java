package com.janusresearch.tdXmlPlugin.toolWindow;

import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;

import java.awt.*;

public class XmlConsoleViewContentType extends ConsoleViewContentType{
    private static final TextAttributes ELEMENT_OUTPUT_ATTR = new TextAttributes(XmlColors.console.ELEMENT, XmlColors.console.BACKING, null, null, Font.PLAIN);
    private static final TextAttributes TITLE_OUTPUT_UNDERLINE_ATTR = new TextAttributes(XmlColors.console.CHANGE, XmlColors.console.BACKING, XmlColors.console.CHANGE, EffectType.LINE_UNDERSCORE, Font.PLAIN);
    private static final TextAttributes TITLE_OUTPUT_ATTR = new TextAttributes(XmlColors.console.CHANGE, XmlColors.console.BACKING, XmlColors.console.CHANGE, null, Font.PLAIN);
    private static final TextAttributes VALUE_OUTPUT_ATTR = new TextAttributes(XmlColors.console.VALUE, XmlColors.console.BACKING, null, null, Font.PLAIN);
    private static final TextAttributes ATTRIBUTE_OUTPUT_ATTR = new TextAttributes(XmlColors.console.ATTRIBUTE, XmlColors.console.BACKING, null, null, Font.PLAIN);
    private static final TextAttributes CHANGE_OUTPUT_ATTR = new TextAttributes(XmlColors.console.CHANGE, XmlColors.console.BACKING, null, null, Font.PLAIN);
    private static final TextAttributes ERROR_OUTPUT_ATTR = new TextAttributes(XmlColors.console.ERROR, XmlColors.console.BACKING, null, null, Font.PLAIN);
    private static final TextAttributes MESSAGE_OUTPUT_ATTR = new TextAttributes(XmlColors.console.MESSAGE, XmlColors.console.BACKING, null, null, Font.PLAIN);

    public static final XmlConsoleViewContentType ELEMENT_OUTPUT = new XmlConsoleViewContentType("ELEMENT_OUTPUT", ELEMENT_OUTPUT_ATTR);
    public static final XmlConsoleViewContentType TITLE_OUTPUT_UNDERLINE = new XmlConsoleViewContentType("TITLE_OUTPUT_UNDERLINE", TITLE_OUTPUT_UNDERLINE_ATTR);
    public static final XmlConsoleViewContentType TITLE_OUTPUT = new XmlConsoleViewContentType("TITLE_OUTPUT", TITLE_OUTPUT_ATTR);
    public static final XmlConsoleViewContentType VALUE_OUTPUT = new XmlConsoleViewContentType("VALUE_OUTPUT", VALUE_OUTPUT_ATTR);
    public static final XmlConsoleViewContentType ATTRIBUTE_OUTPUT = new XmlConsoleViewContentType("ATTRIBUTE_OUTPUT", ATTRIBUTE_OUTPUT_ATTR);
    public static final XmlConsoleViewContentType CHANGE_OUTPUT = new XmlConsoleViewContentType("CHANGE_OUTPUT", CHANGE_OUTPUT_ATTR);
    public static final XmlConsoleViewContentType ERROR_OUTPUT = new XmlConsoleViewContentType("ERROR_OUTPUT", ERROR_OUTPUT_ATTR);
    public static final XmlConsoleViewContentType MESSAGE_OUTPUT = new XmlConsoleViewContentType("MESSAGE_OUTPUT", MESSAGE_OUTPUT_ATTR);

    private XmlConsoleViewContentType(String name, TextAttributes textAttributes) {
        super(name, textAttributes);
    }
}
