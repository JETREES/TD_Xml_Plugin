package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.janusresearch.tdXmlPlugin.dialog.OptionsDialog;
import com.janusresearch.tdXmlPlugin.dom.XmlRoot;

import java.util.Objects;

@SuppressWarnings("ConstantConditions")
public class StepTree {
    private XmlRoot root;
    private XmlTag[] nodes;
    private int nodeCount;
    private XmlAttribute[][] nodeAttributes; //array stores name parent and id attributes from StepTree node
    private String[][] oldNodeValues;   //array stores old and new values for each node, used in processing FrameChange
    private String[][] newNodeValues;   //array stores old and new values for each node, used in processing FrameChange

    public StepTree(XmlRoot xmlRoot) {
        root = xmlRoot;
    }

    /** Store every node in the Step Tree in the nodes array */
    private void storeNodes() {
        //Get all node sub tags from StepTree
        nodes = root.getXmlTag().findFirstSubTag("StepTree").findSubTags("node");
        setNodeCount();
    }

    /** Store the reference to every StepTree node attributes in the nodeAttributes array */
    public void storeNodeAttributes() {
        storeNodes();
        //Set array size based on nodes array length
        nodeAttributes = new XmlAttribute[nodeCount][3];
        int i = 0;
        for (XmlTag n : getNodes()) {
            //Store the name, parent and id attributes in the array
            nodeAttributes[i][0] = n.getAttribute("name");
            nodeAttributes[i][1] = n.getAttribute("parent");
            nodeAttributes[i][2] = n.getAttribute("id");
            i++;
        }
    }

    /** Store the old values for each node in the oldNodeValues array */
    public void storeOldNodeValues() {
        //Set array size based on nodes array length
        oldNodeValues = new String[nodeCount][3];
        int i = 0;
        for (XmlAttribute[] a : getNodeAttributes()) {
            //store the old and new values for each node
            oldNodeValues[i][0] = a[0].getValue();
            oldNodeValues[i][1] = a[1].getValue();
            oldNodeValues[i][2] = a[2].getValue();
            i++;
        }
    }

    /** Store the length of the StepTree as nodeCount */
    private void setNodeCount() {
        nodeCount = getNodes().length;
    }

    /** Store the new values for each node in the newNodeValues array */
    public void storeNewNodeValues() {
        //Set array size based on nodes array length
        newNodeValues = new String[nodeCount][3];
        int i = 0;
        for (String[] s : getNewNodeValues()) {
            if (i < 9) {
                s[0] = "0" + (i + 1);
                s[2] = "0" + (i + 1);
            }
            else {
                s[0] = String.valueOf(i + 1);
                s[2] = String.valueOf(i + 1);
            }
            s[1] = "0";
            i++;
        }

        //This determines if we need to find the new value for a nodes
        //parent storing it in the newNodeValues array
        i = 0;
        if (OptionsDialog.subStepsIndented) {
            for (String[] s : getNewNodeValues()) {
                String oldParent = getOldNodeValues()[i][1];
                if (!Objects.equals(oldParent, "0")) {
                    int j = 0;
                    for (String[] n : getOldNodeValues()) {
                        if (Objects.equals(oldParent, n[0])) {
                            s[1] = getNewNodeValues()[j][0];
                            break;
                        }
                        j++;
                    }
                }
                i++;
            }
        }
    }










    /** Returns the nodes array */
    private XmlTag[] getNodes() {
        return nodes;
    }

    /** Returns the nodeCount */
    public int getNodeCount() {
        return nodeCount;
    }

    /** Returns the nodeAttributes array */
    public XmlAttribute[][] getNodeAttributes() {
        return nodeAttributes;
    }

    /** Returns the oldNodeValues array */
    public String[][] getOldNodeValues() {
        return oldNodeValues;
    }

    /** Returns the newNodeValues array */
    public String[][] getNewNodeValues() {
        return newNodeValues;
    }
}