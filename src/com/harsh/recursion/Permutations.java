package com.harsh.recursion;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
        permutation("", "abc");
         System.out.println();
         List<String> p2 = new ArrayList<>();
         permutationV2("", "abc", p2);
         System.out.println(p2);
         System.out.println(permutationCount("", "abc"));
    }

    /*
     * INTUITION: "The Slot Filler"
     * 1. TAKE & INSERT: Take one character from the 'Unprocessed' (up) string.
     * 2. CHOOSE SLOTS: Insert that character into every possible position
     * of the current 'Processed' (p) string.
     * 3. EXPAND:
     * - If p = "ab", and you take 'c', you can put 'c' in 3 spots:
     * _ a _ b _  => "cab", "acb", "abc"
     * 4. TERMINATE: When 'Unprocessed' is empty, you've used all letters.
     * 5. COUNT: Each leaf node in this decision tree represents 1 unique permutation.
     */

    static void permutation(String p, String up) {
        if (up.isEmpty()) {
            System.out.print(p + " ");
            return;
        }

        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String first = p.substring(0, i);
            String second = p.substring(i);

            permutation(first + ch + second, up.substring(1));
        }
    }

    static void permutationV2(String p, String up, List<String> ls) {
        if (up.isEmpty()) {
            ls.add(p);
            return;
        }

        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String first = p.substring(0, i);
            String second = p.substring(i);

            permutationV2(first + ch + second, up.substring(1), ls);
        }
    }

    static int permutationCount(String p, String up) {
        if (up.isEmpty()) {
            return 1;
        }

        int count = 0;
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String first = p.substring(0, i);
            String second = p.substring(i);

            count += permutationCount(first + ch + second, up.substring(1));
        }
        return count;
    }
}
