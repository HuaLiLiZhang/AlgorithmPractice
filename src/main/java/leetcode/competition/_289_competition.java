package leetcode.competition;

import java.util.*;

/**
 * TODO 类的实现描述
 *
 * @author zhanghuali
 * @version 1.0
 * @ClassName: _289_competition
 * @CreateTime: 2022/04/17 10:41:32
 * @Description:
 */
public class _289_competition {


    public static void main(String[] args) {
        int[][] grid1 = {{23, 17, 15, 3, 20}, {8, 1, 20, 27, 11}, {9, 4, 6, 2, 21}, {40, 9, 1, 10, 6}, {22, 7, 4, 5, 3}};
        int[][] grid2 = {{4, 3, 2}, {7, 6, 1}, {8, 8, 8}};
        int[][] grid3 = {{1, 5, 2, 4, 25}};
        int[][] grid4 = {{10}, {6}, {15}};
        int[][] grid5 = {{824, 709, 193, 413, 701, 836, 727},
                {135, 844, 599, 211, 140, 933, 205},
                {329, 68, 285, 282, 301, 387, 231},
                {293, 210, 478, 352, 946, 902, 137},
                {806, 900, 290, 636, 589, 522, 611},
                {450, 568, 990, 592, 992, 128, 92},
                {780, 653, 795, 457, 980, 942, 927},
                {849, 901, 604, 906, 912, 866, 688}};
//        System.out.println(maxTrailingZeros(grid1));
//        System.out.println(maxTrailingZeros(grid2));
//        System.out.println(maxTrailingZeros(grid3));
//        System.out.println(maxTrailingZeros(grid4));
        System.out.println(maxTrailingZeros(grid5));
//        int[] tasks1 = {2, 2, 3, 3, 2, 4, 4, 4, 4, 4};
//        int[] tasks2 = {2, 3, 3};
//        int[] tasks3 = {5, 5, 5, 5};
//        System.out.println(minimumRounds(tasks1));
//        System.out.println(minimumRounds(tasks2));
//        System.out.println(minimumRounds(tasks3));
//        System.out.println(digitSum("11111222223", 3));
//        System.out.println(digitSum("00000000", 3));
//        System.out.println(digitSum("01234567890", 2));

    }

    //示例 1：
    //
    //输入：s = "11111222223", k = 3
    //输出："135"
    //解释：
    //- 第一轮，将 s 分成："111"、"112"、"222" 和 "23" 。
    //  接着，计算每一组的数字和：1 + 1 + 1 = 3、1 + 1 + 2 = 4、2 + 2 + 2 = 6 和 2 + 3 = 5 。
    //  这样，s 在第一轮之后变成 "3" + "4" + "6" + "5" = "3465" 。
    //- 第二轮，将 s 分成："346" 和 "5" 。
    //  接着，计算每一组的数字和：3 + 4 + 6 = 13 、5 = 5 。
    //  这样，s 在第二轮之后变成 "13" + "5" = "135" 。
    //现在，s.length <= k ，所以返回 "135" 作为答案。
    //示例 2：
    //
    //输入：s = "00000000", k = 3
    //输出："000"
    //解释：
    //将 "000", "000", and "00".
    //接着，计算每一组的数字和：0 + 0 + 0 = 0 、0 + 0 + 0 = 0 和 0 + 0 = 0 。
    //s 变为 "0" + "0" + "0" = "000" ，其长度等于 k ，所以返回 "000" 。
    public static String digitSum(String s, int k) {
        if (s.length() <= k) {
            return s;
        }
        StringBuffer sb = new StringBuffer(s);
        StringBuffer sbNew = new StringBuffer();
        while (sb.length() > k) {
            int len = sb.length();
            int count = 0;
            for (int i = 0; i < len; i += k) {
                int tmp = 0;
                for (int j = i; j < i + k && j < len; j++) {
                    tmp += sb.charAt(j) - '0';
                }
                sbNew.append(tmp + "");
                count++;
            }
            sb = sbNew;
            sbNew = new StringBuffer();

        }
        return sb.toString();
    }


    public static int minimumRounds(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1);
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int c = entry.getValue();
            if (c == 1) {
                return -1;
            }
           /* if (c % 2 == 3 || c % 2 == 0 || c % 3 == 2 || c % 3 == 0) {

            }*/
            if (c % 3 == 0) {
                count += c / 3;
            } else if (c % 3 == 2) {
                count += c / 3 + 1;
            } else if (c % 2 == 0) {
                count += c / 3 + 1;
            } else if (c % 2 == 1) {
                count += c / 3 + 1;
            }
        }
        return count;
    }

    public static int maxTrailingZeros(int[][] grid) {
        // 总体思路:枚举四种拐角路径->[左上,左下,右上,右下]
        // 这里由于是求路径中尾随0的的最大个数,因此肯定是路径某个方向取最长的结果(多了某个格子不影响)
        // 我们要找尾随0的个数必须要10因子,必然可以分解为2与5因子,[2,5]的对数就是尾随0个数->min(2的个数,5的个数)
        int m = grid.length, n = grid[0].length;
        // 创建四个二维数组分别代表:grid[i - 1][j - 1](含)左边的2,5因子总个数;grid[i][j](含)上边的2,5因子总个数
        int[][] r2 = new int[m + 1][n + 1], r5 = new int[m + 1][n + 1], c2 = new int[m + 1][n + 1], c5 = new int[m + 1][n + 1];
        // 默认左边界和上边界的为r2[i][0]=c2[0][j]=r5[i][0]=c5[0][j]=0
        // 遍历每个格子完善r2,r5,c2,c5
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 当前格子的值
                int cur = grid[i - 1][j - 1];
                int num2 = getFactorNum(cur, 2), num5 = getFactorNum(cur, 5);
                // 进行转移求前缀和
                r2[i][j] = r2[i][j - 1] + num2;
                r5[i][j] = r5[i][j - 1] + num5;
                c2[i][j] = c2[i - 1][j] + num2;
                c5[i][j] = c5[i - 1][j] + num5;
            }
        }
        // 结果
        int res = 0;
        // 遍历四种拐弯方向(其余的都可以进行等价)
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // grid[i-1][j-1]为拐弯的格子,总体计算方法就是横竖的2或者5因子个数相加,注意避免重叠
                // 左边向右出发,然后向上走
                int min1 = Math.min(r2[i][j] + c2[i - 1][j], r5[i][j] + c5[i - 1][j]);
                res = Math.max(res, min1);
                // 左边向右出发,然后向下走
                int min2 = Math.min(r2[i][j] + c2[m][j] - c2[i][j], r5[i][j] + c5[m][j] - c5[i][j]);
                res = Math.max(res, min2);
                // 右边向左出发,然后向上走
                int min3 = Math.min(r2[i][n] - r2[i][j] + c2[i][j], r5[i][n] - r5[i][j] + c5[i][j]);
                res = Math.max(res, min3);
                // 右边向左出发,然后向下走
                int min4 = Math.min(r2[i][n] - r2[i][j] + c2[m][j] - c2[i - 1][j], r5[i][n] - r5[i][j] + c5[m][j] - c5[i - 1][j]);
                res = Math.max(res, min4);
            }
        }
        return res;
    }

    // 获取某个数字对应因子的数目:8=2*2*2
    private static int getFactorNum(int num, int factor) {
        int k = 0;
        // 提取因子
        while (num % factor == 0) {
            num /= factor;
            k++;
        }
        return k;
    }

    public static int maxTrailingZeros_溢出啦(int[][] grid) {
        int col = grid[0].length;
        int row = grid.length;
        int count = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                long up = 1;
                long down = 1;
                long zuo = 1;
                long you = 1;
                for (int k = j; k >= 0; k--) {
                    up *= grid[k][i];
                }
                for (int k = j; k < row; k++) {
                    down *= grid[k][i];
                }
                for (int k = i - 1; k >= 0; k--) {
                    zuo *= grid[j][k];
                }
                for (int k = i + 1; k < col; k++) {
                    you *= grid[j][k];
                }
                int tmp = get2_5_min(up, down, zuo, you);
                count = Math.max(tmp, count);
            }
        }
        return count;
    }

    private static int get2_5_min(long up, long down, long zuo, long you) {
        int up_2 = up % 2 == 0 ? get2_5Count(up, 2) : 0;
        int up_5 = up % 5 == 0 ? get2_5Count(up, 5) : 0;
        int down_2 = down % 2 == 0 ? get2_5Count(down, 2) : 0;
        int down_5 = down % 5 == 0 ? get2_5Count(down, 5) : 0;
        int zuo_2 = zuo % 2 == 0 ? get2_5Count(zuo, 2) : 0;
        int zuo_5 = zuo % 5 == 0 ? get2_5Count(zuo, 5) : 0;
        int you_2 = you % 2 == 0 ? get2_5Count(you, 2) : 0;
        int you_5 = you % 5 == 0 ? get2_5Count(you, 5) : 0;
        int max1 = Math.min(up_2 + zuo_2, up_5 + zuo_5);
        int max2 = Math.min(up_2 + you_2, up_5 + you_5);
        int max3 = Math.min(down_2 + zuo_2, down_5 + you_5);
        int max4 = Math.min(down_2 + you_2, down_5 + you_5);
        int max5 = Math.max(max1, max2);
        int max6 = Math.max(max3, max4);
        return Math.max(max5, max6);
    }

    public static int get2_5Count(long chengji, int k) {
        int tmp = 0;
        while (chengji % k == 0) {
            tmp += 1;
            chengji /= k;
        }
        return tmp;
    }


}
