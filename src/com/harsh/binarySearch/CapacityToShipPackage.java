package com.harsh.binarySearch;

public class CapacityToShipPackage {
    public static void main(String[] args) {
        int[] weights = {1,2,3,4,5,6,7,8,9,10};
        int days = 5;
        int min = 0, max = 0;

        // Lower Bound (min): Heaviest package (the ship must at least carry this).
        // Upper Bound (max): Total weight (shipping everything at once).
        for (int weight : weights) {
            min = Math.max(min, weight);
            max += weight;
        }

        System.out.println(getMinCapacity(weights, min, max, days));
    }

    /*
     * INTUITION: "The Packing Optimization"
     * 1. THE RANGE:
     * - MIN: At least the heaviest single package (otherwise you can't lift it).
     * - MAX: The sum of all packages (shipping everything in exactly 1 day).
     * 2. THE SEARCH: We search for the smallest capacity 'mid' that allows us
     * to ship all packages within the given 'days'.
     * 3. VALIDATION (Greedy):
     * - Start filling a ship. If adding the next package exceeds 'mid',
     * send the current ship and start a new day with that package.
     * 4. DECISION:
     * - If total days used <= required days: This capacity works!
     * Try to go even LOWER (Search Left).
     * - If total days used > required days: Capacity is too small.
     * We MUST increase it (Search Right).
     */
    static int getMinCapacity(int[] nums, int start, int end, int days) {
        if (start == end) {
            return start;
        }

        // Calculate 'mid' as our test capacity.
        // Logic: "Can we finish shipping in 'days' if the ship's limit is 'mid'?"
        int mid = start + (end - start) / 2;
        int day = 1, sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum > mid) {    // Ship is full!
                sum = num;      // This package goes on the next day's ship
                day++;          // Increment day count
            }
        }

        if (day <= days) {
            // We finished on time (or early). Try a smaller capacity.
            return getMinCapacity(nums, start, mid, days);
        }

        // Too many days taken. Increase capacity.
        return getMinCapacity(nums, mid + 1, end, days);
    }
}
