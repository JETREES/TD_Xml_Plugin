package com.janusresearch.tdXmlPlugin.xml.inspections;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.codeInspection.ex.BaseLocalInspectionTool;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.XmlElementVisitor;
import com.intellij.psi.xml.XmlElement;
import com.intellij.psi.xml.XmlTag;
import com.janusresearch.tdXmlPlugin.xml.inspections.quickFixes.UnnecessarySpacingQuickFix;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 */
public class UnnecessarySpacingInspection extends BaseLocalInspectionTool {
    @Nls
    @NotNull
    @Override
    public String getGroupDisplayName() {
        return XmlBundle.message( "inspections.xml.text.spacing" );
    }

    @Nls
    @NotNull
    @Override
    public String getDisplayName() {
        return XmlBundle.message( "inspections.xml.text.spacing" );
    }

    @NotNull
    @Override
    public String getShortName() {
        return "UnnecessarySpacing";
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor( @NotNull final ProblemsHolder holder, boolean isOnTheFly ) {
        return new XmlElementVisitor() {
            @Override
            public void visitXmlElement(XmlElement element) {
                if( isProblematic( element ) ) {
                    PsiElement thisElement;
                    if(element.getClass().getName().endsWith( "XmlTextImpl" )) {
                        if (((XmlTag) element.getParent()).getName().matches("Title|Objective|Acquire|Practice|Validate|Default|Warning|Caution|Reference|Component|Text|Text2")) {
                            char[] elemChars = element.getText().toCharArray();
                            int start;
                            int end;
                            for (int i  = 0; i < elemChars.length; i++) {
                                if (i > 0 && elemChars[i] == ' ' && elemChars[i + 1] == ' ') {
                                    if (elemChars[i - 1] == '!'
                                     || elemChars[i - 1] == '.'
                                     || elemChars[i - 1] == ':'
                                     || elemChars[i - 1] == ';'
                                     || elemChars[i - 1] == '?') {
                                        //noinspection UnnecessaryContinue
                                        continue;
                                    }
                                    else {
                                        start = i;
                                        end = i + 1;
                                        while (elemChars[end + 1] == ' ') {
                                            end++;
                                        }
                                        end++;
                                        i = end;
                                        holder.registerProblem( element, XmlBundle.message( "inspections.xml.text.spacing.error" ), ProblemHighlightType.GENERIC_ERROR_OR_WARNING, TextRange.create(start, end),new Fix( element ) );
                                    }
                                }
                            }
                        }
                    }
                    else if(element.getClass().getName().endsWith( "XmlTokenImpl" )) {
                        if (element.getText().equals("label") && (((XmlTag) element.getParent().getParent()).getName().equals("node") || ((XmlTag) element.getParent().getParent()).getName().equals("File"))) {
                            String text = Objects.requireNonNull(element.getContext()).getChildren()[2].getText();
                            if (!text.equals("\"\"")) {
                                thisElement = element.getContext().getChildren()[2].getFirstChild().getNextSibling();
                            } else {
                                thisElement = element.getParent();
                            }
                            holder.registerProblem(thisElement, XmlBundle.message("inspections.xml.text.spacing.error"), ProblemHighlightType.GENERIC_ERROR_OR_WARNING, new Fix(element));
                        }
                    }
                    return;
                }
                super.visitXmlElement(element);
            }
        };
    }

    private static boolean isProblematic( PsiElement elem ) {
        if(elem.getClass().getName().endsWith( "XmlTextImpl" )) {
            if (((XmlTag) elem.getParent()).getName().matches("Title|Objective|Acquire|Practice|Validate|Default|Warning|Caution|Reference|Component|Text|Text2")) {
                char[] elemChars = elem.getText().toCharArray();
                for (int i  = 0; i < elemChars.length; i++) {
                    if (i > 0 && elemChars[i] == ' ' && elemChars[i + 1] == ' ') {
                        if (elemChars[i - 1] == '!'
                         || elemChars[i - 1] == '.'
                         || elemChars[i - 1] == ':'
                         || elemChars[i - 1] == ';'
                         || elemChars[i - 1] == '?') {
                            //noinspection UnnecessaryContinue
                            continue;
                        }
                        else {
                            return true;
                        }
                    }
                }
            }
        }
        else if(elem.getClass().getName().endsWith( "XmlTokenImpl" )) {
            if (elem.getText().equals("label") && (((XmlTag) elem.getParent().getParent()).getName().equals("node") || ((XmlTag) elem.getParent().getParent()).getName().equals("File"))) {
                String text = Objects.requireNonNull(elem.getContext()).getChildren()[2].getFirstChild().getNextSibling().getText();
                return text.contains("  ");
            }
        }
        return false;
    }

    class Fix implements LocalQuickFix {
        private final UnnecessarySpacingQuickFix _fix;

        Fix(PsiElement elem) {
            _fix = new UnnecessarySpacingQuickFix( elem );
        }

        @NotNull
        public String getName() {
            return _fix.getText();
        }

        @NotNull
        public String getFamilyName() {
            return "Unnecessary spacing fix";
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
