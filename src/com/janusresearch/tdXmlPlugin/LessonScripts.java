package com.janusresearch.tdXmlPlugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.psi.PsiFile;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomManager;
import com.janusresearch.tdXmlPlugin.debug.Debug;
import com.janusresearch.tdXmlPlugin.dialog.LessonScriptsDialog;
import com.janusresearch.tdXmlPlugin.dom.XmlRoot;
import com.janusresearch.tdXmlPlugin.xml.FrameSet;
import com.janusresearch.tdXmlPlugin.xml.ScriptGenerator;
import com.janusresearch.tdXmlPlugin.xml.StepTree;

import java.util.Objects;

public class LessonScripts extends AnAction {

    XmlFile xmlFile;
    String fileName;
    XmlRoot xmlRoot;

    @Override
    @SuppressWarnings("ConstantConditions")
    public void actionPerformed(AnActionEvent e) {
        //Get all the required data from data keys
        Project project = e.getData(PlatformDataKeys.PROJECT);
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);

        Module module = ProjectFileIndex.getInstance(project).getModuleForFile(psiFile.getVirtualFile());

        //Create DomManager, FileDescription and register the description
        DomManager manager = DomManager.getDomManager(project);

        //Create a Script Generator object and store the acronym pronunciations in memory
        ScriptGenerator sg = new ScriptGenerator(project, module);
        sg.createAcronymPronunciations();

        //Create and display the Lesson Scripts dialog
        LessonScriptsDialog dialog = new LessonScriptsDialog();
        dialog.show();

        if (dialog.isOK()) {
            if (LessonScriptsDialog.isCurrentLesson()) {
                if (psiFile != null) {
                    //Determine if file is xml type and name matches our naming schema
                    boolean isXmlFile = psiFile.getFileType().getName().equalsIgnoreCase("xml");
                    boolean isLessonFile = psiFile.getName().matches("[A-Z]{2}1[A-Z][0-9]{4}[a-zA-Z]?\\.xml");
                    boolean isColFile = psiFile.getName().matches("[A-Z]{2}1[A-Z][0-9]{4}[a-zA-Z]?(?:COL)\\.xml");

                    if (isXmlFile && isLessonFile || isColFile) {
                        //get the current lesson name and XmlRoot File Element
                        xmlFile = (XmlFile) psiFile;
                        fileName = xmlFile.getName().replaceFirst("\\..*", "");
                        xmlRoot = manager.getFileElement(xmlFile, XmlRoot.class).getRootElement();

                        if (isLessonFile && Objects.equals(xmlRoot.getXmlElementName(), "Module")) {
                            //Create StepTree and FrameSet objects to collect lesson data and then use that data
                            //to generate the audio scripts for the selected lesson
                            StepTree stepTree = new StepTree(xmlRoot);
                            FrameSet frameSet = new FrameSet(project, xmlRoot);
                            stepTree.storeNodes();
                            frameSet.storeFrames();
                            sg.createLessonScript(fileName, stepTree, frameSet);
                        }
                        else if (isColFile && Objects.equals(xmlRoot.getXmlElementName(), "COLs")) {

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
