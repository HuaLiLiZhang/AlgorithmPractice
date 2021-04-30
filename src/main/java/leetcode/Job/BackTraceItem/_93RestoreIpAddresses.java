package leetcode.Job.BackTraceItem;

//给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。 
//
// 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。 
//
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 
//和 "192.168@1.1" 是 无效 IP 地址。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 
//
// 示例 2： 
//
// 
//输入：s = "0000"
//输出：["0.0.0.0"]
// 
//
// 示例 3： 
//
// 
//输入：s = "1111"
//输出：["1.1.1.1"]
// 
//
// 示例 4： 
//
// 
//输入：s = "010010"
//输出：["0.10.0.10","0.100.1.0"]
// 
//
// 示例 5： 
//
// 
//输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3000 
// s 仅由数字组成 
// 
// Related Topics 字符串 回溯算法 
// 👍 565 👎 0


import java.util.ArrayList;
import java.util.List;

public class _93RestoreIpAddresses {
    public static void main(String[] args) {
        Solution solution = new _93RestoreIpAddresses().new Solution();
        System.out.println(solution.restoreIpAddresses("25525511135"));
        System.out.println(solution.isLegalIPDuan("012"));
        System.out.println(solution.isLegalIPDuan("152"));
        System.out.println(solution.isLegalIPDuan("352"));
    }

    /**
     * 思路：
     * 首先最开始的时候判断是否含有[0-9]之外的字符以及字符串长度大于12
     * 然后在开始回溯方法：
     * 退出条件就是pointNum == 3的时候，如果最后一段满足要求，则加入结果集，如果不满足，直接返回
     * 循环： 从0开始，把字符串分为4段(剪枝：如果剩下的长度 大于(4-pointNum)*3的长度，那么直接退出)，
     *      对于每次从start开始的字符串，判断每一段转化为Int,判断是否小于等于255以及不以0开头，因为001是不合法的
     *      满足条件，将此小段加入临时的StringBuilder中，并且加入.，回溯，回溯结束删除此段和点，继续添加start到下一个位置是否满足
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> restoreIpAddresses(String s) {
            List<String> result = new ArrayList<>();
            if (s == null || s.length() <= 0 || s.length() > 12) {
                return result;
            }
            //字符串含有非数字的字符，不合法
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c > '9' || c < '0') {
                    return result;
                }
            }
            getAllValidIpAddress(s, 0, result, 0, new StringBuilder());
            return result;
        }

        /**
         * @Description: startIndex一定是需要的，因为不能重复分割，记录下一层递归分割的起始位置。
         * 本题我们还需要一个变量pointNum，记录添加逗点的数量。
         * @Param: [s, start, result, pointNum]
         */
        private void getAllValidIpAddress(String s, int start, List<String> result, int pointNum,
                                          StringBuilder stringBuilder) {
            if (pointNum == 3) {
                //如果前面已经有三个句点，那么分割结束，判断最后一截字符串是否符合要求，符合加入结果集，不符合返回
                String end = s.substring(start);
                if (start < s.length() && isLegalIPDuan(end)) {
                    result.add(stringBuilder.toString() + end);
                }
                return;
            }
            for (int i = start; i < s.length() && ((s.length() - start) <= 12 - pointNum * 3); i++) {
                String temp = s.substring(start, i + 1);
                //先判断s的[start, i + 1)区间是否合法
                if (!isLegalIPDuan(temp)) {
                    continue;
                }
                int len = stringBuilder.length();
                //合法的话，把此段字符串加入临时str,并加入点 .
                stringBuilder.append(temp);
                stringBuilder.append(".");
                //回溯，从i+1开始回溯，因为s.substring取不到i+1
                getAllValidIpAddress(s, i + 1, result, pointNum + 1, stringBuilder);
                //回溯删掉之前加入的字符串
                stringBuilder.delete(len, stringBuilder.length());
            }
        }

        /**
         * @Description: 判断str是否是合法的Ip段：
         * 段位以0为开头的数字不合法
         * 段位里有非正整数字符不合法 //这个在最开始的时候判断
         * 段位如果大于255了不合法
         * @Param: [strIpPoint]
         */
        private boolean isLegalIPDuan(String strIpPoint) {
            //字符串长度超过3、小于等于0不合法
            if (strIpPoint.length() > 3 || strIpPoint.length() <= 0) {
                return false;
            }
            //字符串以0开头不合法
            if (strIpPoint.length() > 1 && strIpPoint.startsWith("0")) {
                return false;
            }
            //字符串的取值大于255不合法
            Integer val = Integer.valueOf(strIpPoint);
            return val >= 0 && val <= 255;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}