package leetcode.Job.GreedyAlgorithm;

//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：nums = [-1]
//输出：-1
// 
//
// 示例 5： 
//
// 
//输入：nums = [-100000]
//输出：-100000
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// -105 <= nums[i] <= 105 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
// Related Topics 数组 分治算法 动态规划 
// 👍 3329 👎 0


public class _53MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new _53MaximumSubarray().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
        * @Description: 贪心解法
         * 贪心贪的是哪里呢？
         *
         * 如果 -2 1 在一起，计算起点的时候，一定是从1开始计算，因为负数只会拉低总和，这就是贪心贪的地方！
         * 局部最优：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，因为负数加上下一个元素 “连续和”只会越来越小。
         *
         * 全局最优：选取最大“连续和”
         * 局部最优的情况下，并记录最大的“连续和”，可以推出全局最优。
         * 从代码角度上来讲：遍历nums，从头开始用count累积，如果count一旦加上nums[i]变为负数，
         * 那么就应该从nums[i+1]开始从0累积count了，因为已经变为负数的count，只会拖累总和。
         * 这相当于是暴力解法中的不断调整最大子序和区间的起始位置。
         *
         * 那有同学问了，区间终止位置不用调整么？ 如何才能得到最大“连续和”呢？
         * 区间的终止位置，其实就是如果count取到最大值了，及时记录下来了。例如如下代码：
         * if (count > result) result = count;
         * 这样相当于是用result记录最大子序和区间和（变相的算是调整了终止位置）。
        * @Param: [nums]
        */
        public int maxSubArray(int[] nums) {
            if (nums == null || nums.length <= 0) {
                return 0;
            }
            int curSum = 0;
            int result = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                curSum += nums[i];
                result = Math.max(curSum, result); // 取区间累计的最大值（相当于不断确定最大子序终止位置）
                if (curSum <= 0){
                    curSum = 0; // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
                }

            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}