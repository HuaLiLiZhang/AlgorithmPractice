package leetcode.middleProblem;

//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 19 
// 
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 
// 👍 1363 👎 0


public class _96UniqueBinarySearchTrees {
    public static void main(String[] args) {
        Solution solution = new _96UniqueBinarySearchTrees().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp[3]，就是 元素1为头结点搜索树的数量 + 元素2为头结点搜索树的数量 + 元素3为头结点搜索树的数量
        //
        //元素1为头结点搜索树的数量 = 右子树有2个元素的搜索树数量 * 左子树有0个元素的搜索树数量
        //
        //元素2为头结点搜索树的数量 = 右子树有1个元素的搜索树数量 * 左子树有1个元素的搜索树数量
        //
        //元素3为头结点搜索树的数量 = 右子树有0个元素的搜索树数量 * 左子树有2个元素的搜索树数量
        //有2个元素的搜索树数量就是dp[2]。
        //
        //有1个元素的搜索树数量就是dp[1]。
        //
        //有0个元素的搜索树数量就是dp[0]。
        //
        //所以dp[3] = dp[2] * dp[0] + dp[1] * dp[1] + dp[0] * dp[2]
        //所以可以得出递推公式：dp[i] += dp[i-j][j-1]，其中 i 为节点的个数；j-1为j为头结点左子树节点数量， i-j为j为头结点右子树节点数量
        //初始化：空节点也是一颗二叉树，也是一颗二叉搜索树也可以。
        public int numTrees(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    //对于第i个节点，需要考虑1作为根节点直到i作为根节点的情况，所以需要累加
                    //一共i个节点，对于根节点j时,左子树的节点个数为j-1，右子树的节点个数为i-j
                    dp[i] += dp[i - j] * dp[j - 1];
                }
            }
            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}