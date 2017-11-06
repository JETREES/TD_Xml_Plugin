package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.janusresearch.tdXmlPlugin.dom.XmlRoot;

public class FrameSet {
    public static XmlTag[] frames;
    public static String[][] oldToNewFrameValues;
    public static XmlAttribute[][] frameAttributes;

    @SuppressWarnings("ConstantConditions")
    public FrameSet(XmlRoot xmlRoot) {

        //Get the FrameSet XmlTag
        XmlTag frameSet = xmlRoot.getXmlTag().findFirstSubTag("FrameSet");

        //Get all sub tags from FrameSet
        frames = frameSet.findSubTags("Frame");

        //2 dimensional array stores id, node and weight attributes from FrameSet Frame
        frameAttributes = new XmlAttribute[frames.length][3];

        //2 dimensional array stores old and new values for each node, used in processing FrameChange
        oldToNewFrameValues = new String[frames.length][2];

        int i = 0;
        for (XmlTag f : frames) {
            //Store the id, node and weight attributes in the array
            frameAttributes[i][0] = f.getAttribute("id");
            frameAttributes[i][1] = f.getAttribute("node");
            frameAttributes[i][2] = f.getAttribute("weight");

            //store the old and new values for each frame
            oldToNewFrameValues[i][0] = f.getAttribute("id").getValue();
            if (i < 9)
                oldToNewFrameValues[i][1] = "0" + (i + 1);
            else
                oldToNewFrameValues[i][1] = String.valueOf(i + 1);
            i++;
        }
    }
}