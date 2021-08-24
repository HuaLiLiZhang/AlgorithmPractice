package leetcode.competition;

import java.util.Arrays;

/**
 * @author: Created by zhanghl
 */
public class MaxProductDifference {
    public static void main(String[] args) {
//        int[] nums = {5, 6, 2, 7, 4};
        int[] nums = {4, 2, 5, 9, 7, 4, 8};
        System.out.println(new MaxProductDifference().maxProductDifference(nums));
    }


    public int maxProductDifference(int[] nums) {
        if (nums == null || nums.length <= 3) {
            return 0;
        }
        int max2 = Integer.MIN_VALUE;
        int max1 = max2;
        int min1 = Integer.MAX_VALUE;
        int min2 = min1;
        //min1 min2 max1 max2
        for (int n : nums) {
            if (n < min2) {
                min2 = Math.max(min1, n);
                min1 = Math.min(min1, n);
            }
            if (n > max1) {
                max1 = Math.min(max2, n);
                max2 = Math.max(max2, n);
            }
        }
        return max1 * max2 - min1 * min2;
    }
}
