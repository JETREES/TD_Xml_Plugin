package com.janusresearch.tdXmlPlugin;

import com.intellij.execution.process.BaseOSProcessHandler;
import com.intellij.execution.process.NopProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomManager;
import com.janusresearch.tdXmlPlugin.dialog.LessonScriptsDialog;
import com.janusresearch.tdXmlPlugin.dom.COLs;
import com.janusresearch.tdXmlPlugin.dom.Module;
import com.janusresearch.tdXmlPlugin.toolWindow.XmlConsoleViewContentType;
import com.janusresearch.tdXmlPlugin.toolWindow.XmlToolWindow;
import com.janusresearch.tdXmlPlugin.xml.ScriptGenerator;
import com.sun.jna.platform.win32.WinDef;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LessonScripts extends AnAction {
    private String lessonFile = "[A-Z]{2}1[A-Z][0-9]{4}[a-zA-Z]?\\.xml";
    private String colFile = "[A-Z]{2}1[A-Z][0-9]{4}[a-zA-Z]?(?:COL)\\.xml";
    private String projectName = null;

    @Override
    @SuppressWarnings("ConstantConditions")
    public void actionPerformed(AnActionEvent e) {
        //Get Project from the data from data keys
        Project project = e.getData(PlatformDataKeys.PROJECT);

        //Create and display the Lesson Scripts dialog
        LessonScriptsDialog dialog = new LessonScriptsDialog();
        dialog.show();

        if (dialog.isOK()) {
            XmlToolWindow.getXmlConsole().clear();

            /*try {
                ProcessBuilder pb = new
                        ProcessBuilder("cal", "2022");
                final Process process = pb.start();
                BufferedReader br=new BufferedReader(
                        new InputStreamReader(
                                process.getInputStream()));
                String line;
                while((line=br.readLine())!=null){
                    System.out.println(line);
                }
                Runtime r = Runtime.getRuntime();
                r.
                ProcessHandler processHandler = new BaseOSProcessHandler(process, null, Charset.defaultCharset());
                processHandler.startNotify();
                XmlToolWindow.getXmlConsole().attachToProcess(processHandler);
            } catch (Exception ex) {
                System.out.println(ex);
            }*/

            FileChooserDescriptor descriptor = null;
            //Set File Choice criteria and File Browser information
            if (dialog.isSpecificLessons()) {
                descriptor = new FileChooserDescriptor(true, false, false, false, false, true);
                descriptor.setTitle("Select One or More Lessons");
            } else if (dialog.isProjectLessons()) {
                descriptor = new FileChooserDescriptor(false, true, false, false, false, false);
                descriptor.setTitle("Select a Project's Lessons Folder");
            }
            descriptor.setDescription("Component (Zero i.e. BX0A0101), Popup (POP), and Preload (PL) files are ignored during processing.");

            //Open File/Path chooser and select the Lesson XML files or folder path and store the lesson files paths in a list
            FileChooser.chooseFiles(descriptor, project, null, files -> {
                final ProgressManager pm = ProgressManager.getInstance();
                List<VirtualFile> xmlFiles = new ArrayList<>();
                final boolean hasSelection = pm.runProcessWithProgressSynchronously((Runnable) () -> xmlFiles.addAll(files), "Looking for Lesson Files...", false, project);
                if (!hasSelection || xmlFiles.isEmpty()) {
                    XmlToolWindow.getXmlConsole().print("No Path or Xml Lesson Files were selected\n", XmlConsoleViewContentType.MESSAGE_OUTPUT);
                    return;
                }

                if (xmlFiles.size() == 1 && xmlFiles.get(0).isDirectory()) {
                    VirtualFile vFile = xmlFiles.get(0);
                    xmlFiles.clear();
                    for (VirtualFile v : vFile.getChildren()) {
                        if (isLessonFile(v)) {
                            xmlFiles.add(v);
                        }
                    }
                }

                if (xmlFiles.size() != 0) {
                    Pattern pattern = Pattern.compile(".*StreamingAssets/(.*?)/.*");
                    projectName = xmlFiles.get(0).getPath().replaceAll(String.valueOf(pattern), "$1");
                    /*pm.run(new Task.Backgroundable(project, "testtitle") {
                        @Override
                        public void run(@NotNull ProgressIndicator progressIndicator) {*/

                    //Create DomManager, FileDescription and register the description
                    DomManager manager = DomManager.getDomManager(project);

                    //Create a Script Generator object and store the acronym pronunciations in memory
                    ScriptGenerator sg = new ScriptGenerator(project);
                    sg.createAcronymPronunciations(manager, pm, dialog.isAcronymMatching());

                    XmlToolWindow.getXmlConsole().print("Script Generator\n", XmlConsoleViewContentType.TITLE_OUTPUT_UNDERLINE);
                    XmlToolWindow.getXmlConsole().print("Lessons Found:", XmlConsoleViewContentType.MESSAGE_OUTPUT);
                    XmlToolWindow.getXmlConsole().print(" " + xmlFiles.size() + "\n", XmlConsoleViewContentType.VALUE_OUTPUT);
//                    XmlToolWindow.getXmlConsole().print("Generating Scripts...\n", XmlConsoleViewContentType.MESSAGE_OUTPUT);
                    for (VirtualFile v : xmlFiles) {
                        //get the Virtual file as an XML File and get the name of the file
                        XmlFile xmlFile = (XmlFile) PsiManager.getInstance(project).findFile(v);
                        String fileName = xmlFile.getName().replaceFirst("\\..*", "");

                        //Get the root of the file and generate a script
                        if (xmlFile != null) {
                            if (isLessonFile(xmlFile)) {
                                Module moduleRoot = manager.getFileElement(xmlFile, Module.class).getRootElement();
                                sg.createLessonScript(fileName, projectName, moduleRoot);
                            } else if (isColFile(xmlFile)) {
                                COLs colsRoot = manager.getFileElement(xmlFile, COLs.class).getRootElement();
                            }
                        }
                    }
                    XmlToolWindow.getXmlConsole().print("Files Processed Successfully:", XmlConsoleViewContentType.MESSAGE_OUTPUT);
                    XmlToolWindow.getXmlConsole().print(" " + sg.getFilesProcessed() + "\n", XmlConsoleViewContentType.VALUE_OUTPUT);
                    XmlToolWindow.getXmlConsole().print("Scripts Location: C:\\Lesson Scripts\\" + projectName + "\nScript Generation Complete\n\n", XmlConsoleViewContentType.MESSAGE_OUTPUT);
                     /*   }
                    });*/
                } else {
                    XmlToolWindow.getXmlConsole().print("Script Generator\n", XmlConsoleViewContentType.TITLE_OUTPUT_UNDERLINE);
                    XmlToolWindow.getXmlConsole().print("Lessons Found:", XmlConsoleViewContentType.MESSAGE_OUTPUT);
                    XmlToolWindow.getXmlConsole().print(" " + xmlFiles.size() + "\n\n", XmlConsoleViewContentType.VALUE_OUTPUT);
                }
                XmlToolWindow.showToolWindow();
            });
        }
    }

    @Override
    public void update(AnActionEvent e) {
        //Set visibility of plugin button
        e.getPresentation().setEnabled(true);
    }

    private boolean isLessonFile(XmlFile file) {
        return file.getName().matches(lessonFile);
    }

    private boolean isColFile(XmlFile file) {
        return file.getName().matches(colFile);
    }

    private boolean isLessonFile(VirtualFile file) {
        return file.getName().matches(lessonFile);
    }

    private boolean isColFile(VirtualFile file) {
        return file.getName().matches(colFile);
    }
}
