package leetcode.easyProblem;

//输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。 
//
// 要求时间复杂度为O(n)。 
//
// 
//
// 示例1: 
//
// 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 10^5 
// -100 <= arr[i] <= 100 
// 
//
// 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/ 
//
// 
// Related Topics 数组 分治 动态规划 
// 👍 421 👎 0


public class _剑指_Offer_42LianXuZiShuZuDeZuiDaHeLcof {
    public static void main(String[] args) {
        Solution t = new _剑指_Offer_42LianXuZiShuZuDeZuiDaHeLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            if (nums == null || nums.length <= 0) {
                return 0;
            }
            int max = nums[0];
            int pre = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (pre > 0) {
                    pre += nums[i];
                } else {
                    pre = nums[i];
                }
                max = Math.max(max, pre);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}