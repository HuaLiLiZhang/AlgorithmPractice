package leetcode.Job.TreeNodeItem;

//计算给定二叉树的所有左叶子之和。 
//
// 示例： 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
//在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24 
//
// 
// Related Topics 树 
// 👍 297 👎 0


import java.util.LinkedList;
import java.util.Queue;

public class _404SumOfLeftLeaves {
    public static void main(String[] args) {
        Solution solution = new _404SumOfLeftLeaves().new Solution();
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
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public int sumOfLeftLeaves(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return getSumOfLeftLeaves(root);
        }

        /**
         * @Description: 使用层次遍历，那么判断节点的左子节点是否为空，
         * 为空不操作，
         * 不为空，判断这个节点是否是叶子节点，是叶子节点则计算加加
         * 若不是叶子节点，这将这个节点接入到队列中；
         * 若节点的右子节点不为空，则将右子节点也加入队列中。
         *
         * 时间复杂度：O(n)，其中 n 是树中的节点个数。
         *
         * 空间复杂度：O(n)。空间复杂度与广度优先搜索使用的队列需要的容量相关，为 O(n)。
         *
         * @Param: [root]
         */
        private int getSumOfLeftLeaves(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int sum = 0;
            while (!queue.isEmpty()) {
                TreeNode temp = queue.poll();
                if (temp.left != null) {
                    if (isLeafNode(temp.left)) {
                        sum += temp.left.val;
                    } else {
                        queue.offer(temp.left);
                    }
                }
                if (temp.right != null) {
                    //可以在优化一下，若右节点就是叶子节点，那么就不需要继续进行了，直接排除
                    if (!isLeafNode(temp.right)) {
                        queue.offer(temp.right);
                    }
                }
            }
            return sum;
        }

        boolean isLeafNode(TreeNode node) {
            if (node.left == null && node.right == null) {
                return true;
            }
            return false;
        }


        /**
         * @Description: 深度优先遍历：
         * 时间复杂度：O(n)，其中 n 是树中的节点个数。
         * <p>
         * 空间复杂度：O(n)。空间复杂度与深度优先搜索使用的栈的最大深度相关。
         * 在最坏的情况下，树呈现链式结构，深度为 O(n)，对应的空间复杂度也为 O(n)。
         * <p>
         * 思路：
         * @Param: [root]
         */
        private int getSumOfLeftLeaves1(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int sum = 0;
            if (root.left != null && root.left.left == null && root.left.right == null) {
                sum += root.left.val;
            }
            //这里没剪枝，会多次判断为null的节点，递归栈较深，空间复杂度较大
            sum += getSumOfLeftLeaves1(root.left);
            sum += getSumOfLeftLeaves1(root.right);
            return sum;
        }

        private int getSumOfLeftLeaves2(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int sum = 0;
            //这里进行了剪枝，为空的左节点和右节点直接跳过。
            if (root.left != null) {
                if (root.left.left == null && root.left.right == null) {
                    sum += root.left.val;
                } else {
                    sum += getSumOfLeftLeaves2(root.left);
                }
            }
            if (root.right != null) {
                sum += getSumOfLeftLeaves2(root.right);
            }
            return sum;
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