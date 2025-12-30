package com.recursion;

public class Pattern {
    public static void main(String[] args) {
         invertedTriangle(0, 5);
         triangle(1, 0, 5);
    }

    static void triangle(int c, int r, int n) {
        if (r == n) {
            return;
        }

        if (c < r) {
            System.out.print("*");
            triangle(c + 1, r, n);
        } else {
            System.out.println();
            triangle(0, r + 1, n);
        }
    }

    static void invertedTriangle(int c, int r) {
        if (r == 0) {
            return;
        }

        if (c < r) {
            System.out.print("*");
            invertedTriangle(c + 1, r);
        } else {
            System.out.println();
            invertedTriangle(0, r - 1);
        }
    }
}
