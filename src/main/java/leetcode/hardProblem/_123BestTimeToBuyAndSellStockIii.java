package leetcode.hardProblem;

//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 
//输入：prices = [3,3,5,0,0,3,1,4]
//输出：6
//解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 
//
// 示例 2： 
//
// 
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3： 
//
// 
//输入：prices = [7,6,4,3,1] 
//输出：0 
//解释：在这个情况下, 没有交易完成, 所以最大利润为 0。 
//
// 示例 4： 
//
// 
//输入：prices = [1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 105 
// 0 <= prices[i] <= 105 
// 
// Related Topics 数组 动态规划 
// 👍 803 👎 0


public class _123BestTimeToBuyAndSellStockIii {
    public static void main(String[] args) {
        Solution solution = new _123BestTimeToBuyAndSellStockIii().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
        * @Description:
         * 思路：
         * 由于我们最多可以完成两笔交易，因此在任意一天结束之后，我们会处于以下五个状态中的一种：
         * 未进行过任何操作；
         * 只进行过一次买操作；
         * 进行了一次买操作和一次卖操作，即完成了一笔交易；
         * 在完成了一笔交易的前提下，进行了第二次买操作；
         * 完成了全部两笔交易。
         *
         * 由于第一个状态的利润显然为 0，因此我们可以不用将其记录。
         * 对于剩下的四个状态，我们分别将它们的最大利润记为buy1 ，sell1，buy2以及 sell2
         *
         * 时间复杂度：O(n)，其中 n 是数组 prices 的长度。
         * 空间复杂度：O(1)。
        * @Param: [prices]
        */
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length <= 1) {
                return 0;
            }
            int buy1 = -prices[0];
            int sell1 = 0;
            int buy2 = -prices[0];
            int sell2 = 0;
            for (int i = 1; i < prices.length; i++) {
                buy1 = Math.max(buy1, -prices[i]);
                sell1 = Math.max(sell1, buy1 + prices[i]);
                buy2 = Math.max(buy2, sell1 - prices[i]);
                sell2 = Math.max(sell2, buy2 + prices[i]);
            }
            return sell2;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}