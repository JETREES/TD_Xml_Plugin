package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomManager;
import com.intellij.util.xml.GenericAttributeValue;
import com.janusresearch.tdXmlPlugin.dom.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jetbrains.annotations.Contract;
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
    private List<List<String>> acronyms;
    private String currentFileName;
    private String currentFrame;

    public ScriptGenerator(Project project) {
        this.project = project;
    }

    /** Generates a lesson Script from a lesson given the filename */
    public void createLessonScript(String fileName, Module moduleRoot) {
        //Create Blank Document
        document = new XWPFDocument();
        currentFileName = fileName;

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
        List<Frame> frames = moduleRoot.getFrameSet().getFrames();
        List<StepTreeNode> nodes = moduleRoot.getStepTree().getNodes();
        for (Frame f : frames) {
            currentFrame = f.getId().getRawText();
            //Create the Frame title
            run = paragraph.createRun();
            run.setFontSize(15);
            run.setText("Frame: " + matchFrameToStep(f.getId(), f.getNode(), nodes));
            paragraph = document.createParagraph();
            run = paragraph.createRun();

            //If the Frame is a Play Frame then only collect Text
            //For all other Frames collect all 3 Text types
            if (isPlayFrame(f)) {
                //Create Text Entry for current Frame
                run.setBold(true);
                run.setText("Text");
                run = paragraph.createRun();
                run.setText(":  ");
                run = paragraph.createRun();
                text = splitParagraphs(matchAcronymPronunciations(stripText(f.getText().getRawText())));
                formatParagraphs(text);
            }
            else {
                //Create InfoText Entry for current Frame
                run.setBold(true);
                run.setText("InfoText");
                run = paragraph.createRun();
                run.setText(":  ");
                run = paragraph.createRun();
                infoText = splitParagraphs(matchAcronymPronunciations(stripText(f.getInfoText().getRawText())));
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
                text = splitParagraphs(matchAcronymPronunciations(stripText(f.getText().getRawText())));
                formatParagraphs(text);

                //Create Text2 Entry for current Frame
                paragraph = document.createParagraph();
                run = paragraph.createRun();
                run.addBreak();
                text2 = splitParagraphs(matchAcronymPronunciations(stripText(f.getText2().getRawText())));
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

    private boolean isPlayFrame(Frame frame) {
        boolean playFrame = false;
        for (Event e : frame.getEvents().getEvents()) {
            if (Objects.equals(e.getGet().getRawText(), "Play")) {
                playFrame = true;
                break;
            }
        }
        return playFrame;
    }

    @Contract("null -> null")
    private String matchAcronymPronunciations(String text) {
        if (text != null) {
            for (List<String> s : acronyms) {
                try {
                    text = text.replace(" " + s.get(0) + " ", " " + s.get(0) + " " + s.get(1) + " ");
                    text = text.replace(" " + s.get(0) + "s", " " + s.get(0) + "s " + s.get(1));
                    text = text.replace(" " + s.get(0) + ".", " " + s.get(0) + " " + s.get(1) + ".");
                    text = text.replace(" " + s.get(0) + ",", " " + s.get(0) + " " + s.get(1) + ",");
                    text = text.replace(" " + s.get(0) + "]", " " + s.get(0) + " " + s.get(1) + "]");
                    text = text.replace(" " + s.get(0) + "-", " " + s.get(0) + "- " + s.get(1) + " ");
                    text = text.replace(" " + s.get(0) + "/", " " + s.get(0) + " " + s.get(1) + "/");
                    text = text.replace("/" + s.get(0) + " ", "/" + s.get(0) + " " + s.get(1) + " ");

                    text = text.replace(" (" + s.get(0) + ") ", " (" + s.get(0) + ") " + s.get(1) + " ");
                    text = text.replace(" (" + s.get(0) + ")s", " (" + s.get(0) + ")s " + s.get(1));
                    text = text.replace(" (" + s.get(0) + ").", " (" + s.get(0) + ") " + s.get(1) + ".");
                    text = text.replace(" (" + s.get(0) + "),", " (" + s.get(0) + ") " + s.get(1) + ",");
                    text = text.replace(" (" + s.get(0) + ")]", " (" + s.get(0) + ") " + s.get(1) + "]");
                    text = text.replace(" (" + s.get(0) + ")-", " (" + s.get(0) + ")- " + s.get(1) + " ");
                    text = text.replace(" (" + s.get(0) + ")/", " (" + s.get(0) + ") " + s.get(1) + "/");
                    text = text.replace("/(" + s.get(0) + ") ", "/(" + s.get(0) + ") " + s.get(1) + " ");
                } catch (Exception ex) {
                    System.err.println("Exception Matching Acronyms: " + currentFileName + " - " + currentFrame + " >> " + ex.getMessage());
                }
            }
        }
        return text;
    }

    /** Formats the List of Lists containing the paragraphs for the document ensuring
     *  paragraphs and breaks are placed appropriately */
    private void formatParagraphs(List<List<String>> text) {
        if (text != null) {
            try {
                int i = 0;
                for (List<String> p : text) {
                    if (i != 0) {
                        paragraph = document.createParagraph();
                        run = paragraph.createRun();
                    }

                    if (p.size() == 1) {
                        run.setText(p.get(0));
                    } else {
                        for (int j = 0; j < p.size(); j++) {
                            run.setText(p.get(j));
                            if (j != p.size() - 1) {
                                run.addBreak();
                            }
                        }
                    }
                    i++;
                }
            } catch (Exception ex) {
                System.err.println("Exception Formatting Paragraphs: " + currentFileName + " - " + currentFrame + " >> " + ex.getMessage());
            }
        }
    }

    /** Returns List of Lists containing the Frames text after being split by double/single returns
     *  The split creates a 2 dimensional List where each row contains a paragraph and each column (if any) contains
     *  text that is in the same paragraph but on different lines due to single returns */
    private List<List<String>> splitParagraphs(String text) {
        List<List<String>> paragraphs = new ArrayList<>();
        if (text != null) {
            try {
                List<String> paragraph;
                String[] tempParagraphs;
                String[] tempLines;

                if (text.contains("\\r\\r")) {
                    tempParagraphs = text.split("\\\\r\\\\r");
                    for (String p : tempParagraphs) {
                        paragraph = new ArrayList<>();
                        if (!p.contains("\\r")) {
                            paragraph.add(p);
                        } else {
                            tempLines = p.split("\\\\r");
                            paragraph.addAll(Arrays.asList(tempLines));
                        }
                        paragraphs.add(paragraph);
                    }
                } else if (text.contains("\\r")) {
                    tempLines = text.split("\\\\r");
                    paragraph = new ArrayList<>(Arrays.asList(tempLines));
                    paragraphs.add(paragraph);
                } else {
                    paragraph = new ArrayList<>();
                    paragraph.add(text);
                    paragraphs.add(paragraph);
                }
            } catch (Exception ex) {
                System.err.println("Exception Splitting Paragraphs: " + currentFileName + " - " + currentFrame + " >> " + ex.getMessage());
            }
        }
        return paragraphs;
    }

    /** Matches the current Frame with its StepTree node in order to get the correct label attribute from the StepTree
     *  Returns a String combination of the Frame id and StepTree label
     * @param frameId is the id for the current Frame
     * @param frameNode is the node for the current Frame
     * @param nodes is a List of the StepTree nodes*/
    @NotNull
    private String matchFrameToStep(GenericAttributeValue<String> frameId, GenericAttributeValue<String> frameNode, List<StepTreeNode> nodes) {
        String stepTreeLabel = null;
        try {
            for (StepTreeNode n : nodes) {
                if (frameNode.getRawText().equals(n.getName().getRawText())) {
                    stepTreeLabel = n.getLabel().getRawText();
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("Exception Matching Frame to Step: " + currentFileName + " - " + currentFrame + " >> "  + ex.getMessage());
        }
        return frameId + " - " + stepTreeLabel;
    }

    /** Returns a Frames text with code related items stripped out of the text so it will be presented in a cleaner fashion in the document */
    private String stripText(String text) {
        try {
            if (text != null) {
                text = text.replaceAll("\\[[fF] .*?]", "");
                text = text.replace("&#x2022;", "•");
                text = text.replace("&amp;", "&");
                text = text.replace("\t", "     ");
                text = text.replaceAll("\\[c.*?].*?H.*?]WARNING(?i)\\[.*?\\?]", "");
                text = text.replaceAll("\\[c.*?].*?H.*?]CAUTION(?i)\\[.*?\\?]", "");
                text = text.replaceAll("\\[c.*?].*?H.*?]NOTE(?i)\\[.*?\\?]", "");
                text = text.replaceAll("\\[c.*?].*?H.*?](.*?)\\[.*?\\?]", "$1");
            }
        } catch (Exception ex) {
            System.err.println("Exception Stripping Text: " + currentFileName + " - " + currentFrame + " >> "  + ex.getMessage());
        }

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

            //Gets the path to the Dictionaries folder located inside Streaming Assets
            VirtualFile vFile = LocalFileSystem.getInstance().findFileByPath(acronymFolder.get(0).getPath() + "\\AcronymPronunciations.xml");
            XmlFile xmlFile = null;
            if (vFile != null) {
                xmlFile = (XmlFile) PsiManager.getInstance(project).findFile(vFile);
                }

            try {
                //Get the Acronym File Element and store access to the acronyms
                Acronyms acronymsRoot = Objects.requireNonNull(manager.getFileElement(xmlFile, Acronyms.class)).getRootElement();
                acronyms = new ArrayList<>();
                for (Acronym a : acronymsRoot.getAcronyms()) {
                    List<String> newAcronym = new ArrayList<>();
                    newAcronym.add(a.getName().getRawText());
                    newAcronym.add(a.getPronunciation().getRawText());
                    acronyms.add(newAcronym);
                }
            } catch (Exception ex) {
                System.err.println("Exception Creating Acronyms List: " + currentFileName + " - " + currentFrame + " >> "  + ex.getMessage());
            }
        });
    }
}
