package com.harsh.binarySearch;

public class PeakElement {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(findPeak(nums, 0, nums.length - 1));
    }

    /*
     * INTUITION: "The Mountain Climber" logic
     * 1. THE PEAK: A peak is an element greater than its neighbor. On a 2D graph,
     * this is where the slope changes from "going up" to "going down."
     * 2. SLOPE DETECTION: We check 'mid' and its neighbor 'mid + 1':
     * - If arr[mid] > arr[mid+1]: We are on a downward slope. A peak MUST exist
     * to our left (or is 'mid' itself).
     * - If arr[mid] < arr[mid+1]: We are on an upward slope. A peak MUST exist
     * to our right.
     * 3. WHY BINARY SEARCH? Even though the array isn't sorted, the "slopes"
     * guide us. By always moving toward the higher neighbor, we are guaranteed
     * to eventually hit a peak.
     */
    static int findPeak(int[] arr, int start, int end) {
        // BASE CASE: When start == end, we have narrowed it down to a single
        // element that must be a peak.
        if (start == end)
            return start;

        int mid = start + (end - start) / 2;

        // Check the relationship between 'mid' and the element to its right
        if (arr[mid] > arr[mid+1]) {
            // You are in the decreasing part of the mountain.
            // The peak is to your left (including mid).
            return findPeak(arr, start, mid);
        } else {
            // You are in the increasing part of the mountain.
            // The peak is to your right (mid + 1 onwards).
            return findPeak(arr, mid+1, end);
        }
    }
}
