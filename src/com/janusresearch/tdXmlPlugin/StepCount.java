package com.janusresearch.tdXmlPlugin;

import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomManager;
import com.janusresearch.tdXmlPlugin.dom.module.Frame;
import com.janusresearch.tdXmlPlugin.dom.module.Module;
import com.janusresearch.tdXmlPlugin.toolWindow.XmlConsoleViewContentType;
import com.janusresearch.tdXmlPlugin.toolWindow.XmlToolWindow;

import java.util.Objects;

public class StepCount extends AnAction {

    private int actualStepCount;
    private Project project;
    private Module moduleRoot;

    @Override
    @SuppressWarnings("ConstantConditions")
    public void actionPerformed(AnActionEvent e) {
        //Get all the required data from data keys
        this.project = e.getData(PlatformDataKeys.PROJECT);

        //get the current editor as a Xml File
        XmlFile xmlFile = (XmlFile) e.getData(LangDataKeys.PSI_FILE);

        //Create DomManager, FileDescription and register the description
        DomManager manager = DomManager.getDomManager(project);

        //Get the Module File Element
        this.moduleRoot = manager.getFileElement(xmlFile, Module.class).getRootElement();

        if (Objects.equals(this.moduleRoot.getXmlElementName(), "Module")) {
            printStepCount(xmlFile.getName(), getStepCount());
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

    /** Returns the step count from a lesson. If the lesson contains step attributes in each Frame it will use
     * those values for the count.  If they do not have the steps attribute or if the steps attribute is an empty
     * String or equals zero then attribute is added/updated with a value that is an approximate value
     * determined through processing the Text2 block
     */
    private int getStepCount() {
        actualStepCount = 0;
        WriteCommandAction.runWriteCommandAction(project, () -> {
            for (Frame f : moduleRoot.getFrameSet().getFrames()) {
                if (!hasSteps(f) || (hasSteps(f) && (isStepsEmpty(f) || isStepsZero(f)))) {
                    int stepCount = parseTextForCount(f.getText2().getValue());
                    f.getSteps().setValue(String.valueOf(stepCount));
                }
                actualStepCount += Integer.parseInt(f.getSteps().getRawText());
            }
        });
        return actualStepCount;
    }

    /** Parses the Text2 text value to attempt to determine the number of steps in a given Frame*/
    private int parseTextForCount(String s) {
        //parse String s to determine an approximate count for the Frame
        //for Play Frames set steps="1"

        return 1;
    }


    /** Returns boolean true if the Frame has a steps attribute otherwise false*/
    private boolean hasSteps(Frame f) {
        return f.getSteps().getXmlAttribute() != null;
    }

    /** Returns boolean true if the Frame has a steps attribute with no value otherwise false*/
    private boolean isStepsEmpty(Frame f) {
        return Objects.equals(f.getSteps().getRawText(), "");
    }

    /** Returns boolean true if the Frame has a steps attribute with a value of zero otherwise false*/
    private boolean isStepsZero(Frame f) {
        return Objects.equals(f.getSteps().getRawText(), "0");
    }
}
