package leetcode.Job.Array;

//给定一个含有 n 个正整数的数组和一个正整数 target 。 
//
// 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长
//度。如果不存在符合条件的子数组，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：target = 7, nums = [2,3,1,2,4,3]
//输出：2
//解释：子数组 [4,3] 是该条件下的长度最小的子数组。
// 
//
// 示例 2： 
//
// 
//输入：target = 4, nums = [1,4,4]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：target = 11, nums = [1,1,1,1,1,1,1,1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= target <= 109 
// 1 <= nums.length <= 105 
// 1 <= nums[i] <= 105 
// 
//
// 
//
// 进阶： 
//
// 
// 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。 
// 
// Related Topics 数组 双指针 二分查找 
// 👍 568 👎 0


public class _209MinimumSizeSubarraySum {
    public static void main(String[] args) {
        Solution solution = new _209MinimumSizeSubarraySum().new Solution();
        int[] nums = {2, 3, 1, 2, 4, 3};
        solution.minSubArrayLen(7, nums);
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 这道题目暴力解法当然是 两个for循环，然后不断的寻找符合条件的子序列，时间复杂度很明显是O(n^2) 。
         * @Param: [target, nums]
         */
        public int minSubArrayLen1(int target, int[] nums) {
            int result = Integer.MAX_VALUE; // 最终的结果
            int sum = 0; // 子序列的数值之和
            int subLength = 0; // 子序列的长度
            for (int i = 0; i < nums.length; i++) { // 设置子序列起点为i
                sum = 0;
                for (int j = i; j < nums.length; j++) { // 设置子序列终止位置为j
                    sum += nums[j];
                    if (sum >= target) { // 一旦发现子序列和超过了s，更新result
                        subLength = j - i + 1; // 取子序列的长度
                        result = result < subLength ? result : subLength;
                        break; // 因为我们是找符合条件最短的子序列，所以一旦符合条件就break
                    }
                }
            }
            // 如果result没有被赋值的话，就返回0，说明没有符合条件的子序列
            return result == Integer.MAX_VALUE ? 0 : result;
        }

        /**
         * @Description: 所谓滑动窗口，「就是不断的调节子序列的起始位置和终止位置，从而得出我们要想的结果」。
         * @Param: [target, nums]
         */
        public int minSubArrayLen(int target, int[] nums) {
            int len = nums.length;
            if (len == 0) {
                return 0;
            }
            int minLen = Integer.MAX_VALUE;
            int startIndex = 0, endIndex = 0;
            int sum = 0;
            while (endIndex < len) {
                sum += nums[endIndex];
                while (sum >= target) {
                    minLen = Math.min(minLen, endIndex - startIndex + 1);
                    sum -= nums[startIndex];
                    startIndex++;
                }
                endIndex++;
            }
            return minLen == Integer.MAX_VALUE ? 0 : minLen;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}