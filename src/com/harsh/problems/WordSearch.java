package com.harsh.problems;

public class WordSearch {
    public static void main(String[] args) {
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String word = "ABCCED";
        System.out.println(exist(board,word));
    }

    /*
     * INTUITION: "The Path Finder" (DFS + Backtracking)
     * 1. SEARCH: Iterate through every cell to find the first character of the word.
     * 2. EXPLORE: Once the first char matches, look in 4 directions (Up, Down, Left, Right).
     * 3. MARK VISITED: To avoid using the same cell twice in one path,
     * "hide" the current char (replace with '*') before moving to the next letter.
     * 4. BACKTRACK: After checking all directions, replace the '*' back with the
     * original character so other potential paths can use it.
     * 5. TERMINATE: If the word becomes empty, we found it! If we hit a boundary,
     * a '*' (visited), or a mismatch, that path is dead.
     */

    // LeetCode #69 => Word Search
    // Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
    // Output: true
    static boolean exist(char[][] board, String word) {
        // Grid Scanner: Check every cell as a potential starting point
        for(int i = 0 ; i < board.length;i++) {
            for(int j = 0 ; j < board[i].length ; j++) {
                if(isPresent(board,word,i,j)) return true;
            }
        }
        return false;
    }

    static boolean isPresent(char[][] board, String word, int row, int col) {
        // BASE CASES:
        // 1. Success: Found all letters!
        // 2. Failure: Out of bounds, already visited ('*'), or wrong letter.
        if (word.isEmpty()) {
            return true;
        }

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }

        if (board[row][col] == '*') {
            return false;
        }

        if (board[row][col] != word.charAt(0)) {
            return false;
        }

        char temp = board[row][col];
        board[row][col] = '*';
        if (isPresent(board, word.substring(1), row + 1, col))
            return true;
        if (isPresent(board, word.substring(1), row - 1, col))
            return true;
        if (isPresent(board, word.substring(1), row, col - 1))
            return true;
        if (isPresent(board, word.substring(1), row, col + 1))
            return true;

        board[row][col] = temp;
        return false;
    }


}
