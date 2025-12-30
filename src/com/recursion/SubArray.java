package com.recursion;

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
    }
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
}
