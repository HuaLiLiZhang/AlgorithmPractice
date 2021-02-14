package leetcode.hot100;

//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 104 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 字符串 动态规划 
// 👍 1159 👎 0


import java.util.Arrays;
import java.util.Stack;

public class _32LongestValidParentheses {
    public static void main(String[] args) {
        Solution solution = new _32LongestValidParentheses().new Solution();
        System.out.println(solution.longestValidParentheses("(()"));
        System.out.println(solution.longestValidParentheses(")()())"));
        System.out.println(solution.longestValidParentheses(""));
        System.out.println(solution.longestValidParentheses("()((())"));
        System.out.println(solution.longestValidParentheses("())(())"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 正向逆向结合法：为什么要正向反向两次，因为特殊情况：()((())和())(())
         * 时间复杂度O(n), 空间复杂度O(1)
         * @Param:
         */
        public int longestValidParentheses(String s) {
            if (s == null || s.length() <= 1) {
                return 0;
            }
            int left = 0;
            int right = 0;
            int maxLen = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    left++;
                }
                if (s.charAt(i) == ')') {
                    right++;
                }
                if (left == right) {
                    maxLen = Math.max(maxLen, 2 * right);
                } else if (right > left) {
                    left = right = 0;
                }
            }
            left = right = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '(') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    maxLen = Math.max(maxLen, 2 * left);
                } else if (left > right) {
                    left = right = 0;
                }
            }
            return maxLen;
        }


        /**
         * @Description: 利用栈，将其index插入，
         * 定义两个参数，一个记录当前有效括号的长度，一个记录最大有效括号的长度
         * 具体做法是我们始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」，这样的做法主要是考虑了边界条件的处理，栈里其他元素维护左括号的下标：
         * <p>
         * 对于遇到的每个 "(’ ，我们将它的下标放入栈中
         * 对于遇到的每个 ")’ ，我们先弹出栈顶元素表示匹配了当前右括号：
         * 如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
         * 如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
         * <p>
         * 需要注意的是，如果一开始栈为空，第一个字符为左括号的时候我们会将其放入栈中，这样就不满足提及的「最后一个没有被匹配的右括号的下标」，为了保持统一，我们在一开始的时候往栈中放入一个值为 -1−1 的元素。
         *
         * <p>
         * 时间复杂度O(n), 空间复杂度为O(n)
         * @Param:
         */
        public int longestValidParentheses1(String s) {
            int len = 0;
            int max_len = 0;
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else if (s.charAt(i) == ')') {
                    stack.pop();
                    if (stack.isEmpty()) {
                        //如果栈为空，说明当前的右括号为没有被匹配的右括号，
                        // 我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
                        //防止())(())这样的，在遇到index=2,就是右括号，因为没有与其匹配的左括号，弹出一个元素-1，堆栈为空，
                        // 此时要把当前的Index加进去，更新我们之前提到的「最后一个没有被匹配的右括号的下标」
                        stack.push(i);
                    }
                    len = i - stack.peek();
                    max_len = Math.max(max_len, len);
                }
            }
            return max_len;
        }


        /**
         * @Description: 利用动态规划的思想；首先初始化一维动态数组dp，初始化值为0,
         * 遇到左括号将值设置为0，遇到右括号设置为当前有效括号的最大值，
         * 对于()((())对于这个字符串来说，第6位字符为右括号，判断前面是左括号，最大有效括号长度为2，
         * 第7位字符为右括号，最大有效括号长度为 前一位最大有效括号长度值+ (i-dp[i-1]-1)位的值，
         * 时间复杂度O(n), 空间复杂度为O(n)
         * @Param: [s]
         */
        public int longestValidParentheses2(String s) {
            if (s == null || s.length() <= 1) {
                return 0;
            }
            int len = s.length();
            int maxans = 0;
            int[] dp = new int[len];
            Arrays.fill(dp, 0);
            //是有效括号最少是两个字符，所以从第二位判断
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        //对应成对的()，再加上之前连续有效括号的长度。
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        //对应成对的....)(())，最后一个位置的连续有效括号的长度判断条件：
                        //先判断i - dp[i - 1] - 1是否是(，如果是，那么就是成对的(())
                        //此时还要加上前面的成对括号，比如如果有()(())，
                        // 那么此时的连续括号的长度为：2(当前匹配的括号) + dp[i - 1] + dp[i - dp[i - 1] - 2]
                        //
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    //然后求出最大值，因为连续括号有可能是一段一段的
                    maxans = Math.max(maxans, dp[i]);
                }
            }
            return maxans;
        }


        /**
         * @Description: 暴力方法：时间复杂度O(n3)，空间复杂度O(n)
         * 因为有效括号的长度一定是偶数，所以从字符串最大偶数长度开始进行滑动窗口遍历，判断是否是有效括号
         * 然后对偶数长度-2，直到为0,滑动窗口遍历，判断是否是有效括号
         * 求出最大值
         * @Param: [s]
         */
        public int longestValidParentheses3(String s) {
            int len = s.length();
            int lenParent = len;
            int maxLen = 0;
            if (len % 2 != 0) {
                lenParent = len - 1;
            }
            for (int i = lenParent; i > 0; i -= 2) {
                for (int j = 0; j < len && (j + i - 1) < len; j++) {
                    if (isValid(s.substring(j, j + i))) {
                        maxLen = Math.max(maxLen, i);
                    }
                }
            }
            return maxLen;
        }

        public boolean isValid(String s) {
            if (s.length() % 2 != 0) {
                return false;
            }
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(' || c == '{' || c == '[') {
                    stack.push(c);
                }
                if (c == ')' && (stack.isEmpty() || stack.pop() != '(')) {
                    return false;
                }
                if (c == '}' && (stack.isEmpty() || stack.pop() != '{')) {
                    return false;
                }
                if (c == ']' && (stack.isEmpty() || stack.pop() != '[')) {
                    return false;
                }
            }
            return stack.isEmpty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}