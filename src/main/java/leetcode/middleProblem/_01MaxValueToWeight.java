package leetcode.middleProblem;

/**
 * @author: Created by zhanghl
 */
public class _01MaxValueToWeight {
    //01背包问题
    //有N件物品和一个最多能背重量为W 的背包。第i件物品的重量是weight[i]，
    // 得到的价值是value[i] 。
    // 每件物品只能用一次，求解将哪些物品装入背包里物品价值总和最大。
    //例子：
    //背包最大重量为4。
    //
    //物品为：
    //
    //      重量	价值
    //物品0	1	15
    //物品1	3	20
    //物品2	4	30
    //问背包能背的物品最大价值是多少？
    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int W = 4;
        System.out.println(new _01MaxValueToWeight().getMaxValueToW(weight, value, W));
    }

    public int getMaxValueToW(int[] weight, int[] value, int W) {
        //使用dp[i][j]:表示任意选择前[0, i-1]个物品的任一数量，放在容量为j的背包里，得到的最高价值为：dp[i][j]
        //而dp[i][j]的价值=选择第i个物品放入容量为j的背包里dp[i-1][j-weight[i]] + value[i] 和 不选择第i个物品的价值dp[i-1][j]两个中的最大值
        if (W <= 0) {
            return 0;
        }
        int num = weight.length;
        int[][] dp = new int[num + 1][W + 1];
        for (int i = 1; i <= num; i++) {
            for (int j = 1; j <= W; j++) {
                if (j >= weight[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        //打印dp数组
        for (int i = 0; i <= num; i++) {
            for (int j = 0; j <= W; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.print("\n");
        }
        return dp[num][W];
    }

}
