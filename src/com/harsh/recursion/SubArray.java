package com.harsh.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubArray {
    public static void main(String[] args) {
         int[] arr3 = {1, 2, 3};
         List<List<Integer>> ans = iterSubArray(arr3);
         for (List<Integer> ls : ans) {
             System.out.print(ls + " ");
         }

         System.out.println();

         int[] arr4 = {1, 2, 2};
         List<List<Integer>> ans2 = iterDuplicateSubarray(arr4);
         for (List<Integer> ls : ans2) {
             System.out.print(ls + " ");
         }

         System.out.println();

         List<List<Integer>> ans3 = subsetsWithDup(arr4);
         for (List<Integer> ls : ans3) {
            System.out.print(ls + " ");
         }
    }

    /*
     * INTUITION: "The Copy-Paste-Add" Method
     * 1. START: Begin with an empty list inside 'outer' (e.g., [[]]).
     * 2. CLONE: For every new number, take ALL existing subsets in 'outer'.
     * 3. APPEND: Create a copy of each and add the new number to it.
     * 4. GROWTH: The size of 'outer' doubles with every unique number.
     * - Start: [[]]
     * - Add 1: [[], [1]]
     * - Add 2: [[], [1], [2], [1, 2]]
     */

    static List<List<Integer>> iterSubArray(int[] arr) {
        List<List<Integer>> outer = new ArrayList<>();
        outer.add(new ArrayList<>());

        for (int num : arr) {
            int n = outer.size();
            for (int i = 0; i < n; i++) {
                List<Integer> internal = new ArrayList<>(outer.get(i));
                internal.add(num);
                outer.add(internal);
            }
        }
        return outer;
    }

    /*
     * INTUITION: "The Smart Copy" (Avoid Redundancy)
     * 1. SORT: First, sort the array so duplicates are side-by-side.
     * 2. NORMAL MOVE: If the number is new, copy ALL existing subsets and add the number.
     * 3. DUPLICATE MOVE: If the number is a repeat (arr[i] == arr[i-1]), ONLY copy
     * the subsets created in the PREVIOUS step.
     * 4. WHY: This prevents creating subsets we already made when we first saw that number.
     */

    static List<List<Integer>> iterDuplicateSubarray(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> outer = new ArrayList<>();
        outer.add(new ArrayList<>());

        int start = 0, end = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i - 1] == arr[i]) {
                start = end + 1;
            }
            end = outer.size() - 1;
            for (int j = start; j <= end; j++) {
                List<Integer> internal = new ArrayList<>(outer.get(j));
                internal.add(arr[i]);
                outer.add(internal);
            }
        }
        return outer;
    }

    /*
     * INTUITION: "Decision Tree with Pruning"
     * 1. SORT: Crucial to group duplicates together.
     * 2. PICK: At each level of recursion, we decide which number to "start" a subset with.
     * 3. SKIP: If nums[i] is the same as nums[i-1], we skip it—but ONLY if it's
     * not the first choice at that level (i > start).
     * 4. RECURSE: Move the 'start' pointer forward (i + 1) to build deeper combinations.
     */

    // LeetCode #90
    // Input: nums = [1,2,2]
    // Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
    static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        return getSubsetsWithDuplicate(nums, new ArrayList<>(), 0);
    }

    static List<List<Integer>> getSubsetsWithDuplicate(int[] nums, List<Integer> ans, int start) {
        List<List<Integer>> outer = new ArrayList<>();
        outer.add(new ArrayList<>(ans));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            } else {
                int curr = nums[i];
                List<Integer> ls = new ArrayList<>(ans);

                ls.add(curr);
                outer.addAll(getSubsetsWithDuplicate(nums, ls, i + 1));
            }
        }
        return outer;
    }

}
