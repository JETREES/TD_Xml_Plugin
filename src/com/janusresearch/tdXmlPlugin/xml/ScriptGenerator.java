package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.DomManager;
import com.janusresearch.tdXmlPlugin.dom.Acronym;
import com.janusresearch.tdXmlPlugin.dom.Acronyms;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ScriptGenerator {
    private Project project;
    private XWPFDocument document;
    private XWPFParagraph paragraph;
    private XWPFRun run;
    private List<Acronym> acronyms;

    public ScriptGenerator(Project project) {
        this.project = project;
    }

    /** Generates a lesson Script from a lesson given the filename */
    public void createLessonScript(String fileName, StepTree stepTree, FrameSet frameSet) {
        //Create Blank Document
        document = new XWPFDocument();

        //Create the directory to save the Lesson Scripts in if it does not exist
        if (!Files.isDirectory(Paths.get("C:\\Lesson Scripts\\"))) {
            File dir = new File("C:\\Lesson Scripts\\");
            boolean successful = dir.mkdir();
            if (successful) {
                //add message to console for successful directory creation
            }
            else {
                //add message to console for failed directory creation
            }
        }

        //Name the document with the lesson number
        FileOutputStream out = null;
        try {
            out = new FileOutputStream( new File("C:\\Lesson Scripts\\" + fileName + ".docx"));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }


        //Create the document Title and document Heading
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(20);
        run.setText("Lesson");
        run = paragraph.createRun();
        run.setFontSize(20);
        run.setText(": " + fileName);
        run = paragraph.createRun();
        run.addBreak();
        run.setText("------------------------------------------------------------------------------------------------------------------------------------------");
        run.addBreak();

        //Loop through the lesson collecting all the InfoText, Text, and Text2 data where necessary
        List<List<String>> infoText;
        List<List<String>> text;
        List<List<String>> text2;
        for (XmlTag f : frameSet.getFrames()) {
            //Create the Frame title
            run = paragraph.createRun();
            run.setFontSize(15);
            run.setText("Frame: " + matchFrameToStep(f.getAttributeValue("id"), f.getAttributeValue("node"), stepTree));
            paragraph = document.createParagraph();
            run = paragraph.createRun();

            //If the Frame is a Play Frame then only collect Text
            //For all other Frames collect all 3 Text types
            if (frameSet.isPlayFrame(f)) {
                //Create Text Entry for current Frame
                run.setBold(true);
                run.setText("Text");
                run = paragraph.createRun();
                run.setText(":  ");
                run = paragraph.createRun();
                text = splitParagraphs(matchAcronymPronunciations(stripText(frameSet.getFrameText(f))));
                formatParagraphs(text);
            }
            else {
                //Create InfoText Entry for current Frame
                run.setBold(true);
                run.setText("InfoText");
                run = paragraph.createRun();
                run.setText(":  ");
                run = paragraph.createRun();
                infoText = splitParagraphs(matchAcronymPronunciations(stripText(frameSet.getFrameInfoText(f))));
                formatParagraphs(infoText);

                //Create Text Entry for current Frame
                paragraph = document.createParagraph();
                run = paragraph.createRun();
                run.addBreak();
                run.setBold(true);
                run.setText("Text1");
                run = paragraph.createRun();
                run.setText(":  ");
                run = paragraph.createRun();
                text = splitParagraphs(matchAcronymPronunciations(stripText(frameSet.getFrameText(f))));
                formatParagraphs(text);

                //Create Text2 Entry for current Frame
                paragraph = document.createParagraph();
                run = paragraph.createRun();
                run.addBreak();
                text2 = splitParagraphs(matchAcronymPronunciations(stripText(frameSet.getFrameText2(f))));
                run.setBold(true);
                run.setText("Text2");
                run = paragraph.createRun();
                run.setText(":  ");
                run = paragraph.createRun();
                formatParagraphs(text2);
            }
            //Create a line to separate each Frames script
            run.addBreak();
            run.setText("------------------------------------------------------------------------------------------------------------------------------------------");
            run.addBreak();
        }

        //Write the Document
        try {
            document.write(out);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        //Close the Document
        try {
            assert out != null;
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private String matchAcronymPronunciations(String text) {
        for (Acronym s : acronyms) {
            /*I tried this with regex at one point and it didn't seem to work properly in every single case
            text = text.replaceAll("( | \\()" + s.getName() + "( |\\) |\\.)", "$1" + s.getName() + "$2" + s.getPronunciation() + " " );*/
            text = text.replace(" " + s.getName() + " ", " " + s.getName() + " " + s.getPronunciation() + " " );
            text = text.replace(" " + s.getName() + "s", " " + s.getName() + "s " + s.getPronunciation());
            text = text.replace(" " + s.getName() + ".", " " + s.getName() + " " + s.getPronunciation() + "." );
            text = text.replace(" " + s.getName() + ",", " " + s.getName() + " " + s.getPronunciation() + "," );
            text = text.replace(" " + s.getName() + "]", " " + s.getName() + " " + s.getPronunciation() + "]" );
            text = text.replace(" " + s.getName() + "-", " " + s.getName() + "- " + s.getPronunciation() + " ");
            text = text.replace(" " + s.getName() + "/", " " + s.getName() + " " + s.getPronunciation() + "/" );
            text = text.replace("/" + s.getName() + " ", "/" + s.getName() + " " + s.getPronunciation() + " " );

            text = text.replace(" (" + s.getName() + ") ", " (" + s.getName() + ") " + s.getPronunciation() + " ");
            text = text.replace(" (" + s.getName() + ")s", " (" + s.getName() + ")s " + s.getPronunciation());
            text = text.replace(" (" + s.getName() + ").", " (" + s.getName() + ") " + s.getPronunciation() + "." );
            text = text.replace(" (" + s.getName() + "),", " (" + s.getName() + ") " + s.getPronunciation() + "," );
            text = text.replace(" (" + s.getName() + ")]", " (" + s.getName() + ") " + s.getPronunciation() + "]" );
            text = text.replace(" (" + s.getName() + ")-", " (" + s.getName() + ")- " + s.getPronunciation() + " ");
            text = text.replace(" (" + s.getName() + ")/", " (" + s.getName() + ") " + s.getPronunciation() + "/" );
            text = text.replace("/(" + s.getName() + ") ", "/(" + s.getName() + ") " + s.getPronunciation() + " " );
        }
        return text;
    }

    /** Formats the List of Lists containing the paragraphs for the document ensuring
     *  paragraphs and breaks are placed appropriately */
    private void formatParagraphs(@NotNull List<List<String>> text) {
        int i = 0;
        for (List<String> p : text) {
            if (i != 0) {
                paragraph = document.createParagraph();
                run = paragraph.createRun();
            }

            if (p.size() == 1) {
                run.setText(p.get(0));
            }
            else {
                for (int j = 0; j < p.size(); j++) {
                    run.setText(p.get(j));
                    if (j != p.size() - 1) {
                        run.addBreak();
                    }
                }
            }
            i++;
        }
    }

    /** Returns List of Lists containing the Frames text after being split by double/single returns
     *  The split creates a 2 dimensional List where each row contains a paragraph and each column (if any) contains
     *  text that is in the same paragraph but on different lines due to single returns */
    private List<List<String>> splitParagraphs(@NotNull String text) {
        List<List<String>> paragraphs = new ArrayList<>();
        List<String> paragraph;
        String[] tempParagraphs;
        String[] tempLines;

        if (text.contains("\\r\\r")) {
            tempParagraphs = text.split("\\\\r\\\\r");
            for (String p : tempParagraphs) {
                paragraph = new ArrayList<>();
                if (!p.contains("\\r")) {
                    paragraph.add(p);
                }
                else {
                    tempLines = p.split("\\\\r");
                    paragraph.addAll(Arrays.asList(tempLines));
                }
                paragraphs.add(paragraph);
            }
        }
        else if (text.contains("\\r")) {
            tempLines = text.split("\\\\r");
            paragraph = new ArrayList<>(Arrays.asList(tempLines));
            paragraphs.add(paragraph);
        }
        else {
            paragraph = new ArrayList<>();
            paragraph.add(text);
            paragraphs.add(paragraph);
        }
        return paragraphs;
    }

    /** Matches the current Frame with its StepTree node in order to get the correct label attribute from the StepTree
     *  Returns a String combination of the Frame id and StepTree label*/
    @NotNull
    private String matchFrameToStep(String frameId, String frameNode, @NotNull StepTree stepTree) {
        String stepTreeLabel = null;
        for (XmlTag n : stepTree.getNodes()) {
            if (frameNode.equals(n.getAttributeValue("name"))) {
                stepTreeLabel = n.getAttributeValue("label");
            }
        }
        return frameId + " - " + stepTreeLabel;
    }

    /** Returns a Frames text with code related items stripped out of the text so it will be presented in a cleaner fashion in the document */
    private String stripText(String text) {
        text = text.replaceAll("\\[[fF] .*?]", "");
        text = text.replace("&#x2022;", "•");
        text = text.replace("&amp;", "&");
        text = text.replace("\t", "     ");
        text = text.replaceAll("\\[c.*?].*?H.*?]WARNING(?i)\\[.*?\\?]", "");
        text = text.replaceAll("\\[c.*?].*?H.*?]CAUTION(?i)\\[.*?\\?]", "");
        text = text.replaceAll("\\[c.*?].*?H.*?]NOTE(?i)\\[.*?\\?]", "");
        text = text.replaceAll("\\[c.*?].*?H.*?](.*?)\\[.*?\\?]", "$1");

        return text;
    }

    /** Stores the Acronyms and their pronunciations in a 2-dimensional array to be used for matching/replacing */
    public void createAcronymPronunciations(DomManager manager, ProgressManager pm) {
        FileChooserDescriptor descriptor = new FileChooserDescriptor(false, true, false, false, false, false);
        descriptor.setTitle("Select the Location of AcronymPronunciations.Xml");
        descriptor.setDescription("Browse to and select the folder where the AcronymPronunciations.Xml file resides.");

        FileChooser.chooseFiles(descriptor, project, null, folder -> {
            List<VirtualFile> acronymFolder = new ArrayList<>();
            final boolean hasSelection = pm.runProcessWithProgressSynchronously((Runnable) () -> acronymFolder.addAll(folder), "Looking for Acronym Folder...", false, project);
            if (!hasSelection || acronymFolder.isEmpty()) return;

            pm.runProcessWithProgressSynchronously(() -> {
                //Gets the path to the Dictionaries folder located inside Streaming Assets
                VirtualFile vFile = LocalFileSystem.getInstance().findFileByPath(acronymFolder.get(0).getPath() + "\\AcronymPronunciations.xml");
                XmlFile xmlFile = null;
                if (vFile != null) {
                    xmlFile = (XmlFile) PsiManager.getInstance(project).findFile(vFile);
                }

                //Get the Module File Element
                Acronyms acronymsRoot = Objects.requireNonNull(manager.getFileElement(xmlFile, Acronyms.class)).getRootElement();
                acronyms = acronymsRoot.getAcronyms();

            }, "Reading Acronym Pronunciations...", false, project);
        });





//        XmlTag[] acronymTags;
//        acronymTags = acronymsRoot.getXmlTag().findSubTags("acronym");
//        acronyms = new String[acronymTags.length][2];
//        int i = 0;
//        for (XmlTag x : acronymTags) {
//            acronyms[i][0] = x.getAttributeValue("name");
//            acronyms[i][1] = x.getAttributeValue("pronunciation");
//            i++;
//        }




    }
}
