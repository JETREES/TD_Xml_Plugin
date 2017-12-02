package com.janusresearch.tdXmlPlugin.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class OptionsDialog extends DialogWrapper {
    private static boolean subSteps = false;

    public OptionsDialog() {
        super(false);
        init();
        setTitle("Sub Steps");
    }

    @Nullable
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Does this lesson contain sub steps?");
        setButtonsAlignment(0);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        setOKButtonText("Yes");
        setCancelButtonText("No");
        panel.add(label);



//        JCheckBox frameChangeCB = new JCheckBox("FrameChange Commands");
//        JCheckBox subStepsHiddenCB = new JCheckBox("Sub Steps - Hidden");
//        JCheckBox subStepsIndentedCB = new JCheckBox("Sub Steps - Indented");
//        frameChangeCB.addItemListener(e -> frameChange = e.getStateChange() == ItemEvent.SELECTED);
//        subStepsHiddenCB.addItemListener(e -> subStepsHidden = e.getStateChange() == ItemEvent.SELECTED);
//        subStepsIndentedCB.addItemListener(e -> subStepsIndented = e.getStateChange() == ItemEvent.SELECTED);

//        panel.add(frameChangeCB);
//        panel.add(subStepsHiddenCB);
//        panel.add(subStepsIndentedCB);

        return panel;
    }

    public static boolean hasSubSteps() {
        return subSteps;
    }
    public void setSubSteps(boolean bool) {
        subSteps = bool;
    }
}
