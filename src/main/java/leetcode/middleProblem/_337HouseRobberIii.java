package leetcode.middleProblem;

//在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“
//房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。 
//
// 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。 
//
// 示例 1: 
//
// 输入: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   3
//    \   \ 
//     3   1
//
//输出: 7 
//解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7. 
//
// 示例 2: 
//
// 输入: [3,4,5,1,3,null,1]
//
//     3
//    / \
//   4   5
//  / \   \ 
// 1   3   1
//
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 
// 👍 1026 👎 0


import leetcode.Job.TreeNode;

import java.util.HashMap;

public class _337HouseRobberIii {
    public static void main(String[] args) {
        Solution solution = new _337HouseRobberIii().new Solution();
    }
    /**

     */
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public int rob(TreeNode root) {
            int[] robMax = robDigui(root);
            return Math.max(robMax[0], robMax[1]);

        }

        //利用动态规划，发现偷节点和不偷节点记录两个值
        //时间复杂度：O(n) 每个节点只遍历了一次
        //空间复杂度：O(logn) 算上递推系统栈的空间
        private int[] robDigui(TreeNode root) {
            if (root == null) {
                return new int[]{0, 0};
            }
            if (root.left == null && root.right == null) {
                return new int[]{0, root.val};
            }
            int[] leftRob = robDigui(root.left);
            int[] rightRob = robDigui(root.right);
            //不偷根节点
            int val1 = Math.max(leftRob[1], leftRob[0]) + Math.max(rightRob[0], rightRob[1]);
            //偷根节点
            int val2 = root.val + robDigui(root.left)[0] + robDigui(root.right)[0];
            return new int[]{val1, val2};
        }


        //利用map记录以及计算的节点的结果
        //时间复杂度：O(n)
        //空间复杂度：O(logn) 算上递推系统栈的空间
        private HashMap<TreeNode, Integer> map = new HashMap<>();

        private int robDigui_n(TreeNode root) {
            if (root == null) {
                return 0;
            }
            if (root.left == null && root.right == null) {
                return root.val;
            }
            // 如果umap里已经有记录则直接返回
            if (map.containsKey(root)) {
                return map.get(root);
            }
            //不偷父节点
            int val1 = robDigui_n(root.left) + robDigui_n(root.right);
            // 偷父节点
            int val2 = root.val;
            if (root.left != null) {
                val2 += robDigui_n(root.left.left) + robDigui_n(root.left.right);
            }
            if (root.right != null) {
                val2 += robDigui_n(root.right.left) + robDigui_n(root.right.right);
            }
            map.put(root, Math.max(val1, val2));
            return map.get(root);
        }


        //时间复杂度：O(n^2) 这个时间复杂度不太标准，也不容易准确化，例如越往下的节点重复计算次数就越多
        //空间复杂度：O(logn) 算上递推系统栈的空间
        private int robDigui_n2(TreeNode root) {
            if (root == null) {
                return 0;
            }
            if (root.left == null && root.right == null) {
                return root.val;
            }
            //不偷父节点
            int val1 = robDigui_n2(root.left) + robDigui_n2(root.right);
            // 偷父节点
            int val2 = root.val;
            if (root.left != null) {
                val2 += robDigui_n2(root.left.left) + robDigui_n2(root.left.right);
            }
            if (root.right != null) {
                val2 += robDigui_n2(root.right.left) + robDigui_n2(root.right.right);
            }
            return Math.max(val1, val2);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}