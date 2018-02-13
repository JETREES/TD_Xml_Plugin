package com.janusresearch.tdXmlPlugin.toolWindow;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.RoundedLineBorder;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import icons.PluginIcons;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class XmlToolWindow implements ToolWindowFactory{
    private ConsoleView console;
    private static ConsoleView xmlConsole;
    private static ToolWindow myToolWindow;
    private JPanel xmlToolWindowContent;
    private JPanel consolePanel;
    private JPanel controlPanel;
    private JButton clearBtn;
    private JComponent consoleComponent;
    private static JComponent xmlConsoleComponent;
    private static JButton clearBtn_copy;

    public XmlToolWindow() {}

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        myToolWindow = toolWindow;
        myToolWindow.setIcon(PluginIcons.xml);

        //Create a console, get its component and then add it to the console panel in the tool window
        console = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
        xmlConsole = console;
        consoleComponent = console.getComponent();
        xmlConsoleComponent = consoleComponent;
        createUIComponents();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(xmlToolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    private void createUIComponents() {
        consolePanel.add(consoleComponent, BorderLayout.CENTER);
        initClearBtn();

    }

    public static void showToolWindow() {
        myToolWindow.show(null);
        if (xmlConsole.getContentSize() > 0) {
            clearBtn_copy.setEnabled(true);
        }
        else {
            clearBtn_copy.setEnabled(false);
        }
    }

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

    @Contract(pure = true)
    public static ConsoleView getXmlConsole() {
        if (!myToolWindow.isVisible()) {
            showToolWindow();
        }
        return xmlConsole;
    }
}
