package com.janusresearch.tdXmlPlugin.xml.inspections;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.codeInspection.ex.BaseLocalInspectionTool;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.xml.XmlElement;
import com.intellij.psi.xml.XmlTag;
import com.janusresearch.tdXmlPlugin.xml.inspections.quickFixes.ModuleTitleQuickFix;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.janusresearch.tdXmlPlugin.file.FileHandler.getConfigTitle;

/**
 */
public class ModuleTitleInspection extends BaseLocalInspectionTool {
    @Nls
    @NotNull
    @Override
    public String getGroupDisplayName() {
        return XmlBundle.message("inspections.xml.module.title");
    }

    @Nls
    @NotNull
    @Override
    public String getDisplayName() {
        return XmlBundle.message("inspections.xml.module.title");
    }

    @NotNull
    @Override
    public String getShortName() {
        return "ModuleTitle";
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
                        String text = element.getContext().getChildren()[2].getText();
                        if (!text.equals("\"\"")) {
                            thisElement = element.getContext().getChildren()[2].getFirstChild().getNextSibling();
                        }
                        else {
                            thisElement = element.getParent();
                        }
                        holder.registerProblem(thisElement, XmlBundle.message("inspections.xml.title.error"), ProblemHighlightType.GENERIC_ERROR_OR_WARNING, new Fix(element));
                    }
                    return;
                }
                super.visitXmlElement(element);
            }
        };
    }

    private static boolean isProblematic( XmlElement elem ) {
        String text = elem.getText();
        String title;
        if (text.equals("title") && ((XmlTag) elem.getParent().getParent()).getName().equals("Module")) {
            title = Objects.requireNonNull(elem.getContext()).getChildren()[2].getFirstChild().getNextSibling().getText();
            return !Objects.requireNonNull(title).equals(getConfigTitle(elem));
        }
        return false;
    }

    class Fix implements LocalQuickFix {
        private final ModuleTitleQuickFix _fix;

        Fix(PsiElement elem) {
            _fix = new ModuleTitleQuickFix( elem );
        }

        @NotNull
        public String getName() {
            return _fix.getText();
        }

        @NotNull
        public String getFamilyName() {
            return "Module Title fix";
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
