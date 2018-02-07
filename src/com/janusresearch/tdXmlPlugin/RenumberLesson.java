package com.janusresearch.tdXmlPlugin;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomManager;
import com.janusresearch.tdXmlPlugin.debug.Debug;
import com.janusresearch.tdXmlPlugin.dialog.SubStepsDialog;
import com.janusresearch.tdXmlPlugin.dom.XmlRoot;
import com.janusresearch.tdXmlPlugin.notification.Notifications;
import com.janusresearch.tdXmlPlugin.toolWindow.XmlConsole;
import com.janusresearch.tdXmlPlugin.write.WriteToXmlFile;
import com.janusresearch.tdXmlPlugin.xml.CommandMacros;
import com.janusresearch.tdXmlPlugin.xml.FrameSet;
import com.janusresearch.tdXmlPlugin.xml.StepTree;

import java.util.Objects;

public class RenumberLesson extends AnAction {



    @Override
    @SuppressWarnings("ConstantConditions")
    public void actionPerformed(AnActionEvent e) {
        //Get all the required data from data keys
        Project project = e.getData(PlatformDataKeys.PROJECT);

        SubStepsDialog dialog = new SubStepsDialog();
        dialog.show();

        if (dialog.isOK()) {
            dialog.setSubSteps(true);
        }
        else {
            dialog.setSubSteps(false);
        }
            //get the current editor as a Xml File
            PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
            XmlFile xmlFile = (XmlFile) psiFile;

            //Create DomManager, FileDescription and register the description
            DomManager manager = DomManager.getDomManager(project);

            //Get the XmlRoot File Element
            XmlRoot xmlRoot = manager.getFileElement(xmlFile, XmlRoot.class).getRootElement();

            if (Objects.equals(xmlRoot.getXmlElementName(), "Module")) {
                //Process the Step Tree
                StepTree stepTree = new StepTree(xmlRoot);
                stepTree.storeNodeAttributes();
                stepTree.storeOldNodeValues();
                stepTree.storeNewNodeValues();

                //Process the FrameSet
                FrameSet frameSet = new FrameSet(project, xmlRoot);
                frameSet.storeFrameAttributes();
                frameSet.storeOldFrameValues();
                frameSet.storeNewFrameValues();
                frameSet.processEvents();

                //Process CommandMacros to store FrameChange commands and their new values
                CommandMacros commandMacros = new CommandMacros(xmlRoot);
                commandMacros.processMacros(frameSet.getOldFrameValues(), frameSet.getNewFrameValues());

                //write all the stored data from arrays to the xml file
                WriteToXmlFile.renumberLesson(project, stepTree, frameSet, commandMacros);

                //Determine if the Last StepTree node id is equal to the Last Frame node id then show a notification when it doesn't
                int lastNode = Integer.parseInt(stepTree.getNodeAttributes()[stepTree.getNodeCount() - 1][2].getValue());
                int lastFrame = Integer.parseInt(frameSet.getFrameAttributes()[frameSet.getFrameCount() - 1][1].getValue());
                if (lastNode < lastFrame) {
                    Notifications.showWarningMessage("Check Xml - There are not enough StepTree nodes.");
                }
                else if (lastNode > lastFrame) {
                    Notifications.showWarningMessage("Check Xml - There are too many StepTree nodes");
                }

                //Print the results in the Xml Console
                XmlConsole.printLessonModifications(psiFile, stepTree, frameSet);
            }
    }

    @Override
    public void update(AnActionEvent e) {
        final Project project = e.getData(CommonDataKeys.PROJECT);
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        PsiFile data = e.getData(LangDataKeys.PSI_FILE);
        boolean isXmlFile = false;
        boolean nameMatchesSchema = false;

        if (data != null) {
            //Determine if file is xml type and name matches our naming schema
            isXmlFile = data.getFileType().getName().equalsIgnoreCase("xml");
            nameMatchesSchema = data.getName().matches("[A-Z]{2}[0-9][A-Z][0-9]{4}[a-zA-Z]?\\.xml");
        }

        //Set visibility of plugin button
        if (isXmlFile && nameMatchesSchema) {
            e.getPresentation().setEnabled(true);
        } else
            e.getPresentation().setEnabled(false);
    }
}
