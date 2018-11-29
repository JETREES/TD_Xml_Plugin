package com.janusresearch.tdXmlPlugin.xml.inspections;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.codeInspection.ex.BaseLocalInspectionTool;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.xml.XmlElement;
import com.intellij.psi.xml.XmlTag;
import com.janusresearch.tdXmlPlugin.xml.inspections.quickFixes.LessonTitleQuickFix;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.janusresearch.tdXmlPlugin.file.FileHandler.getConfigTitle;

/**
 */
public class LessonTitleInspection extends BaseLocalInspectionTool {
    @Nls
    @NotNull
    @Override
    public String getGroupDisplayName() {
        return XmlBundle.message("inspections.xml.lessonTitle");
    }

    @Nls
    @NotNull
    @Override
    public String getDisplayName() {
        return XmlBundle.message("inspections.xml.lessonTitle");
    }

    @NotNull
    @Override
    public String getShortName() {
        return "LessonTitle";
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor( @NotNull final ProblemsHolder holder, boolean isOnTheFly ) {
        return new XmlElementVisitor() {
            @Override
            public void visitXmlElement(XmlElement element) {
                if( isProblematic( element ) ) {
                    PsiElement thisElement;
                    if (element.getContext() != null) {
                        if(element.getClass().getName().endsWith( "XmlTokenImpl" )) {
                            if (element.getText().equals("title") && ((XmlTag) element.getParent().getParent()).getName().equals("Module")) {
                                String text = element.getContext().getChildren()[2].getText();
                                if (!text.equals("\"\"")) {
                                    thisElement = element.getContext().getChildren()[2].getFirstChild().getNextSibling();
                                } else {
                                    thisElement = element.getParent();
                                }
                                holder.registerProblem(thisElement, XmlBundle.message("inspections.xml.lessonTitle.error"), ProblemHighlightType.GENERIC_ERROR_OR_WARNING, new Fix(element));
                            }
                        }
                        else if( element.getClass().getName().endsWith( "XmlTextImpl" )) {
                            if (((XmlTag) element.getParent()).getName().equals("Title")) {
                                thisElement = element.getContext().getChildren()[3];
                                holder.registerProblem(thisElement, XmlBundle.message("inspections.xml.lessonTitle.error"), ProblemHighlightType.GENERIC_ERROR_OR_WARNING, new Fix(element));
                            }
                            else if (element.getText().startsWith("\"This concludes the")){
                                holder.registerProblem(element, XmlBundle.message("inspections.xml.lessonTitle.error"), ProblemHighlightType.GENERIC_ERROR_OR_WARNING, TextRange.create(20, element.getTextLength() - 70), new Fix(element));
                            }
                        }
                    }
                    return;
                }
                super.visitXmlElement(element);
            }
        };
    }

    private static boolean isProblematic( XmlElement elem ) {
        String text;
        //checks Module title="" for Lesson Title problems
        if (elem.getText().equals("title") && ((XmlTag) elem.getParent().getParent()).getName().equals("Module")) {
            text = Objects.requireNonNull(elem.getContext()).getChildren()[2].getFirstChild().getNextSibling().getText();
            return !Objects.requireNonNull(text).equals(getConfigTitle(elem));
        }

        //checks Description Title xml tag for Lesson Title problems
        if (elem.getClass().getName().endsWith("XmlTextImpl") && ((XmlTag) elem.getParent()).getName().equals("Title")) {
            text = elem.getText();
            return !Objects.equals(text, getConfigTitle(elem));
        }

        //checks Conclusion Frame for Lesson Title problems
        if (elem.getText().startsWith("\"This concludes the") && elem.getText().endsWith("Lesson Manager.\"")) {
            text = elem.getText().replaceAll("\"This concludes the (.*) lesson.*", "$1");
            return !Objects.equals(text, getConfigTitle(elem));
        }

        return false;
    }

    class Fix implements LocalQuickFix {
        private final LessonTitleQuickFix _fix;

        Fix(PsiElement elem) {
            _fix = new LessonTitleQuickFix( elem );
        }

        @NotNull
        public String getName() {
            return _fix.getText();
        }

        @NotNull
        public String getFamilyName() {
            return "Lesson Title fix";
        }

        public void applyFix( @NotNull Project project, @NotNull ProblemDescriptor descriptor ) {
            PsiElement element = descriptor.getPsiElement();
            if( element == null ) {
                return;
            }
            final PsiFile psiFile = element.getContainingFile();
            if( _fix.isAvailable( project, null, psiFile ) ) {
                _fix.invoke( project, null, psiFile );
            }
        }
    }
}
