package leetcode.Job.TreeNodeItem;

//给定一个二叉树，判断它是否是高度平衡的二叉树。 
//
// 本题中，一棵高度平衡二叉树定义为： 
//
// 
// 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,2,3,3,null,null,4,4]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数在范围 [0, 5000] 内 
// -104 <= Node.val <= 104 
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 641 👎 0


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class _110BalancedBinaryTree {
    public static void main(String[] args) {
        Solution solution = new _110BalancedBinaryTree().new Solution();
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
        /**
         * @Description:
         * 算法流程：从底至顶（提前阻断） 思路是对二叉树做先序遍历，从底至顶返回子树最大高度，若判定某子树不是平衡树则 “剪枝” ，直接向上返回。
         * recur(root):
         *
         * 递归返回值：
         * 当节点root 左 / 右子树的高度差 < 2 ：则返回以节点root为根节点的子树的最大高度，即节点 root 的左右子树中最大高度加 1 （ max(left, right) + 1 ）；
         * 当节点root 左 / 右子树的高度差 ≥2 ：则返回 −1 ，代表 此子树不是平衡树 。
         * 递归终止条件：
         * 当越过叶子节点时，返回高度 0 ；
         * 当左（右）子树高度 left== -1 时，代表此子树的 左（右）子树 不是平衡树，因此直接返回 −1 ；
         * isBalanced(root) ：
         *
         * 返回值： 若 recur(root) != -1 ，则说明此树平衡，返回 true ； 否则返回 false 。
         * 复杂度分析：
         * 时间复杂度 O(N)： N 为树的节点数；最差情况下，需要递归遍历树的所有节点。
         * 空间复杂度 O(N)： 最差情况下（树退化为链表时），系统递归需要使用 O(N) 的栈空间。
         *
         * @Param: [root]
         */
        public boolean isBalanced(TreeNode root) {
            return getDepth(root) >= 0;
        }

        // 返回以该节点为根节点的二叉树的高度，如果不是二叉搜索树了则返回-1
        public int getDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftHeight = getDepth(root.left);
            int rightHeight = getDepth(root.right);
            // leftHeight == -1 说明左子树已经不是二叉平衡树  rightHeight == -1 说明右子树已经不是二叉平衡树；
            // 因为之前已经Math.abs(leftHeight - rightHeight) > 1， 所以才返回了 -1
            if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
                return -1;
            } else {
                return Math.max(leftHeight, rightHeight) + 1;
            }
        }


        /**
        * @Description: 非递归版，迭代版，使用层次遍历，对于每个节点的左子树和右子树的深度不能超过1
         *          时间复杂度：O(N²) 空间复杂度：O(N)
        * @Param: [root]
        */
        public boolean isBalanced2(TreeNode root) {
            if (root == null) {
                return true;
            }
            Stack<TreeNode> stack = new Stack<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                TreeNode temp = stack.pop();
                int leftDepth = getHeight(temp.left);
                int rightDepth = getHeight(temp.right);
                if (Math.abs(leftDepth - rightDepth) > 1) {
                    return false;
                }
                if (temp.left != null) {
                    stack.add(temp.left);
                }
                if (temp.right != null) {
                    stack.add(temp.right);
                }

            }
            return true;
        }

        private int getHeight(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> stack = new LinkedList<>();
            int depth = 0;
            stack.offer(root);
            while (!stack.isEmpty()) {
                int size = stack.size();
                depth++;
                for (int i = 0; i < size; i++) {
                    TreeNode temp = stack.poll();
                    if (temp.left != null) {
                        stack.offer(temp.left);
                    }
                    if (temp.right != null) {
                        stack.offer(temp.right);
                    }
                }
            }
            return depth;
        }


        /**
         * @Description: 平衡树是对于任意节点来说，其左子树和右子树的高度差都不能超过1
         * 时间复杂度 O(Nlog_2 N)： 最差情况下， isBalanced(root) 遍历树所有节点，占用 O(N) ；
         * 判断每个节点的最大高度 depth(root) 需要遍历 各子树的所有节点 ，子树的节点数的复杂度为 O(log_2 N)
         *
         * 空间复杂度 O(N)： 最差情况下（树退化为链表时），系统递归需要使用 O(N) 的栈空间。
         *
         * @Param: [root]
         */
        public boolean isBalanced1(TreeNode root) {
            //递归终止条件
            if (root == null) {
                return true;
            }
            //单层递归逻辑，当前节点的左子树和右子树的节点差不能超过1
            int leftDepth = height1(root.left);
            int rightDepth = height1(root.right);
            //                                              递归循环条件是：当前节点的左子节点和右子节点也要满足平衡
            return Math.abs(leftDepth - rightDepth) <= 1 && isBalanced1(root.left) && isBalanced1(root.right);
        }

        private int height1(TreeNode root) {
            if (root == null) {
                return 0;
            } else {
                return Math.max(height1(root.left), height1(root.right)) + 1;
            }
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