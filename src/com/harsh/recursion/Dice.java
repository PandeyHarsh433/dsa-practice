package com.harsh.recursion;

import java.util.ArrayList;
import java.util.List;

public class Dice {
    public static void main(String[] args) {
        System.out.println(diceRoll("", 4));
    }

    /*
     * INTUITION: "The Budgeting Method"
     * 1. TARGET: Think of the total 'up' as a budget you need to spend exactly.
     * 2. CHOICES: On every turn, you can roll any face (1 to 6) as long as
     * it doesn't exceed your remaining budget (i <= up).
     * 3. BRANCHING: Each roll creates a new branch in the recursion tree.
     * 4. SUCCESS: If budget hits exactly 0, you found a valid combination!
     * 5. COLLECT: As recursion "unwinds," it gathers all valid paths from
     * the bottom up into one big list.
     */
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
