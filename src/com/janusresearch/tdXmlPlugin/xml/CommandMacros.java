package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.janusresearch.tdXmlPlugin.dom.XmlRoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandMacros {
    public static List<XmlAttribute> frameChangeCommands = new ArrayList<>();
    public static List<String> newFrameChangeCommandsValues = new ArrayList<>();

    @SuppressWarnings("ConstantConditions")
    public CommandMacros(XmlRoot xmlRoot, String[][] oldToNewFrameValues) {
        //Get the CommandMacros XmlTag
        XmlTag commandMacros = xmlRoot.getXmlTag().findFirstSubTag("CommandMacros");

        //Get all sub tags from CommandMacros
        XmlTag[] macros = commandMacros.findSubTags("Macro");

        for (XmlTag m : macros) {
            //Get all sub tags from Macros
            XmlTag[] commands = m.findSubTags("Command");

            for (XmlTag c : commands) {

                //Get the current Command send attribute
                XmlAttribute send = c.getAttribute("send");

                //if the send attribute starts with FrameChange
                if (send.getValue().startsWith("FrameChange")) {

                    //Add the current Command send attribute to the List
                    frameChangeCommands.add(send);

                    //Get the length of the FrameChange command
                    //And create String variable to hold send value
                    int length = send.getValue().length();
                    String value;

                    //If the value contains an open bracket
                    if (send.getValue().contains("[") ) {
                        value = send.getValue().substring(12, (length - 1));
                    }
                    else {
                        value = send.getValue().substring(12);
                    }

                    for (String[] s : oldToNewFrameValues) {
                        //If the FrameChange ID equals an old Frame ID
                        //Then set it to the new Frame ID
                        if (Objects.equals(s[0], value)) {
                            newFrameChangeCommandsValues.add("FrameChange [" + s[1] + "]");
                            break;
                        }
                    }
                }
            }
        }
    }
}