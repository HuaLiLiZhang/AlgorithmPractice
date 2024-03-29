package leetcode.easyProblem;

//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 记忆化搜索 数学 动态规划 
// 👍 1928 👎 0


public class _70ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new _70ClimbingStairs().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int climbStairs(int n) {
            if (n <= 2) {
                return n;
            }
            int dp1 = 1;
            int dp2 = 2;
            for (int i = 3; i <= n; i++) {
                int sum = dp1 + dp2;
                dp1 = dp2;
                dp2 = sum;
            }
            return dp2;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}