package leetcode.Job.TreeNodeItem;

//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明：叶子节点是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的范围在 [0, 105] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 473 👎 0


import java.util.LinkedList;
import java.util.Queue;

public class _111MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new _111MinimumDepthOfBinaryTree().new Solution();
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
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int depth = 0;
            Queue<TreeNode> que = new LinkedList<>();
            que.offer(root);
            while (!que.isEmpty()) {
                int size = que.size();
                depth++; // 记录最小深度
                int flag = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode node = que.poll();
                    if (node.left != null) {
                        que.offer(node.left);
                    }
                    if (node.right != null) {
                        que.offer(node.right);
                    }
                    if (node.left == null && node.right == null) { // 当左右孩子都为空的时候，说明是最低点的一层了，退出
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1) {
                    break;
                }
            }
            return depth;
        }

        public int minDepth1(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftDepth = minDepth1(root.left);
            int rightDepth = minDepth1(root.right);
            //此时不能直接求最小值，因为  当一个左子树为空，右不为空，这时并不是最低点
            if (root.left == null) {
                return 1 + rightDepth;
            }
            if (root.right == null) {
                return 1 + leftDepth;
            }
            return 1 + Math.min(leftDepth, rightDepth);

        }

        class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int val) {
                this.val = val;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}