package leetcode.middleProblem;

//给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。 
//
// 示例 1: 
//
// 输入: 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1。 
//
// 示例 2: 
//
// 输入: 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。 
//
// 说明: 你可以假设 n 不小于 2 且不大于 58。 
// Related Topics 数学 动态规划 
// 👍 602 👎 0


public class _343IntegerBreak {
    public static void main(String[] args) {
        Solution solution = new _343IntegerBreak().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int integerBreak(int n) {
            //因为题目要求n不小于2不大于58
            int[] dp = new int[n + 1];
            // dp[0] 和dp[1]没有意义
            // 2 = 1+1 => 1*1 = 1
            dp[2] = 1;
            for (int i = 3; i <= n; i++) {
                // 因为i -1 和j =1的时候重复了，所以不用再计算一遍j=i-1的值
                for (int j = 1; j < i-1; j++) {
                    dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));
                }
            }
            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}