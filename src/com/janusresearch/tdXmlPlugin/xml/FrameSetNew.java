package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.GenericDomValue;
import com.janusresearch.tdXmlPlugin.dialog.SubStepsDialog;
import com.janusresearch.tdXmlPlugin.dom.Frame;
import com.janusresearch.tdXmlPlugin.dom.Module;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("ConstantConditions")
public class FrameSetNew {
    private Module root;
    private XmlTag[] frames;
    private int frameCount;
    private Project myProject;
    private String[][] frameAttributes;
    private String[][] oldFrameValues;
    private String[][] newFrameValues;
    private List<XmlAttribute> playEvents = new ArrayList<>();
    private List<XmlAttribute> backEvents = new ArrayList<>();
    private List<XmlAttribute> otherEvents = new ArrayList<>();
    private List<String> newPlayValues = new ArrayList<>();
    private List<String> newBackValues = new ArrayList<>();
    private List<String> newOtherValues = new ArrayList<>();
    private int actualStepCount;

    public FrameSetNew(Project project, Module moduleRoot) {
        myProject = project;
        root = moduleRoot;
    }

    /** Store every Frame sub tag from Frame Set in the frames array */
    private void storeFrames() {
        frames = root.getXmlTag().findFirstSubTag("FrameSet").findSubTags("Frame");
        setFrameCount(frames);
    }

    /** Store the length of the FrameSet as frameCount */
    private void setFrameCount(XmlTag[] frames) {
        frameCount = root.getFrameSet().getFrames().size();
    }

    /** Store all Frame attributes from FrameSet in the frameAttributes array */
    public void storeFrameAttributes() {
        frameAttributes = new String[frameCount][3];
        int i = 0;
        for (Frame f : this.root.getFrameSet().getFrames()) {
            frameAttributes[i][0] = f.getId().getRawText();
            frameAttributes[i][1] = f.getNode().getRawText();
            frameAttributes[i][2] = f.getWeight().getRawText();
            i++;
        }
    }

    /** Store the old values for each Frame in the oldFrameValues array */
    public void storeOldFrameValues() {
        oldFrameValues = new String[frameCount][3];
        int i = 0;
        for (String[] s : getFrameAttributes()) {
            oldFrameValues[i][0] = s[0];
            oldFrameValues[i][1] = s[1];
            oldFrameValues[i][2] = s[2];
            i++;
        }
    }

    /** Store the new values for each Frame in the newFrameValues array */
    public void storeNewFrameValues() {
        //Set array size based on nodes array length
        newFrameValues = new String[frameCount][3];
        int i = 0;
        String oldLastNode;
        String oldCurrentNode;
        String newLastNode;
        for (String[] s : getNewFrameValues()) {
            //store the new values for each frame
            if (i == 0) {
                s[0] = "01";
                s[1] = "01";
                s[2] = "01";
            }
            else {
                oldLastNode = getFrameAttributes()[i - 1][1];
                oldCurrentNode = getFrameAttributes()[i][1];

                if (i < 9) {
                    s[0] = "0" + (i + 1);
                }
                else {
                    s[0] = String.valueOf(i + 1);
                }

                newLastNode = getNewFrameValues()[i - 1][1];
                if (Objects.equals(oldCurrentNode, oldLastNode) && !Objects.equals(oldCurrentNode, "") && SubStepsDialog.hasSubSteps()) {
                    s[1] = newLastNode;
                    s[2] = newLastNode;
                }
                else {
                    int n = Integer.parseInt(newLastNode) + 1;
                    if (n < 10) {
                        s[1] = "0" + String.valueOf(n);
                        s[2] = "0" + String.valueOf(n);
                    }
                    else {
                        s[1] = String.valueOf(n);
                        s[2] = String.valueOf(n);
                    }
                }
            }
            i++;
        }
    }

    /** Returns the step count from a lesson. If the lesson contains step attributes in each Frame it will use
     * those values for the count.  If they do not have the steps attribute or if the steps attribute is an empty
     * String or equals zero then attribute is added/updated with a value that is an approximate value
     * determined through processing the Text2 block
     */
    public int getStepCount() {
        storeFrames();
        actualStepCount = 0;
        WriteCommandAction.runWriteCommandAction(myProject, () -> {
            for (Frame f : this.root.getFrameSet().getFrames()) {
                if (!hasSteps(f) || (hasSteps(f) && (isStepsEmpty(f) || isStepsZero(f)))) {
                    int stepCount = parseTextForCount(f.getText2().getRawText());
                    f.getSteps().setStringValue(String.valueOf(stepCount));
                }
                actualStepCount += Integer.parseInt(f.getSteps().getRawText());
            }
        });
        return actualStepCount;
    }

    /** Parses the Text2 text value to attempt to determine the number of steps in a given Frame
     * @param s*/
    private int parseTextForCount(String s) {
        //parse String s to determine an approximate count for the Frame
        //for Play Frames set steps="1"

        return 1;
    }

    /** Process Events from every Frame to determine new nextid values */
    public void processEvents() {
        int i = 0;
        for (XmlTag f : getFrames()) {
            for (XmlTag e : getFrameEvents(f)) {
                XmlAttribute nextid = e.getAttribute("nextid");
                if (nextid != null) {
                    String get = e.getAttribute("get").getValue();
                    if (Objects.equals(get, "Play")) {
                        addPlayEvent(nextid);
                        addNewPlayValue(i);
                    }
                    else if (Objects.equals(get, "Back")) {
                        addBackEvent(nextid);
                        addNewBackValue(i);
                    }
                    else {
                        addOtherEvent(nextid);
                        addNewOtherValue(i);
                    }
                }
            }
            i++;
        }
    }

    /** Adds reference to Play nextid attribute to the list*/
    private void addPlayEvent(XmlAttribute x) {
        playEvents.add(x);
    }

    /** Adds the Play nextid new value to the list */
    private void addNewPlayValue(int i) {
        if (i < 8) {
            getNewPlayValues().add("0" + (i + 2));
        }
        else {
            getNewPlayValues().add(String.valueOf(i + 2));
        }
    }

    /** Adds reference to Back nextid attribute to the list */
    private void addBackEvent(XmlAttribute x) {
        backEvents.add(x);
    }

    /** Adds the Back nextid new value to the list */
    private void addNewBackValue(int i) {
        if (i < 10) {
            getNewBackValues().add("0" + i);
        }
        else {
            getNewBackValues().add(String.valueOf(i));
        }
    }

    /** Adds reference to Other nextid attribute to the list */
    private void addOtherEvent(XmlAttribute x) {
        otherEvents.add(x);
    }

    /** Adds the Other nextid new value to the list */
    private void addNewOtherValue(int i) {
        if (i < 8) {
            getNewOtherValues().add("0" + (i + 2));
        }
        else {
            getNewOtherValues().add(String.valueOf(i + 2));
        }
    }



    /** Get all Event sub tags from a Frame */
    @NotNull
    public XmlTag[] getFrameEvents(XmlTag x) {
        return x.findFirstSubTag("Events").findSubTags("Event");
    }

    /** Get the Text2 value from the Frame
     * @param x*/
    @NotNull
    private String getFrameText2(Frame x) {
        String text = "";
        if (x.findFirstSubTag("Text2") != null) {
            text = x.findFirstSubTag("Text2").getValue().getText();
        }
        return text;
    }

    /** Returns boolean true if the Frame has a steps attribute otherwise false
     * @param x*/
    private boolean hasSteps(Frame x) {
        return x.getAttribute("steps") != null;
    }

    /** Returns boolean true if the Frame has a steps attribute with no value otherwise false
     * @param x*/
    private boolean isStepsEmpty(Frame x) {
        return x.getAttribute("steps").getValue().isEmpty();
    }

    /** Returns boolean true if the Frame has a steps attribute with a value of zero otherwise false
     * @param x*/
    private boolean isStepsZero(Frame x) {
        return x.getAttribute("steps").getValue().equals("0");
    }









    /** Returns the newPlayValues list */
    public List<String> getNewPlayValues() {
        return newPlayValues;
    }

    /** Returns the newBackValues list */
    public List<String> getNewBackValues() {
        return newBackValues;
    }

    /** Returns the newOtherValues list */
    public List<String> getNewOtherValues() {
        return newOtherValues;
    }

    /** Returns the playEvents list */
    public List<XmlAttribute> getPlayEvents() {
        return playEvents;
    }

    /** Returns the backEvents list */
    public List<XmlAttribute> getBackEvents() {
        return backEvents;
    }

    /** Returns the otherEvents list */
    public List<XmlAttribute> getOtherEvents() {
        return otherEvents;
    }

    /** Returns the frames array */
    @Contract(pure = true)
    public XmlTag[] getFrames() {
        return frames;
    }

    /** Returns the nodeCount */
    public int getFrameCount() {
        return frameCount;
    }

    /** Returns the nodeAttributes array */
    public String[][] getFrameAttributes() {
        return frameAttributes;
    }

    /** Returns the oldNodeValues array */
    public String[][] getOldFrameValues() {
        return oldFrameValues;
    }

    /** Returns the newNodeValues array */
    public String[][] getNewFrameValues() {
        return newFrameValues;
    }
}