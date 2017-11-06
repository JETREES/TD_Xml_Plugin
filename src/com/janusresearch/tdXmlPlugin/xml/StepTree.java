/*
* This class processes the StepTree nodes and gathers all
* necessary attributes and stores them in a 2 dimensional array
*/

package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.janusresearch.tdXmlPlugin.dom.XmlRoot;

public class StepTree {
    public static XmlAttribute[][] nodeAttributes;
    public static String[][] oldToNewNodeValues;

    @SuppressWarnings("ConstantConditions")
    public StepTree(XmlRoot xmlRoot) {

        //Get the StepTree XmlTag
        XmlTag stepTree = xmlRoot.getXmlTag().findFirstSubTag("StepTree");

        //Get all sub tags from StepTree
        XmlTag[] nodes = stepTree.findSubTags("node");

        //2 dimensional array stores name and id attributes from StepTree node
        nodeAttributes = new XmlAttribute[nodes.length][3];

        //2 dimensional array stores old and new values for each node, used in processing FrameChange
        oldToNewNodeValues = new String[nodes.length][6];

        int i = 0;
        for (XmlTag n : nodes) {
            //Store the name and id attributes in the array
            nodeAttributes[i][0] = n.getAttribute("name");
            nodeAttributes[i][1] = n.getAttribute("parent");
            nodeAttributes[i][2] = n.getAttribute("id");

            //store the old and new values for each node
            oldToNewNodeValues[i][0] = nodeAttributes[i][0].getValue();
            if (i < 9)
                oldToNewNodeValues[i][1] = "0" + (i + 1);
            else
                oldToNewNodeValues[i][1] = String.valueOf(i + 1);

            i++;
        }
    }
}