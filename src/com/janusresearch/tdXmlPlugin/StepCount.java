package com.janusresearch.tdXmlPlugin;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomManager;
import com.janusresearch.tdXmlPlugin.dom.XmlRoot;
import com.janusresearch.tdXmlPlugin.toolWindow.XmlConsole;
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

        //Get the XmlRoot File Element
        XmlRoot xmlRoot = manager.getFileElement(xmlFile, XmlRoot.class).getRootElement();

        if (Objects.equals(xmlRoot.getXmlElementName(), "Module")) {
            FrameSet frameSet = new FrameSet(xmlRoot);
            frameSet.storeFrameAttributes();
            XmlConsole.printStepCount(psiFile, frameSet.getStepCount());
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
