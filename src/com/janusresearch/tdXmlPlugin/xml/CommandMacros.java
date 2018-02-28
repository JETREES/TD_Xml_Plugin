package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.util.xml.GenericAttributeValue;
import com.janusresearch.tdXmlPlugin.dom.module.Command;
import com.janusresearch.tdXmlPlugin.dom.module.Macro;
import com.janusresearch.tdXmlPlugin.dom.module.Module;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("ConstantConditions")
public class CommandMacros {
    private Module root;
    private List<GenericAttributeValue<String>> frameChangeCommands = new ArrayList<>();
    private List<String> newFrameChangeValues = new ArrayList<>();

    public CommandMacros(Module moduleRoot, String[][] oldFrameValues, String[][] newFrameValues) {
        this.root = moduleRoot;
        processMacros(oldFrameValues, newFrameValues);
    }

    /** Add reference to FrameChange command to the list*/
    private void addFrameChange(GenericAttributeValue<String> x) {
        frameChangeCommands.add(x);
    }

    /** Add the FrameChange new id to the list */
    private void addFrameChangeValue(String s) {
        newFrameChangeValues.add(s);
    }

    /** Determines if the Command in the Macro is a FrameChange command*/
    @Contract(pure = true)
    private boolean isFrameChange(GenericAttributeValue<String> s) {
        return s.getValue().startsWith("FrameChange");
    }

    /** Process Macros from CommandMacros to determine new FrameChange values */
    private void processMacros(String[][] oldFrameValues, String[][] newFrameValues) {
        for (Macro m : getRoot().getCommandMacros().getMacros()) {
            for (Command c : m.getCommands()) {
                GenericAttributeValue<String> send = c.getSend();
                if (isFrameChange(send)) {
                    addFrameChange(send);
                    String frameId;
                    if (send.getValue().contains("[") ) {
                        frameId = send.getValue().substring(13, (send.getValue().length() - 1));
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

    /** Returns the new FrameChange values list */
    public List<String> getNewFrameChangeValues() {
        return newFrameChangeValues;
    }

    /** Returns the FrameChange commands list */
    public List<GenericAttributeValue<String>> getFrameChanges() {
        return frameChangeCommands;
    }

    public Module getRoot() {
        return root;
    }
}