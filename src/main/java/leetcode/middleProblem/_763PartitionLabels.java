package leetcode.middleProblem;

//字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。 
//
// 
//
// 示例： 
//
// 
//输入：S = "ababcbacadefegdehijhklij"
//输出：[9,7,8]
//解释：
//划分结果为 "ababcbaca", "defegde", "hijhklij"。
//每个字母最多出现在一个片段中。
//像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
// 
//
// 
//
// 提示： 
//
// 
// S的长度在[1, 500]之间。 
// S只包含小写字母 'a' 到 'z' 。 
// 
// Related Topics 贪心 哈希表 双指针 字符串 
// 👍 569 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _763PartitionLabels {
    public static void main(String[] args) {
        Solution solution = new _763PartitionLabels().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> partitionLabels_1(String s) {
            //思路：记录每个字符最远出现的下标，遍历一遍，更新字符的最远出现的下标，
            // 如果最远出现的下标与当前位置相等，那么此时就是最大的分割的一个位置
            if (s == null || s.length() <= 0) {
                return null;
            }
            int[] index = new int[s.length()];
            int maxIndex = Integer.MIN_VALUE;
            int startIndex = 0;
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                index[i] = s.lastIndexOf(s.charAt(i)); //这里可能查询的时间比较久，优化一下，
                // 应该用一个固定的数组，存储某个字母的最大位置即可，然后遍历字符串S，当最大位置等于i时，那么就可以进行分割了
                maxIndex = Math.max(maxIndex, index[i]);
                if (maxIndex == i) {
                    result.add(maxIndex + 1 - startIndex);
                    startIndex = i + 1;
                }
            }
            return result;
        }

        public List<Integer> partitionLabels(String S) {
            List<Integer> list = new LinkedList<>();
            int[] edge = new int[123];
            char[] chars = S.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                edge[chars[i] - '0'] = i;
            }
            int idx = 0;
            int last = -1;
            for (int i = 0; i < chars.length; i++) {
                idx = Math.max(idx, edge[chars[i] - '0']);
                if (i == idx) {
                    list.add(i - last);
                    last = i;
                }
            }
            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}