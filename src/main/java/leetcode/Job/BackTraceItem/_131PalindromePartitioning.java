package leetcode.Job.BackTraceItem;

//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。 
//
// 回文串 是正着读和反着读都一样的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：[["a"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
// Related Topics 深度优先搜索 动态规划 回溯算法 
// 👍 707 👎 0


import java.util.ArrayList;
import java.util.List;

public class _131PalindromePartitioning {
    public static void main(String[] args) {
        Solution solution = new _131PalindromePartitioning().new Solution();
        System.out.println(solution.partition("aab"));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> partition(String s) {
            List<List<String>> result = new ArrayList<>();
            if (s == null || s.length() == 0) {
                return result;
            }
            getAllPalindromePartion(s, 0, new ArrayList<String>(), result);
            return result;
        }

        /**
         * @Description: 切割问题也可以抽闲为组合问题，其中截取子串问题，左闭右开还是左闭右闭，那么i的范围是不一样的
         * @Param: [str, startIndex, arrList, result]
         */
        private void getAllPalindromePartion(String str, int startIndex, ArrayList<String> arrList,
                                             List<List<String>> result) {
            if (startIndex >= str.length()) {
                result.add(new ArrayList<>(arrList));
                return;
            }
            for (int i = startIndex; i < str.length(); i++) {
                //1、因为subString是左闭右开的，所以i<=str.length() ，其中str.substring(startIndex, i)
                //2、若str.substring(startIndex, i + 1)此时是左闭右闭的,则i < str.length()
                if (!isPalindrome(str.substring(startIndex, i + 1))) {
                    continue;
                } else {
                    arrList.add(str.substring(startIndex, i + 1));
                }
                //1、subString是左闭右开的，所以i是取不到的，所以下一次应该从i开始，
                //2、若str.substring(startIndex, i + 1)此时是左闭右闭的,此时i是可以取到的，所以下一次从i+1开始
                getAllPalindromePartion(str, i + 1, arrList, result);
                arrList.remove(arrList.size() - 1);
            }
        }

        private boolean isPalindrome(String strTemp) {
            if (strTemp.length() == 0) {
                return false;
            }
            String reverseStr = new StringBuilder(strTemp).reverse().toString();
            return reverseStr.equals(strTemp);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}