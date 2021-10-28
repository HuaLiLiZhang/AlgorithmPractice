package leetcode.middleProblem;

//给你一个整数数组 nums 和一个整数 target 。 
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ： 
//
// 
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 
// 
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], target = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 1000 
// 
// Related Topics 数组 动态规划 回溯 
// 👍 933 👎 0


import java.util.ArrayList;
import java.util.Arrays;

public class _494TargetSum {
    public static void main(String[] args) {
        Solution solution = new _494TargetSum().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //首先这个题的目的是什么：对Nums里面的数字加+或者-，使得和等于target
        //也就是找出一部分数字left和一部分数字right，使得left-right=target
        //而left+right= sum
        //从而得出 left= (sum + target )/2,也就是找出有哪些数字的和等于left，就变成背包问题，把这些数字装进等于(sum + target )/2的背包里，有多少种
        public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            if ((sum + target) % 2 == 1) {
                return 0;
            }
            if (Math.abs(target) > sum) {
                return 0;
            }
            int left = (sum + target) / 2;
            int[] dp = new int[left + 1];
            //从递归公式可以看出，在初始化的时候dp[0] 一定要初始化为1，因为dp[0]是在公式中一切递推结果的起源，如果dp[0]是0的话，递归结果将都是0。
            //
            //dp[0] = 1，理论上也很好解释，装满容量为0的背包，有1种方法，就是装0件物品。
            dp[0] = 1;
            //dp[j] 表示：填满j（包括j）这么大容积的包，有dp[i]种方法
            for (int i = 0; i < nums.length; i++) {
                for (int j = left; j >= nums[i]; j--) {
                    dp[j] += dp[j - nums[i]];
                }
            }
            return dp[left];
        }


        private ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        private ArrayList<Integer> path = new ArrayList<>();

        public int findTargetSumWays_backTracing(int[] nums, int target) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            if ((sum + target) % 2 == 1) {
                return 0;
            }
            if (Math.abs(target) > sum) {
                return 0;
            }
            int left = (sum + target) / 2;
            Arrays.sort(nums);
            backTracking(nums, left, 0, 0);
            return result.size();
        }

        private void backTracking(int[] nums, int left, int num, int startIndex) {
            if (num == left) {
                result.add(path);
            }
            for (int i = startIndex; i < nums.length; i++) {
                path.add(nums[startIndex]);
                backTracking(nums, left, num + nums[startIndex], i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}