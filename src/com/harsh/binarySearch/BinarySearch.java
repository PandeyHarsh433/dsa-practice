package com.harsh.binarySearch;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,3,4,5,6,7,23,54,67};
        System.out.println(binarySearch(arr,0,arr.length-1,23));
        System.out.println(orderAgnosticBinarySearch(arr,23,0,arr.length-1));
    }

    static int binarySearch(int[] arr, int start, int end , int target) {
        if(end < start) return -1;

        int mid = start + (end-start)/2;
        if(arr[mid] == target) {
            return mid;
        }
        if(arr[mid] > target) {
            return binarySearch(arr,start,mid-1,target);
        }

        return binarySearch(arr,mid+1,end,target);
    }

    static int orderAgnosticBinarySearch(int[] arr, int target , int start, int end) {
        if(start > end) return -1;

        int mid = start + (end-start)/2;
        if(arr[mid] == target) {
            return mid;
        }

        if(arr[start] < arr[end]) {
            if(arr[mid] > target) {
                return orderAgnosticBinarySearch(arr,target,start,mid-1);
            } else {
                return orderAgnosticBinarySearch(arr,target,mid+1,end);
            }
        } else {
            if(arr[mid] > target) {
                return orderAgnosticBinarySearch(arr,target,mid+1,end);
            } else {
                return orderAgnosticBinarySearch(arr,target,start,mid-1);
            }
        }
    }
}
