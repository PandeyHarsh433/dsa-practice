package com.recursion;

import java.util.ArrayList;

public class Search {
    public static void main(String[] args) {
         System.out.println(findIndex(new int[]{1, 2, 4, 3, 8, 9}, 8, 0));
         System.out.println(findMultiIndex(new int[]{1, 2, 8, 4, 3, 8, 9}, 8, 0, new ArrayList<>()));
         System.out.println(findMultiIndexV2(new int[]{1, 2, 8, 4, 3, 8, 9}, 8, 0));
         System.out.println(searchInRotatedArray(new int[]{4, 5, 6, 7, 1, 2, 3}, 2, 0, 6));
    }

    static int findIndex(int[] arr, int target, int index) {
        if (index == arr.length) {
            return -1;
        }

        if (target == arr[index]) {
            return index;
        }
        return findIndex(arr, target, index + 1);
    }

    static ArrayList<Integer> findMultiIndex(int[] arr, int target, int index, ArrayList<Integer> ls) {
        if (index == arr.length) {
            return ls;
        }

        if (arr[index] == target) {
            ls.add(index);
        }
        return findMultiIndex(arr, target, index + 1, ls);
    }

    static ArrayList<Integer> findMultiIndexV2(int[] arr, int target, int index) {
        ArrayList<Integer> ls = new ArrayList<>();
        if (index == arr.length) {
            return ls;
        }

        if (target == arr[index]) {
            ls.add(index);
        }

        ArrayList<Integer> prevAns = findMultiIndexV2(arr, target, index + 1);
        ls.addAll(prevAns);
        return ls;
    }

    // [4, 5, 6, 7, 1, 2, 3] , target = 2
    // There is a core concept in this which tells us that least one part of the array
    // divided my mid would always be sorted so we check which half is sorted and then
    // search in that only
    static int searchInRotatedArray(int[] arr, int target, int s, int e) {
        if (s > e) {
            return -1;
        }

        int mid = s + (e - s) / 2;
        if (target == arr[mid]) {
            return mid;
        }

        // Left half is sorted
        if (arr[mid] >= arr[s]) {
            // figuring out if our element lies in that range or not
            if (target >= arr[s] && target < arr[mid]) {
                return searchInRotatedArray(arr, target, s, mid - 1);
            } else {
                return searchInRotatedArray(arr, target, mid + 1, e);
            }
        } // Right half is sorted
        else {
            if (target > arr[mid] && target <= arr[e]) {
                return searchInRotatedArray(arr, target, mid + 1, e);
            } else {
                return searchInRotatedArray(arr, target, s, mid - 1);
            }
        }
    }
}
