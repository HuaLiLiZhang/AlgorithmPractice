package leetcode.Job.TreeNodeItem;

//给定一个二叉树，找出其最大深度。 
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
//给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索 递归 
// 👍 824 👎 0


import java.util.LinkedList;
import java.util.Queue;

public class _104MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new _104MaximumDepthOfBinaryTree().new Solution();
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
        public int maxDepth(TreeNode root) {
            int depth = 0;
            if (root == null) {
                return depth;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                depth++;
                for (int i = 0; i < size; i++) {
                    TreeNode temp = queue.poll();
                    if (temp.left != null) {
                        queue.offer(temp.left);
                    }
                    if (temp.right != null) {
                        queue.offer(temp.right);
                    }
                }
            }
            return depth;
        }

        public int maxDepth1(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftDepth = maxDepth1(root.left);
            int rightDepth = maxDepth1(root.right);
            return 1 + Math.max(leftDepth, rightDepth);
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}