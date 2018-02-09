package com.janusresearch.tdXmlPlugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomManager;
import com.janusresearch.tdXmlPlugin.dialog.LessonScriptsDialog;
import com.janusresearch.tdXmlPlugin.dom.XmlRoot;
import com.janusresearch.tdXmlPlugin.xml.FrameSet;
import com.janusresearch.tdXmlPlugin.xml.ScriptGenerator;
import com.janusresearch.tdXmlPlugin.xml.StepTree;

import java.util.Objects;

public class LessonScripts extends AnAction {

    @Override
    @SuppressWarnings("ConstantConditions")
    public void actionPerformed(AnActionEvent e) {
        //Get all the required data from data keys
        Project project = e.getData(PlatformDataKeys.PROJECT);

        LessonScriptsDialog dialog = new LessonScriptsDialog();
        dialog.show();

        if (dialog.isOK()) {
            //Create DomManager, FileDescription and register the description
            DomManager manager = DomManager.getDomManager(project);

            if (LessonScriptsDialog.isCurrentLesson()) {
                PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);

                if (psiFile != null) {
                    boolean isXmlFile;
                    boolean nameMatchesSchema;

                    //Determine if file is xml type and name matches our naming schema
                    isXmlFile = psiFile.getFileType().getName().equalsIgnoreCase("xml");
                    nameMatchesSchema = psiFile.getName().matches("[A-Z]{2}[0-9][A-Z][0-9]{4}[a-zA-Z]?\\.xml");

                    if (isXmlFile && nameMatchesSchema) {
                        //get the current editor as a Xml File
                        XmlFile xmlFile = (XmlFile) psiFile;

                        //get the current lesson name
                        String fileName = xmlFile.getName().replaceFirst("\\..*", "");

                        //Get the XmlRoot File Element
                        XmlRoot xmlRoot = manager.getFileElement(xmlFile, XmlRoot.class).getRootElement();

                        if (Objects.equals(xmlRoot.getXmlElementName(), "Module")) {
                            StepTree stepTree = new StepTree(xmlRoot);
                            FrameSet frameSet = new FrameSet(project, xmlRoot);

                            stepTree.storeNodes();
                            frameSet.storeFrames();

                            ScriptGenerator sg = new ScriptGenerator();
                            sg.createLessonScript(fileName, stepTree, frameSet);
                        }
                    }
                }
            }
            else if (LessonScriptsDialog.isProjectLessons()) {

            }
        }
    }

    @Override
    public void update(AnActionEvent e) {
        //Set visibility of plugin button
        e.getPresentation().setEnabled(true);
    }
}
