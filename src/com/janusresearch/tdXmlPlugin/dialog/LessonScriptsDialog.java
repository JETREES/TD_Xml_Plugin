package com.janusresearch.tdXmlPlugin.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class LessonScriptsDialog extends DialogWrapper {
    private static boolean specificLessons = false;
    private static boolean projectLessons = false;

    public LessonScriptsDialog() {
        super(false);
        init();
        setTitle("Generate Lesson Scripts For:");
    }

    @Nullable
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JCheckBox specificLesson = new JCheckBox("One or More Lessons");
        JCheckBox projectLessons = new JCheckBox("All Lessons in a Project");
        specificLesson.addItemListener(e -> LessonScriptsDialog.specificLessons = e.getStateChange() == ItemEvent.SELECTED);
        projectLessons.addItemListener(e -> LessonScriptsDialog.projectLessons = e.getStateChange() == ItemEvent.SELECTED);
        setOKButtonText("OK");
        setCancelButtonText("Cancel");
        specificLesson.setSelected(true);

        panel.add(specificLesson);
        panel.add(projectLessons);

        return panel;
    }

    public static boolean isSpecificLessons() {
        return specificLessons;
    }
    public static boolean isProjectLessons() {
        return projectLessons;
    }
}
