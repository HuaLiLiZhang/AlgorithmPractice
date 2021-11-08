package leetcode.middleProblem;

/**
 * 多重背包：
 * 背包最大重量为10。
 *
 * 物品为：
 *
 * 重量	价值	数量
 * 物品0	1	15	2
 * 物品1	3	20	3
 * 物品2	4	30	2
 * 问背包能背的物品最大价值是多少？
 *
 * @author: Created by zhanghl
 */
public class MultiPackMethod {
    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int[] nums = {2, 3, 2};
        int bagWeight = 10;
        System.out.println(multi_pack(nums, weight, value, bagWeight));
    }

    //时间复杂度：O(m * n * k) m：物品种类个数，n背包容量，k单类物品数量
    public static int multi_pack(int[] nums, int[] weight, int[] valus, int bagWeight) {
        int[] dp = new int[bagWeight + 1];
        for (int i = 0; i < weight.length; i++) {
            for (int j = bagWeight; j >= weight[i]; j--) {
                for (int k = 0; k <= nums[i] && (j - k * weight[i] >= 0); k++) {
                    dp[j] = Math.max(dp[j], dp[j - weight[i] * k] + k * valus[i]);
                }
            }
        }
        return dp[bagWeight];
    }
}
