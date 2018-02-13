package com.janusresearch.tdXmlPlugin;

import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomManager;
import com.janusresearch.tdXmlPlugin.dialog.SubStepsDialog;
import com.janusresearch.tdXmlPlugin.dom.Module;
import com.janusresearch.tdXmlPlugin.notification.Notifications;
import com.janusresearch.tdXmlPlugin.toolWindow.XmlConsoleViewContentType;
import com.janusresearch.tdXmlPlugin.toolWindow.XmlToolWindow;
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

            //Get the Module File Element
            Module moduleRoot = manager.getFileElement(xmlFile, Module.class).getRootElement();

            if (Objects.equals(moduleRoot.getXmlElementName(), "Module")) {
                //Process the Step Tree
                StepTree stepTree = new StepTree(moduleRoot);
                stepTree.storeNodeAttributes();
                stepTree.storeOldNodeValues();
                stepTree.storeNewNodeValues();

                //Process the FrameSet
                FrameSet frameSet = new FrameSet(project, moduleRoot);
                frameSet.storeFrameAttributes();
                frameSet.storeOldFrameValues();
                frameSet.storeNewFrameValues();
                frameSet.processEvents();

                //Process CommandMacros to store FrameChange commands and their new values
                CommandMacros commandMacros = new CommandMacros(moduleRoot);
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
                printRenumberingModifications(psiFile.getName(), stepTree, frameSet);
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

    private void printRenumberingModifications(String fileName, StepTree stepTree, FrameSet frameSet) {
        //Print lesson number to console
        XmlToolWindow.getConsole().print("Renumber Lesson:", XmlConsoleViewContentType.TITLE_OUTPUT);
        XmlToolWindow.getConsole().print(" " + fileName + "\n", XmlConsoleViewContentType.ELEMENT_OUTPUT);

        //Print StepTree Modifications to console
        XmlToolWindow.getConsole().print("Step Tree Modifications\n", XmlConsoleViewContentType.TITLE_OUTPUT);
        int i;
        for (i = 0; i < stepTree.getNodeCount(); i++) {
            int spaces = 14 - stepTree.getOldNodeValues()[i][0].length() - stepTree.getOldNodeValues()[i][1].length() - stepTree.getOldNodeValues()[i][2].length();
            XmlToolWindow.getConsole().print("<node ", XmlConsoleViewContentType.ELEMENT_OUTPUT);
            XmlToolWindow.getConsole().print("name=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            XmlToolWindow.getConsole().print("\"" + stepTree.getOldNodeValues()[i][0] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            XmlToolWindow.getConsole().print("parent=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            XmlToolWindow.getConsole().print("\"" + stepTree.getOldNodeValues()[i][1] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            XmlToolWindow.getConsole().print("id=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            XmlToolWindow.getConsole().print("\"" + stepTree.getOldNodeValues()[i][2] + "\"", XmlConsoleViewContentType.VALUE_OUTPUT);
            XmlToolWindow.getConsole().print("/>  ", XmlConsoleViewContentType.ELEMENT_OUTPUT);
            for (int j = 0; j < spaces; j++) {
                XmlToolWindow.getConsole().print(" ", XmlConsoleViewContentType.CHANGE_OUTPUT);
            }
            XmlToolWindow.getConsole().print("-->  ", XmlConsoleViewContentType.CHANGE_OUTPUT);
            XmlToolWindow.getConsole().print("<node ", XmlConsoleViewContentType.ELEMENT_OUTPUT);
            XmlToolWindow.getConsole().print("name=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            XmlToolWindow.getConsole().print("\"" + stepTree.getNewNodeValues()[i][0] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            XmlToolWindow.getConsole().print("parent=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            XmlToolWindow.getConsole().print("\"" + stepTree.getNewNodeValues()[i][1] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            XmlToolWindow.getConsole().print("id=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            XmlToolWindow.getConsole().print("\"" + stepTree.getNewNodeValues()[i][2] + "\"", XmlConsoleViewContentType.VALUE_OUTPUT);
            XmlToolWindow.getConsole().print("/>\n", XmlConsoleViewContentType.ELEMENT_OUTPUT);
        }
        XmlToolWindow.getConsole().print("\n", ConsoleViewContentType.NORMAL_OUTPUT);

        //Print FrameSet Modifications to console
        XmlToolWindow.getConsole().print("Frame Set Modifications\n", XmlConsoleViewContentType.TITLE_OUTPUT);
        for (i = 0; i < frameSet.getFrameCount(); i++) {
            int spaces = 13 - frameSet.getOldFrameValues()[i][0].length() - frameSet.getOldFrameValues()[i][1].length() - frameSet.getOldFrameValues()[i][2].length();
            XmlToolWindow.getConsole().print("<Frame ", XmlConsoleViewContentType.ELEMENT_OUTPUT);
            XmlToolWindow.getConsole().print("id=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            XmlToolWindow.getConsole().print("\"" + frameSet.getOldFrameValues()[i][0] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            XmlToolWindow.getConsole().print("node=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            XmlToolWindow.getConsole().print("\"" + frameSet.getOldFrameValues()[i][1] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            XmlToolWindow.getConsole().print("weight=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            XmlToolWindow.getConsole().print("\"" + frameSet.getOldFrameValues()[i][2] + "\"", XmlConsoleViewContentType.VALUE_OUTPUT);
            XmlToolWindow.getConsole().print("/>  ", XmlConsoleViewContentType.ELEMENT_OUTPUT);
            for (int j = 0; j < spaces; j++) {
                XmlToolWindow.getConsole().print(" ", XmlConsoleViewContentType.CHANGE_OUTPUT);
            }
            XmlToolWindow.getConsole().print("-->  ", XmlConsoleViewContentType.CHANGE_OUTPUT);
            XmlToolWindow.getConsole().print("<Frame ", XmlConsoleViewContentType.ELEMENT_OUTPUT);
            XmlToolWindow.getConsole().print("id=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            XmlToolWindow.getConsole().print("\"" + frameSet.getNewFrameValues()[i][0] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            XmlToolWindow.getConsole().print("node=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            XmlToolWindow.getConsole().print("\"" + frameSet.getNewFrameValues()[i][1] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            XmlToolWindow.getConsole().print("weight=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            XmlToolWindow.getConsole().print("\"" + frameSet.getNewFrameValues()[i][2] + "\"", XmlConsoleViewContentType.VALUE_OUTPUT);
            XmlToolWindow.getConsole().print("/>\n", XmlConsoleViewContentType.ELEMENT_OUTPUT);
        }
        XmlToolWindow.getConsole().print("\n", ConsoleViewContentType.NORMAL_OUTPUT);
        XmlToolWindow.showToolWindow();
    }
}
