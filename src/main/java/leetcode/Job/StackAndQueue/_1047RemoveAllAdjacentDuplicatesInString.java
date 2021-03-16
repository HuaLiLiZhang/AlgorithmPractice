package leetcode.Job.StackAndQueue;

//给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。 
//
// 在 S 上反复执行重复项删除操作，直到无法继续删除。 
//
// 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。 
//
// 
//
// 示例： 
//
// 输入："abbaca"
//输出："ca"
//解释：
//例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又
//只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= S.length <= 20000 
// S 仅由小写英文字母组成。 
// 
// Related Topics 栈 
// 👍 238 👎 0


import java.util.Stack;

public class _1047RemoveAllAdjacentDuplicatesInString {
    public static void main(String[] args) {
        Solution solution = new _1047RemoveAllAdjacentDuplicatesInString().new Solution();
        System.out.println(solution.removeDuplicates("abbaca"));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeDuplicates(String S) {
            StringBuilder stringBuilder = new StringBuilder();
            int j = -1;
            for (int i = 0; i < S.length(); i++) {
                if (j >= 0 && S.charAt(i) == stringBuilder.charAt(j)) {
                    stringBuilder.deleteCharAt(j);
                    j--;
                    continue;
                }
                stringBuilder.append(S.charAt(i));
                j++;
            }
            return stringBuilder.toString();
        }


        /**
         * @Description: 利用栈顶判断是否有重复元素，有的话，删除栈顶
         * @Param: [S]
         */
        public String removeDuplicates1(String S) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < S.length(); i++) {
                if (!stack.isEmpty() && stack.peek().equals(S.charAt(i))) {
                    stack.pop();
                } else {
                    stack.push(S.charAt(i));
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            while (!stack.isEmpty()) {
                stringBuilder.append(stack.pop());
            }
            return stringBuilder.reverse().toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}