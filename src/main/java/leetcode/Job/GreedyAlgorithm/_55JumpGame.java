package leetcode.Job.GreedyAlgorithm;

//给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个下标。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,1,1,4]
//输出：true
//解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1,0,4]
//输出：false
//解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// 0 <= nums[i] <= 105 
// 
// Related Topics 贪心算法 数组 动态规划 
// 👍 1243 👎 0


public class _55JumpGame {
    public static void main(String[] args) {
        Solution solution = new _55JumpGame().new Solution();
        int[] arr = {3, 2, 1, 0, 4};
        System.out.println(solution.canJump(arr));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 刚看到本题一开始可能想：当前位置元素如果是3，我究竟是跳一步呢，还是两步呢，还是三步呢，究竟跳几步才是最优呢？
         * 其实跳几步无所谓，关键在于可跳的覆盖范围！
         * 不一定非要明确一次究竟跳几步，每次取最大的跳跃步数，这个就是可以跳跃的覆盖范围。
         * 这个范围内，别管是怎么跳的，反正一定可以跳过来。
         * 那么这个问题就转化为跳跃覆盖范围究竟可不可以覆盖到终点！
         * 每次移动取最大跳跃步数（得到最大的覆盖范围），每移动一个单位，就更新最大覆盖范围。
         * 贪心算法局部最优解：每次取最大跳跃步数（取最大覆盖范围），整体最优解：最后得到整体最大覆盖范围，看是否能到终点。
         * 局部最优推出全局最优，找不出反例，试试贪心！
         * @Param: [nums]
         */
        public boolean canJump(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return true;
            }
            if (nums[0] <= 0) {
                return false;
            }
            int coverRange = nums[0];
            for (int i = 0; i <= coverRange; i++) {
                coverRange = Math.max(coverRange, i + nums[i]);
                if (coverRange >= nums.length - 1) {
                    return true;
                }
            }
            return false;
        }

        public boolean canJumpOf有点复杂(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return true;
            }
            if (nums[0] <= 0) {
                return false;
            }
            int[] count = new int[nums.length];
            for (int i = 0; i < nums.length - 1; i++) {
                int cur = nums[i];
                int curIndex = i;
                while (cur > 0 && curIndex < nums.length) {
                    count[curIndex++] += 1;
                    cur--;
                }
                if (count[i] <= 0) {
                    return false;
                }
            }
            return count[nums.length - 2] > 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}