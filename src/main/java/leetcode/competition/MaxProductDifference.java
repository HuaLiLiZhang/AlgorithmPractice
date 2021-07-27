package leetcode.competition;

import java.util.Arrays;

/**
 * @author: Created by zhanghl
 */
public class maxProductDifferenceClass {
    public static void main(String[] args) {
        int[] nums = {5, 6, 2, 7, 4};
//        int[] nums = {4, 2, 5, 9, 7, 4, 8};
        System.out.println(new maxProductDifferenceClass().maxProductDifference(nums));
    }


    public int maxProductDifference(int[] nums) {
        if (nums == null || nums.length <= 3) {
            return 0;
        }
        Arrays.sort(nums);
        return nums[nums.length - 1] * nums[nums.length - 2] - nums[0] * nums[1];
    }
}
