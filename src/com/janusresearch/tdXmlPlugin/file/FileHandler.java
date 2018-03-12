package com.janusresearch.tdXmlPlugin.file;

import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomFileDescription;
import com.intellij.util.xml.DomManager;
import com.intellij.util.xml.impl.DomManagerImpl;
import com.janusresearch.tdXmlPlugin.dom.config.Configuration;
import com.janusresearch.tdXmlPlugin.dom.config.Device;
import com.janusresearch.tdXmlPlugin.dom.config.Group;
import com.janusresearch.tdXmlPlugin.dom.config.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class FileHandler {
    @Nullable
    public static DomElement getDomElement(PsiElement psiElement, DomManager myDomManager) {
        if (psiElement instanceof XmlTag) {
            return myDomManager.getDomElement((XmlTag)psiElement);
        } else {
            return psiElement instanceof XmlAttribute ? myDomManager.getDomElement((XmlAttribute)psiElement) : null;
        }
    }

    public static String getConfigTitle(@NotNull PsiElement psiElement) {
        String lessonTitle = "";
        String fileName = psiElement.getContainingFile().getName().replaceFirst("\\..*", "");
        String path = psiElement.getContainingFile().getVirtualFile().getPath().replaceFirst("Lessons/.*", "");
        VirtualFile vFile = LocalFileSystem.getInstance().findFileByPath(path + "Management/Config.xml");

        if (vFile != null) {
            XmlFile xmlFile = (XmlFile) PsiManager.getInstance(psiElement.getProject()).findFile(vFile);
            //Create DomManager and get config file root element
            DomManager manager = DomManager.getDomManager(psiElement.getProject());
            Configuration configRoot = Objects.requireNonNull(manager.getFileElement(xmlFile, Configuration.class)).getRootElement();
            for (Type t : configRoot.getCourse().getTypes()) {
                for (Group g : t.getGroups().getGroups()) {
                    for (Device d : g.getDevices().getDevices()) {
                        String lessonNumber = d.getModules().get(0).getNumber().getValue();
                        if (fileName.equals(lessonNumber)) {
                            lessonTitle = d.getName().getValue();
                            return lessonTitle;
                        }
                    }
                }
            }
        }
        return lessonTitle;
    }
}
