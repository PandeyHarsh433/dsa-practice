package com.harsh.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix {
    public static void main(String[] args) {
        System.out.println(countNumberOfWays(3,3));
        System.out.println(findPath("",3,3));
        System.out.println(findPathV2("",3,3));

        boolean[][] board = {
                {true,true,true},
                {true,true,true},
                {true,true,true}
        };
        System.out.println(findPathWithObstruction("",board,0,0));
        System.out.println(findPathWithObstructionAndBacktracking("",board,0,1));

        int[][] arr= new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = 0;
            }
        }
        findPathWithObstructionAndBacktrackingV2("", arr,0,0,1);
    }

    static int countNumberOfWays(int i , int j) {
        if(i == 1 || j == 1) {
            return 1;
        }

        int right = countNumberOfWays(i-1,j);
        int down = countNumberOfWays(i,j-1);

        return down + right;
    }

    /*
     * INTUITION: "The Destination Seeker"
     * 1. GOAL: Move from (0,0) to (N,M).
     * 2. REDUCE: Every move reduces the distance to the goal.
     * 3. SUM: The total ways to reach a cell is the SUM of ways from its neighbors
     * (e.g., ways to current = ways from Top + ways from Left).
     * 4. BASE CASE: If you are on the edge (row=1 or col=1), there is only 1
     * straight path left to the corner.
     */

    // when you only can go right and down
    static List<String> findPath(String p, int r, int c) {
        if(r == 1 && c == 1) {
            List<String> ls = new ArrayList<>();
            ls.add(p);
            return ls;
        }

        List<String> ans = new ArrayList<>();
        if(r > 1) {
            ans.addAll(findPath(p + "R",r-1,c));
        }
        if(c > 1) {
            ans.addAll(findPath(p + "D", r,c-1));
        }

        return ans;
    }

    // when you can go right, down and diagonally
    static List<String> findPathV2(String p, int r, int c) {
        if(r == 1 && c == 1) {
            List<String> ls = new ArrayList<>();
            ls.add(p);
            return ls;
        }

        List<String> ans = new ArrayList<>();
        if(r > 1) {
            ans.addAll(findPathV2(p + "V",r-1,c));
        }
        if(c > 1) {
            ans.addAll(findPathV2(p + "H", r,c-1));
        }
        if(c > 1 && r > 1) {
            ans.addAll(findPathV2(p + "D", r-1,c-1));
        }

        return ans;
    }

    /*
     * INTUITION: "The Wall Detector"
     * 1. CONSTRAINT: Before moving, check if maze[r][c] is 'false' (a wall).
     * 2. DEAD END: If you hit a wall, return an empty list—this branch of the
     * search is dead.
     * 3. SUCCESS: Only paths that never touch a 'false' cell reach the destination.
     */

    // when you can go right, down and diagonally
    static List<String> findPathWithObstruction(String p,boolean[][] maze, int r, int c) {
        if(r == maze.length-1 && c == maze[0].length-1) {
            List<String> ls = new ArrayList<>();
            ls.add(p);
            return ls;
        }
        if(!maze[r][c]) {
            return new ArrayList<>();
        }

        List<String> ans = new ArrayList<>();
        if(r < maze.length-1) {
            ans.addAll(findPathWithObstruction(p+"D",maze,r+1,c));
        }
        if(c < maze[0].length-1) {
            ans.addAll(findPathWithObstruction(p+"R",maze, r,c+1));
        }

        return ans;
    }

    /*
     * INTUITION: "Breadcrumbs & Erasers"
     * 1. THE PROBLEM: If you can move in ALL 4 directions (U, D, L, R), you might
     * go in circles forever.
     * 2. THE LOCK: When you step on a cell, set maze[r][c] = false (or mark it).
     * This "locks" the cell so the current path doesn't revisit it.
     * 3. THE UNLOCK (Backtracking): Once the recursion returns (you've finished
     * exploring all neighbors), set maze[r][c] = true again.
     * 4. WHY: You must "clean up" your tracks so that a DIFFERENT path can use
     * that cell later.
     */

    static List<String> findPathWithObstructionAndBacktracking(String p,boolean[][] maze, int r, int c) {
        if(r == maze.length-1 && c == maze[0].length-1) {
            List<String> ls = new ArrayList<>();
            ls.add(p);
            return ls;
        }
        if(!maze[r][c]) {
            return new ArrayList<>();
        }

        // I am considering this cell in my path so marking false for now to avoid going back
        maze[r][c] = false;

        List<String> ans = new ArrayList<>();
        if(r < maze.length-1) {
            ans.addAll(findPathWithObstructionAndBacktracking(p+"D",maze,r+1,c));
        }
        if(c < maze[0].length-1) {
            ans.addAll(findPathWithObstructionAndBacktracking(p+"R",maze, r,c+1));
        }
        if(r > 0) {
            ans.addAll(findPathWithObstructionAndBacktracking(p+"U",maze, r-1,c));
        }
        if(c > 0) {
            ans.addAll(findPathWithObstructionAndBacktracking(p+"L",maze, r,c-1));
        }

        // I am getting out of my current path so before this functions I would have to cover my track
        // my undoing the changes in the maze so that in the next call they could again mark their own
        // path without conflicting with mine...
        maze[r][c] = true;

        return ans;
    }

    static void findPathWithObstructionAndBacktrackingV2(String p, int[][] arr, int r, int c, int level) {
        if(r == arr.length-1 && c == arr[0].length-1) {
            arr[r][c] = level;
            System.out.println(p);
            for(int[] a : arr) {
                System.out.println(Arrays.toString(a));
            }
            return;
        }

        if(arr[r][c] != 0) {
            return;
        }

        arr[r][c] = level;
        if(r < arr.length-1) {
            findPathWithObstructionAndBacktrackingV2(p+"D",arr,r+1,c,level+1);
        }
        if(c < arr[0].length-1) {
            findPathWithObstructionAndBacktrackingV2(p+"R",arr, r,c+1,level+1);
        }
        if(r > 0) {
            findPathWithObstructionAndBacktrackingV2(p+"U",arr, r-1,c,level+1);
        }
        if(c > 0) {
            findPathWithObstructionAndBacktrackingV2(p+"L",arr, r,c-1,level+1);
        }

        arr[r][c] = 0;
    }
}
