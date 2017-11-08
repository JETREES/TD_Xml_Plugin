package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.janusresearch.tdXmlPlugin.dialog.OptionsDialog;
import com.janusresearch.tdXmlPlugin.dom.XmlRoot;

@SuppressWarnings("ConstantConditions")
public class FrameSet {
    private XmlRoot root;
    private XmlTag[] frames;
    private int frameCount;
    private XmlAttribute[][] frameAttributes;
    private String[][] oldFrameValues;
    private String[][] newFrameValues;

    public FrameSet(XmlRoot xmlRoot) {
        root = xmlRoot;
    }

    /** Store every Frame from Frame Set in the frames array */
    private void storeFrames() {
        //Get all Frame sub tags from FrameSet
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
        //Set array size based on frames array length
        frameAttributes = new XmlAttribute[frameCount][3];
        int i = 0;
        for (XmlTag x : getFrames()) {
            //Store the id, node and weight attributes in the array
            frameAttributes[i][0] = x.getAttribute("id");
            frameAttributes[i][1] = x.getAttribute("node");
            frameAttributes[i][2] = x.getAttribute("weight");
            i++;
        }
    }

    /** Store the old values for each Frame in the oldFrameValues array */
    public void storeOldFrameValues() {
        //Set array size based on frames array length
        oldFrameValues = new String[frameCount][3];
        int i = 0;
        for (XmlAttribute[] x : getFrameAttributes()) {
            //store the old values for each Frame
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

        i = 0;
        if (OptionsDialog.subStepsHidden) {
            int j;
            //TODO add code that handles hidden sub steps
        }
    }

    /** Returns the frames array */
    public XmlTag[] getFrames() {
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