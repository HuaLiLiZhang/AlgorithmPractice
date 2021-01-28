//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 4877 👎 0


package leetcode.hot100;

import java.util.HashSet;
import java.util.Set;

public class _3LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new _3LongestSubstringWithoutRepeatingCharacters().new Solution();
        int longestSubStr = solution.lengthOfLongestSubstring("abcabcbb");
        System.out.println(longestSubStr);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 滑动窗口解决, 列举出可能的子串，使用hashset去重，
         * @Param: [s]
         */
        public int lengthOfLongestSubstring(String s) {
            Set<Character> sortSet = new HashSet<Character>();
            int right = -1;
            int longestSubStr = 0;
            int len = s.length();
            for (int l = 0; l < len; l++) {
                if (l != 0) {
                    sortSet.remove(s.charAt(l - 1));
                }
                while ((right + 1 < len) && (!sortSet.contains(s.charAt(right + 1)))) {
                    sortSet.add(s.charAt(right + 1));
                    right++;
                }
                longestSubStr = Math.max(longestSubStr, right - l + 1);
            }
            return longestSubStr;
        }


        public int lengthOfLongestSubstring2(String s) {
            int longestSubStr = 0;
            for (int i = 0; i < s.length(); i++) {
                for (int j = i; j < s.length(); j++) {
                    if (s.substring(i, j).contains(s.substring(j, j + 1))) {
                        break;
                    } else {
                        longestSubStr = Math.max(longestSubStr, (j + 1 - i));
                    }

                }
            }
            return longestSubStr;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}