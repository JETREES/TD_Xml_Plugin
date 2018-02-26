package com.janusresearch.tdXmlPlugin.xml.highlighting;

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.highlighting.DomElementAnnotationHolder;
import com.intellij.util.xml.highlighting.DomElementsAnnotator;
import com.janusresearch.tdXmlPlugin.dom.Module;
import com.janusresearch.tdXmlPlugin.dom.StepTreeNode;

import java.util.Objects;

public class XmlAnnotator implements DomElementsAnnotator {
    @Override
    public void annotate(DomElement element, DomElementAnnotationHolder holder) {
        if (element.getParent() instanceof Module) {
            XmlAttribute title = element.getXmlTag().getAttribute("title");
            String titles = title != null ? title.getValue() : null;
            if (Objects.equals(titles, "")) {

                holder.createProblem(element, HighlightSeverity.ERROR, "A Lesson Title is Required");
            }
        }

        if (element.getParent() instanceof StepTreeNode) {
            XmlAttribute name = element.getXmlTag().getAttribute("name");
            String names = name != null ? name.getValue() : null;
            if (Objects.equals(names, "")) {

                holder.createProblem(element, HighlightSeverity.ERROR, "name attribute must have a value");
            }
        }
    }
}
