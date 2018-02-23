package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.util.xml.GenericAttributeValue;
import com.janusresearch.tdXmlPlugin.dialog.SubStepsDialog;
import com.janusresearch.tdXmlPlugin.dom.Module;
import com.janusresearch.tdXmlPlugin.dom.StepTreeNode;

import java.util.Objects;

@SuppressWarnings("ConstantConditions")
public class StepTree {
    private Module root;
    private int nodeCount;
    private GenericAttributeValue[][] nodeAttributes; //array stores name parent and id attributes from StepTree node
    private GenericAttributeValue[][] oldNodeValues;   //array stores old and new values for each node, used in processing FrameChange
    private String[][] newNodeValues;   //array stores old and new values for each node, used in processing FrameChange

    public StepTree(Module moduleRoot) {
        this.root = moduleRoot;
        setNodeCount();
        storeNodeAttributes();
        storeOldNodeValues();
        storeNewNodeValues();

    }

    /** Store the length of the StepTree as nodeCount */
    private void setNodeCount() {
        nodeCount = getRoot().getStepTree().getNodes().size();
    }

    /** Store the reference to every StepTree node attributes in the nodeAttributes array */
    private void storeNodeAttributes() {
        nodeAttributes = new GenericAttributeValue[nodeCount][3];
        int i = 0;
        for (StepTreeNode n : getRoot().getStepTree().getNodes()) {
            //Store the name, parent and id attributes in the array
            nodeAttributes[i][0] = n.getName();
            nodeAttributes[i][1] = n.getParentAttr();
            nodeAttributes[i][2] = n.getId();
            i++;
        }
    }

    /** Store the old values for each node in the oldNodeValues array */
    private void storeOldNodeValues() {
        oldNodeValues = new GenericAttributeValue[nodeCount][3];
        int i = 0;
        for (GenericAttributeValue[] a : getNodeAttributes()) {
            //store the old values for each node
            oldNodeValues[i][0] = a[0];
            oldNodeValues[i][1] = a[1];
            oldNodeValues[i][2] = a[2];
            i++;
        }
    }

    /** Store the new values for each node in the newNodeValues array */
    private void storeNewNodeValues() {
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
                String oldParent = getOldNodeValues()[i][1].getStringValue();
                if (!Objects.equals(oldParent, "0") && SubStepsDialog.hasSubSteps()) {
                    int j = 0;
                    for (GenericAttributeValue[] n : getOldNodeValues()) {
                        if (Objects.equals(oldParent, n[0].getStringValue())) {
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

    /** Returns the nodeCount */
    public int getNodeCount() {
        return nodeCount;
    }

    /** Returns the nodeAttributes array */
    public GenericAttributeValue[][] getNodeAttributes() {
        return nodeAttributes;
    }

    /** Returns the oldNodeValues array */
    public GenericAttributeValue[][] getOldNodeValues() {
        return oldNodeValues;
    }

    /** Returns the newNodeValues array */
    public String[][] getNewNodeValues() {
        return newNodeValues;
    }

    private Module getRoot() {
        return root;
    }
}