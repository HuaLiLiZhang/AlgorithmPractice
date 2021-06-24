package com.zhanghuali.leetcode.editor.cn;

//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 假设你总是可以到达数组的最后一个位置。 
//
// 
//
// 示例 1: 
//
// 
//输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 示例 2: 
//
// 
//输入: [2,3,0,1,4]
//输出: 2
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 105 
// 
// Related Topics 贪心算法 数组 动态规划 
// 👍 1026 👎 0


public class _45JumpGameIi {
    public static void main(String[] args) {
        Solution solution = new _45JumpGameIi().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int jump(int[] nums) {
            if (nums == null || nums.length == 0 || nums.length == 1) {
                return 0;
            }
            //记录跳跃的次数
            int count = 0;
            //当前的覆盖最大区域
            int curDistance = 0;
            //最大的覆盖区域
            int maxDistance = 0;
            for (int i = 0; i < nums.length; i++) {
                //在可覆盖区域内更新最大的覆盖区域
                maxDistance = Math.max(maxDistance, i + nums[i]);
                //说明当前一步，在跳一步就到达了末尾
                if (maxDistance >= nums.length - 1) {
                    count++;
                    break;
                }
                //走到当前覆盖的最大区域时，更新下一步可达的最大区域
                if (i == curDistance) {
                    curDistance = maxDistance;
                    count++;
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}