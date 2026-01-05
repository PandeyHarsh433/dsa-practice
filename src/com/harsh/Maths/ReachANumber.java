package com.harsh.Maths;

public class ReachANumber {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(reachNumber(n));
    }

    /*
     * INTUITION: "The Overshoot & Flip" logic
     * 1. SYMMETRY: Moving to -5 is the same as moving to +5. Always use target = abs(target).
     * 2. THE GOAL: You are making steps: 1, 2, 3, 4... To reach 'target', your
     * cumulative 'sum' must be at least equal to 'target'.
     * 3. THE GAP: If you overshoot the target (sum > target), you need to change
     * a previous step '+k' into a '-k'.
     * 4. THE MAGIC OF 2: Flipping a '+k' to a '-k' reduces the total sum by
     * exactly (2 * k). This means the difference (sum - target) MUST be an EVEN number.
     * 5. STRATEGY:
     * - Keep adding steps until sum >= target.
     * - If the gap (sum - target) is even, you are done.
     * - If the gap is odd, keep adding steps until it becomes even.
     */
    static int reachNumber(int target) {
        target = Math.abs(target);

        int sum = 0;
        int step = 0;

        while(sum < target || (sum-target) % 2 != 0) {
            step++;
            sum += step;
        }

        return step;
    }
}
