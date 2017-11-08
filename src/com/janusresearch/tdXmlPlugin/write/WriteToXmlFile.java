/*
* This class handles writing all necessary data to the xml file in the editor view
*/
package com.janusresearch.tdXmlPlugin.write;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlAttribute;
import com.janusresearch.tdXmlPlugin.debug.Debug;
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

            int i = 0;
            try {
                //replace the StepTree name and id attributes so they are in sequence starting with 01
                for (XmlAttribute[] x : stepTree.getNodeAttributes()) {
                    x[0].setValue(stepTree.getNewNodeValues()[i][0]);
                    x[1].setValue(stepTree.getNewNodeValues()[i][1]);
                    x[2].setValue(stepTree.getNewNodeValues()[i][2]);
                    i++;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                Debug.print("StepTree " + e.getMessage());
            }

            i = 0;
            try {
                //replace each Frame id, node and weight attributes in sequence
                for (XmlAttribute[] x : frameSet.getFrameAttributes()) {
                    x[0].setValue(frameSet.getNewFrameValues()[i][0]);
                    x[1].setValue(frameSet.getNewFrameValues()[i][1]);
                    x[2].setValue(frameSet.getNewFrameValues()[i][2]);
                    i++;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                Debug.print("Frame " + e.getMessage());
            }

            i = 0;
            try {
                //replace each Play Events nextid attribute value
                for (XmlAttribute p : Events.playEvents) {
                    p.setValue(Events.newPlayEventsValues.get(i));
                    i++;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                Debug.print("Play " + e.getMessage());
            }

            i = 0;
            try {
                //replace each Back Events nextid attribute value
                for (XmlAttribute b : Events.backEvents) {
                    b.setValue(Events.newBackEventsValues.get(i));
                    i++;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                Debug.print("Back " + e.getMessage());
            }

            i = 0;
            try {
                //replace each Other Events nextid attribute value
                for (XmlAttribute o : Events.otherEvents) {
                    o.setValue(Events.newOtherEventsValues.get(i));
                    i++;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                Debug.print("Other " + e.getMessage());
            }

            i = 0;
            try {
                if (CommandMacros.frameChangeCommands.size() != 0) {
                    //replace FrameChange commands for CommandMacros
                    for (XmlAttribute f : CommandMacros.frameChangeCommands) {
                        f.setValue(CommandMacros.newFrameChangeCommandsValues.get(i));
                        i++;
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                Debug.print("FrameChange" + e.getMessage());
            }
        });
    }
}
