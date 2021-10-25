package leetcode.middleProblem;

//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 
// 👍 980 👎 0


public class _416PartitionEqualSubsetSum {
    public static void main(String[] args) {
        Solution solution = new _416PartitionEqualSubsetSum().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //其主要思路就是：数组中是否存在由某些数字组成的和等于sum/2
        //0-1背包：数组中取出数字塞进背包容量为sum/2的包里，并且最大价值为sum/2，价值表示数组的元素的值，重量为1
        public boolean canPartition(int[] nums) {
            if (nums == null || nums.length <= 0) {
                return false;
            }
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            if (sum % 2 != 0) {
                return false;
            }
            int N = sum / 2;
            int[] dp = new int[N + 1];
            for (int i = 0; i < nums.length; i++) {
                for (int j = N; j >= nums[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                }
            }
            return dp[N] == N;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}