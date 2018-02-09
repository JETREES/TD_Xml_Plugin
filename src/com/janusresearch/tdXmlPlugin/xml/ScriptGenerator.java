package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.psi.xml.XmlTag;
import com.janusresearch.tdXmlPlugin.debug.Debug;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScriptGenerator {
    private XWPFDocument document;
    private XWPFParagraph paragraph;
    private XWPFRun run;

    public ScriptGenerator() {
    }

    public void createLessonScript(String fileName, StepTree stepTree, FrameSet frameSet) {
        //Blank Document
        document = new XWPFDocument();

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
        //Write the Document in file system
        FileOutputStream out = null;
        try {
            out = new FileOutputStream( new File("C:\\Lesson Scripts\\" + fileName + ".docx"));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        //create Paragraph
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


        List<List<String>> infoText;
        List<List<String>> text;
        List<List<String>> text2;
        for (XmlTag f : frameSet.getFrames()) {
            Debug.print(f.getAttributeValue("id"));
            run = paragraph.createRun();
            run.setFontSize(15);
            run.setText("Frame: " + matchFrameToStep(f.getAttributeValue("id"), f.getAttributeValue("node"), stepTree));
            paragraph = document.createParagraph();
            run = paragraph.createRun();

            if (frameSet.isPlayFrame(f)) {
                run.setBold(true);
                run.setText("Text1");
                run = paragraph.createRun();
                run.setText(":  ");
                run = paragraph.createRun();
                text = splitParagraphs(stripText(frameSet.getFrameText(f)));
                formatParagraphs(text);

            }
            else {
                run.setBold(true);
                run.setText("InfoText");
                run = paragraph.createRun();
                run.setText(":  ");
                run = paragraph.createRun();
                infoText = splitParagraphs(stripText(frameSet.getFrameInfoText(f)));
                formatParagraphs(infoText);

                paragraph = document.createParagraph();
                run = paragraph.createRun();
                run.addBreak();
                run.setBold(true);
                run.setText("Text1");
                run = paragraph.createRun();
                run.setText(":  ");
                run = paragraph.createRun();
                text = splitParagraphs(stripText(frameSet.getFrameText(f)));
                formatParagraphs(text);

                paragraph = document.createParagraph();
                run = paragraph.createRun();
                run.addBreak();
                text2 = splitParagraphs(stripText(frameSet.getFrameText2(f)));
                run.setBold(true);
                run.setText("Text2");
                run = paragraph.createRun();
                run.setText(":  ");
                run = paragraph.createRun();
                formatParagraphs(text2);
            }
            run.addBreak();
            run.setText("------------------------------------------------------------------------------------------------------------------------------------------");
            run.addBreak();
        }

        try {
            document.write(out);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            assert out != null;
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void formatParagraphs(List<List<String>> text) {
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

    private List<List<String>> splitParagraphs(String text) {
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

    private String matchFrameToStep(String frameId, String frameNode, StepTree stepTree) {
        String stepTreeLabel = null;
        for (XmlTag n : stepTree.getNodes()) {
            if (frameNode.equals(n.getAttributeValue("name"))) {
                stepTreeLabel = n.getAttributeValue("label");
            }
        }
        return frameId + " - " + stepTreeLabel;
    }

    private String stripText(String text) {
        text = text.replace("[f arial_bold]", "");
        text = text.replace("[f arial_italic]", "");
        text = text.replace("[f arial]", "");
        text = text.replace("&#x2022;", "•");
        text = text.replace("&amp;", "&");
        text = text.replace("\t", "     ");
        text = text.replaceAll("\\[c.*?].*?H.*?]WARNING(?i)\\[.*?\\?]", "");
        text = text.replaceAll("\\[c.*?].*?H.*?]CAUTION(?i)\\[.*?\\?]", "");
        text = text.replaceAll("\\[c.*?].*?H.*?]NOTE(?i)\\[.*?\\?]", "");
        text = text.replaceAll("\\[c.*?].*?H.*?](.*?)\\[.*?\\?]", "$1");

        return text;
    }
}
