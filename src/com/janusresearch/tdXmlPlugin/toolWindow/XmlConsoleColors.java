package com.janusresearch.tdXmlPlugin.toolWindow;

import com.intellij.ui.JBColor;

class XmlConsoleColors extends JBColor {
    final static XmlConsoleColors ELEMENT = new XmlConsoleColors(128, 15253354);
    final static XmlConsoleColors VALUE = new XmlConsoleColors(32768, 6981465);
    final static XmlConsoleColors ATTRIBUTE = new XmlConsoleColors(255, 12237498);
    final static XmlConsoleColors BACKING = new XmlConsoleColors(15724527, 2829099);
    final static XmlConsoleColors CHANGE = new XmlConsoleColors(14227737, 14454550);
    final static XmlConsoleColors BORDER = new XmlConsoleColors(12895428, 8028292);
    final static XmlConsoleColors BACKGROUND = new XmlConsoleColors(12895428, 5462106);

    private XmlConsoleColors(int rgb, int darkRGB) {
        super(rgb, darkRGB);
    }
}
