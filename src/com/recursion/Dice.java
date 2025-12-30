package com.recursion;

import java.util.ArrayList;
import java.util.List;

public class Dice {
    public static void main(String[] args) {
        System.out.println(diceRoll("", 4));
    }

    static List<String> diceRoll(String p, int up) {
        if (up == 0) {
            List<String> ls = new ArrayList<>();
            ls.add(p);
            return ls;
        }
        List<String> outer = new ArrayList<>();

        for (int i = 1; i <= 6 && i <= up; i++) {
            outer.addAll(diceRoll(p + i, up - i));
        }
        return outer;
    }
}
