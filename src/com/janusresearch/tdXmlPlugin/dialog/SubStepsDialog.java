package com.janusresearch.tdXmlPlugin.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class SubStepsDialog extends DialogWrapper {
    private static boolean subSteps = false;

    public SubStepsDialog() {
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

        return panel;
    }

    public static boolean hasSubSteps() {
        return subSteps;
    }
    public void setSubSteps(boolean bool) {
        subSteps = bool;
    }
}
