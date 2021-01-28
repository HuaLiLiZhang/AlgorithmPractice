package leetcode.hot100;

import java.util.HashMap;

/**
 * @author: Created by zhanghl
 */
public class _1TwoSum {
    /**
     * @Description:两数之和：第一种：暴力，两重循环，第二种：一次循环，使用hashMap
     * @Param: [nums, target]
     */

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (indexMap.containsKey(target - nums[i])) {
                result[0] = indexMap.get(target - nums[i]);
                ;
                result[1] = i;
                return result;
            }
            indexMap.put(nums[i], i);
        }
        return result;
    }
}
