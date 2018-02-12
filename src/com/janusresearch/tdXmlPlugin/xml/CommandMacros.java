package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.janusresearch.tdXmlPlugin.dom.Module;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("ConstantConditions")
public class CommandMacros {
    private Module root;
    private XmlTag[] macros;
    private List<XmlAttribute> frameChangeCommands = new ArrayList<>();
    private List<String> newFrameChangeValues = new ArrayList<>();

    public CommandMacros(Module moduleRoot) {
        root = moduleRoot;
    }

    /** Store every Macro sub tag from CommandMacros in the macros array */
    private void storeMacros() {
        macros = root.getXmlTag().findFirstSubTag("CommandMacros").findSubTags("Macro");
    }

    /** Add reference to FrameChange command to the list */
    private void addFrameChange(XmlAttribute x) {
        frameChangeCommands.add(x);
    }

    /** Add the FrameChange new id to the list */
    private void addFrameChangeValue(String s) {
        newFrameChangeValues.add(s);
    }

    /** Determines if the Command in the Macro is a FrameChange command */
    private boolean isFrameChange(XmlAttribute x) {
        return x.getValue().startsWith("FrameChange");
    }

    /** Process Macros from CommandMacros to determine new FrameChange values */
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

    /** Returns the macros array */
    @Contract(pure = true)
    public XmlTag[] getMacros() {
        return macros;
    }

    /** Returns an array of Commands from within a Macro */
    @NotNull
    private XmlTag[] getMacroCommands(XmlTag x) {
        return x.findSubTags("Command");
    }

    /** Returns the length of the Commands value */
    private int getLength(XmlAttribute x) {
        return x.getValue().length();
    }

    /** Returns the new FrameChange values list */
    public List<String> getNewFrameChangeValues() {
        return newFrameChangeValues;
    }

    /** Returns the FrameChange commands list */
    public List<XmlAttribute> getFrameChanges() {
        return frameChangeCommands;
    }
}