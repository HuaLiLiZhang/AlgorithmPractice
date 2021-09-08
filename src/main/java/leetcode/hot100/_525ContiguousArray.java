package leetcode.hot100;

//给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [0,1]
//输出: 2
//说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。 
//
// 示例 2: 
//
// 
//输入: nums = [0,1,0]
//输出: 2
//说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// nums[i] 不是 0 就是 1 
// 
// Related Topics 数组 哈希表 前缀和 
// 👍 455 👎 0


import java.util.HashMap;
import java.util.Map;

public class _525ContiguousArray {
    public static void main(String[] args) {
        Solution solution = new _525ContiguousArray().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMaxLength(int[] nums) {
            int counter = 0;
            int maxLen = 0;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    counter--;
                } else {
                    counter++;
                }
                //如果map中包含了一个相同的counter，那么说明中间这段是等于0的，那么就可以更新最长的为0的字符串的长度
                if (map.containsKey(counter)) {
                    int preSumLen = i - map.get(counter);
                    maxLen = Math.max(maxLen, preSumLen);
                } else {
                    //如果map不包含counter，那么加入更新即可
                    map.put(counter, i);
                }
            }
            return maxLen;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}