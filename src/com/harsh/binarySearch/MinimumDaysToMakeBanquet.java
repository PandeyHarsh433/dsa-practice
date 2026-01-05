package com.harsh.binarySearch;

public class MinimumDaysToMakeBanquet {
    public static void main(String[] args) {
        int[] bloomDay = {1,10,3,10,2};
        int m = 3, k = 1;

        // IMPOSSIBLE: If required flowers (m * k) > total flowers, return -1 immediately.
        // RANGE: Find the min and max bloom days to define our search boundaries.
        if ((long) m * k > bloomDay.length) {
            System.out.println(-1);
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int day : bloomDay) {
            min = Math.min(min, day);
            max = Math.max(max, day);
        }

        System.out.println(findNumberOfDays(bloomDay, min, max, m, k));
    }

    /*
     * INTUITION: "The Waiting Game" (Binary Search on Answer)
     * 1. THE RANGE: The answer (min days) must be between the earliest bloom
     * day (min) and the latest bloom day (max) in the array.
     * 2. THE SEARCH: Instead of searching for an index, we search for the
     * FIRST day where we can successfully pick 'm' bouquets.
     * 3. VALIDATION: On a given 'mid' day:
     * - Can we pick 'k' ADJACENT flowers that have bloomed?
     * - If yes, we count it as 1 bouquet and reset the counter.
     * - If a flower hasn't bloomed yet, the "adjacency chain" breaks (reset to 0).
     * 4. DECISION:
     * - If we can make 'm' bouquets on 'mid' day, maybe we can do it EARLIER? (Search Left)
     * - If not, we definitely need MORE time. (Search Right)
     */

    static int findNumberOfDays(int[] nums, int start, int end, int m, int k) {
        if (start == end) {
            return start;
        }

        // Standard Binary Search logic applied to "Days" instead of "Indices".
        int mid = start + (end - start) / 2;
        if (isValidBouquets(nums, m, k, mid)) {
            // Current 'mid' works, try to find a SMALLER day in the left half
            return findNumberOfDays(nums, start, mid, m, k);
        }

        return findNumberOfDays(nums, mid + 1, end, m, k);
    }

    static boolean isValidBouquets(int[] nums, int m, int k, int day) {
        int bouquets = 0, flowers = 0;

        for (int bloom : nums) {
            if (bloom <= day) {
                flowers++;
                if(flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }
        return bouquets >= m;
    }
}
