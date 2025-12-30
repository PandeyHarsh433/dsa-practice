package com.recursion;

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
