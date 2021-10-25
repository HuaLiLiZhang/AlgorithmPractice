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
        System.out.println(new _01MaxValueToWeight().getMaxValueToW1(weight, value, W));
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

    //当然为了节省空间，也可以用一维滚动数组
    //其实可以发现如果把dp[i - 1]那一层拷贝到dp[i]上，
    // 表达式完全可以是：dp[i][j] = max(dp[i][j], dp[i][j - weight[i]] + value[i]);
    //
    //与其把dp[i - 1]这一层拷贝到dp[i]上，不如只用一个一维数组了，
    // 只用dp[j]（一维数组，也可以理解是一个滚动数组）。

    private int getMaxValueToW1(int[] weight, int[] value, int W) {
        if (W <= 0) {
            return 0;
        }
        int len = weight.length;
        int[] dp = new int[W + 1];
        for (int i = 0; i < len; i++) {
            //背包顺序必须是倒序
            //这里大家发现和二维dp的写法中，遍历背包的顺序是不一样的！
            //
            //二维dp遍历的时候，背包容量是从小到大，而一维dp遍历的时候，背包是从大到小。
            //
            //为什么呢？
            //
            //倒叙遍历是为了保证物品i只被放入一次！。但如果一旦正序遍历了，那么物品0就会被重复加入多次！
            //
            //举一个例子：物品0的重量weight[0] = 1，价值value[0] = 15
            //
            //如果正序遍历
            //
            //dp[1] = dp[1 - weight[0]] + value[0] = 15
            //
            //dp[2] = dp[2 - weight[0]] + value[0] = 30
            //
            //此时dp[2]就已经是30了，意味着物品0，被放入了两次，所以不能正序遍历。
            for (int j = W; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j - weight[i]] + value[i], dp[j]);
            }
        }
        return dp[W];
    }
}
