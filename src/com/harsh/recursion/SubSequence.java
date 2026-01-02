package com.harsh.recursion;

import java.util.ArrayList;
import java.util.Collections;

public class SubSequence {
    public static void main(String[] args) {
        System.out.println(subSeq("","abc"));
    }
    /**
     * Prints all subsequences of a given string.
     *
     * @param str The given string.
     * @param up The string to form subsequences from.
     */
    static ArrayList<String> subSeq(String p, String up) {
        // p is processed array and up is unprocessed array
        if (up.isEmpty()) {
            return new ArrayList<>(Collections.singletonList(p));
        }
        char ch = up.charAt(0);

        ArrayList<String> left = subSeq(p + ch, up.substring(1));
        ArrayList<String> right = subSeq(p, up.substring(1));

        left.addAll(right);
        return left;
    }
}
