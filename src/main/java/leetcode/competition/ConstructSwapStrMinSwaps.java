package leetcode.competition;

/**
 * @author: Created by zhanghl
 */
public class ConstructSwapStrMinSwaps {
    public static void main(String[] args) {
        String s = "010"/*"1110"*//*"111000"*//*"100"*/;
        System.out.println(new ConstructSwapStrMinSwaps().minSwaps(s));
    }

    /**
     * @Description: 给你一个二进制字符串 s ，现需要将其转化为一个 交替字符串 。请你计算并返回转化所需的 最小 字符交换次数，如果无法完成转化，返回 -1 。
     * <p>
     * 交替字符串 是指：相邻字符之间不存在相等情况的字符串。例如，字符串 "010" 和 "1010" 属于交替字符串，但 "0100" 不是。
     * <p>
     * 任意两个字符都可以进行交换，不必相邻 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "111000"
     * 输出：1
     * 解释：交换位置 1 和 4："111000" -> "101010" ，字符串变为交替字符串。
     * 示例 2：
     * <p>
     * 输入：s = "010"
     * 输出：0
     * 解释：字符串已经是交替字符串了，不需要交换。
     * 示例 3：
     * <p>
     * 输入：s = "1110"
     * 输出：-1
     * @Param: [s]
     */
    public int minSwaps(String s) {
        if (s == null || s.length() <= 0) {
            return -1;
        }
        int len = s.length();
        //这个数组存储以1开头的正确的交换字符串，比如10101010.....
        int[] trueArr = new int[len];
        trueArr[0] = 1;
        int firstStartTrueArrDiff = 0; //这里计数假设字符串需要转换为以1开头的交换字符串的话，不一样的字符的个数
        int zeroStartTrueArrDiff = 0;   //这里计数假设字符串需要转换为以0开头的交换字符串的话，不一样的字符的个数
        int first = 0;
        int zero = 0;
        for (int i = 0; i < len; i++) {
            //首先给以1开头的交换字符串数组赋值，赋值为10101010....
            if (i != 0) {
                trueArr[i] = trueArr[i - 1] ^ 1;
            }
            //计算字符串中1和0的个数
            int num = s.charAt(i) - '0';
            if (num == 0) {
                zero++;
            } else {
                first++;
            }
            //如果字符串转换为以1开头的正确交换字符串的换，不一样的字符数
            if (num != trueArr[i]) {
                firstStartTrueArrDiff++;
            }
            //如果字符串转换为以0开头的正确交换字符串的换，不一样的字符数
            if (num == trueArr[i]) {
                zeroStartTrueArrDiff++;
            }
        }
        //如果字符串中0和1的个数差大于等于2的话，则字符串不可交换，返回-1
        if (Math.abs(first - zero) >= 2) {
            return -1;
        }
        //如果变成以1开头或以0开头的正确交换字符串，有一个字符数不一样的个数为零，说明不用转换即可构成可交换字符串
        if (firstStartTrueArrDiff == 0 || zeroStartTrueArrDiff == 0) {
            return Math.min(firstStartTrueArrDiff, zeroStartTrueArrDiff);
        }
        //如果变成以1开头或以0开头的正确交换字符串，字符数不一样的个数都为偶数，说明都可交换为正切可交换字符串，那么取最小的交换次数即可，
        if (firstStartTrueArrDiff % 2 == 0 && zeroStartTrueArrDiff % 2 == 0) {
            return Math.min(firstStartTrueArrDiff / 2, zeroStartTrueArrDiff / 2);
        }
        //如果变成以1开头或以0开头的正确交换字符串，字符数不一样的个数只有一个为偶数，
        // 说明只有字符数不一样的个数为偶数的那种情况才能交换为正确的可交换字符，
        // 比如10001，与10101不一样的字符数为1，为奇数，所以转换为10101是不可能的，而与01010不一样的字符数为4，是可以转换为01010，转换次数为2
        if (firstStartTrueArrDiff % 2 == 0 || zeroStartTrueArrDiff % 2 == 0) {
            if (firstStartTrueArrDiff % 2 == 0) {
                return firstStartTrueArrDiff / 2;
            }
            if (zeroStartTrueArrDiff % 2 == 0) {
                return zeroStartTrueArrDiff / 2;
            }
        }
        return -1;
    }
}
