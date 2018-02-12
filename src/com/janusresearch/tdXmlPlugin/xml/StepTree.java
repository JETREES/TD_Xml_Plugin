package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.janusresearch.tdXmlPlugin.dialog.SubStepsDialog;
import com.janusresearch.tdXmlPlugin.dom.Module;
import org.jetbrains.annotations.Contract;

import java.util.Objects;

@SuppressWarnings("ConstantConditions")
public class StepTree {
    private Module root;
    private XmlTag[] nodes;
    private int nodeCount;
    private XmlAttribute[][] nodeAttributes; //array stores name parent and id attributes from StepTree node
    private String[][] oldNodeValues;   //array stores old and new values for each node, used in processing FrameChange
    private String[][] newNodeValues;   //array stores old and new values for each node, used in processing FrameChange

    public StepTree(Module moduleRoot) {
        root = moduleRoot;
    }

    /** Store every node tag in the Step Tree in the nodes array */
    public void storeNodes() {
        //Get all node sub tags from StepTree
        nodes = root.getXmlTag().findFirstSubTag("StepTree").findSubTags("node");
        setNodeCount();
    }

    /** Store the length of the StepTree as nodeCount */
    private void setNodeCount() {
        nodeCount = getNodes().length;
    }

    /** Store the reference to every StepTree node attributes in the nodeAttributes array */
    public void storeNodeAttributes() {
        storeNodes();
        //Set array size based on nodes array length
        nodeAttributes = new XmlAttribute[nodeCount][3];
        int i = 0;
        for (XmlTag x : getNodes()) {
            //Store the name, parent and id attributes in the array
            nodeAttributes[i][0] = x.getAttribute("name");
            nodeAttributes[i][1] = x.getAttribute("parent");
            nodeAttributes[i][2] = x.getAttribute("id");
            i++;
        }
    }

    /** Store the old values for each node in the oldNodeValues array */
    public void storeOldNodeValues() {
        //Set array size based on nodes array length
        oldNodeValues = new String[nodeCount][3];
        int i = 0;
        for (XmlAttribute[] x : getNodeAttributes()) {
            //store the old values for each node
            oldNodeValues[i][0] = x[0].getValue();
            oldNodeValues[i][1] = x[1].getValue();
            oldNodeValues[i][2] = x[2].getValue();
            i++;
        }
    }

    /** Store the new values for each node in the newNodeValues array */
    public void storeNewNodeValues() {
        //Set array size based on nodes array length
        newNodeValues = new String[nodeCount][3];

        //This determines if we need to find the new value for a nodes
        //parent storing it in the newNodeValues array
        int i = 0;
        for (String[] s : getNewNodeValues()) {
            if (i == 0) {
                s[0] = "01";
                s[1] = "0";
                s[2] = "01";
            }
            else {
                if (i < 9) {
                    s[0] = "0" + (i + 1);
                    s[2] = "0" + (i + 1);
                }
                else {
                    s[0] = String.valueOf(i + 1);
                    s[2] = String.valueOf(i + 1);
                }
                String oldParent = getOldNodeValues()[i][1];
                if (!Objects.equals(oldParent, "0") && SubStepsDialog.hasSubSteps()) {
                    int j = 0;
                    for (String[] n : getOldNodeValues()) {
                        if (Objects.equals(oldParent, n[0])) {
                            s[1] = getNewNodeValues()[j][0];
                            break;
                        }
                        j++;
                    }
                }
                else {
                    s[1] = "0";
                }
            }
            i++;
        }
    }

    /** Returns the nodes array */
    @Contract(pure = true)
    XmlTag[] getNodes() {
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