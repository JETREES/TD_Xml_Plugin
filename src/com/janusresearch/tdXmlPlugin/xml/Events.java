package com.janusresearch.tdXmlPlugin.xml;

import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Events {
    public static List<XmlAttribute> playEvents = new ArrayList<>();
    public static List<XmlAttribute> backEvents = new ArrayList<>();
    public static List<XmlAttribute> otherEvents = new ArrayList<>();
    public static List<String> newPlayEventsValues = new ArrayList<>();
    public static List<String> newBackEventsValues = new ArrayList<>();
    public static List<String> newOtherEventsValues = new ArrayList<>();

    @SuppressWarnings("ConstantConditions")
    public Events(XmlTag[] frames) {
        int i = 0;
        for (XmlTag f : frames) {
            //Get the Events XmlTag
            XmlTag eventsTag = f.findFirstSubTag("Events");

            //Get all sub tags from Events
            XmlTag[] events = eventsTag.findSubTags("Event");

            for (XmlTag e : events) {
                //store all play/back/other events nextid attribute in an XmlAttribute
                //list and store he value they will change to in a String list
                if (e.getAttribute("nextid") != null) {             //if Event contains nextid attribute
                    XmlAttribute get = e.getAttribute("get");       //Store the get attribute in play list

                    if (Objects.equals(get.getValue(), "Play")) {       //if get attribute value equals Play
                        playEvents.add(e.getAttribute("nextid"));   //add nextid attribute to the list

                        //determine the new Play Event nextid value and store it in the list
                        if (i < 8) {
                            newPlayEventsValues.add("0" + (i + 2));
                        }
                        else {
                            newPlayEventsValues.add(String.valueOf(i + 2));
                        }
                    }
                    else if (Objects.equals(get.getValue(), "Back")) {  //if get attribute value equals Back
                        backEvents.add(e.getAttribute("nextid"));   //Store the get attribute in back list

                        //determine the new Back Event nextid value and store it in the list
                        if (i < 10) {
                            newBackEventsValues.add("0" + i);
                        }
                        else {
                            newBackEventsValues.add(String.valueOf(i));
                        }
                    }
                    else {                                                  //get attribute value !equals Play/Back
                        otherEvents.add(e.getAttribute("nextid"));  //Store the get attribute in other list

                        //side note: Other Event could be probably be combined with Play Event
                        //however for now I am going to keep it separate on the off chance
                        //its needed at some point later

                        //determine the new Other Event nextid value and store it in the list
                        if (i < 8) {
                            newOtherEventsValues.add("0" + (i + 2));
                        }
                        else {
                            newOtherEventsValues.add(String.valueOf(i + 2));
                        }
                    }
                }
            }
            i++;
        }
    }
}