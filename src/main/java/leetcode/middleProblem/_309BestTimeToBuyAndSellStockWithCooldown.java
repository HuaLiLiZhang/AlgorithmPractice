package leetcode.middleProblem;

//给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。 
//
// 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）: 
//
// 
// 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 
// 
//
// 示例: 
//
// 输入: [1,2,3,0,2]
//输出: 3 
//解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出] 
// Related Topics 数组 动态规划 
// 👍 944 👎 0


import java.util.Arrays;

public class _309BestTimeToBuyAndSellStockWithCooldown {
    public static void main(String[] args) {
        Solution solution = new _309BestTimeToBuyAndSellStockWithCooldown().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length <= 1) {
                return 0;
            }
            int n = prices.length;
            // dp[i][j] 代表第 i 天在 j 状态下的最大利润
            // j 代表今天持有股票的状态，0 为未持有，1 为持有, 2为卖出
            int[][] dp = new int[n][3];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            dp[0][2] = 0;
            for (int i = 1; i < n; i++) {
                // 一、第i天不持股且没卖出的状态dp[i][0]，也就是我没有股票，而且还不是因为我卖了它才没有的，那换句话说是从i-1天到第i天转移时，它压根就没给我股票！所以i-1天一定也是不持有，那就是不持有的两种可能：i-1天不持股且当天没有卖出dp[i-1][0]；i-1天不持股但是当天卖出去了dp[i-1][2]；
                //所以： dp[i][0]=max(dp[i-1][0],dp[i-1][2])
                //
                //二、第i天持股dp[i][1]，今天我持股，来自两种可能：
                //1、要么是昨天我就持股，今天继承昨天的，也就是dp[i-1][1]，这种可能很好理解；
                //2、要么：是昨天我不持股，今天我买入的，但前提是昨天我一定没卖！因为如果昨天我卖了，那么今天我不能交易！也就是题目中所谓“冷冻期”的含义，只有昨天是“不持股且当天没卖出”这个状态，我今天才能买入！所以是dp[i-1][0]-p[i]
                //所以： dp[i][1]=max(dp[i-1][1],dp[i-1][0]-p[i])
                //
                //三、i天不持股且当天卖出了，这种就简单了，那就是说昨天我一定是持股的，要不然我今天拿什么卖啊，而持股只有一种状态，昨天持股的收益加上今天卖出得到的新收益，就是dp[i-1][1]+p[i]啦
                //所以：dp[i][2]=dp[i-1][1]+p[i]
                //
                //总结：最后一天的最大收益有两种可能，而且一定是“不持有”状态下的两种可能，把这两种“不持有”比较一下大小，返回即可
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
                dp[i][2] = dp[i - 1][1] + prices[i];
            }
            return Math.max(dp[n - 1][0], dp[n - 1][2]);
        }

        public int maxProfit_4status(int[] prices) {
            if (prices == null || prices.length <= 1) {
                return 0;
            }
            //时间复杂度：O(n)
            //空间复杂度：O(n)

            //达到买入股票状态（状态一）即：dp[i][0]，有两个具体操作：
            //
            //操作一：前一天就是持有股票状态（状态一），dp[i][0] = dp[i - 1][0]
            //操作二：今天买入了，有两种情况
            //前一天是冷冻期（状态四），dp[i - 1][3] - prices[i]
            //前一天是保持卖出股票状态（状态二），dp[i - 1][1] - prices[i]
            //所以操作二取最大值，即：max(dp[i - 1][3], dp[i - 1][1]) - prices[i]
            //
            //那么dp[i][0] = max(dp[i - 1][0], max(dp[i - 1][3], dp[i - 1][1]) - prices[i]);
            //


            //达到保持卖出股票状态（状态二）即：dp[i][1]，有两个具体操作：
            //
            //操作一：前一天就是状态二
            //操作二：前一天是冷冻期（状态四）
            //dp[i][1] = max(dp[i - 1][1], dp[i - 1][3]);
            //


            //达到今天就卖出股票状态（状态三），即：dp[i][2] ，只有一个操作：
            //
            //操作一：昨天一定是买入股票状态（状态一），今天卖出
            //即：dp[i][2] = dp[i - 1][0] + prices[i];
            //


            //达到冷冻期状态（状态四），即：dp[i][3]，只有一个操作：
            //
            //操作一：昨天卖出了股票（状态三）
            //p[i][3] = dp[i - 1][2];
            int n = prices.length;
            int[][] dp = new int[n][4];
            dp[0][0] = -prices[0];
            dp[0][1] = 0;
            dp[0][2] = 0;
            dp[0][3] = 0;
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][3] - prices[i], dp[i - 1][1] - prices[i]));
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]);
                dp[i][2] = dp[i - 1][0] + prices[i];
                dp[i][3] = dp[i - 1][2];
            }
            return Arrays.stream(dp[n - 1]).max().getAsInt();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}