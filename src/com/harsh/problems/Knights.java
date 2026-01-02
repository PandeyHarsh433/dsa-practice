package com.harsh.problems;

import java.util.Arrays;

public class Knights {
    static double[][][] dp;

    public static void main(String[] args) {
        int n = 3, k = 2;
        dp = new double[k + 1][n][n];
        System.out.println(knightProbability(n,k,0,0));

        int[][] grid = new int[][]{
            {0,11,16,5,20},
            {17,4,19,10,15},
            {12,1,8,21,6},
            {3,18,23,14,9},
            {24,13,2,7,22}
        };

        System.out.println(checkValidGrid(grid));
    }

    /*
     * INTUITION: Probabilistic DFS with Memoization
     * 1. PROBABILITY SPLIT: Every move has 8 possible directions.
     * The chance of picking any one direction is 1/8 (or 0.125).
     * 2. RECURSIVE DEPTH: We track 'k' moves. Each successful move reduces 'k'
     * until k=0 (still on board) or the knight falls off (return 0).
     * 3. MEMOIZATION (3D DP): We store results in dp[k][row][col] because the
     * probability of surviving from a specific cell with 'k' moves left
     * is always the same. This prevents redundant calculations.
     * 4. AGGREGATION: The probability at the current cell is the sum of
     * probabilities of all 8 neighboring moves, each divided by 8.
     */

    // LeetCode #688 => Knight Possibility in Chessboard
    // Input: n = 3, k = 2, row = 0, column = 0
    // Output: 0.06250
    static double knightProbability(int n, int k, int row, int column) {
        if (k == 0) {
            return (double) 1;
        }

        for (int i = 0; i <= k; i++) {
            for (int r = 0; r < n; r++) {
                Arrays.fill(dp[i][r], -1);
            }
        }

        return knight(n, k, row, column);
    }

    static double knight(int n, int k, int row, int col) {
        if (k == 0) {
            return 1.0;
        }

        if (row >= n || col >= n || row < 0 || col < 0) {
            return 0;
        }

        if (dp[k][row][col] != -1)
            return dp[k][row][col];


        double count = 0;

        if (row > 1) {
            if (col > 0) {
                count += knight(n, k - 1, row - 2, col - 1)/8;
            }
            if (col + 1 < n) {
                count += knight(n, k - 1, row - 2, col + 1)/8;
            }
        }

        if (col > 1) {
            if (row > 0) {
                count += knight(n, k - 1, row - 1, col - 2)/8;
            }
            if (row + 1 < n) {
                count += knight(n, k - 1, row + 1, col - 2)/8;
            }
        }

        if (row + 2 < n) {
            if (col > 0) {
                count += knight(n, k - 1, row + 2, col - 1)/8;
            }
            if (col + 1 < n) {
                count += knight(n, k - 1, row + 2, col + 1)/8;
            }
        }

        if (col + 2 < n) {
            if (row > 0) {
                count += knight(n, k - 1, row - 1, col + 2)/8;
            }
            if (row + 1 < n) {
                count += knight(n, k - 1, row + 1, col + 2)/8;
            }
        }

        dp[k][row][col] = count;
        return count;
    }

    /*
     * INTUITION: Sequential Path Verification
     * 1. TARGET-DRIVEN: We aren't searching for any path; we are verifying a
     * specific sequence (0 -> 1 -> 2 ... -> n*n-1).
     * 2. L-SHAPED VALIDATION: From the current cell (row, col) with value 'key',
     * we look at all 8 knight moves to find the cell containing 'key + 1'.
     * 3. CHAIN REACTION: If the next number in the sequence exists in a
     * valid L-move, we jump there and repeat.
     * 4. SUCCESS CONDITION: If we successfully reach the value (n * n - 1),
     * the entire tour is valid. If at any step the next number isn't
     * reachable via a knight move, the chain breaks (return false).
     */

    // LeetCode #2596 => Check Knight Tour Configuration
    // Input: grid = [[0,11,16,5,20],[17,4,19,10,15],[12,1,8,21,6],[3,18,23,14,9],[24,13,2,7,22]]
    // Output: true
    static boolean checkValidGrid(int[][] grid) {
        if(grid[0][0] != 0) return false;
        return isValidKnightTour(grid,0,0,0);
    }

    static boolean isValidKnightTour(int[][] grid, int row, int col, int key) {
        int n = grid.length;
        if (key == n * n - 1) {
            return true;
        }

        int next = key +1;
        int[][] moves = {
                { -2, -1 }, { -2, 1 },
                { -1, -2 }, { -1, 2 },
                { 1, -2 }, { 1, 2 },
                { 2, -1 }, { 2, 1 }
        };

        for(int[] m : moves) {
            int r = row + m[0];
            int c = col + m[1];

            if(r >= 0 && r < n && c >= 0 && c < n ) {
                if(grid[r][c] == next) {
                    return isValidKnightTour(grid,r,c,next);
                }
            }
        }

        return false;
    }
}
