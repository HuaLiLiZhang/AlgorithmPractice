package leetcode.competition;

/**
 * @author: Created by zhanghl
 */
//给你一个非常大的整数 n 和一个整数数字 x ，大整数 n 用一个字符串表示。n 中每一位数字和数字 x 都处于闭区间 [1, 9] 中，且 n 可能表示一个 负数 。
//
//你打算通过在 n 的十进制表示的任意位置插入 x 来 最大化 n 的 数值 ​​​​​​。但 不能 在负号的左边插入 x 。
//
//例如，如果 n = 73 且 x = 6 ，那么最佳方案是将 6 插入 7 和 3 之间，使 n = 763 。
//如果 n = -55 且 x = 2 ，那么最佳方案是将 2 插在第一个 5 之前，使 n = -255 。
//返回插入操作后，用字符串表示的 n 的最大值。
//
//
//
//示例 1：
//
//输入：n = "99", x = 9
//输出："999"
//解释：不管在哪里插入 9 ，结果都是相同的。
//示例 2：
//
//输入：n = "-13", x = 2
//输出："-123"
//解释：向 n 中插入 x 可以得到 -213、-123 或者 -132 ，三者中最大的是 -123 。
//
//
//提示：
//
//1 <= n.length <= 105
//1 <= x <= 9
//n​​​ 中每一位的数字都在闭区间 [1, 9] 中。
//n 代表一个有效的整数。
//当 n 表示负数时，将会以字符 '-' 开始。
public class InsertXmakeNBigger {
    public static void main(String[] args) {
        String n = "99";
        int x = 9;
        System.out.println(new InsertXmakeNBigger().maxValue(n, x));
        n = "-13";
        x = 2;
        System.out.println(new InsertXmakeNBigger().maxValue(n, x));
        n = "73";
        x = 6;
        System.out.println(new InsertXmakeNBigger().maxValue(n, x));
        n = "-55";
        x = 2;
        System.out.println(new InsertXmakeNBigger().maxValue(n, x));
        n = "-648468153646";
        x = 5;
        System.out.println(new InsertXmakeNBigger().maxValue(n, x));
    }

    public String maxValue(String n, int x) {
        if (n == null || n.length() <= 0) {
            return x + "";
        }
        boolean isBiggerZero = true;
        if (n.startsWith("-")) {
            isBiggerZero = false;
        }
        String afterInsert = insertMax(n, x, isBiggerZero);
        return afterInsert;
    }

    private String insertMax(String n, int x, boolean isBiggerZero) {
        int insertIndex = n.length();
        if (isBiggerZero) {
            for (int i = 0; i < n.length(); i++) {
                int curInt = n.charAt(i) - '0';
                if (x <= curInt) {
                } else {
                    insertIndex = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < n.length(); i++) {
                int curInt = n.charAt(i) - '0';
                if (x >= curInt) {
                } else {
                    insertIndex = i;
                    break;
                }
            }
        }
        if (insertIndex == 0) {
            return x + n;
        } else if (insertIndex == n.length()) {
            return n + x;
        } else {
            return n.substring(0, insertIndex) + x + n.substring(insertIndex);
        }
    }
}
