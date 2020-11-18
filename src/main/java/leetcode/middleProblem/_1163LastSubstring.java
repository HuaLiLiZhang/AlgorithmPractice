package leetcode.middleProblem;

/**
 * @author: Created by zhanghl
 */
public class _1163LastSubstring {

    /**
     * @Description: 给你一个字符串 s，找出它的所有子串并按字典序排列，返回排在最后的那个子串。
     * 示例 1：
     * <p>
     * 输入："abab"
     * 输出："bab"
     * 解释：我们可以找出 7 个子串 ["a", "ab", "aba", "abab", "b", "ba", "bab"]。按字典序排在最后的子串是 "bab"。
     * 示例 2：
     * <p>
     * 输入："leetcode"
     * 输出："tcode"
     * @Param:
     */
    public static String lastSubstring(String s) {
        int front = 0;
        int len = s.length();
        for (int i = 1; i < len; i++) {
            int subLen = 0;
            while (i + subLen < len && s.charAt(front + subLen) == s.charAt(i + subLen)) {
                subLen++;
            }

            if(i + subLen >= len) {
                break;
            }
            if (s.charAt(i + subLen) > s.charAt(front + subLen)) {
                front = i;
            }

        }
        return s.substring(front);
    }

    public static void main(String[] args) {
//        String s = "abab";
        String s = "zazb";
//        String s = "zbza";
        String subStr = lastSubstring(s);
        System.out.println(subStr);
    }
}
