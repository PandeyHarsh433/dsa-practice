package com.harsh.recursion;

public class Basic {
    public static void main(String[] args) {
         int[] arr = {0, 5, 9, 11, 19, 25, 31};
         System.out.println(binarySearch(arr, 5, 0, arr.length - 1));
         System.out.println(factorial(5));
         System.out.println(sumOfDigit(5963));
         System.out.println(reverseNumber(2034));
         System.out.println(countZerosV2(1203300, 0));
         System.out.println(countZeros(1203300));
         System.out.println(numberOfSteps(8, 0));
         System.out.println(isSortedArray(new int[]{1, 2, 4, 3, 8, 9}, 0));
    }

    static int binarySearch(int[] arr, int k, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;
        if (arr[mid] == k) {
            return mid;
        }

        if (arr[mid] > k) {
            return binarySearch(arr, k, low, mid - 1);
        } else {
            return binarySearch(arr, k, mid + 1, high);
        }
    }

    static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    static int sumOfDigit(int n) {
        if (n == 0) {
            return 0;
        }

        return n % 10 + sumOfDigit(n / 10);
    }

    static int reverseNumber(int n) {
        return helper(n, 0);
    }

    static int helper(int n, int rev) {
        if (n == 0) {
            return rev;
        }

        return helper(n / 10, rev * 10 + (n % 10));
    }

    static int countZeros(int n) {
        if (n == 0) {
            return 1;
        }
        return countZerosHelper(n);
    }

    static int countZerosHelper(int n) {
        if (n == 0) {
            return 0;
        }

        int count = (n % 10 == 0) ? 1 : 0;
        return count + countZerosHelper(n / 10);
    }

    static int countZerosV2(int n, int ans) {
        if (n == 0) {
            return ans;
        }

        int count = n % 10 == 0 ? 1 : 0;
        return countZerosV2(n / 10, ans + count);
    }

    static int numberOfSteps(int n, int ans) {
        if (n == 0) {
            return ans;
        }

        if (n % 2 == 0) {
            return numberOfSteps(n / 2, ans + 1);
        } else {
            return numberOfSteps(n - 1, ans + 1);
        }
    }

    static boolean isSortedArray(int[] arr, int ptr) {
        if (arr == null || arr.length < 2) {
            return true;
        }
        if (ptr == arr.length - 1) {
            return true;
        }

        return arr[ptr] < arr[ptr + 1] && isSortedArray(arr, ptr + 1);
    }
}
