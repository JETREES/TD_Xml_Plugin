package com.janusresearch.tdXmlPlugin.xml.highlighting;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.*;
import com.intellij.util.xml.highlighting.*;
import com.intellij.util.xml.impl.DomManagerImpl;
import com.janusresearch.tdXmlPlugin.debug.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class XmlAnnotator implements Annotator {
    @Override
    /*public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
        *//*if (element.getParent() instanceof Module) {
            XmlAttribute title = element.getXmlTag().getAttribute("title");
            String titles = title != null ? title.getValue() : null;
            if (Objects.equals(titles, "")) {

                holder.createProblem(element, HighlightSeverity.ERROR, "A Lesson Title is Required");
            }
        }*//*

        *//*if (element.getParent() instanceof StepTreeNode) {
            XmlAttribute name = element.getXmlTag().getAttribute("name");
            String names = name != null ? name.getValue() : null;
            if (Objects.equals(names, "")) {

                holder.createProblem(element, HighlightSeverity.ERROR, "name attribute must have a value");
            }
        }*//*
    }*/

    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder holder) {
        DomManagerImpl domManager = DomManagerImpl.getDomManager(psiElement.getProject());
        DomFileDescription description = domManager.getDomFileDescription(psiElement);
        if (description != null) {
            DomElement domElement = getDomElement(psiElement, domManager);
            if (domElement != null) {
                String xmlElementName = domElement.getXmlElementName();

                switch (xmlElementName) {
                    case "title" :
                        if (Objects.equals(domElement.toString(), "")) {
                                PsiElement[] children = new PsiElement[0];
                                if (domElement.getXmlElement() != null) {
                                        children = domElement.getXmlElement().getChildren();
                                }
                                holder.createErrorAnnotation(children[children.length - 1], "Enter a lesson title."); //red squiggly line with hover text
//                                holder.createInfoAnnotation(children[children.length - 1], "Enter a lesson title.");  //no visible indication but has hover text
//                                holder.createWeakWarningAnnotation(children[children.length - 1], "Enter a lesson title."); //yellow squiggly line with hover text
//                                holder.createWarningAnnotation(children[children.length - 1], "Enter a lesson title."); //yellow highlight with hover text
                        }
                        break;
                    case "Title" :
                    case "Objective" :
                    case "Acquire" :
                    case "Practice" :
                    case "Validate" :
                    case "Default" :
                        if (Objects.equals(domElement.toString(), "")) {
                            holder.createErrorAnnotation(psiElement, "A lesson title is required.");
                        }
                        break;
                    default:
                        break;
                }

                /*if (Objects.equals(xmlElementName, "title") && Objects.equals(domElement.toString(), "")) {
                    PsiElement[] children = new PsiElement[0];
                    if (domElement.getXmlElement() != null) {
                        children = domElement.getXmlElement().getChildren();
                    }
                    holder.createErrorAnnotation(children[children.length - 1], "A lesson title is required.");
                }*/

               /* if (Objects.equals(xmlElementName, "title") && Objects.equals(domElement.toString(), "")) {
                    holder.createErrorAnnotation(children[children.length - 1], "A lesson title is required.");
                }*/
            }
        }

    }

    @Nullable
    private static DomElement getDomElement(PsiElement psiElement, DomManager myDomManager) {
        if (psiElement instanceof XmlTag) {
            return myDomManager.getDomElement((XmlTag)psiElement);
        } else {
            return psiElement instanceof XmlAttribute ? myDomManager.getDomElement((XmlAttribute)psiElement) : null;
        }
    }

    /*public final void runInspection(DomElement domElement, List<Annotation> list) {
        DomFileElement root = DomUtil.getFileElement(domElement);
        this.runInspection(this.getAnnotationsManager(domElement).getMockInspection(root), root, list);
    }

    public <T extends DomElement> void runInspection(@Nullable DomElementsInspection<T> inspection, DomFileElement<T> fileElement, List<Annotation> toFill) {
        if (inspection != null) {
            DomElementAnnotationsManagerImpl annotationsManager = this.getAnnotationsManager(fileElement);
            if (!DomElementAnnotationsManagerImpl.isHolderUpToDate(fileElement) || !annotationsManager.getProblemHolder(fileElement).isInspectionCompleted(inspection)) {
                DomElementAnnotationHolderImpl annotationHolder = new DomElementAnnotationHolderImpl(true);
                inspection.checkFileElement(fileElement, annotationHolder);
                annotationsManager.appendProblems(fileElement, annotationHolder, inspection.getClass());
                Iterator var6 = annotationHolder.iterator();

                while(var6.hasNext()) {
                    DomElementProblemDescriptor descriptor = (DomElementProblemDescriptor)var6.next();
                    toFill.addAll(descriptor.getAnnotations());
                }

                toFill.addAll(annotationHolder.getAnnotations());
            }
        }
    }

    protected DomElementAnnotationsManagerImpl getAnnotationsManager(DomElement element) {
        return (DomElementAnnotationsManagerImpl) DomElementAnnotationsManager.getInstance(element.getManager().getProject());
    }*/
}
