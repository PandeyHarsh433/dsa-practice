package com.harsh.problems;

public class MaximumOnesSquare {
    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(maximalSquare(matrix));
    }

    /*
     * INTUITION:
     * 1. SCAN: Use recursion to visit every cell (r, c) as a potential TOP-LEFT corner.
     * 2. EXPAND: For each cell, expand outward level-by-level (level 0, 1, 2...).
     * 3. VALIDATE: In each level, only check the NEWLY added row and column (the "L-shape").
     * 4. MAXIMIZE: If the new level is all '1's, update the max area: (level+1)^2.
     * * WHY THIS WORKS:
     * It virtually "grows" a square island from every possible starting point until it hits a '0'
     * or the boundary, ensuring no valid square is missed.
     */

    // Problem #221 => Maximal Square
    // Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
    // Output: 4
    static int maximalSquare(char[][] matrix) {
        return findSquareArea(matrix, 0, 0);
    }

    static int findSquareArea(char[][] matrix, int row, int col) {
        if (row == matrix.length) {
            return 0;
        }
        if (col == matrix[0].length) {
            return findSquareArea(matrix, row + 1, 0);
        }

        int max = findSquareArea(matrix, row, col + 1);

        int level = 0;
        while (checkSquare(matrix, row, col, level)) {
            max = Math.max(max, (level + 1) * (level + 1));
            level++;
        }

        return max;
    }

    static boolean checkSquare(char[][] matrix, int r, int c, int level) {
        int n = matrix.length;
        int m = matrix[0].length;

        int nr = r + level;
        int nc = c + level;

        if (nr >= n || nc >= m)
            return false;

        for (int i = r; i <= nr; i++) {
            if (matrix[i][nc] == '0')
                return false;
        }

        for (int j = c; j <= nc; j++) {
            if (matrix[nr][j] == '0')
                return false;
        }

        return true;
    }
}
