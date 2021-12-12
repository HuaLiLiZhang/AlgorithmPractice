package leetcode.editor.cn;

//给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。 
//
// 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。 
//
// 返回获得利润的最大值。 
//
// 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。 
//
// 
//
// 示例 1： 
//
// 
//输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
//输出：8
//解释：能够达到的最大利润:  
//在此处买入 prices[0] = 1
//在此处卖出 prices[3] = 8
//在此处买入 prices[4] = 4
//在此处卖出 prices[5] = 9
//总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8 
//
// 示例 2： 
//
// 
//输入：prices = [1,3,7,5,10,3], fee = 3
//输出：6
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 5 * 104 
// 1 <= prices[i] < 5 * 104 
// 0 <= fee < 5 * 104 
// 
// Related Topics 贪心 数组 动态规划 
// 👍 579 👎 0


public class _714BestTimeToBuyAndSellStockWithTransactionFee {
    public static void main(String[] args) {
        Solution t = new _714BestTimeToBuyAndSellStockWithTransactionFee().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length <= 1) {
                return 0;
            }
            int pre = prices[0];
            int sum = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] < pre) {
                    pre = prices[i];
                } else if (prices[i] > pre + fee) {
                    sum += prices[i] - pre - fee;
                    //因为如果有连续上升的阶段，那么只需要减一次手续费，也就是在最低点买入，最高点卖出即可。
                    //这里只在第一次的时候减去手续费。
                    pre = prices[i] - fee;
                }
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}