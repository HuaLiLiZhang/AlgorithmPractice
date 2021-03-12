package leetcode.Job.CharacterString;

//给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。 
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例: 
//
// 输入: s = "abcdefg", k = 2
//输出: "bacdfeg"
// 
//
// 
//
// 提示： 
//
// 
// 该字符串只包含小写英文字母。 
// 给定字符串的长度和 k 在 [1, 10000] 范围内。 
// 
// Related Topics 字符串 
// 👍 117 👎 0


public class _541ReverseStringIi {
    public static void main(String[] args) {
        Solution solution = new _541ReverseStringIi().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //左闭右闭区间
        //所以当需要固定规律一段一段去处理字符串的时候，要想想在在for循环的表达式上做做文章。
        public String reverseStr(String s, int k) {
            char[] c = s.toCharArray();
            for (int i = 0; i < c.length; i += 2 * k) {
                if (i + k < c.length) {
                    reverseString(c, i, i + k - 1);
                } else {
                    reverseString(c, i, c.length - 1);
                }
            }
            return new String(c);
        }

        public void reverseString(char[] s, int left, int right) {
            while (left < right) {
                swap(s, left, right);
                left++;
                right--;
            }
        }

        private void swap(char[] s, int a, int b) {
            s[a] ^= s[b];
            s[b] ^= s[a];
            s[a] ^= s[b];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}