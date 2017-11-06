package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Commands {
    private static List<XmlAttribute> runMacroCommands = new ArrayList<>();
    private static List<String> newRunMacroCommandsValues = new ArrayList<>();

    public Commands(XmlTag[] frames) {
        //array to store the first nine frames id number
        String[] zeroFrames = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09"};

        for (XmlTag f : frames) {
            //Get the Commands XmlTag
            XmlTag commandsTag = f.findFirstSubTag("Commands");

            //Get all sub tags from Commands
            XmlTag[] commands = new XmlTag[0];
            if (commandsTag != null) {
                commands = commandsTag.findSubTags("Command");
            }

            for (XmlTag c : commands) {
                XmlAttribute send = c.getAttribute("send");
                if (send != null) {
                    String value = send.getValue();
                    if (!Objects.equals(value, "")) {
                        if (value != null && value.startsWith("RunMacro")) {
                            //TODO finish code for find Run Macro commands within a Frames Commands tree

                        }
                    }
                }
            }
        }

    }
}