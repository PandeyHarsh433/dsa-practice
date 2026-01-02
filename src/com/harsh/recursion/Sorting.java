package com.harsh.recursion;

import java.util.Arrays;

public class Sorting {
    public static void main(String[] args) {
        int[] arr = {4, 34, 56, 3, 8, 6, 43, 23};

        int[] sort1 = MergeSort.mergeSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(sort1));

        QuickSort.quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

        int[] sort2 = BubbleSort.recursiveBubbleSort(arr,0, arr.length-1);
        System.out.println(Arrays.toString(sort2));

        int[] sort3 = SelectionSort.recursiveSelectionSort(arr,0, arr.length-1 ,0 );
        System.out.println(Arrays.toString(sort3));
    }
}

class MergeSort{
    /**
     * Sorts an array using the merge sort algorithm.
     * <p>
     * This algorithm works by recursively dividing the array into two halves
     * until each half contains only one element. It then merges the two
     * halves together in sorted order.
     *
     * @param arr The array to sort.
     * @param s The start index of the array.
     * @param e The end index of the array.
     * @return The sorted array.
     */
    static int[] mergeSort(int[] arr, int s, int e) {
        if (s == e) {
            return new int[]{arr[s]};
        }
        int mid = s + (e - s) / 2;
        int[] left = mergeSort(arr, s, mid);
        int[] right = mergeSort(arr, mid + 1, e);

        return merge(left, right);
    }

    static int[] merge(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            merged[k++] = arr1[i++];
        }
        while (j < arr2.length) {
            merged[k++] = arr2[j++];
        }
        return merged;
    }
}

class QuickSort{
    /**
     * Sorts an array using the quick sort algorithm.
     * <p>
     * This algorithm works by selecting a pivot element and partitioning
     * the array around the pivot element. It then recursively sorts the two
     * halves of the array.
     *
     * @param arr The array to sort.
     * @param low The start index of the array.
     * @param high The end index of the array.
     */
    static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int s = low, e = high;
        int mid = s + (e - s) / 2;
        int pivot = arr[mid];

        while (s <= e) {
            while (arr[s] < pivot) {
                s++;
            }
            while (arr[e] > pivot) {
                e--;
            }
            if (s <= e) {
                int tmp = arr[s];
                arr[s] = arr[e];
                arr[e] = tmp;

                s++;
                e--;
            }
        }

        quickSort(arr, low, e);
        quickSort(arr, s, high);
    }
}

class BubbleSort{
    /**
     * Sorts an array using the recursive bubble sort algorithm.
     * <p>
     * This algorithm works by recursively iterating through the array and
     * swapping adjacent elements if they are in the wrong order. It then
     * recursively sorts the array without the last element.
     *
     * @param arr The array to sort.
     * @param c The current index of the array.
     * @param r The end index of the array.
     * @return The sorted array.
     */
    static int[] recursiveBubbleSort(int[] arr, int c, int r) {
        if (r == 0) {
            return arr;
        }

        if (c < r) {
            if (arr[c] > arr[c + 1]) {
                int tmp = arr[c];
                arr[c] = arr[c + 1];
                arr[c + 1] = tmp;
            }
            return recursiveBubbleSort(arr, c + 1, r);
        } else {
            return recursiveBubbleSort(arr, 0, r - 1);
        }
    }
}

class SelectionSort{
    /**
     * Sorts an array using the recursive selection sort algorithm.
     * <p>
     * This algorithm works by recursively iterating through the array and
     * finding the maximum element in the array. It then swaps the maximum
     * element with the last element of the array and then recursively
     * sorts the array without the last element.
     *
     * @param arr The array to sort.
     * @param c The current index of the array.
     * @param r The end index of the array.
     * @param maxIdx The index of the maximum element found so far.
     * @return The sorted array.
     */
    static int[] recursiveSelectionSort(int[] arr, int c, int r, int maxIdx) {
        if (r == 0) {
            return arr;
        }

        if (c <= r) {
            if (arr[c] > arr[maxIdx]) {
                maxIdx = c;
            }
            return recursiveSelectionSort(arr, c + 1, r, maxIdx);
        } else {
            int tmp = arr[r];
            arr[r] = arr[maxIdx];
            arr[maxIdx] = tmp;
            return recursiveSelectionSort(arr, 0, r - 1, 0);
        }
    }
}