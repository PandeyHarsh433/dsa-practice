package com.harsh.binarySearch;

public class KokoEatingBanana {
    public static void main(String[] args) {
        int[] piles = {3,6,7,11};
        int h = 8;

        // MIN Speed: 1 (Koko eats at least 1 banana per hour).
        // MAX Speed: The largest pile (No point eating faster than the biggest pile).
        int max = 0, min = 1;
        for (int pile : piles) {
            min = Math.min(pile, min);
            max = Math.max(pile, max);
        }

        System.out.println(getMinTime(piles, min, max, h));
    }

    /*
     * INTUITION: "The Speed Limit"
     * 1. THE GOAL: Find the minimum speed 'K' (bananas per hour) Koko needs
     * to eat all bananas within 'H' hours.
     * 2. THE RANGE:
     * - MIN: 1 banana/hour (Slowest possible speed).
     * - MAX: The largest pile (at this speed, Koko finishes each pile in 1 hour).
     * 3. THE SEARCH: We "guess" a speed (mid) and check if it’s fast enough.
     * 4. VALIDATION:
     * - For each pile, time taken = ceil(piles[i] / speed).
     * - If total hours used <= H: This speed works! Try even slower (Search Left).
     * - If total hours used > H: Too slow! Must increase speed (Search Right).
     */
    static int getMinTime(int[] nums, int start, int end, int h) {
        if (start == end) {
            return start;
        }

        // Binary Search logic applied to "Eating Speed".
        int mid = start + (end - start) / 2;
        if (canEat(nums, mid, h)) {
            // Current speed is fast enough. Can we be more efficient (slower)?
            return getMinTime(nums, start, mid, h);
        }

        // Not enough time! Must pick up the pace.
        return getMinTime(nums, mid + 1, end, h);
    }

    static boolean canEat(int[] nums, int mid, int h) {
        int hr = 0;

        // Calculate total hours required at speed 'mid'.
        for (int num : nums) {
            // Math Trick: (num + mid - 1) / mid is equivalent to ceil(num / mid).
            // It handles the "remainder" bananas that still take 1 full hour.
            hr += (num + mid - 1) / mid;
        }
        return hr <= h;
    }
}
