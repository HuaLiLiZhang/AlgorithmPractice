package leetcode.easyProblem;

//数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。 
//
// 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。 
//
// 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。 
//
// 
//
// 示例 1： 
//
// 
//输入：cost = [10, 15, 20]
//输出：15
//解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
// 
//
// 示例 2： 
//
// 
//输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
//输出：6
//解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
// 
//
// 
//
// 提示： 
//
// 
// cost 的长度范围是 [2, 1000]。 
// cost[i] 将会是一个整型数据，范围为 [0, 999] 。 
// 
// Related Topics 数组 动态规划 
// 👍 668 👎 0


public class _746MinCostClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new _746MinCostClimbingStairs().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minCostClimbingStairs1(int[] cost) {
            if (cost == null || cost.length <= 1) {
                return 0;
            }
            //往前括一步是因为：要走到最后面，也就是最后一个元素的后一位或者最后一个元素，最后一个元素不加花费
            int[] dp = new int[cost.length + 1];
            dp[0] = cost[0];
            dp[1] = cost[1];
            for (int i = 2; i < dp.length; i++) {
                if (i == dp.length - 1) {
                    //最后一个元素不花费体力值
                    dp[i] = Math.min(dp[i - 1], dp[i - 2]);
                    break;
                }
                dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
            }
            return Math.min(dp[dp.length - 1], dp[dp.length - 2]);
        }

        public int minCostClimbingStairs(int[] cost) {
            if (cost == null || cost.length <= 1) {
                return 0;
            }
            int[] dp = new int[cost.length];
            dp[0] = cost[0];
            dp[1] = cost[1];
            for (int i = 2; i < cost.length; i++) {
                dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
            }
            //最后一位不加体力值，计算最后一位或者前一位的最小值
            return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}