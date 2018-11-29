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

/**
 */
public class TextDoubleSpaceQuickFix extends LocalQuickFixAndIntentionActionOnPsiElement {
    private static final Logger LOG = Logger.getInstance( "#" + TextDoubleSpaceQuickFix.class.getName() );

    public TextDoubleSpaceQuickFix(PsiElement elem ) {
        super( elem );
    }

    @NotNull
    @Override
    public String getText() {
        return "Replace double space with single space";
    }

    @Override
    @NotNull
    public String getFamilyName() {
        return "Replace double with single space";
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
            file.getViewProvider().getDocument().replaceString( startElement.getTextOffset(),
                    startElement.getTextOffset() + startElement.getTextLength(),
                    "Bar" );
        }
        catch( IncorrectOperationException e ) {
            LOG.error( e );
        }
    }
}
