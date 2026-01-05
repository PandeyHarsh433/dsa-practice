package com.harsh.binarySearch;

public class RotatedArray {
    public static void main(String[] args) {
        int[] arr = {3,4,5,6,7,0,1,2};
        System.out.println(findPivot(arr,0,arr.length-1));
    }

    static int findPivot(int[] arr, int start , int end) {
        if(start > end) return -1;

        int mid = start + (end - start)/2;
        if(arr[mid] > arr[mid+1]) {
            return mid;
        }

        if(arr[mid] <= arr[start]) {
            return findPivot(arr,start,mid-1);
        }

        return findPivot(arr,mid+1,end);
    }
}
