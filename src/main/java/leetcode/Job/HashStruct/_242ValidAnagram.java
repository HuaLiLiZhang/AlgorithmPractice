package leetcode.Job.HashStruct;

//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 
// 👍 351 👎 0


import java.util.HashMap;

public class _242ValidAnagram {
    public static void main(String[] args) {
        Solution solution = new _242ValidAnagram().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 定义一个数组叫做record用来上记录字符串s里字符出现的次数。
         * 「因为字符a到字符z的ASCII是26个连续的数值，所以字符a映射为下表0，相应的字符z映射为下表25。」
         * @Param: [s, t]
         */
        public boolean isAnagram1(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            int[] record = new int[26];
            for (int i = 0; i < s.length(); i++) {
                record[s.charAt(i) - 'a']++;
                record[t.charAt(i) - 'a']--;
            }
            for (int i = 0; i < 26; i++) {
                if (record[i] != 0) {
                    return false;
                }
            }
            return true;
        }

        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            HashMap<Character, Integer> countMap = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            }
            for (int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                countMap.put(c, countMap.getOrDefault(c, 0) - 1);
                if (countMap.get(c) < 0) {
                    return false;
                }
            }
            /*for (int i = 0; i < s.length(); i++) {
                countMap.put(s.charAt(i), countMap.getOrDefault(s.charAt(i), 0) + 1);
                countMap.put(t.charAt(i), countMap.getOrDefault(t.charAt(i), 0) - 1);
            }

            for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
                if (entry.getValue() != 0) {
                    return false;
                }
            }*/
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}