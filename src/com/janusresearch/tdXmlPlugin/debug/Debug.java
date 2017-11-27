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
}
