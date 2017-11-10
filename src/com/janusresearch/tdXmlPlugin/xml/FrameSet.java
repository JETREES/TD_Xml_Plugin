package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.janusresearch.tdXmlPlugin.dom.XmlRoot;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("ConstantConditions")
public class FrameSet {
    private XmlRoot root;
    private XmlTag[] frames;
    private int frameCount;
    private XmlAttribute[][] frameAttributes;
    private String[][] oldFrameValues;
    private String[][] newFrameValues;
    private List<XmlAttribute> playEvents = new ArrayList<>();
    private List<XmlAttribute> backEvents = new ArrayList<>();
    private List<XmlAttribute> otherEvents = new ArrayList<>();
    private List<String> newPlayValues = new ArrayList<>();
    private List<String> newBackValues = new ArrayList<>();
    private List<String> newOtherValues = new ArrayList<>();

    public FrameSet(XmlRoot xmlRoot) {
        root = xmlRoot;
    }

    /** Store every Frame sub tag from Frame Set in the frames array */
    private void storeFrames() {
        frames = root.getXmlTag().findFirstSubTag("FrameSet").findSubTags("Frame");
        setFrameCount();
    }

    /** Store the length of the FrameSet as frameCount */
    private void setFrameCount() {
        frameCount = getFrames().length;
    }

    /** Store the reference to all Frame attributes from FrameSet in the frameAttributes array */
    public void storeFrameAttributes() {
        storeFrames();
        frameAttributes = new XmlAttribute[frameCount][3];
        int i = 0;
        for (XmlTag x : getFrames()) {
            frameAttributes[i][0] = x.getAttribute("id");
            frameAttributes[i][1] = x.getAttribute("node");
            frameAttributes[i][2] = x.getAttribute("weight");
            i++;
        }
    }

    /** Store the old values for each Frame in the oldFrameValues array */
    public void storeOldFrameValues() {
        oldFrameValues = new String[frameCount][3];
        int i = 0;
        for (XmlAttribute[] x : getFrameAttributes()) {
            oldFrameValues[i][0] = x[0].getValue();
            oldFrameValues[i][1] = x[1].getValue();
            oldFrameValues[i][2] = x[2].getValue();
            i++;
        }
    }

    /** Store the new values for each Frame in the newFrameValues array */
    public void storeNewFrameValues() {
        //Set array size based on nodes array length
        newFrameValues = new String[frameCount][3];
        int i = 0;
        for (String[] s : getNewFrameValues()) {
            //store the new values for each frame
            if (i < 9) {
                s[0] = "0" + (i + 1);
                s[1] = "0" + (i + 1);
                s[2] = "0" + (i + 1);
            }
            else {
                s[0] = String.valueOf(i + 1);
                s[1] = String.valueOf(i + 1);
                s[2] = String.valueOf(i + 1);
            }
            i++;
        }

        /*i = 0;
        if (OptionsDialog.subStepsHidden) {
            int j;
            //TODO add code that handles hidden sub steps
        }*/
    }

    /** Get all Event sub tags from a Frame */
    @NotNull
    private XmlTag[] getFrameEvents(XmlTag x) {
        return x.findFirstSubTag("Events").findSubTags("Event");
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
    private XmlTag[] getFrames() {
        return frames;
    }

    /** Returns the nodeCount */
    public int getFrameCount() {
        return frameCount;
    }

    /** Returns the nodeAttributes array */
    public XmlAttribute[][] getFrameAttributes() {
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