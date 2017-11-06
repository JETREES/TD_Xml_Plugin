package com.janusresearch.tdXmlPlugin.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class OptionsDialog extends DialogWrapper {
    //    public static boolean frameChange = false;
    public static boolean subStepsHidden = false;
    public static boolean subStepsIndented = false;

    public OptionsDialog() {
        super(false);
        init();
        setTitle("Lesson Contains");
    }

    @Nullable
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

//        JCheckBox frameChangeCB = new JCheckBox("FrameChange Commands");
        JCheckBox subStepsHiddenCB = new JCheckBox("Sub Steps - Hidden");
        JCheckBox subStepsIndentedCB = new JCheckBox("Sub Steps - Indented");
//        frameChangeCB.addItemListener(e -> frameChange = e.getStateChange() == ItemEvent.SELECTED);
        subStepsHiddenCB.addItemListener(e -> subStepsHidden = e.getStateChange() == ItemEvent.SELECTED);
        subStepsIndentedCB.addItemListener(e -> subStepsIndented = e.getStateChange() == ItemEvent.SELECTED);

//        panel.add(frameChangeCB);
        panel.add(subStepsHiddenCB);
        panel.add(subStepsIndentedCB);

        return panel;
    }

}
