package com.janusresearch.tdXmlPlugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomManager;
import com.janusresearch.tdXmlPlugin.dialog.LessonScriptsDialog;
import com.janusresearch.tdXmlPlugin.dom.Acronym;
import com.janusresearch.tdXmlPlugin.dom.Acronyms;
import com.janusresearch.tdXmlPlugin.dom.COLs;
import com.janusresearch.tdXmlPlugin.dom.Module;
import com.janusresearch.tdXmlPlugin.toolWindow.XmlConsoleViewContentType;
import com.janusresearch.tdXmlPlugin.toolWindow.XmlToolWindow;
import com.janusresearch.tdXmlPlugin.xml.ScriptGenerator;
import org.jetbrains.annotations.Contract;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LessonScripts extends AnAction {
    private Project project;
    private String lessonFile = "[A-Z]{2}1[A-Z][0-9]{4}[a-zA-Z]?\\.xml";
    private String colFile = "[A-Z]{2}1[A-Z][0-9]{4}[a-zA-Z]?(?:COL)\\.xml";
    private String projectName = null;
    private DomManager manager;
    private ProgressManager pm = null;
    private ScriptGenerator sg;
    private List<VirtualFile> xmlFiles = null;
    private FileChooserDescriptor descriptor = null;
    private LessonScriptsDialog dialog;
    private XmlFile acronymFile = null;

    @Override
    @SuppressWarnings("ConstantConditions")
    public void actionPerformed(AnActionEvent e) {
        //Get Project from the data from data keys
        this.project = e.getData(PlatformDataKeys.PROJECT);

        //Create and display the Lesson Scripts dialog
        this.dialog = new LessonScriptsDialog();
        getDialog().show();

        if (getDialog().isOK()) {
            //Clear and show the console
            XmlToolWindow.getXmlConsole().clear();
            XmlToolWindow.showToolWindow();

            //Create a new ScriptGenerator object
            this.sg = new ScriptGenerator(getProject());

            //Create DomManager, FileDescription and register the description
            this.manager = DomManager.getDomManager(getProject());

            //Set File Choice criteria and File Browser information
            if (getDialog().isSpecificLessons()) {
                this.descriptor = new FileChooserDescriptor(true, false, false, false, false, true);
                getDescriptor().setTitle("Select One or More Lessons");
            } else if (getDialog().isProjectLessons()) {
                this.descriptor = new FileChooserDescriptor(false, true, false, false, false, false);
                getDescriptor().setTitle("Select a Project's Lessons Folder");
            }
            getDescriptor().setDescription("Component (Zero i.e. BX0A0101), POP, COL, and PL files are ignored during processing.");

            //Open File/Path chooser and select the Lesson XML files or folder path and store the lesson files paths in a list
            this.pm = ProgressManager.getInstance();
            this.xmlFiles = new ArrayList<>();
            FileChooser.chooseFiles(getDescriptor(), getProject(), null, selections -> {
                final boolean filesSelected = getPm().runProcessWithProgressSynchronously((Runnable) () -> getXmlFiles().addAll(selections), "Looking for Lesson Files...", false, getProject());
                if (!filesSelected || getXmlFiles().isEmpty()) {
                    XmlToolWindow.getXmlConsole().print("No Path or Xml Lesson Files were selected\n", XmlConsoleViewContentType.MESSAGE_OUTPUT);
                    return;
                }

                if (getXmlFiles().size() == 1 && getXmlFiles().get(0).isDirectory()) {
                    VirtualFile vFile = getXmlFiles().get(0);
                    getXmlFiles().clear();
                    for (VirtualFile v : vFile.getChildren()) {
                        if (isLessonFile(v)) {
                            getXmlFiles().add(v);
                        }
                    }
                }

                if (getXmlFiles().size() != 0) {
                    //Create the acronyms list in memory if the option was selected on the dialog
                    if (getDialog().isAcronymMatching()) {
                        //Choose the AcronymPronunciations.xml file
                        this.descriptor = new FileChooserDescriptor(true, false, false, false, false, false);
                        FileChooser.chooseFiles(getDescriptor(), getProject(), null, file -> {
                            List<VirtualFile> acronymFile = new ArrayList<>();
                            final boolean acronymFileSelected = getPm().runProcessWithProgressSynchronously((Runnable) () -> acronymFile.addAll(file), "Looking for Acronym Folder...", false, getProject());
                            if (!acronymFileSelected || acronymFile.isEmpty()) {
                                XmlToolWindow.getXmlConsole().print("No AcronymPronunciations.Xml file was selected.", XmlConsoleViewContentType.MESSAGE_OUTPUT);
                                return;
                            }

                            //Gets the path to the Dictionaries folder located inside Streaming Assets
                            VirtualFile vFile = LocalFileSystem.getInstance().findFileByPath(acronymFile.get(0).getPath());

                            if (vFile != null) {
                                this.acronymFile = (XmlFile) PsiManager.getInstance(getProject()).findFile(vFile);
                            }
                        });
                    }

                    Pattern pattern = Pattern.compile(".*StreamingAssets/(.*?)/.*");
                    this.projectName = getXmlFiles().get(0).getPath().replaceAll(String.valueOf(pattern), "$1");

                    getPm().runProcessWithProgressSynchronously(() -> ApplicationManager.getApplication().runReadAction(this::generateScripts), "Script Generator", false, getProject());
                } else {
                    XmlToolWindow.getXmlConsole().print("Script Generator\n", XmlConsoleViewContentType.TITLE_OUTPUT_UNDERLINE);
                    XmlToolWindow.getXmlConsole().print("Lessons Found:", XmlConsoleViewContentType.MESSAGE_OUTPUT);
                    XmlToolWindow.getXmlConsole().print(" " + getXmlFiles().size() + "\n\n", XmlConsoleViewContentType.VALUE_OUTPUT);
                }
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

    @SuppressWarnings("ConstantConditions")
    private void generateScripts() {
        //Send update to console Script Generator started and how many lesson files were found
        XmlToolWindow.getXmlConsole().print("Script Generator\n", XmlConsoleViewContentType.TITLE_OUTPUT_UNDERLINE);
        XmlToolWindow.getXmlConsole().print("Lessons Found:", XmlConsoleViewContentType.MESSAGE_OUTPUT);
        XmlToolWindow.getXmlConsole().print(" " + getXmlFiles().size() + "\n", XmlConsoleViewContentType.VALUE_OUTPUT);

        ProgressIndicator indicator = getPm().getProgressIndicator();

        if (getDialog().isAcronymMatching() && getAcronymFile() != null) {
            indicator.setText("Creating Acronym List With Pronunciations...");
            //Create a list of lists containing the Acronyms and Pronunciations
            getSg().createAcronymPronunciations(getManager(), getAcronymFile());
        }

        indicator.setText("Generating Audio Scripts...");
        int fileCount = getXmlFiles().size();
        int filesProcessed = 0;
        for (VirtualFile v : getXmlFiles()) {
            //get the Virtual file as an XML File and get the name of the file
            XmlFile xmlFile = (XmlFile) PsiManager.getInstance(getProject()).findFile(v);
            String fileName = xmlFile != null ? xmlFile.getName().replaceFirst("\\..*", "") : null;

            //Get the root of the file and generate a script
            if (isLessonFile(xmlFile)) {
                Module moduleRoot = getManager().getFileElement(xmlFile, Module.class).getRootElement();
                getSg().createLessonScript(fileName, getProjectName(), moduleRoot);
            } else if (isColFile(xmlFile)) {
                COLs colsRoot = getManager().getFileElement(xmlFile, COLs.class).getRootElement();
            }
            filesProcessed++;
            indicator.setFraction(percent(filesProcessed, fileCount));
        }
        XmlToolWindow.getXmlConsole().print("Files Processed Successfully:", XmlConsoleViewContentType.MESSAGE_OUTPUT);
        XmlToolWindow.getXmlConsole().print(" " + getSg().getFilesProcessed() + "\n", XmlConsoleViewContentType.VALUE_OUTPUT);
        XmlToolWindow.getXmlConsole().print("Scripts Location: C:\\Lesson Scripts\\" + getProjectName() + "\nScript Generation Complete\n\n", XmlConsoleViewContentType.MESSAGE_OUTPUT);
    }

    private double percent(int complete, int total) {
        double p = ((double) complete) / total;
        BigDecimal bd = new BigDecimal(Double.toString(p));
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Contract(pure = true)
    private DomManager getManager() {
        return manager;
    }

    @Contract(pure = true)
    private ProgressManager getPm() {
        return pm;
    }

    @Contract(pure = true)
    private ScriptGenerator getSg() {
        return sg;
    }

    @Contract(pure = true)
    private Project getProject() {
        return project;
    }

    @Contract(pure = true)
    private List<VirtualFile> getXmlFiles() {
        return xmlFiles;
    }

    @Contract(pure = true)
    private String getProjectName() {
        return projectName;
    }

    @Contract(pure = true)
    private FileChooserDescriptor getDescriptor() {
        return descriptor;
    }

    @Contract(pure = true)
    private LessonScriptsDialog getDialog() {
        return dialog;
    }

    @Contract(pure = true)
    private XmlFile getAcronymFile() {
        return acronymFile;
    }
}
