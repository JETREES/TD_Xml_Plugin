package com.janusresearch.tdXmlPlugin.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.execution.configuration.BrowseModuleValueActionListener;

public class XmlFileBrowser extends BrowseModuleValueActionListener {
    public XmlFileBrowser(Project project) {
        super(project);
    }

    @Override
    public String showDialog() {
        FileChooserDescriptor descriptor = new FileChooserDescriptor(true, true, false, false, false, true) {
            @Override
            public boolean isFileVisible(VirtualFile virtualFile, boolean showHidden) {
                if(!showHidden && virtualFile.getName().charAt(0) == '.') return false;
                return virtualFile.isDirectory() || "xml".equals(virtualFile.getExtension());
            }
        };
        descriptor.setDescription("Please select the testng.xml suite file");
        descriptor.setTitle("Select Suite");
        VirtualFile file = FileChooser.chooseFile(descriptor, getProject(), null);
        return file != null ? file.getPath() : null;
    }

}
