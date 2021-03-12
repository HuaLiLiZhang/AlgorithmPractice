package leetcode.Job.CharacterString;

//实现 strStr() 函数。 
//
// 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如
//果不存在，则返回 -1。 
//
// 示例 1: 
//
// 输入: haystack = "hello", needle = "ll"
//输出: 2
// 
//
// 示例 2: 
//
// 输入: haystack = "aaaaa", needle = "bba"
//输出: -1
// 
//
// 说明: 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。 
// Related Topics 双指针 字符串 
// 👍 738 👎 0


public class _28ImplementStrstr {
    public static void main(String[] args) {
        Solution solution = new _28ImplementStrstr().new Solution();
        System.out.println(solution.strStr("aabaabaaf", "aabaaf"));
        System.out.println(solution.strStr("hello", "ll"));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int strStr(String haystack, String needle) {
            // 各种边界情况
            if (haystack != null && haystack.equals(needle)) {
                return 0;
            }
            if (haystack == null || haystack.length() == 0) {
                return -1;
            }
            if (needle == null || needle.length() <= 0) {
                return 0;
            }
            int[] next = getNextArr(needle);
            int j = 0;
            for (int i = 0; i < haystack.length(); i++) {

                while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {  // 不匹配
                    j = next[j - 1];// j 寻找之前匹配的位置
                }
                if (haystack.charAt(i) == needle.charAt(j)) { // 匹配，j和i同时向后移动
                    j++;
                }
                if (j == needle.length()) { // 文本串s里出现了模式串t
                    return i - needle.length() + 1;
                }
            }
            return -1;
        }

        /**
         * @Description: 四步：初始化next数组， 前后缀不相等，前后缀相等，更新next数组
         * @Param: [pattern]
         */
        public int[] getNextArr(String pattern) {
            int j = 0; //前缀末尾
            int[] next = new int[pattern.length()];
            next[0] = 0;
            for (int i = 1; i < pattern.length(); i++) { //表示后缀末尾，从1开始
                while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) { // 前后缀不相同了
                    j = next[j - 1];  // 向前回溯
                }
                if (pattern.charAt(i) == pattern.charAt(j)) { // 找到相同的前后缀
                    j++;
                }
                next[i] = j; // 将j（前缀的长度）赋给next[i]
            }
            return next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}