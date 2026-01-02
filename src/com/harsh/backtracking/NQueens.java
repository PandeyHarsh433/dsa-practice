package com.harsh.backtracking;

public class NQueens {
    public static void main(String[] args) {
        int n = 5;
        boolean[][] board = new boolean[n][n];
        System.out.println(countQueen(board,0));
        System.out.println(countQueenV2(board,0));
    }

    /*
     * INTUITION: "The Defensive Placement"
     * 1. ROW-BY-ROW: Place one queen per row. Once a queen is placed, move
     * immediately to the next row (row + 1) to avoid horizontal conflicts.
     * 2. COLUMN CHECK: Before placing, scan the current column to see if
     * any queen is already "looking" at this spot from above.
     * 3. DIAGONAL CHECK: Scan both the Left-Up and Right-Up diagonals.
     * A queen is safe ONLY if all three directions (Up, Left-Up, Right-Up) are clear.
     * 4. BACKTRACK: If you reach a row where NO column is safe, the previous
     * queen was placed poorly. Go back, move it, and try again.
     * 5. SUCCESS: If 'row == board.length', you've successfully placed N queens.
     */

    static int countQueen(boolean[][] board, int row) {
        if(row == board.length) {
            display(board);
            System.out.println();
            return 1;
        }

        int count = 0;
        for(int col = 0 ; col < board.length ; col++) {
            if(isSafe(board,row,col)) {
                board[row][col] = true;
                count += countQueen(board,row+1);
                board[row][col] = false;
            }
        }
        return count;
    }

    static boolean isSafe(boolean[][] board, int row, int col) {
        // Safe in the same column i.e. Vertically
        for(int i = row; i >= 0 ; i--) {
            if(board[i][col]) return false;
        }

        // Safe in the left diagonal
        for(int i = row-1, j = col-1; i >= 0 && j >= 0 ; i--,j--) {
            if(board[i][j]) return false;
        }

        // Safe in the right diagonal
        for(int i = row-1,j = col + 1; i >= 0 && j < board.length ; i--,j++) {
            if(board[i][j]) return false;
        }
        return true;
    }

    static void display(boolean[][] board) {
        for(boolean[] row : board) {
            for(boolean elem : row) {
                if(elem) System.out.print("Q ");
                else System.out.print("X ");
            }
            System.out.println();
        }
    }

    static int countQueenV2(boolean[][] board, int col) {
        if(col == board[0].length) {
            display(board);
            System.out.println();
            return 1;
        }

        int count = 0;
        for(int row = 0 ; row < board.length ; row++) {
            if(isSafeV2(board,row,col)){
                board[row][col] = true;
                count += countQueenV2(board,col+1);
                board[row][col] = false;
            }
        }
        return count;
    }

    static boolean isSafeV2(boolean[][] board, int row, int col) {
        // Vertically Up: Check if a queen exists in this column in previous rows
        // Left Diagonal: Check the \ path upwards
        // Right Diagonal: Check the / path upwards
        // Note: No need to check rows below or the current row because they are empty!

        // check in the same row
        for(int i = col-1; i>=0 ; i--) {
            if(board[row][i]) return false;
        }

        // check for the left up diagonal
        for(int i = row-1, j = col-1; i >= 0 && j >= 0; i--,j--) {
            if(board[i][j]) return false;
        }

        //check for the left down diagonal
        for(int i = row+1, j = col-1; i < board.length && j >=0 ; i++,j--) {
            if(board[i][j]) return false;
        }
        return true;
    }
}