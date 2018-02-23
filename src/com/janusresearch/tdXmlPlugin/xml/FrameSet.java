package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.util.xml.GenericAttributeValue;
import com.janusresearch.tdXmlPlugin.dialog.SubStepsDialog;
import com.janusresearch.tdXmlPlugin.dom.Event;
import com.janusresearch.tdXmlPlugin.dom.Frame;
import com.janusresearch.tdXmlPlugin.dom.Module;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("ConstantConditions")
public class FrameSet {
    private Module root;
    private int frameCount;
    private GenericAttributeValue[][] frameAttributes;
    private String[][] oldFrameValues;
    private String[][] newFrameValues;
    private List<GenericAttributeValue<String>> playEvents = new ArrayList<>();
    private List<GenericAttributeValue<String>> backEvents = new ArrayList<>();
    private List<GenericAttributeValue<String>> otherEvents = new ArrayList<>();
    private List<String> newPlayValues = new ArrayList<>();
    private List<String> newBackValues = new ArrayList<>();
    private List<String> newOtherValues = new ArrayList<>();

    public FrameSet(Module moduleRoot) {
        this.root = moduleRoot;
        setFrameCount();
        storeFrameAttributes();
        storeOldFrameValues();
        storeNewFrameValues();
        processEvents();
    }

    /** Store the length of the FrameSet as frameCount */
    private void setFrameCount() {
        frameCount = getRoot().getFrameSet().getFrames().size();
    }

    /** Store all Frame attributes from FrameSet in the frameAttributes array */
    private void storeFrameAttributes() {
        frameAttributes = new GenericAttributeValue[frameCount][3];
        int i = 0;
        for (Frame f : getRoot().getFrameSet().getFrames()) {
            frameAttributes[i][0] = f.getId();
            frameAttributes[i][1] = f.getNode();
            frameAttributes[i][2] = f.getWeight();
            i++;
        }
    }

    /** Store the old values for each Frame in the oldFrameValues array */
    private void storeOldFrameValues() {
        oldFrameValues = new String[frameCount][3];
        int i = 0;
        for (GenericAttributeValue[] s : getFrameAttributes()) {
            oldFrameValues[i][0] = s[0].getStringValue();
            oldFrameValues[i][1] = s[1].getStringValue();
            oldFrameValues[i][2] = s[2].getStringValue();
            i++;
        }
    }

    /** Store the new values for each Frame in the newFrameValues array */
    private void storeNewFrameValues() {
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
                oldLastNode = getFrameAttributes()[i - 1][1].getStringValue();
                oldCurrentNode = getFrameAttributes()[i][1].getStringValue();

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

    /** Process Events from every Frame to determine new nextid values */
    private void processEvents() {
        int i = 0;
        for (Frame f : getRoot().getFrameSet().getFrames()) {
            for (Event e : f.getEvents().getEvents()) {
                GenericAttributeValue<String> nextid = e.getNextid();
                if (nextid != null) {
                    String get = e.getGet().getValue();
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
    private void addPlayEvent(GenericAttributeValue<String> a) {
        playEvents.add(a);
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

    /** Adds reference to Back nextid attribute to the list*/
    private void addBackEvent(GenericAttributeValue<String> a) {
        backEvents.add(a);
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

    /** Adds reference to Other nextid attribute to the list*/
    private void addOtherEvent(GenericAttributeValue<String> a) {
        otherEvents.add(a);
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

    /** Returns the root of the lesson file list */
    public Module getRoot() {
        return root;
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
    public List<GenericAttributeValue<String>> getPlayEvents() {
        return playEvents;
    }

    /** Returns the backEvents list */
    public List<GenericAttributeValue<String>> getBackEvents() {
        return backEvents;
    }

    /** Returns the otherEvents list */
    public List<GenericAttributeValue<String>> getOtherEvents() {
        return otherEvents;
    }

    /** Returns the nodeCount */
    public int getFrameCount() {
        return frameCount;
    }

    /** Returns the nodeAttributes array */
    public GenericAttributeValue[][] getFrameAttributes() {
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