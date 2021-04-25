package leetcode.Job.BackTraceItem;
//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//
//
//
// 示例 1：
//
//
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
//
//
// 示例 2：
//
//
//输入：digits = ""
//输出：[]
//
//
// 示例 3：
//
//
//输入：digits = "2"
//输出：["a","b","c"]
//
//
//
//
// 提示：
//
//
// 0 <= digits.length <= 4
// digits[i] 是范围 ['2', '9'] 的一个数字。
//
// Related Topics 深度优先搜索 递归 字符串 回溯算法
// 👍 1119 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Created by zhanghl
 */
public class _17LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        Solution solution = new _17LetterCombinationsOfAPhoneNumber().new Solution();
        String str1 = "";
        String str2 = "2";
        String str3 = "23";
        System.out.println(solution.letterCombinations(str1));
        System.out.println(solution.letterCombinations(str2));
        System.out.println(solution.letterCombinations(str3));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 数字到号码的映射
        final HashMap<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        //回溯函数
        public List<String> letterCombinations(String digits) {
            List<String> result = new ArrayList<>();
            if (digits == null || digits.length() == 0) {
                return result;
            }
            getAllLetterCombinations(digits, 0, new StringBuilder(), result);
            return result;

        }

        private void getAllLetterCombinations(String digits, int startIndexOfDig,
                                              StringBuilder oneCombination, List<String> result) {
            //遍历到的startIndexOfDig索引等于digits的长度的时候，说明已经产生一种结果，那么将这个结果加入到结果集
            if (startIndexOfDig == digits.length()) {
                result.add(oneCombination.toString());
                return;
            }
            //因为是给与数字的所有字母组合，所以要得到所有的长度为digits.length的结果集，所有字母串的长度都等于digits.lengt
            //所有要从0遍历到digits.lengt，即得到一种结果，然后循环每个数字的可能对应的字母，则得到所有的结果集
            char character = digits.charAt(startIndexOfDig);
            String condinate = phoneMap.get(character);
            for (int j = 0; j < condinate.length(); j++) {
                oneCombination.append(condinate.charAt(j));
                getAllLetterCombinations(digits, startIndexOfDig + 1, oneCombination, result);
                oneCombination.deleteCharAt(oneCombination.length() - 1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
