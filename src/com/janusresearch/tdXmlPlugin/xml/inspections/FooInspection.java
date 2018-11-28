package com.janusresearch.tdXmlPlugin.xml.inspections;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.codeInspection.ex.BaseLocalInspectionTool;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.XmlElementVisitor;
import com.intellij.psi.xml.XmlElement;
import com.janusresearch.tdXmlPlugin.xml.inspections.quickFixes.FooQuickFix;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

/**
 */
public class FooInspection extends BaseLocalInspectionTool {
    @Nls
    @NotNull
    @Override
    public String getGroupDisplayName() {
        return XmlBundle.message( "inspections.sample.foo.name" );
    }

    @Nls
    @NotNull
    @Override
    public String getDisplayName() {
        return XmlBundle.message( "inspections.sample.foo.name" );
    }

    @NotNull
    @Override
    public String getShortName() {
        return "FooInspection";
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor( @NotNull final ProblemsHolder holder, boolean isOnTheFly ) {
        return new XmlElementVisitor() {
            @Override
            public void visitXmlElement(XmlElement element) {
                if( isProblematic( element ) ) {
                    holder.registerProblem( element, XmlBundle.message( "inspections.sample.foo.error" ), ProblemHighlightType.GENERIC_ERROR_OR_WARNING, new Fix( element ) );
                    return;
                }
                super.visitXmlElement(element);
            }
        };
    }

    private static boolean isProblematic( PsiElement elem ) {
        String text = elem.getText();
        if( elem.getClass().getName().endsWith( "GosuIdentifierImpl" ) ) {
            return elem.getText().startsWith("Foo");
        }
        return false;
    }

    class Fix implements LocalQuickFix {
        private final FooQuickFix _fix;

        Fix(PsiElement elem) {
            _fix = new FooQuickFix( elem );
        }

        @NotNull
        public String getName() {
            return _fix.getText();
        }

        @NotNull
        public String getFamilyName() {
            return "Foo fix";
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
