package leetcode.hot100;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 
// 👍 2143 👎 0


import java.util.Stack;

public class _20ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new _20ValidParentheses().new Solution();
        System.out.println(solution.isValid("([)]"));
        System.out.println(solution.isValid("{[]}"));
        System.out.println(solution.isValid("{()[{}]}"));
        System.out.println(solution.isValid("()"));
        System.out.println(solution.isValid("()[]{}"));
        System.out.println(solution.isValid("(]"));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //如果字符长度不是偶数，不是
        //使用栈进行判断，将字符压入栈
        // 因为如果遇到了右括号，那么栈顶元素一定是左括号，此字符串才是有效的，此时弹出栈顶元素
        //如果遇到右括号，栈为空或者栈顶元素不是左括号，那么这个字符就一定不是有效子字符。
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