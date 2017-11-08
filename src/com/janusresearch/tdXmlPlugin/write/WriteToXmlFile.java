/*
* This class handles writing all necessary data to the xml file in the editor view
*/
package com.janusresearch.tdXmlPlugin.write;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlAttribute;
import com.janusresearch.tdXmlPlugin.dialog.OptionsDialog;
import com.janusresearch.tdXmlPlugin.notification.Notifications;
import com.janusresearch.tdXmlPlugin.xml.CommandMacros;
import com.janusresearch.tdXmlPlugin.xml.Events;
import com.janusresearch.tdXmlPlugin.xml.FrameSet;
import com.janusresearch.tdXmlPlugin.xml.StepTree;

import java.util.Objects;

public class WriteToXmlFile {
    public static void writeFile(Project project, StepTree stepTree, FrameSet frameSet) {

        //Start writing to the xml file
        WriteCommandAction.runWriteCommandAction(project, () -> {

            //replace the StepTree name and id attributes so they are in sequence starting with 01
            int i = 0;
            for (XmlAttribute[] x : stepTree.getNodeAttributes()) {
                x[0].setValue(stepTree.getNewNodeValues()[i][0]);
                x[1].setValue(stepTree.getNewNodeValues()[i][1]);
                x[2].setValue(stepTree.getNewNodeValues()[i][2]);
                i++;
            }

            //replace each Frame id, node and weight attributes in sequence
            i = 0;
            for (XmlAttribute[] x : frameSet.getFrameAttributes()) {
                x[0].setValue(frameSet.getNewFrameValues()[i][0]);
                x[1].setValue(frameSet.getNewFrameValues()[i][1]);
                x[2].setValue(frameSet.getNewFrameValues()[i][2]);
                i++;
            }

            //replace each Play Events nextid attribute value
            i = 0;
            for (XmlAttribute p : Events.playEvents) {
                p.setValue(Events.newPlayEventsValues.get(i));
                i++;
            }

            //replace each Back Events nextid attribute value
            i = 0;
            for (XmlAttribute b : Events.backEvents) {
                b.setValue(Events.newBackEventsValues.get(i));
                i++;
            }

            //replace each Other Events nextid attribute value
            i = 0;
            for (XmlAttribute o : Events.otherEvents) {
                o.setValue(Events.newOtherEventsValues.get(i));
                i++;
            }

            //replace FrameChange commands for CommandMacros
            i = 0;
            for (XmlAttribute f : CommandMacros.frameChangeCommands) {
                f.setValue(CommandMacros.newFrameChangeCommandsValues.get(i));
                i++;
            }
        });
    }
}
