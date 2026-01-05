package com.harsh.binarySearch;

import java.util.Arrays;
import java.util.Comparator;

public class FindRightInterval {
    public static void main(String[] args) {
        int[][] intervals = {{1,2}};
        System.out.println(Arrays.toString(findRightInterval(intervals)));
    }

    /*
     * INTUITION: "The Next Train" Logic
     * 1. THE PROBLEM: For each interval 'A', find another interval 'B' that starts
     * exactly when 'A' ends (or as soon as possible after).
     * 2. PRE-PROCESS: To find something quickly, we need to sort. We map each
     * START time to its ORIGINAL INDEX so we don't lose track of it after sorting.
     * 3. THE SEARCH: For every interval's 'end' time, we need the smallest 'start'
     * time that is >= end.
     * 4. LOWER BOUND: Since the 'starts' array is sorted, we use Binary Search
     * (Lower Bound) to find that "next" starting point in O(log N) time.
     */
    static int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;

        int[][] starts = new int[n][2];
        for (int i = 0; i < n; i++) {
            starts[i][0] = intervals[i][0];
            starts[i][1] = i;
        }

        // 1. DATA PREP: Create a 2D array storing [startTime, originalIndex]
        // 2. SORT: Order them by startTime so we can Binary Search through them.
        Arrays.sort(starts, Comparator.comparingInt(a -> a[0]));

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int end = intervals[i][1];
            int idx = lowerBound(starts, end);
            result[i] = idx == -1 ? -1 : starts[idx][1];
        }

        return result;
    }

    static int lowerBound(int[][] arr, int target) {
        int l = 0, r = arr.length - 1;
        int ans = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid][0] >= target) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
