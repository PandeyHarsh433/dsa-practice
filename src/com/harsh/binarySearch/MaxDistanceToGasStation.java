package com.harsh.binarySearch;

public class MaxDistanceToGasStation {
    public static void main(String[] args) {
        int[] stations = {1, 2, 3, 4, 5};
        int K = 2;

        double min = 0.0, max = 0.0;

        // RANGE: The smallest possible max-distance is 0.
        // The largest possible is the current biggest gap without adding any stations.
        for (int i = 0; i < stations.length - 1; i++) {
            max = Math.max(max, stations[i + 1] - stations[i]);
        }

        System.out.println(binarySearch(stations, min, max, K));
    }

    /*
     * INTUITION: "The Gap Filler"
     * 1. THE GOAL: We want to add K new stations so that the biggest gap between
     * any two stations is as small as possible.
     * 2. BINARY SEARCH ON ANSWER: The answer (max distance) is a decimal between
     * 0 and the current largest gap. We "guess" a distance (mid) and check if it's possible.
     * 3. VALIDATION: To maintain a maximum gap of 'mid':
     * - For every existing gap (stations[i+1] - stations[i]), we calculate
     * how many new stations are needed to break that gap into pieces <= mid.
     * - Formula: (gap / mid) tells us the number of segments.
     * Subtracting 1 (or using floor logic) tells us the new stations needed.
     * 4. DECISION:
     * - If we can achieve the distance 'mid' using K or fewer stations,
     * it's a valid answer! Try an even smaller distance (Search Left).
     * - If we need more than K stations, 'mid' is too small (Search Right).
     */
    static double binarySearch(int[] stations, double start, double end, int K) {
        if (end - start < 1e-6) {
            return start;
        }

        // PRECISION: Since we are dealing with doubles, we stop when the search
        // range is tiny (1e-6), which acts as our 'base case'.
        double mid = start + (end - start) / 2;

        if (isValidDistance(stations, mid, K)) {
            // This distance is possible! Can we make it even smaller?
            return binarySearch(stations, start, mid, K);
        }

        // This distance is too small; we'd need more than K stations.
        return binarySearch(stations, mid, end, K);
    }

    static boolean isValidDistance(int[] stations, double mid, int K) {
        int used = 0;

        // For each gap, calculate how many stations are required to keep all
        // sub-gaps <= mid.
        for (int i = 0; i < stations.length - 1; i++) {
            double gap = stations[i + 1] - stations[i];

            // used += (int)(gap / mid)
            // Example: gap = 10, mid = 3. 10/3 = 3.33 -> 3 stations needed.
            // This splits 10 into 4 segments of 2.5 each (all <= 3).
            used += (int)(gap / mid);

            if (used > K) {
                return false;
            }
        }

        return true;
    }
}
