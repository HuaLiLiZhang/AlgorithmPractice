package leetcode.middleProblem;

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
// 👍 553 👎 0


public class _714BestTimeToBuyAndSellStockWithTransactionFee {
    public static void main(String[] args) {
        Solution solution = new _714BestTimeToBuyAndSellStockWithTransactionFee().new Solution();
        int[] prices = {1, 3, 2, 8, 4, 9};
        System.out.println(solution.maxProfit(prices, 2));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 情况一：收获利润的这一天并不是收获利润区间里的最后一天（不是真正的卖出，相当于持有股票），所以后面要继续收获利润。
         * 情况二：前一天是收获利润区间里的最后一天（相当于真正的卖出了），今天要重新记录最小价格了。
         * 情况三：不作操作，保持原有状态（买入，卖出，不买不卖）
         * @Param: [prices, fee]
         */
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length <= 0) {
                return 0;
            }
            int profitSum = 0;
            int curMin = prices[0];
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] < curMin) {
                    curMin = prices[i];
                }
                /*if (prices[i] > curMin && prices[i] <= (curMin + fee)) {
                    continue;
                }*/
                if (prices[i] > (curMin + fee)) {
                    profitSum += prices[i] - curMin - fee;
                    curMin = prices[i] - fee; //情况一：保证还不是收获利润的最后一天（上升期），避免多减去一次手续费
                }
            }
            return profitSum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}