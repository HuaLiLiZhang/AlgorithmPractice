package leetcode.Job.CharacterString;

//给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。 
//
// 示例 1: 
//
// 
//输入: "abab"
//
//输出: True
//
//解释: 可由子字符串 "ab" 重复两次构成。
// 
//
// 示例 2: 
//
// 
//输入: "aba"
//
//输出: False
// 
//
// 示例 3: 
//
// 
//输入: "abcabcabcabc"
//
//输出: True
//
//解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
// 
// Related Topics 字符串 
// 👍 469 👎 0


public class _459RepeatedSubstringPattern {
    public static void main(String[] args) {
        Solution solution = new _459RepeatedSubstringPattern().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean repeatedSubstringPattern(String s) {
            if (s == null || s.length() <= 1) {
                return false;
            }
            int[] next = getNext(s);
            return next[next.length - 1] != 0 && s.length() % (s.length() - (next[next.length - 1])) == 0;
        }

        private int[] getNext(String s) {
            int[] next = new int[s.length()];
            next[0] = 0;
            int j = 0;
            for (int i = 1; i < s.length(); i++) {
                while (j > 0 && s.charAt(i) != s.charAt(j)) {
                    j = next[j - 1];
                }
                if (s.charAt(i) == s.charAt(j)) {
                    j++;
                }
                next[i] = j;
            }
            return next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}