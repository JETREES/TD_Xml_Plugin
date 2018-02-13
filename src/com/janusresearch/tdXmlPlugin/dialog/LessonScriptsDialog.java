package com.janusresearch.tdXmlPlugin.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class LessonScriptsDialog extends DialogWrapper {
    private JRadioButton projectLessons;
    private JRadioButton specificLesson;
    private JCheckBox acronymMatching;


    public LessonScriptsDialog() {
        super(false);
        init();
        setTitle("Generate Scripts for:");
    }

    @Nullable
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        ButtonGroup dialogRbGroup = new ButtonGroup();
        projectLessons = new JRadioButton("All Lessons in a Project");
        specificLesson = new JRadioButton("Specific Lesson(s)");
        acronymMatching = new JCheckBox("Include Acronym Pronunciations");
        dialogRbGroup.add(projectLessons);
        dialogRbGroup.add(specificLesson);
        setOKButtonText("OK");
        setCancelButtonText("Cancel");
        panel.add(projectLessons);
        panel.add(specificLesson);
        panel.add(acronymMatching);
        projectLessons.setSelected(true);
        acronymMatching.setSelected(true);

        return panel;
    }

    public boolean isSpecificLessons() {
        return specificLesson.isSelected();
    }

    public boolean isProjectLessons() {
        return projectLessons.isSelected();
    }

    public boolean isAcronymMatching() {
        return acronymMatching.isSelected();
    }
}
