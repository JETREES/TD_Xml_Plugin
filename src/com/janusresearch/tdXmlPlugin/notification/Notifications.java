package com.janusresearch.tdXmlPlugin.notification;

import com.intellij.notification.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;

public class Notifications {

    private static final NotificationGroup GROUP_DISPLAY_ID_INFO =
            new NotificationGroup("Warning Events",
                    NotificationDisplayType.BALLOON, true);

    public static void showWarningMessage(String message) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                Notification notification = GROUP_DISPLAY_ID_INFO.createNotification(message, NotificationType.WARNING);
                Project[] projects = ProjectManager.getInstance().getOpenProjects();
                com.intellij.notification.Notifications.Bus.notify(notification, projects[0]);
            }
        });
    }

    public static void showErrorMessage(String message) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                Notification notification = GROUP_DISPLAY_ID_INFO.createNotification(message, NotificationType.ERROR);
                Project[] projects = ProjectManager.getInstance().getOpenProjects();
                com.intellij.notification.Notifications.Bus.notify(notification, projects[0]);
            }
        });
    }

    public static void showInformationMessage(String message) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                Notification notification = GROUP_DISPLAY_ID_INFO.createNotification(message, NotificationType.INFORMATION);
                Project[] projects = ProjectManager.getInstance().getOpenProjects();
                com.intellij.notification.Notifications.Bus.notify(notification, projects[0]);
            }
        });
    }

}
