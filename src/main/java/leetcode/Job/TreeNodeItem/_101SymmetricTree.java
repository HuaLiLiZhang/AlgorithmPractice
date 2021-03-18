package leetcode.Job.TreeNodeItem;

//给定一个二叉树，检查它是否是镜像对称的。 
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 1295 👎 0


import java.util.Stack;

public class _101SymmetricTree {
    public static void main(String[] args) {
        Solution solution = new _101SymmetricTree().new Solution();
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
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return compareTreeNode(root);

        }

        private boolean compareTreeNode(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root.left);
            stack.push(root.right);
            while (!stack.isEmpty()) {
                TreeNode temp1 = stack.pop();
                TreeNode temp2 = stack.pop();
                if (temp1 != null && temp2 == null) {
                    return false;
                }
                if (temp1 == null && temp2 != null) {
                    return false;
                }
                if (temp1 == null && temp2 == null) {
                    continue; //因为需要判断外侧和内侧，此时不能返回，只能continue
                }
                if (temp1.val != temp2.val) {
                    return false;
                }
                stack.push(temp1.left);
                stack.push(temp2.right);
                stack.push(temp1.right);
                stack.push(temp2.left);
            }
            return true;
        }

        private boolean compareTreeNode1(TreeNode left, TreeNode right) {
            // 首先排除空节点的情况
            if (left == null && right != null) {
                return false;
            } else if (left != null && right == null) {
                return false;
            } else if (left == null && right == null) {
                return true;
            }
            // 排除了空节点，再排除数值不相同的情况
            else if (left.val != right.val) {
                return false;
            }
            boolean isLeft = compareTreeNode1(left.left, right.right);
            boolean isRight = compareTreeNode1(left.right, right.left);
            return isLeft && isRight;
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