package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.janusresearch.tdXmlPlugin.dom.XmlRoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("ConstantConditions")
public class CommandMacros {
    private XmlRoot root;
    private XmlTag[] macros;
    private List<XmlAttribute> frameChangeCommands = new ArrayList<>();
    private List<String> newFrameChangeValues = new ArrayList<>();

    public CommandMacros(XmlRoot xmlRoot) {
        root = xmlRoot;
    }

    private void storeMacros() {
        macros = root.getXmlTag().findFirstSubTag("CommandMacros").findSubTags("Macro");
    }

    private void addFrameChange(XmlAttribute x) {
        frameChangeCommands.add(x);
    }

    private void addFrameChangeValue(String s) {
        newFrameChangeValues.add(s);
    }

    private boolean isFrameChange(XmlAttribute x) {
        return x.getValue().startsWith("FrameChange");
    }

    public void processMacros(String[][] oldFrameValues, String[][] newFrameValues) {
        storeMacros();
        for (XmlTag m : getMacros()) {
            for (XmlTag c : getMacroCommands(m)) {
                XmlAttribute send = c.getAttribute("send");
                if (isFrameChange(send)) {
                    addFrameChange(send);
                    String frameId;
                    if (send.getValue().contains("[") ) {
                        frameId = send.getValue().substring(13, (getLength(send) - 1));
                    }
                    else {
                        frameId = send.getValue().substring(12);
                    }

                    int i = 0;
                    for (String[] s : oldFrameValues) {
                        if (Objects.equals(s[0], frameId)) {
                            addFrameChangeValue("FrameChange [" + newFrameValues[i][0] + "]");
                            break;
                        }
                        i++;
                    }
                }
            }
        }
    }

    private XmlTag[] getMacros() {
        return macros;
    }

    private XmlTag[] getMacroCommands(XmlTag x) {
        return x.findSubTags("Command");
    }

    private int getLength(XmlAttribute x) {
        return x.getValue().length();
    }

    public List<String> getNewFrameChangeValues() {
        return newFrameChangeValues;
    }

    public List<XmlAttribute> getFrameChanges() {
        return frameChangeCommands;
    }
}