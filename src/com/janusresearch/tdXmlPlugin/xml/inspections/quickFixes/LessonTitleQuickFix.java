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
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static com.janusresearch.tdXmlPlugin.file.FileHandler.getConfigTitle;

/**
 */
public class LessonTitleQuickFix extends LocalQuickFixAndIntentionActionOnPsiElement {
    private static final Logger LOG = Logger.getInstance( "#" + LessonTitleQuickFix.class.getName() );

    public LessonTitleQuickFix(PsiElement elem) {
        super( elem );
    }

    @NotNull
    @Override
    public String getText() {
        return "Replace with Lesson Title from Config.xml";
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

            // fixes lesson title in conclusion frame
            if (startElement.getText().startsWith("\"This concludes the ") && startElement.getText().endsWith("Lesson Manager.\"") && (Objects.equals(((XmlTag) startElement.getParent()).getName(), "Text") || Objects.equals(((XmlTag) startElement.getParent()).getName(), "Text2"))) {
                int startOffset = startElement.getTextRange().getStartOffset() + 20;
                int endOffset = startElement.getTextRange().getEndOffset() - 70;
                Objects.requireNonNull(file.getViewProvider().getDocument()).replaceString(startOffset,
                        endOffset,
                        getConfigTitle(startElement));
            }

            // fixes lesson title in the Module title attribute
            if (startElement.getParent().getParent().getClass().getName().endsWith("XmlTagImpl")) {
                if (Objects.equals(((XmlTag) startElement.getParent().getParent()).getName(), "Module")) {
                    PsiElement[] children = Objects.requireNonNull(startElement.getContext()).getChildren()[2].getChildren();
                    if (children.length == 3) {
                        startElement = Objects.requireNonNull(startElement.getContext()).getChildren()[2].getFirstChild().getNextSibling();
                        Objects.requireNonNull(file.getViewProvider().getDocument()).replaceString(startElement.getTextOffset(),
                                startElement.getTextOffset() + startElement.getTextLength(),
                                getConfigTitle(startElement));
                    } else if (children.length == 2) {
                        startElement = startElement.getContext().getChildren()[2].getLastChild();
                        Objects.requireNonNull(file.getViewProvider().getDocument()).replaceString(startElement.getTextOffset(),
                                startElement.getTextOffset() + startElement.getTextLength(),
                                getConfigTitle(startElement) + "\"");
                    }
                }
            }

            // fixes lesson title in Description Title tag
            if (startElement.getParent().getParent().getClass().getName().endsWith("XmlTagImpl")) {
                if (Objects.equals(((XmlTag) startElement.getParent()).getName(), "Title") && Objects.equals(((XmlTag) startElement.getParent().getParent()).getName(), "Description")) {
                    PsiElement[] children = Objects.requireNonNull(startElement.getContext()).getChildren();
                    if (children.length == 6) {
                        startElement = startElement.getContext().getChildren()[3];
                        Objects.requireNonNull(file.getViewProvider().getDocument()).replaceString(startElement.getTextOffset(),
                                startElement.getTextOffset() + startElement.getTextLength(),
                                getConfigTitle(startElement) + "</");
                    } else if (children.length == 7) {
                        startElement = startElement.getContext().getChildren()[3];
                        Objects.requireNonNull(file.getViewProvider().getDocument()).replaceString(startElement.getTextOffset(),
                                startElement.getTextOffset() + startElement.getTextLength(),
                                getConfigTitle(startElement));
                    }
                }
            }
        }
        catch( IncorrectOperationException e ) {
            LOG.error( e );
        }
    }
}
