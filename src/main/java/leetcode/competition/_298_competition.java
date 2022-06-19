package leetcode.competition;

import java.util.Locale;

/**
 * TODO 类的实现描述
 *
 * @author zhanghuali
 * @version 1.0
 * @ClassName: _298_competition
 * @CreateTime: 2022/06/19 14:36:23
 * @Description:
 */
public class _298_competition {
    public static void main(String[] args) {
        _298_competition competition = new _298_competition();
        int m = 3, n = 5;
        int[][] prices = {{1, 4, 2}, {2, 2, 7}, {2, 1, 3}};
        System.out.println(competition.sellingWood(m, n, prices)); //19
        m = 4;
        n = 6;
        int[][] prices1 = {{3, 2, 10}, {1, 4, 2}, {4, 1, 3}};
        System.out.println(competition.sellingWood(m, n, prices1)); // 32

        /*String s = "111100010000011101001110001111000000001011101111111110111000011111011000010101110100110110001111001001011001010011010000011111101001101000000101101001110110000111101011000101";
        int k = 11713332;
        System.out.println(competition.longestSubsequence(s, k)); // 96
        s = "110001111001001000001001010001001011000110100110011101111100111000010010110111100110000010001011100011101010100100010110110101111100110100101010111100000000001000001000010010010101000011110110101111110011001100101000010101000011011010100110110000001110000101010110100111010001011101100001001111010100000100100101001111010100010000011100100111010010011101110010100010111111001111001001001001000101110011100010111100101110011101000101001101101000001110101011101010000001110101111111011011010010111110010011110010011111111001010001010101110001001000101101010110000011100101101101001111110010000101100010110000011011111001100100010101000010100110110110001010001100101110001110011011110011111110111000110011011100010000010100110000111110100110000001011100010001000111110111000111110000110001101010000010101001000001100101110110001100111001101011101110110011001000101101010111010111101011101100011100101";
        k = 41212881;
        System.out.println(competition.longestSubsequence(s, k)); // 470
        s = "1001010";
        k = 5; //5
        System.out.println(competition.longestSubsequence(s, k));
        s = "00101001";
        k = 1;  //6
        System.out.println(competition.longestSubsequence(s, k));
        s = "1";
        k = 215358216; //1
        System.out.println(competition.longestSubsequence(s, k));*/


       /* System.out.println(competition.minimumNumbers(10, 0));
        System.out.println(competition.minimumNumbers(30, 4));
        System.out.println(competition.minimumNumbers(18, 3));
        System.out.println(competition.minimumNumbers(4, 0));
        System.out.println(competition.minimumNumbers(58, 9));
        System.out.println(competition.minimumNumbers(37, 2));
        System.out.println(competition.minimumNumbers(0, 7));*/
        /*String s = "lEeTcOdE";
        System.out.println(competition.greatestLetter(s));
        s = "arRAzFif";
        System.out.println(competition.greatestLetter(s));
        s = "AbCdEfGhIjK";
        System.out.println(competition.greatestLetter(s));*/

    }

    /**
     * 5254. 卖木头块
     *
     * @param m
     * @param n
     * @param pries
     * @return
     * @author zhanghuali
     * @CreateTime: 2022/06/19 17:32:41
     * @Description:
     **/
    public long sellingWood(int m, int n, int[][] pries) {
        /**
         线性DP->木块劈完之后还是一个方方正正的木块:
         1.状态定义:f[i][j]表示切割高 i 长 j 木块最多能卖多少钱?
         2.状态转移:要求f[i][j]就要看看有哪几种分割方法?
         2.1 直接不劈整块卖 pr[i][j]
         2.2 横着劈 f[i][j]=max(f[i][j], f[k][j]+f[i-k][j]) k∈[1,i-1]
         2.3 竖着劈 f[i][j]=max(f[i][j], f[i][k]+f[i][j-k]) k∈[1,j-1]
         反过来想一下其实很好懂:对于一块高 i 宽 j 的木头
         怎么由更小的木头组成?只有两种方式:横着拼接和竖着拼接
         那为什么不同时横着拼接与竖着拼接?因为没必要!因为i与j的遍历均为正序
         比i与j小的的木头能卖的最大价钱其实已经有了
         不需要分割成4块,就算你分割成4块和分割成2块计算出来的其实是一样的
         因此归根到底转移途径就3条:横着切卖,竖着切卖,不切直接卖!
         3.初始化:可以将所有f[i][j]初始化为直接卖的价钱
         4.遍历顺序:i∈[1,m];j∈[1,n];k∈[1,i-1]以及[1,j-1]
         而k在i/2左右两侧切割都是对称的，只需要k∈[1,i/2]以及[1,j/2]
         5.返回形式:返回f[m][n]就代表高m 宽 n的木头经过切割能卖多少钱
         **/
        long[][] dp = new long[m + 1][n + 1];
        // 不切割，一大块整块卖
        for (int[] line : pries) {
            dp[line[0]][line[1]] = line[2];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 横着切卖
                /**
                 * |    |
                 * |    |
                 * -------
                 * |    |
                 * |    |
                 **/
                for (int k = 0; k <= i / 2; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }
                // 竖着切卖
                /**
                 *      |
                 * |    |   |   |
                 * |    |   |   |
                 *      |
                 *      |
                 **/
                for (int k = 0; k <= j / 2; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 6099. 小于等于 K 的最长二进制子序列
     *
     * @param s
     * @param k
     * @return
     * @author zhanghuali
     * @CreateTime: 2022/06/19 16:02:17
     * @Description:
     **/
    public int longestSubsequence(String s, int k) {
        // 贪心, 计算能最多有几个1
        long sum = 0;
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                count++;
            } else {
                // 这里count<n, n的大小问题：由于1 <= k <= 10^9, 2^30>10^9>2^27
                // 这里count设置一个边界是因为避免sum太大，溢出
                // count 只要小于30->64都可，（long的最大值的进制位数）
                if (count < 30 && sum + (1L << count) <= k) {
                    sum += 1L << count;
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 5218. 个位数字为 K 的整数之和
     *
     * @param num
     * @param k
     * @return
     * @author zhanghuali
     * @CreateTime: 2022/06/19 16:02:41
     * @Description:
     **/
    public int minimumNumbers(int num, int k) {
        // num = 10* m + k + k * n
        if (num == 0) {
            return 0;
        }
        for (int i = (num / 10 + 1); i >= 0; i--) {
            int beishu = k == 0 ? 1 : num / k + 1;
            for (int j = beishu; j > 0; j--) {
                int temp = 10 * i + k * j;
                if (temp == num) {
                    return j;
                }
            }
        }
        return -1;
    }

    public int minimumNumbersBadNOPass(int num, int k) {
        // num = 10* m + k + k * n
        if (num == 0) {
            return 0;
        }
        int count = 0;
        int dumpy = num;
        while (num > 0) {
            int gewei = num % 10;
            if (gewei >= k) {
                int temp = num - ((num / 10) * 10 + k);
                if (temp == num) {
                    num = num - k - 10;
                    count++;
                    continue;
                }
                num = temp;
            } else {
                int temp = num - ((num / 10 - 1) * 10 + k);
                if (temp >= num) {
                    num = num - k;
                    count++;
                    continue;
                }
                num = temp;
            }
            count++;
        }
        if (num == 0) {
            return count;
        } else {
            if (k != 0 && dumpy % k == 0) {
                return dumpy / k;
            }
            return -1;
        }

    }

    /**
     * 5242. 兼具大小写的最好英文字母
     *
     * @param s
     * @return
     * @author zhanghuali
     * @CreateTime: 2022/06/19 16:03:02
     * @Description:
     **/
    public String greatestLetter(String s) {
        String best = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < 'a') {
                String lowerCase = (c + "").toLowerCase();
                if (s.contains(lowerCase)) {
                    best = best.compareTo(c + "") > 0 ? best : c + "";
                }
            } else {
                String upperCase = (c + "").toUpperCase();
                if (s.contains(upperCase)) {
                    best = upperCase.compareTo(best) > 0 ? upperCase : best;
                }
            }
        }
        return best;

    }

}
