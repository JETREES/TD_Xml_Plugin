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
public class DescriptionTitleQuickFix extends LocalQuickFixAndIntentionActionOnPsiElement {
    private static final Logger LOG = Logger.getInstance( "#" + DescriptionTitleQuickFix.class.getName() );

    public DescriptionTitleQuickFix(PsiElement elem) {
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

            PsiElement[] children = Objects.requireNonNull(startElement.getContext()).getChildren();
            if (children.length == 6) {
                startElement = startElement.getContext().getChildren()[3];
                Objects.requireNonNull(file.getViewProvider().getDocument()).replaceString(startElement.getTextOffset(),
                        startElement.getTextOffset() + startElement.getTextLength(),
                        getConfigTitle(startElement) + "</");
            }
            else if (children.length == 7) {
                startElement = startElement.getContext().getChildren()[3];
                Objects.requireNonNull(file.getViewProvider().getDocument()).replaceString(startElement.getTextOffset(),
                        startElement.getTextOffset() + startElement.getTextLength(),
                        getConfigTitle(startElement));
            }
        }
        catch( IncorrectOperationException e ) {
            LOG.error( e );
        }
    }
}
