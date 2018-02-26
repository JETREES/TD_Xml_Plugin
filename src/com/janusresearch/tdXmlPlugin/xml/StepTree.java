package com.janusresearch.tdXmlPlugin.xml;

import com.janusresearch.tdXmlPlugin.dialog.SubStepsDialog;
import com.janusresearch.tdXmlPlugin.dom.Module;
import com.janusresearch.tdXmlPlugin.dom.StepTreeNode;

import java.util.Objects;

@SuppressWarnings("ConstantConditions")
public class StepTree {
    private Module root;
    private int nodeCount;
    private String[][] oldNodeValues;   //array stores old and new values for each node, used in processing FrameChange
    private String[][] newNodeValues;   //array stores old and new values for each node, used in processing FrameChange

    public StepTree(Module moduleRoot) {
        this.root = moduleRoot;
        storeNewNodeValues();
    }

    /** Store the length of the StepTree as nodeCount */
    private void setNodeCount() {
        nodeCount = getRoot().getStepTree().getNodes().size();
    }

    /** Store the old values for each node in the oldNodeValues array */
    private void storeOldNodeValues() {
        oldNodeValues = new String[nodeCount][3];
        int i = 0;
        for (StepTreeNode n : getRoot().getStepTree().getNodes()) {
            //store the old values for each node
            oldNodeValues[i][0] = n.getName().getStringValue();
            oldNodeValues[i][1] = n.getParentAttr().getStringValue();
            oldNodeValues[i][2] = n.getId().getStringValue();
            i++;
        }
    }

    /** Store the new values for each node in the newNodeValues array */
    private void storeNewNodeValues() {
        setNodeCount();
        storeOldNodeValues();

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
                    for (String[] st : getOldNodeValues()) {
                        if (Objects.equals(oldParent, st[0])) {
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

    /** Returns the oldNodeValues array */
    public String[][] getOldNodeValues() {
        return oldNodeValues;
    }

    /** Returns the newNodeValues array */
    public String[][] getNewNodeValues() {
        return newNodeValues;
    }

    public Module getRoot() {
        return root;
    }
}