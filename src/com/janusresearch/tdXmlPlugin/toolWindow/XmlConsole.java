package com.janusresearch.tdXmlPlugin.toolWindow;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.psi.PsiFile;
import com.intellij.ui.RoundedLineBorder;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.janusresearch.tdXmlPlugin.xml.FrameSet;
import com.janusresearch.tdXmlPlugin.xml.StepTree;
import icons.PluginIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class XmlConsole implements ToolWindowFactory{
    private static ConsoleView console;
    private static ToolWindow myToolWindow;
    private JPanel xmlToolWindowContent;
    private JPanel consolePanel;
    private JPanel controlPanel;
    private JButton clearBtn;
    private static JButton clearBtn_copy;

    public XmlConsole() {}

    private void initClearBtn() {
        clearBtn.setIcon(PluginIcons.clear);
        clearBtn.setDisabledIcon(PluginIcons.clearDisabled);
        clearBtn.setBorder(null);
        clearBtn.setContentAreaFilled(false);

        //Copy the clearBtn button to a static variable so it can be called from the static print method
        clearBtn_copy = clearBtn;

        clearBtn.addActionListener(e -> {
            console.clear();
            clearBtn.setEnabled(false);
            clearBtn.setBorder(null);
            clearBtn.setBorderPainted(false);
            clearBtn.setBackground(null);
            clearBtn.setContentAreaFilled(false);
            myToolWindow.hide(null);
        });

        clearBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (clearBtn.isEnabled()) {
                    clearBtn.setBorder(new RoundedLineBorder(XmlColors.icon.BORDER, 6));
                    clearBtn.setBorderPainted(true);
                    clearBtn.setContentAreaFilled(true);
                    clearBtn.setBackground(XmlColors.icon.BACKGROUND);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (clearBtn.isEnabled()) {
                    clearBtn.setBorder(null);
                    clearBtn.setBorderPainted(false);
                    clearBtn.setBackground(null);
                    clearBtn.setContentAreaFilled(false);
                }
            }
        });
    }


    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        myToolWindow = toolWindow;
        myToolWindow.setIcon(PluginIcons.xml);
        //Create a console, get its component and then add it to the console panel in the tool window
        console = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
        JComponent consoleComponent = console.getComponent();
        consolePanel.add(consoleComponent, BorderLayout.CENTER);
        createUIComponents();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(xmlToolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    public static void printLessonModifications(@NotNull PsiFile psiFile, StepTree stepTree, FrameSet frameSet) {
        myToolWindow.show(null);

        //Print lesson number to console
        console.print("Renumber Lesson:", XmlConsoleViewContentType.TITLE_OUTPUT);
        console.print(" " + psiFile.getName() + "\n", XmlConsoleViewContentType.ELEMENT_OUTPUT);

        //Print StepTree Modifications to console
        console.print("Step Tree Modifications\n", XmlConsoleViewContentType.TITLE_OUTPUT);
        int i;
        for (i = 0; i < stepTree.getNodeCount(); i++) {
            int spaces = 14 - stepTree.getOldNodeValues()[i][0].length() - stepTree.getOldNodeValues()[i][1].length() - stepTree.getOldNodeValues()[i][2].length();
            console.print("<node ", XmlConsoleViewContentType.ELEMENT_OUTPUT);
            console.print("name=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            console.print("\"" + stepTree.getOldNodeValues()[i][0] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            console.print("parent=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            console.print("\"" + stepTree.getOldNodeValues()[i][1] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            console.print("id=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            console.print("\"" + stepTree.getOldNodeValues()[i][2] + "\"", XmlConsoleViewContentType.VALUE_OUTPUT);
            console.print("/>  ", XmlConsoleViewContentType.ELEMENT_OUTPUT);
            for (int j = 0; j < spaces; j++) {
                console.print(" ", XmlConsoleViewContentType.CHANGE_OUTPUT);
            }
            console.print("-->  ", XmlConsoleViewContentType.CHANGE_OUTPUT);
            console.print("<node ", XmlConsoleViewContentType.ELEMENT_OUTPUT);
            console.print("name=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            console.print("\"" + stepTree.getNewNodeValues()[i][0] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            console.print("parent=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            console.print("\"" + stepTree.getNewNodeValues()[i][1] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            console.print("id=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            console.print("\"" + stepTree.getNewNodeValues()[i][2] + "\"", XmlConsoleViewContentType.VALUE_OUTPUT);
            console.print("/>\n", XmlConsoleViewContentType.ELEMENT_OUTPUT);
        }
        console.print("\n", ConsoleViewContentType.NORMAL_OUTPUT);

        //Print FrameSet Modifications to console
        console.print("Frame Set Modifications\n", XmlConsoleViewContentType.TITLE_OUTPUT);
        for (i = 0; i < frameSet.getFrameCount(); i++) {
            int spaces = 13 - frameSet.getOldFrameValues()[i][0].length() - frameSet.getOldFrameValues()[i][1].length() - frameSet.getOldFrameValues()[i][2].length();
            console.print("<Frame ", XmlConsoleViewContentType.ELEMENT_OUTPUT);
            console.print("id=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            console.print("\"" + frameSet.getOldFrameValues()[i][0] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            console.print("node=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            console.print("\"" + frameSet.getOldFrameValues()[i][1] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            console.print("weight=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            console.print("\"" + frameSet.getOldFrameValues()[i][2] + "\"", XmlConsoleViewContentType.VALUE_OUTPUT);
            console.print("/>  ", XmlConsoleViewContentType.ELEMENT_OUTPUT);
            for (int j = 0; j < spaces; j++) {
                console.print(" ", XmlConsoleViewContentType.CHANGE_OUTPUT);
            }
            console.print("-->  ", XmlConsoleViewContentType.CHANGE_OUTPUT);
            console.print("<Frame ", XmlConsoleViewContentType.ELEMENT_OUTPUT);
            console.print("id=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            console.print("\"" + frameSet.getNewFrameValues()[i][0] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            console.print("node=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            console.print("\"" + frameSet.getNewFrameValues()[i][1] + "\" ", XmlConsoleViewContentType.VALUE_OUTPUT);
            console.print("weight=", XmlConsoleViewContentType.ATTRIBUTE_OUTPUT);
            console.print("\"" + frameSet.getNewFrameValues()[i][2] + "\"", XmlConsoleViewContentType.VALUE_OUTPUT);
            console.print("/>\n", XmlConsoleViewContentType.ELEMENT_OUTPUT);
        }
        console.print("\n", ConsoleViewContentType.NORMAL_OUTPUT);
        clearBtn_copy.setEnabled(true);

    }

    private void createUIComponents() {
        initClearBtn();
    }
}