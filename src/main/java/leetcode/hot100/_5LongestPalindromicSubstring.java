//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3152 👎 0


package leetcode.hot100;

public class _5LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new _5LongestPalindromicSubstring().new Solution();
        System.out.println(solution.longestPalindrome("bb"));
        System.out.println(solution.longestPalindrome("babad"));
        System.out.println(solution.longestPalindrome("cbbd"));
        System.out.println(solution.longestPalindrome("a"));
        System.out.println(solution.longestPalindrome("aaaa"));
        System.out.println(solution.longestPalindrome("aaaaa"));
        System.out.println(solution.longestPalindrome("ac"));
        System.out.println(solution.longestPalindrome("aacabdkacaa"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 动态规划
         * 动态转移方程：当i,j位置的子串为回文子串的话，那么i+1,j-1的子串也是回文子串
         * 即：dp[i, j] = dp[i+1][j-1}&&si==sj
         * 并且dp[i,i] = true, => dp[i, i+1] && Si==Si+1
         * <p>
         * 在j-i<3, 也就是区间[i+1, j-1] 的区间长度小于2时，dp[i+1, j-1] == true
         * @Param:
         */
        public String longestPalindrome(String s) {
            int n = s.length();
            if (n <= 1) {
                return s;
            }
            boolean[][] dp = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                dp[i][i] = true;
            }
            int startIndex = 0;
            int maxLen = 1;
            //错误的顺序：因为参考值还没算出来，这叫"后效性"，这种状态转移是错误的“
            //|T|1|2|3|4|
            //| |T|5|6|7|
            //| | |T|8|9|
            //| | | |T|10|
            //| | | | |T|
            //因为3的位置依赖5的位置的值，所以3不能先初始化，以及4依赖6的值
            //所以正确的是：
            //|T|1|2|4|7|
            //| |T|3|5|8|
            //| | |T|6|9|
            //| | | |T|10|
            //| | | | |T|
            //因为dp[i, j]依赖于dp[i+1,j-1]，所以这种方法前面的值还没初始化，不能推算出后面的值
//            for (int i = 0; i < n; i++) {
//                for (int j = i + 1; j < n; j++) {
//
//                }
//            }
            //正确的顺序：

            for (int j = 1; j < n; j++) {
                for (int i = 0; i < j; i++) {
                    if (s.charAt(i) != s.charAt(j)) {
                        dp[i][j] = false;
                    } else {
                        if (j - 1 + 1 >= 4) {
                            dp[i][j] = dp[i + 1][j - 1];
                        } else {
                            dp[i][j] = true;
                        }
                    }
                    // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                    if (dp[i][j] && (j - i + 1) > maxLen) {
                        startIndex = i;
                        maxLen = j - i + 1;
                    }
                }
            }
            return s.substring(startIndex, startIndex + maxLen);
        }


        /**
         * @Description: 时间复杂度：O(N^3)，这里 NN 是字符串的长度，枚举字符串的左边界、右边界，然后继续验证子串是否是回文子串，这三种操作都与 NN 相关；
         * 空间复杂度：O(1)，只使用到常数个临时变量，与字符串长度无关。
         * @Param: [s]
         */
        public String longestPalindrome1(String s) {
            int start = 0;
            int largetLen = 0;
            for (int i = 0; i < s.length(); i++) {
                for (int j = i; j <= s.length(); j++) {
                    if ((j - i) > largetLen && isPalindrome(s.substring(i, j))) {
                        start = i;
                        largetLen = j - i;
                    }
                }
            }
            return s.length() < 2 ? s : s.substring(start, start + largetLen);
        }

        private boolean isPalindrome(String substring) {
            int left = 0;
            int right = substring.length() - 1;
            while (left < right) {
                if (substring.charAt(left) == substring.charAt(right)) {
                    left++;
                    right--;
                } else {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}