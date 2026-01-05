package com.harsh.binarySearch;

public class CeilingNumber {
    public static void main(String[] args) {
        int[] arr = {2,3,4,9,14,16,18};
        System.out.println(findCeiling(arr,0,arr.length-1,5));
        System.out.println(findFloor(arr,0,arr.length-1,5));
    }

    /*
     * INTUITION: "The Neighborhood Search"
     * 1. CEILING: The smallest number in the array that is >= target.
     * 2. FLOOR: The largest number in the array that is <= target.
     * 3. BINARY SEARCH: Since the array is sorted, we narrow the range.
     * If the target isn't there, the search ends with 'start' and 'end'
     * crossing each other.
     * 4. THE CROSSOVER:
     * - After the loop, 'start' points to the next bigger element (Ceiling).
     * - After the loop, 'end' points to the next smaller element (Floor).
     */
    static int findCeiling(int[] nums, int start, int end , int target) {
        if(start > end) return nums[start];

        int mid = start + (end-start/2);
        if(nums[mid] == target) {
            return nums[mid];
        }
        if(nums[mid] < target) {
            return findCeiling(nums,mid+1,end,target);
        }
        return findCeiling(nums,start,mid-1,target);
    }

    static int findFloor(int[] nums, int start, int end , int target) {
        if(start > end) return nums[end];

        int mid = start + (end-start/2);
        if(nums[mid] == target) {
            return nums[mid];
        }
        if(nums[mid] < target) {
            return findCeiling(nums,start,mid-1,target);
        }
        return findCeiling(nums,mid+1,end,target);
    }
}
