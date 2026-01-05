package com.harsh.binarySearch;

public class SplitArrayLargestSum {
    public static void main(String[] args) {
        int[] nums = {7,2,5,10,8};
        int k = 2;

        // Lower Bound (min): The max element (a subarray cannot be smaller than its largest element).
        // Upper Bound (max): The sum of all elements (the total potential weight).
        int min = 0, max = 0;
        for (int num : nums) {
            min = Math.max(min, num);
            max += num;
        }

        System.out.println(findLargestSum(nums, min, max, k));
    }

    /*
     * INTUITION: "The Balancing Act"
     * 1. THE GOAL: Split the array into 'k' subarrays such that the
     * LARGEST sum among them is as SMALL as possible.
     * 2. THE RANGE:
     * - MIN: The largest single number in the array (because it must belong to some piece).
     * - MAX: The sum of all numbers (if k=1, the entire array is one piece).
     * 3. THE SEARCH: We guess a "Max Allowed Sum" (mid) and check:
     * "How many pieces do we need if no piece can exceed this sum?"
     * 4. VALIDATION:
     * - If pieces needed <= k: Our 'mid' is safe, but maybe we can go even smaller? (Search Left)
     * - If pieces needed > k: Our 'mid' is too tight; we need to allow a larger sum. (Search Right)
     */
    static int findLargestSum(int[] nums, int min, int max, int k) {
        if (min == max) {
            return max;
        }

        // Logic: Greedily try to pack as many numbers as possible into one 'piece'
        // without exceeding the 'mid' value.
        int piece = 1, sum = 0;
        int mid = min + (max - min) / 2;
        for (int num : nums) {
            sum += num;
            if (sum > mid) {    // Current piece is "full"
                sum = num;      // Start a new piece with this number
                piece++;        // Increment total pieces used
            }
        }

        if (piece <= k) {
            // We used 'k' or fewer pieces. This 'mid' is a valid maximum sum.
            // Try to find a more restrictive (smaller) maximum.
            return findLargestSum(nums, min, mid, k);
        }

        // We were forced to create too many pieces.
        // We must increase the allowed 'mid' sum.
        return findLargestSum(nums, mid + 1, max, k);
    }
}
