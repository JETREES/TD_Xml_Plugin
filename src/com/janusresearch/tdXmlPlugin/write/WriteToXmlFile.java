/*
* This class handles writing all necessary data to the xml file in the editor view
*/
package com.janusresearch.tdXmlPlugin.write;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlAttribute;
import com.janusresearch.tdXmlPlugin.xml.CommandMacros;
import com.janusresearch.tdXmlPlugin.xml.FrameSet;
import com.janusresearch.tdXmlPlugin.xml.StepTree;

public class WriteToXmlFile {
    public static void writeFile(Project project, StepTree stepTree, FrameSet frameSet, CommandMacros commandMacros) {

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
        });
    }
}
