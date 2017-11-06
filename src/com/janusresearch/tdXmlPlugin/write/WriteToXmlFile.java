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
import com.janusresearch.tdXmlPlugin.xml.StepTree;

import java.util.Objects;

public class WriteToXmlFile {
    public static void writeFile(Project project, XmlAttribute[][] nodeAttributes, XmlAttribute[][] frameAttributes) {

        //Start writing to the xml file
        WriteCommandAction.runWriteCommandAction(project, () -> {

            //replace the StepTree name and id attributes so they are in sequence starting with 01
            int i = 0;
            Notifications.showInformationMessage("StepTree Modifications");
            for (XmlAttribute[] a : nodeAttributes) {
                String message = ("<node name=\"" + a[0].getValue() + "\" parent=\"" + a[1].getValue() + "\" id=\"" + a[2].getValue() + "\"/>  changed to  ");
                if (i < 9) {
                    a[0].setValue("0" + (i + 1));
                    a[2].setValue("0" + (i + 1));
                }
                else {
                    a[0].setValue(String.valueOf(i + 1));
                    a[2].setValue(String.valueOf(i + 1));
                }
                i++;

                //This handles indented sub steps in the Step Tree
                if (OptionsDialog.subStepsIndented) {
                    String parent = a[1].getValue();
                    if (!Objects.equals(parent, "0")) {
                        boolean foundMatch = false;
                        for (String[] n : StepTree.oldToNewNodeValues) {
                            if (Objects.equals(parent, n[0])) {
                                a[1].setValue(n[1]);
                                foundMatch = true;
                                break;
                            }
                        }
                        if (!foundMatch) {
                            a[1].setValue("0");
                        }
                    }
                }
                else {
                    a[1].setValue("0");
                }
                Notifications.showInformationMessage(message + "<node name=\"" + a[0].getValue() + "\" parent=\"" + a[1].getValue() + "\" id=\"" + a[2].getValue() + "\"/>");
            }

            //replace each Frame id, node and weight attributes in sequence
            i = 0;
            for (XmlAttribute[] a : frameAttributes) {
                if (i < 9) {
                    a[0].setValue("0" + (i + 1));
                    a[1].setValue("0" + (i + 1));
                    a[2].setValue("0" + (i + 1));
                }
                else {
                    a[0].setValue(String.valueOf(i + 1));
                    a[1].setValue(String.valueOf(i + 1));
                    a[2].setValue(String.valueOf(i + 1));
                }
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
