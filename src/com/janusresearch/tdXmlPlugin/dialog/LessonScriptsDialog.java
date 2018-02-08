package com.janusresearch.tdXmlPlugin.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class LessonScriptsDialog extends DialogWrapper {
    private static boolean currentLesson = false;
    private static boolean projectLessons = false;

    public LessonScriptsDialog() {
        super(false);
        init();
        setTitle("Generate Lesson Scripts");
    }

    @Nullable
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JCheckBox currentLesson = new JCheckBox("Current Lesson");
        JCheckBox projectLessons = new JCheckBox("All Project Lessons");
        currentLesson.addItemListener(e -> LessonScriptsDialog.currentLesson = e.getStateChange() == ItemEvent.SELECTED);
        projectLessons.addItemListener(e -> LessonScriptsDialog.projectLessons = e.getStateChange() == ItemEvent.SELECTED);
        setOKButtonText("Generate");
        setCancelButtonText("Cancel");
        currentLesson.setSelected(true);

        panel.add(currentLesson);
        panel.add(projectLessons);

        return panel;
    }

    public static boolean isCurrentLesson() {
        return currentLesson;
    }
    public static boolean isProjectLessons() {
        return projectLessons;
    }
}
