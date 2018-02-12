// Generated on Fri Feb 09 21:20:14 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;

/**
 * null:ModuleType interface.
 */
public interface Module extends DomElement {

    /**
     * Returns the value of the title child.
     * @return the value of the title child.
     */
    @NotNull
    @Attribute("title")
    GenericAttributeValue<String> getTitle();


    /**
     * Returns the value of the timed child.
     * @return the value of the timed child.
     */
    @NotNull
    @Attribute("timed")
    GenericAttributeValue<Boolean> getTimed();


    /**
     * Returns the value of the estimatedtime child.
     * @return the value of the estimatedtime child.
     */
    @NotNull
    @Attribute("estimatedTime")
    GenericAttributeValue<String> getEstimatedtime();


    /**
     * Returns the value of the type child.
     * @return the value of the type child.
     */
    @NotNull
    @Attribute("type")
    GenericAttributeValue<String> getType();


    /**
     * Returns the value of the view child.
     * @return the value of the view child.
     */
    @NotNull
    @Attribute("view")
    GenericAttributeValue<String> getView();


    /**
     * Returns the value of the movie child.
     * @return the value of the movie child.
     */
    @NotNull
    @Attribute("movie")
    GenericAttributeValue<String> getMovie();


    /**
     * Returns the value of the lines child.
     * @return the value of the lines child.
     */
    @NotNull
    @Attribute("lines")
    GenericAttributeValue<Integer> getLines();


    /**
     * Returns the value of the version child.
     * @return the value of the version child.
     */
    @NotNull
    @Attribute("version")
    GenericAttributeValue<Integer> getVersion();


    /**
     * Returns the value of the widescreen child.
     * @return the value of the widescreen child.
     */
    @NotNull
    @Attribute("widescreen")
    GenericAttributeValue<String> getWidescreen();


    /**
     * Returns the value of the w3d child.
     * @return the value of the w3d child.
     */
    @NotNull
    @Attribute("w3d")
    GenericAttributeValue<String> getW3d();


    /**
     * Returns the value of the mode child.
     * @return the value of the mode child.
     */
    @NotNull
    @Attribute("mode")
    GenericAttributeValue<String> getMode();


    /**
     * Returns the value of the Resources child.
     * @return the value of the Resources child.
     */
    @NotNull
    @SubTag("Resources")
    Resources getResources();


    /**
     * Returns the value of the Description child.
     * @return the value of the Description child.
     */
    @NotNull
    @SubTag("Description")
    Description getDescription();


    /**
     * Returns the value of the PreloadCommands child.
     * @return the value of the PreloadCommands child.
     */
    @NotNull
    @SubTag("PreloadCommands")
    PreloadCommands getPreloadCommands();


    /**
     * Returns the value of the ExternalCOL child.
     * @return the value of the ExternalCOL child.
     */
    @NotNull
    @SubTag("ExternalCOL")
    ExternalCOL getExternalCOL();


    /**
     * Returns the value of the ExternalPopup child.
     * @return the value of the ExternalPopup child.
     */
    @NotNull
    @SubTag("ExternalPopup")
    ExternalPopup getExternalPopup();


    /**
     * Returns the value of the LessonReference child.
     * @return the value of the LessonReference child.
     */
    @NotNull
    @SubTag("LessonReference")
    LessonReference getLessonReference();


    /**
     * Returns the value of the BaseCamera child.
     * @return the value of the BaseCamera child.
     */
    @NotNull
    @SubTag("BaseCamera")
    GenericDomValue<String> getBaseCamera();


    /**
     * Returns the value of the ObjectTree child.
     * @return the value of the ObjectTree child.
     */
    @NotNull
    @SubTag("ObjectTree")
    ObjectTree getObjectTree();


    /**
     * Returns the value of the ComponentFiles child.
     * @return the value of the ComponentFiles child.
     */
    @NotNull
    @SubTag("ComponentFiles")
    ComponentFiles getComponentFiles();


    /**
     * Returns the value of the StepTree child.
     * @return the value of the StepTree child.
     */
    @NotNull
    @SubTag("StepTree")
    StepTree getStepTree();


    /**
     * Returns the value of the GlobalEvents child.
     * @return the value of the GlobalEvents child.
     */
    @NotNull
    @SubTag("GlobalEvents")
    GlobalEvents getGlobalEvents();


    /**
     * Returns the value of the CommandMacros child.
     * @return the value of the CommandMacros child.
     */
    @NotNull
    @SubTag("CommandMacros")
    CommandMacros getCommandMacros();


    /**
     * Returns the value of the Goals child.
     * @return the value of the Goals child.
     */
    @NotNull
    @SubTag("Goals")
    Goals getGoals();


    /**
     * Returns the value of the CustomNavViews child.
     * @return the value of the CustomNavViews child.
     */
    @NotNull
    @SubTag("CustomNavViews")
    CustomNavViews getCustomNavViews();


    /**
     * Returns the value of the FrameSetOrPages child.
     * @return the value of the FrameSetOrPages child.
     */
    @NotNull
    @Required
    @SubTag("FrameSet")
    FrameSet getFrameSet();

    /**
     * Returns the value of the Pages child.
     * @return the value of the Pages child.
     */
    @NotNull
    @Required
    @SubTag("Pages")
    Pages getPages();

}
