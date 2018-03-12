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
import com.intellij.psi.xml.XmlTag;
import com.janusresearch.tdXmlPlugin.xml.inspections.quickFixes.DescriptionTitleQuickFix;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;


import static com.janusresearch.tdXmlPlugin.file.FileHandler.getConfigTitle;

/**
 */
public class DescriptionTitleInspection extends BaseLocalInspectionTool {
  @Nls
  @NotNull
  @Override
  public String getGroupDisplayName() {
    return XmlBundle.message( "inspections.xml.description.title" );
  }

  @Nls
  @NotNull
  @Override
  public String getDisplayName() {
    return XmlBundle.message( "inspections.xml.description.title" );
  }

  @NotNull
  @Override
  public String getShortName() {
    return "DescriptionTitle";
  }

  @NotNull
  @Override
  public PsiElementVisitor buildVisitor( @NotNull final ProblemsHolder holder, boolean isOnTheFly ) {
    return new XmlElementVisitor() {
      @Override
      public void visitXmlElement(XmlElement element) {
        if( isProblematic( element ) ) {
          PsiElement thisElement = null;
          if (element.getContext() != null) {
            String text = element.getContext().getChildren()[3].getText();
            if (!text.equals("</")) {
              thisElement = element.getContext().getChildren()[3];
            }
            else {
              thisElement = element.getParent();
            }
          }
          if (thisElement != null) {
            holder.registerProblem(thisElement, XmlBundle.message("inspections.xml.title.error"), ProblemHighlightType.GENERIC_ERROR_OR_WARNING, new Fix(element));
          }
          return;
        }
        super.visitXmlElement(element);
      }
    };
  }

  private boolean isProblematic(XmlElement elem) {
    String text = elem.getText();
    if (text.equals("Title") && !elem.getPrevSibling().getText().equals("</") && ((XmlTag) elem.getParent().getParent()).getName().equals("Description")) {
      String text1;
      if (elem.getContext() != null) {
        text1 = elem.getContext().getChildren()[3].getText();
          return !text1.equals(getConfigTitle(elem));
        }
    }
    return false;
  }

  class Fix implements LocalQuickFix {
    private final DescriptionTitleQuickFix _fix;

    Fix(PsiElement elem) {
      _fix = new DescriptionTitleQuickFix( elem );
    }

    @NotNull
    public String getName() {
      return _fix.getText();
    }

    @NotNull
    public String getFamilyName() {
      return "Description Title fix";
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
