package com.janusresearch.tdXmlPlugin.toolWindow;

import com.intellij.ui.JBColor;

class XmlColors extends JBColor {

    static class console {
        static final XmlColors ELEMENT = new XmlColors(128, 15253354);
        final static XmlColors VALUE = new XmlColors(32768, 6981465);
        final static XmlColors ATTRIBUTE = new XmlColors(255, 12237498);
        final static XmlColors BACKING = new XmlColors(15724527, 2829099);
        final static XmlColors CHANGE = new XmlColors(14227737, 14454550);
        final static XmlColors ERROR = new XmlColors(16739176, 16739176);
        final static XmlColors MESSAGE = new XmlColors(12303291, 12303291);
    }

    static class icon {
        final static XmlColors BORDER = new XmlColors(12895428, 8028292);
        final static XmlColors BACKGROUND = new XmlColors(12895428, 5462106);
    }
    private XmlColors(int rgb, int darkRGB) {
        super(rgb, darkRGB);
    }
}
