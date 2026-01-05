package com.harsh.binarySearch;

public class MaximumValueAtGivenIndex {
    public static void main(String[] args) {
        int n = 4,index = 2,maxSum = 6;
        System.out.println(maxValue(n,index,maxSum));
    }

    /*
     * INTUITION: "The Pyramid Builder"
     * 1. GOAL: Maximize the value at 'index' while keeping the total sum <= maxSum.
     * 2. CONSTRAINT: Adjacent elements can only differ by at most 1.
     * 3. SHAPE: To minimize the total sum for a chosen 'peak' value, the array
     * should look like a pyramid: values decrease by 1 as you move away from 'index'.
     * 4. SEARCH: The possible value for 'index' ranges from 1 to maxSum.
     * Use Binary Search to find the HIGHEST 'peak' that stays within the budget.
     * 5. MATH: Use the Sum of Arithmetic Progression formula to calculate
     * the sum of the left and right slopes quickly.
     */

    static int maxValue(int n, int index, int maxSum) {
        // We search for the "Peak Value" between 1 and maxSum.
        // If the requiredSum for a 'mid' value fits in our budget (maxSum):
        //   1. It's a candidate (ans = mid).
        //   2. Try to go HIGHER (low = mid + 1).

        int low = 1, high = maxSum;
        int ans = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            long sum = requiredSum(n, index, mid);
            if (sum <= maxSum) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    static long requiredSum(int n, int index, int peak) {
        // The total sum is: Peak + Left Slope Sum + Right Slope Sum.

        // CASE 1: The slope hits '1' before reaching the array boundary.
        // We sum from (peak-1) down to 1, then fill the remaining spots with 1s.

        // CASE 2: The slope is cut off by the array boundary before reaching '1'.
        // We use the Arithmetic Progression formula: (first + last) * length / 2.
        long sum = peak;

        int leftLen = index;
        if (peak > leftLen) {
            long high = peak - 1;
            long low = peak - leftLen;

            sum += (high + low) * leftLen / 2;
        } else {
            long high = peak - 1;
            sum += (high * (high + 1)) / 2;
            sum += leftLen - high;
        }

        int rightLen = n - index - 1;
        if (peak > rightLen) {
            long high = peak - 1;
            long low = peak - rightLen;

            sum += (high + low) * rightLen / 2;
        } else {
            long high = peak - 1;
            sum += (high * (high + 1)) / 2;
            sum += rightLen - high;
        }

        return sum;
    }
}
