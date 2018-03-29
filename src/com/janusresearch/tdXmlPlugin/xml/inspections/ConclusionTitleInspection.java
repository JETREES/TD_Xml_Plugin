package com.janusresearch.tdXmlPlugin.xml.inspections;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.codeInspection.ex.BaseLocalInspectionTool;
import com.intellij.codeInspection.ex.ProblemDescriptorImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.XmlElementVisitor;
import com.intellij.psi.xml.XmlElement;
import com.janusresearch.tdXmlPlugin.xml.inspections.quickFixes.ConclusionTitleQuickFix;
import com.janusresearch.tdXmlPlugin.xml.inspections.quickFixes.FooQuickFix;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.janusresearch.tdXmlPlugin.file.FileHandler.getConfigTitle;

/**
 */
public class ConclusionTitleInspection extends BaseLocalInspectionTool {
    @Nls
    @NotNull
    @Override
    public String getGroupDisplayName() {
        return XmlBundle.message( "inspections.xml.conclusion.title" );
    }

    @Nls
    @NotNull
    @Override
    public String getDisplayName() {
        return XmlBundle.message( "inspections.xml.conclusion.title" );
    }

    @NotNull
    @Override
    public String getShortName() {
        return "ConclusionTitle";
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor( @NotNull final ProblemsHolder holder, boolean isOnTheFly ) {
        return new XmlElementVisitor() {
            @Override
            public void visitXmlElement(XmlElement element) {
                if( isProblematic( element ) ) {
                    holder.registerProblem(element, XmlBundle.message("inspections.xml.title.error"), ProblemHighlightType.GENERIC_ERROR_OR_WARNING, TextRange.create(20, element.getTextLength() - 70), new Fix(element));
                    return;
                }
                super.visitXmlElement(element);
            }
        };
    }

    private static boolean isProblematic( PsiElement elem ) {
        String text;
        if( elem.getClass().getName().endsWith( "XmlTextImpl" ) && elem.getText().startsWith("\"This concludes the")) {
            text = elem.getText().replaceAll("\"This concludes the (.*) lesson.*", "$1");
            return !text.equals(getConfigTitle(elem));
        }
        return false;
    }

    class Fix implements LocalQuickFix {
        private final ConclusionTitleQuickFix _fix;

        Fix(PsiElement elem) {
            _fix = new ConclusionTitleQuickFix( elem );
        }

        @NotNull
        public String getName() {
            return _fix.getText();
        }

        @NotNull
        public String getFamilyName() {
            return "Conclusion Lesson Title fix";
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
