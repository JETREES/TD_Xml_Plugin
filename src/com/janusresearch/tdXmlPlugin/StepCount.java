package com.janusresearch.tdXmlPlugin;

import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomManager;
import com.janusresearch.tdXmlPlugin.dom.Module;
import com.janusresearch.tdXmlPlugin.toolWindow.XmlConsoleViewContentType;
import com.janusresearch.tdXmlPlugin.toolWindow.XmlToolWindow;
import com.janusresearch.tdXmlPlugin.xml.FrameSet;

import java.util.Objects;

public class StepCount extends AnAction {

    @Override
    @SuppressWarnings("ConstantConditions")
    public void actionPerformed(AnActionEvent e) {
        //Get all the required data from data keys
        Project project = e.getData(PlatformDataKeys.PROJECT);

        //get the current editor as a Xml File
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        XmlFile xmlFile = (XmlFile) psiFile;

        //Create DomManager, FileDescription and register the description
        DomManager manager = DomManager.getDomManager(project);

        //Get the Module File Element
        Module moduleRoot = manager.getFileElement(xmlFile, Module.class).getRootElement();

        if (Objects.equals(moduleRoot.getXmlElementName(), "Module")) {
            FrameSet frameSet = new FrameSet(project, moduleRoot);
            printStepCount(psiFile.getName(), frameSet.getStepCount());
        }
    }

    @Override
    public void update(AnActionEvent e) {
//        final Project project = e.getData(CommonDataKeys.PROJECT);
//        final Editor editor = e.getData(CommonDataKeys.EDITOR);
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

    private void printStepCount(String fileName, int stepCount) {
        //Print lesson step count to console
        XmlToolWindow.getXmlConsole().print("Lesson Number:", XmlConsoleViewContentType.TITLE_OUTPUT_UNDERLINE);
        XmlToolWindow.getXmlConsole().print(" " + fileName + "\n", XmlConsoleViewContentType.ELEMENT_OUTPUT);
        XmlToolWindow.getXmlConsole().print("Step Count=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
        XmlToolWindow.getXmlConsole().print("\"" + String.valueOf(stepCount) + "\"", XmlConsoleViewContentType.VALUE_OUTPUT);
        XmlToolWindow.getXmlConsole().print("\n\n", ConsoleViewContentType.NORMAL_OUTPUT);
        XmlToolWindow.showToolWindow();
    }
}
