package com.harsh.backtracking;

public class SafeKnight {
    public static void main(String[] args) {
        int n = 3;
        boolean[][] board = new boolean[n][n];
        calculateKnightSafe(board,0,0,4);
    }

    /*
     * INTUITION: "The Mutual Exclusion"
     * 1. SCAN: Move through the board cell by cell (row, col).
     * 2. TWO CHOICES: At every cell, you have two options:
     * - OPTION A: Place a Knight (if it's not being attacked by knights above).
     * - OPTION B: Skip the cell and move to the next one.
     * 3. TARGET: Keep track of the 'knight' count. If it hits 0, you've successfully
     * placed all required knights.
     * 4. SAFETY: Since we move top-to-bottom, we only need to check the 4 "L-shapes"
     * pointing UPWARDS to see if an existing knight can hit our current spot.
     * 5. BACKTRACK: After exploring a placement, set board[row][col] back to 'false'
     * to try other configurations.
     */

    static void calculateKnightSafe(boolean[][] board, int row, int col, int knight) {
        if(knight == 0) {
            display(board);
            System.out.println();
            return;
        }

        if(row == board.length) {
            return;
        }

        if(col == board.length) {
            calculateKnightSafe(board,row+1,0,knight);
            return;
        }

        if(isSafe(board,row,col)) {
            board[row][col] = true;
            calculateKnightSafe(board,row,col+1,knight-1);
            board[row][col] = false;
        }

        calculateKnightSafe(board,row,col+1,knight);
    }

    static boolean isSafe(boolean[][] board, int row , int col) {
        int n = board.length;

        // (row-2, col-1)
        if (row - 2 >= 0 && col - 1 >= 0 && board[row - 2][col - 1]) return false;

        // (row-2, col+1)
        if (row - 2 >= 0 && col + 1 < n && board[row - 2][col + 1]) return false;

        // (row-1, col-2)
        if (row - 1 >= 0 && col - 2 >= 0 && board[row - 1][col - 2]) return false;

        // (row-1, col+2)
        if (row - 1 >= 0 && col + 2 < n && board[row - 1][col + 2]) return false;

        return true;
    }

    static void display(boolean[][] board) {
        for(boolean[] row : board) {
            for(boolean elem : row) {
                if(elem) System.out.print("K ");
                else System.out.print("X ");
            }
            System.out.println();
        }
    }

}
