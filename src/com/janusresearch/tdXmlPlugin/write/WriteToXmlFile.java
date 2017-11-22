/*
* This class handles writing all necessary data to the xml file in the editor view
*/
package com.janusresearch.tdXmlPlugin.write;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.janusresearch.tdXmlPlugin.debug.Debug;
import com.janusresearch.tdXmlPlugin.xml.CommandMacros;
import com.janusresearch.tdXmlPlugin.xml.FrameSet;
import com.janusresearch.tdXmlPlugin.xml.StepTree;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("ConstantConditions")
public class WriteToXmlFile {
    public static void renumberLesson(Project project, StepTree stepTree, FrameSet frameSet, CommandMacros commandMacros) {

        //Start writing to the xml file
        WriteCommandAction.runWriteCommandAction(project, () -> {

            int i = 0;
            //replace the StepTree name and id attributes so they are in sequence starting with 01
            for (XmlAttribute[] x : stepTree.getNodeAttributes()) {
                x[0].setValue(stepTree.getNewNodeValues()[i][0]);
                x[1].setValue(stepTree.getNewNodeValues()[i][1]);
                x[2].setValue(stepTree.getNewNodeValues()[i][2]);
                i++;
            }

            i = 0;
            //replace each Frame id, node and weight attributes in sequence
            for (XmlAttribute[] x : frameSet.getFrameAttributes()) {
                x[0].setValue(frameSet.getNewFrameValues()[i][0]);
                x[1].setValue(frameSet.getNewFrameValues()[i][1]);
                x[2].setValue(frameSet.getNewFrameValues()[i][2]);
                i++;
            }

            i = 0;
            //replace each Play Event nextid attribute value
            for (XmlAttribute x : frameSet.getPlayEvents()) {
                x.setValue(frameSet.getNewPlayValues().get(i));
                i++;
            }

            i = 0;
            //replace each Back Event nextid attribute value
            for (XmlAttribute x : frameSet.getBackEvents()) {
                x.setValue(frameSet.getNewBackValues().get(i));
                i++;
            }

            i = 0;
            //replace each Other Event nextid attribute value
            for (XmlAttribute x : frameSet.getOtherEvents()) {
                x.setValue(frameSet.getNewOtherValues().get(i));
                i++;
            }

            i = 0;
            //replace FrameChange commands for CommandMacros
            if (commandMacros.getFrameChanges().size() != 0) {
                for (XmlAttribute f : commandMacros.getFrameChanges()) {
                    f.setValue(commandMacros.getNewFrameChangeValues().get(i));
                    i++;
                }
            }

            Pattern pattern = Pattern.compile("^(?:Frame|frame)?[_\\s]?([\\d]{1,2}[a-zA-Z]?)[_\\s]?.*$");
            for (XmlTag f : frameSet.getFrames()) {
                XmlTag[] events = frameSet.getFrameEvents(f);
                for (XmlTag e : events) {
                    XmlAttribute send = e.getAttribute("send");
                    if (send != null && send.getValue().startsWith("RUNMACRO")) {
                        String eventMacroName = send.getValue().substring(9);
                        if (eventMacroName.matches(String.valueOf(pattern))) {
                            send.setValue(send.getValue().replaceFirst("[\\d]{1,2}", f.getAttribute("id").getValue()));
                            for (XmlTag m : commandMacros.getMacros()) {
                                XmlAttribute commandMacro = m.getAttribute("name");
                                String commandMacroName = m.getAttribute("name").getValue();
                                if (Objects.equals(commandMacroName, eventMacroName)) {
                                    commandMacro.setValue(send.getValue().substring(9));
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
