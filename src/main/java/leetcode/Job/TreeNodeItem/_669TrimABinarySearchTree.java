package leetcode.Job.TreeNodeItem;

//给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树不应
//该改变保留在树中的元素的相对结构（即，如果没有被移除，原有的父代子代关系都应当保留）。 可以证明，存在唯一的答案。 
//
// 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,0,2], low = 1, high = 2
//输出：[1,null,2]
// 
//
// 示例 2： 
//
// 
//输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
//输出：[3,2,null,1]
// 
//
// 示例 3： 
//
// 
//输入：root = [1], low = 1, high = 2
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,null,2], low = 1, high = 3
//输出：[1,null,2]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2], low = 2, high = 4
//输出：[2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数在范围 [1, 104] 内 
// 0 <= Node.val <= 104 
// 树中每个节点的值都是唯一的 
// 题目数据保证输入是一棵有效的二叉搜索树 
// 0 <= low <= high <= 104 
// 
// Related Topics 树 递归 
// 👍 374 👎 0


public class _669TrimABinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new _669TrimABinarySearchTree().new Solution();
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
         * @Description: 在剪枝的时候，可以分为三步：
         * <p>
         * 将root移动到[L, R] 范围内，注意是左闭右闭区间
         * 剪枝左子树
         * 剪枝右子树
         * <p>
         * 时间复杂度为O(n)，空间复杂度：O(1)
         * @Param:
         */
        public TreeNode trimBST(TreeNode root, int low, int high) {
            if (root == null) {
                return null;
            }
            // 处理头结点，让root移动到[L, R] 范围内，注意是左闭右闭
            while (root != null && (root.val > high || root.val < low)) {
                if (root != null && root.val > high) {
                    root = root.left; // 大于R往左走
                }
                if (root != null && root.val < low) { // 小于L往右走
                    root = root.right;
                }
            }
            //先找到在范围中的root
            TreeNode cur = root;
            //修剪左子树,// 此时root已经在[L, R] 范围内，处理左孩子元素小于L的情况
            while (cur != null) {
                while (cur.left != null && cur.left.val < low) {
                    cur.left = cur.left.right;
                }
                cur = cur.left;
            }
            //修剪右子树 // 此时root已经在[L, R] 范围内，处理右孩子大于R的情况
            cur = root;
            while (cur != null) {
                while (cur.right != null && cur.right.val > high) {
                    cur.right = cur.right.left;
                }
                cur = cur.right;
            }
            return root;
        }

        /**
         * @Description: 递归版
         * @Param: [root, low, high]
         */
        public TreeNode trimBST1(TreeNode root, int low, int high) {
            if (root == null) {
                return null;
            }
            if (root.val > high) {
                //如果root(当前节点)的元素大于high的，那么应该递归左子树，并返回左子树符合条件的头结点。
                TreeNode left = trimBST1(root.left, low, high); // 寻找符合区间[low, high]的节点
                return left;
            } else if (root.val < low) {
                //如果root（当前节点）的元素小于low的数值，那么应该递归右子树，并返回右子树符合条件的头结点。
                TreeNode right = trimBST1(root.right, low, high); // 寻找符合区间[low, high]的节点
                return right;
            }
            root.left = trimBST1(root.left, low, high); //接下来要将下一层处理完左子树的结果赋给root->left，处理完右子树的结果赋给root->right。
            root.right = trimBST1(root.right, low, high);
            return root;
        }

        private TreeNode deleteOneNode(TreeNode cur) {
            if (cur == null) {
                return cur;
            }
            if (cur.left != null && cur.right == null) {
                cur = cur.left;
            } else if (cur.left == null && cur.right != null) {
                cur = cur.right;
            } else if (cur.left == null && cur.right == null) {
                cur = null;
            } else {
                TreeNode temp = cur.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                temp.left = cur.left;
                cur = cur.right;
            }
            return cur;
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