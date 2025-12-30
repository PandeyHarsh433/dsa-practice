package com.recursion;

public class StringRec {
    public static void main(String[] args) {
         System.out.println(getStringLength("asygsy"));
         System.out.println(removeLetterA("asygsy" , "" , 0));
         System.out.println(removeLetterAV2("asygsy",0));
    }
    static int getStringLength(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        return getStringLength(s.substring(1)) + 1;
    }

    static String removeLetterA(String str, String ans, int idx) {
        if (idx > str.length() - 1) {
            return ans;
        }

        if (str.charAt(idx) != 'a' && str.charAt(idx) != 'A') {
            ans += str.charAt(idx);
        }

        return removeLetterA(str, ans, idx + 1);
    }

    /**
     * Removes all occurrences of 'a' or 'A' from the given string str.
     *
     * @param str the string from which to remove 'a' or 'A'
     * @param idx the index from which to start removing 'a' or 'A'
     * @return the string with all 'a' or 'A' removed
     */
    static String removeLetterAV2(String str, int idx) {
        if (idx > str.length() - 1) {
            return "";
        }
        String tmp = "";
        if (str.charAt(idx) != 'a' && str.charAt(idx) != 'A') {
            tmp += str.charAt(idx);
        }
        String before = removeLetterAV2(str, idx + 1);
        return tmp + before;
    }
}
