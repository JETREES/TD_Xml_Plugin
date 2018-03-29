/*
 * Copyright 2013. Guidewire Software, Inc.
 */

package com.janusresearch.tdXmlPlugin.xml.inspections.quickFixes;

import com.intellij.codeInspection.LocalQuickFixAndIntentionActionOnPsiElement;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static com.janusresearch.tdXmlPlugin.file.FileHandler.getConfigTitle;

/**
 */
public class ConclusionTitleQuickFix extends LocalQuickFixAndIntentionActionOnPsiElement {
    private static final Logger LOG = Logger.getInstance( "#" + ConclusionTitleQuickFix.class.getName() );

    public ConclusionTitleQuickFix(PsiElement elem ) {
        super( elem );
    }

    @NotNull
    @Override
    public String getText() {
        return "Replace with title from Config.xml";
    }

    @Override
    @NotNull
    public String getFamilyName() {
        return "Replace the title";
    }

    @Override
    public boolean isAvailable( @NotNull Project project,
                                @NotNull PsiFile file,
                                @NotNull PsiElement startElement,
                                @NotNull PsiElement endElement ) {
        return startElement.isValid()
                && startElement.getManager().isInProject( startElement );
    }

    @Override
    public void invoke( @NotNull Project project,
                        @NotNull PsiFile file,
                        @Nullable("is null when called from inspection") Editor editor,
                        @NotNull PsiElement startElement,
                        @NotNull PsiElement endElement ) {
        try {
            String text = startElement.getText();
            String text1 = endElement.getText();
            int startOffset = startElement.getTextRange().getStartOffset() + 20;
            int endOffset = startElement.getTextRange().getEndOffset() - 70;
            Objects.requireNonNull(file.getViewProvider().getDocument()).replaceString(startOffset,
                    endOffset,
                    getConfigTitle(startElement));

        }
        catch( IncorrectOperationException e ) {
            LOG.error( e );
        }
    }
}
