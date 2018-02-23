/*
* This class handles writing all necessary data to the xml file in the editor view
*/
package com.janusresearch.tdXmlPlugin.write;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.util.xml.GenericAttributeValue;
import com.janusresearch.tdXmlPlugin.dom.Event;
import com.janusresearch.tdXmlPlugin.dom.Frame;
import com.janusresearch.tdXmlPlugin.dom.Macro;
import com.janusresearch.tdXmlPlugin.xml.CommandMacros;
import com.janusresearch.tdXmlPlugin.xml.FrameSet;
import com.janusresearch.tdXmlPlugin.xml.StepTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@SuppressWarnings("ConstantConditions")
public class WriteToXmlFile {
    public static void renumberLesson(Project project, StepTree stepTree, FrameSet frameSet, CommandMacros commandMacros) {

        //Start writing to the xml file
        WriteCommandAction.runWriteCommandAction(project, () -> {

            int i = 0;
            //replace the StepTree name and id attributes so they are in sequence starting with 01
            for (GenericAttributeValue[] a : stepTree.getNodeAttributes()) {
                a[0].setStringValue(stepTree.getNewNodeValues()[i][0]);
                a[1].setStringValue(stepTree.getNewNodeValues()[i][1]);
                a[2].setStringValue(stepTree.getNewNodeValues()[i][2]);
                i++;
            }

            i = 0;
            //replace each Frame id, node and weight attributes in sequence
            for (GenericAttributeValue[] a : frameSet.getFrameAttributes()) {
                a[0].setStringValue(frameSet.getNewFrameValues()[i][0]);
                a[1].setStringValue(frameSet.getNewFrameValues()[i][1]);
                a[2].setStringValue(frameSet.getNewFrameValues()[i][2]);
                i++;
            }

            i = 0;
            //replace each Play Event nextid attribute value
            for (GenericAttributeValue<String> a : frameSet.getPlayEvents()) {
                a.setStringValue(frameSet.getNewPlayValues().get(i));
                i++;
            }

            i = 0;
            //replace each Back Event nextid attribute value
            for (GenericAttributeValue<String> a : frameSet.getBackEvents()) {
                a.setStringValue(frameSet.getNewBackValues().get(i));
                i++;
            }

            i = 0;
            //replace each Other Event nextid attribute value
            for (GenericAttributeValue<String> a : frameSet.getOtherEvents()) {
                a.setStringValue(frameSet.getNewOtherValues().get(i));
                i++;
            }

            i = 0;
            //replace FrameChange commands for CommandMacros
            if (commandMacros.getFrameChanges().size() != 0) {
                for (GenericAttributeValue<String> a : commandMacros.getFrameChanges()) {
                    a.setValue(commandMacros.getNewFrameChangeValues().get(i));
                    i++;
                }
            }

            //replace numbers in macros that match the regex pattern
            //this should theoretically keep macro names that are numbered with
            //frame numbers aligned when a lesson is renumbered
            Pattern pattern = Pattern.compile("^(?:Frame|frame)?[_\\s]?([\\d]{1,2}[a-zA-Z]?)[_\\s]?.*$");
            List<List<String>> macrosToChange = new ArrayList<>();
            List<String> currentMacro;
            String eventMacroName;
            GenericAttributeValue<String> send;
            for (Frame f : frameSet.getRoot().getFrameSet().getFrames()) {
                String currentFrame = f.getId().getValue();
                for (Event e : f.getEvents().getEvents()) {
                    if (e.getSend().getValue() != null) {
                        send = e.getSend();
                        if (send.getRawText().startsWith("RUNMACRO")) {
                            eventMacroName = send.getValue().substring(9);
                            if (eventMacroName.matches(String.valueOf(pattern))) {
                                send.setValue(send.getValue().replaceFirst("[\\d]{1,2}", currentFrame));
                                currentMacro = new ArrayList<>();
                                currentMacro.add(0, eventMacroName);
                                currentMacro.add(1, currentFrame);
                                if (!macrosToChange.contains(currentMacro)) {
                                    macrosToChange.add(currentMacro);
                                }
                            }
                        }
                    }
                }
            }
            for (Macro m : commandMacros.getRoot().getCommandMacros().getMacros()) {
                GenericAttributeValue<String> commandMacro = m.getName();
                String commandMacroName = m.getName().getValue();
                if (commandMacroName.matches(String.valueOf(pattern))) {
                    for (List<String> mc : macrosToChange) {
                        if (Objects.equals(commandMacroName, mc.get(0))) {
                            commandMacro.setValue(commandMacro.getValue().replaceFirst("[\\d]{1,2}", mc.get(1)));
                            break;
                        }
                    }
                }
            }
        });
    }
}
