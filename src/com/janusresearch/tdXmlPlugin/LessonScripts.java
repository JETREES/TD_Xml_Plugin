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
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
                //get the current editor as a Xml File
                XmlFile xmlFile = (XmlFile) e.getData(LangDataKeys.PSI_FILE);

                //get the current lesson name
                String fileName = xmlFile.getName().replaceFirst("\\..*", "");

                //Get the XmlRoot File Element
                XmlRoot xmlRoot = manager.getFileElement(xmlFile, XmlRoot.class).getRootElement();

                if (Objects.equals(xmlRoot.getXmlElementName(), "Module")) {
                    FrameSet frameSet = new FrameSet(project, xmlRoot);
                    frameSet.createLessonScript(fileName);






                    run.setText("At tutorialspoint.com, we strive hard to " +
                                    "provide quality tutorials for self-learning " +
                                    "purpose in the domains of Academics, Information " +
                                    "Technology, Management and Computer Programming Languages.");
                    run.addBreak();
                    run.setText("this is just a test paragraph i just created for testing in a test");


                    System.out.println("createdocument.docx written successully");
                }

            }
            else if (LessonScriptsDialog.isProjectLessons()) {

            }

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
}
