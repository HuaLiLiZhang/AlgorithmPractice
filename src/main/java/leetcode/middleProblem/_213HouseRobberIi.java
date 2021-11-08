package leetcode.middleProblem;

//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,2]
//输出：3
//解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,1]
//输出：4
//解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 1000 
// 
// Related Topics 数组 动态规划 
// 👍 820 👎 0


public class _213HouseRobberIi {
    public static void main(String[] args) {
        Solution solution = new _213HouseRobberIi().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rob(int[] nums) {
            //因为成环了，所以第一个和最后一个不能同时选，//
            // 假设不选第一个
            // 假设不选最后一个
            if (nums == null || nums.length <= 0) {
                return -1;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            int firstMax = getMaxMoney(nums, 1, nums.length - 1);
            int endMax = getMaxMoney(nums, 0, nums.length - 2);
            return Math.max(firstMax, endMax);
        }

        public int getMaxMoney_dpSizeNums(int[] nums, int start, int end) {
            //保持dp数组大小与Nums数组大小一致
            if (start == end) {
                return nums[start];
            }
            int[] dp = new int[nums.length];
            dp[start] = nums[start];
            dp[start + 1] = Math.max(nums[start], nums[start + 1]);
            for (int i = (start + 2); i <= end; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
            return dp[end];
        }

        public int getMaxMoney(int[] nums, int start, int end) {
            if (start == end) {
                return nums[start];
            }
            int[] dp = new int[end - start + 1];
            dp[0] = nums[start];
            dp[1] = Math.max(nums[start], nums[start + 1]);
            for (int i = 2; i < dp.length; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[start + i]);
            }
            return dp[dp.length - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}