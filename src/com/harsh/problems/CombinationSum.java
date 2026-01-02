package com.harsh.problems;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> ans = combinationSum(candidates, target);
        for (List<Integer> ls : ans) {
            System.out.print(ls + " ");
        }
    }

    /*
     * INTUITION: Backtracking with "Infinite" Supply
     * 1. DECISION TREE: At each step, we pick a number and subtract it from the target.
     * 2. REUSE: We can pick the same number multiple times, so we pass 'i' (current index)
     * instead of 'i + 1' to the next recursive call.
     * 3. BASE CASES:
     * - Success: target == 0 (Valid combination found!).
     * - Failure: target < 0 (Went too far, stop searching this path).
     * 4. BACKTRACK: After exploring a path, we "removeLast()" to clean up the
     * list before trying the next candidate.
     */

    static List<List<Integer>> combinationSum(int[] candidates, int target) {
        return getCombSum(candidates, target, new ArrayList<>(), 0);
    }

    static List<List<Integer>> getCombSum(int[] candidates, int remainingTarget, List<Integer> p, int idx) {
        if (remainingTarget == 0) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(new ArrayList<>(p));
            return ans;
        }

        if (remainingTarget < 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> outer = new ArrayList<>();
        for (int i = idx; i < candidates.length; i++) {
            int curr = candidates[i];
            p.add(curr);
            List<List<Integer>> prev = getCombSum(candidates, remainingTarget - candidates[i], p, i);
            p.removeLast();
            outer.addAll(prev);
        }
        return outer;
    }
}
