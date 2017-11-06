//Custom methods used for debugging

package com.janusresearch.tdXmlPlugin.debug;

import java.util.Arrays;

public class Debug {

    public static <T> void printArray(T[] anArray) {
        System.out.println(Arrays.toString(anArray));
    }
    public static <T> void printArray(T[][] anArray) {
        System.out.println(Arrays.deepToString(anArray));
    }
    public static <T> void printArray(T[][][] anArray) {
        System.out.println(Arrays.deepToString(anArray));
    }

    public static <T> void print(T type) {
        System.out.println(type);
    }


    public static <T> void printArray2(T[][] anArray) {
        System.out.println("StepTree Modifications");
        for (T[] a : anArray) {

        }
        System.out.println(Arrays.deepToString(anArray));
    }
}
