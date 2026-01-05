package com.harsh.binarySearch;

public class RotatedArray {
    public static void main(String[] args) {
        int[] arr = {3,4,5,6,7,0,1,2};
        System.out.println(findPivot(arr,0,arr.length-1));
    }

    /*
     * INTUITION: "The Cliff Detector"
     * 1. THE ROTATION: A sorted array like [0,1,2,3,4] becomes [3,4,0,1,2] when rotated.
     * 2. THE PIVOT: The pivot is the LARGEST element (the peak) just before the
     * array "drops" back down to the smallest element.
     * 3. THE LOGIC:
     * - If mid > mid + 1: You found the cliff! 'mid' is the pivot.
     * - If mid < start: You are in the second (smaller) sorted part.
     * The pivot must be to your LEFT.
     * - If mid > start: You are in the first (larger) sorted part.
     * The pivot must be to your RIGHT.
     */
    static int findPivot(int[] arr, int start , int end) {
        if(start > end) return -1;

        int mid = start + (end - start)/2;
        if(arr[mid] > arr[mid+1]) {
            return mid;
        }

        if(arr[mid] <= arr[start]) {
            return findPivot(arr,start,mid-1);
        }

        return findPivot(arr,mid+1,end);
    }
}
