package com.harsh.binarySearch;

import java.util.Arrays;

public class SearchIn2DMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {10,20,30,40},
                {15,25,35,45},
                {28,29,37,44},
                {33,34,38,50},
        };

        int[][] matrix2 = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16},
        };

        System.out.println(Arrays.toString(search(matrix, matrix.length, matrix.length - 1, 50)));
    }

    static int[] search(int[][] matrix , int row , int col , int target) {
        if(row >= matrix.length || col < 0) {
            return new int[]{-1,-1};
        }

        if(matrix[row][col] == target) {
            return new int[]{row,col};
        }
        if(matrix[row][col] < target) {
            return search(matrix,row + 1,col,target);
        }
        return search(matrix,row,col-1,target);
    }

//    static int[] search2(int[][] matrix,int row, int col, int target) {
//
//    }
}
