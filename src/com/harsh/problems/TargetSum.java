package com.harsh.problems;

public class TargetSum {
    public static void main(String[] args) {
        int [] nums = {1,1,1,1,1};
        int target = 3;
        System.out.println(getTotal(nums, target, 0));
    }

    static int getTotal(int[] nums, int target , int idx) {
        if(idx == nums.length && target == 0) {
            return 1;
        }

        if(idx == nums.length) {
            return 0;
        }

        int count = 0;
        count += getTotal(nums,target + nums[idx] , idx + 1);
        count += getTotal(nums,target - nums[idx] , idx + 1);

        return count;
    }
}
