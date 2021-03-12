package leetcode.Job.CharacterString;

//给定一个字符串，逐个翻转字符串中的每个单词。 
//
// 说明： 
//
// 
// 无空格字符构成一个 单词 。 
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。 
// 
//
// 
//
// 示例 1： 
//
// 输入："the sky is blue"
//输出："blue is sky the"
// 
//
// 示例 2： 
//
// 输入："  hello world!  "
//输出："world! hello"
//解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 
//
// 示例 3： 
//
// 输入："a good   example"
//输出："example good a"
//解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
// 
//
// 示例 4： 
//
// 输入：s = "  Bob    Loves  Alice   "
//输出："Alice Loves Bob"
// 
//
// 示例 5： 
//
// 输入：s = "Alice does not even like bob"
//输出："bob like even not does Alice"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 包含英文大小写字母、数字和空格 ' ' 
// s 中 至少存在一个 单词 
// 
//
// 
// 
//
// 
//
// 进阶： 
//
// 
// 请尝试使用 O(1) 额外空间复杂度的原地解法。 
// 
// Related Topics 字符串 
// 👍 290 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

public class _151ReverseWordsInAString {
    public static void main(String[] args) {
        Solution solution = new _151ReverseWordsInAString().new Solution();
        solution.reverseWords("the sky is blue");
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //TODO :不要使用辅助空间，空间复杂度要求为O(1).目前没有发现更好的方式

        //时间复杂度：O(N)，其中 N 为输入字符串的长度。
        //
        //空间复杂度：O(N)，双端队列存储单词需要 O(N) 的空间。
        public String reverseWords2(String s) {
            int left = 0, right = s.length() - 1;
            // 去掉字符串开头的空白字符
            while (left <= right && s.charAt(left) == ' ') {
                ++left;
            }

            // 去掉字符串末尾的空白字符
            while (left <= right && s.charAt(right) == ' ') {
                --right;
            }

            Deque<String> d = new ArrayDeque<String>();
            StringBuilder word = new StringBuilder();

            while (left <= right) {
                char c = s.charAt(left);
                if ((word.length() != 0) && (c == ' ')) {
                    // 将单词 push 到队列的头部
                    d.offerFirst(word.toString());
                    word.setLength(0);
                } else if (c != ' ') {
                    word.append(c);
                }
                ++left;
            }
            d.offerFirst(word.toString());

            return String.join(" ", d);
        }

        //时间复杂度：O(N)，其中 N 为输入字符串的长度。
        //
        //空间复杂度：Java 和 Python 的方法需要 O(N) 的空间来存储字符串，而 C++ 方法只需要 O(1) 的额外空间来存放若干变量。

        /**
         * @Description: 算法过程：
         * 先去除首尾以及中间多余的空格，中间单词之间只保留一个空格
         * 然后把整个字符串进行翻转，在翻转单词
         * @Param: [s]
         */
        public String reverseWords(String s) {
            //先去除首尾以及中间多余的空格，中间单词之间只保留一个空格
            //由   leetcode is   fun  变为leetcode is fun
            StringBuilder sb = trimSpace(s);
            //整个字符串进行翻转, 变为nuf si edocteel
            reverseString(sb, 0, sb.length() - 1);
            //翻转单词 变为：fun is leetcode
            reverseEachWord(sb);
            return sb.toString();
        }

        private void reverseEachWord(StringBuilder sb) {
            int start = 0;
            int end = 0;//表示单词的首尾
            while (start < sb.length()) {
                while (end < sb.length() && sb.charAt(end) != ' ') {
                    end++;
                }
                reverseString(sb, start, end - 1);
                start = end + 1;
                end++;
            }
        }

        private StringBuilder trimSpace(String s) {
            int left = 0;
            int right = s.length() - 1;
            StringBuilder stringBuilder = new StringBuilder();
            while (left <= right && s.charAt(left) == ' ') {
                left++;
            }
            while (left <= right && s.charAt(right) == ' ') {
                right--;
            }
            while (left <= right) {
                char c = s.charAt(left);
                if (c != ' ') {
                    stringBuilder.append(c);
                } else if (stringBuilder.charAt(stringBuilder.length() - 1) != ' ') {
                    stringBuilder.append(c);
                }
                left++;
            }
            return stringBuilder;
        }

        void reverseString(StringBuilder s, int left, int right) {
            while (left < right) {
                swap(s, left, right);
                left++;
                right--;
            }
        }

        private void swap(StringBuilder s, int a, int b) {
            char temp = s.charAt(a);
            s.setCharAt(a, s.charAt(b));
            s.setCharAt(b, temp);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}