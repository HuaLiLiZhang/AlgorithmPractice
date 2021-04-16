package leetcode.Job.TreeNodeItem;

//给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于
// node.val 的值之和。 
//
// 提醒一下，二叉搜索树满足下列约束条件： 
//
// 
// 节点的左子树仅包含键 小于 节点键的节点。 
// 节点的右子树仅包含键 大于 节点键的节点。 
// 左右子树也必须是二叉搜索树。 
// 
//
// 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-s
//um-tree/ 相同 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
//输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
// 
//
// 示例 2： 
//
// 输入：root = [0,null,1]
//输出：[1,null,1]
// 
//
// 示例 3： 
//
// 输入：root = [1,0,2]
//输出：[3,3,2]
// 
//
// 示例 4： 
//
// 输入：root = [3,2,4,1]
//输出：[7,9,4,10]
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数介于 0 和 104 之间。 
// 每个节点的值介于 -104 和 104 之间。 
// 树中的所有值 互不相同 。 
// 给定的树为二叉搜索树。 
// 
// Related Topics 树 深度优先搜索 二叉搜索树 递归 
// 👍 507 👎 0


import java.util.Stack;

public class _538ConvertBstToGreaterTree {
    public static void main(String[] args) {
        Solution solution = new _538ConvertBstToGreaterTree().new Solution();
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
         * @Description: 迭代法：进行右中左遍历，然后记录前一个值
         * @Param: [cur]
         */
        public TreeNode convertBST(TreeNode root) {
            if (root == null) {
                return root;
            }
            TreeNode cur = root;
            Stack<TreeNode> nodeStack = new Stack<>();
            int preVal = 0;
            while (!nodeStack.isEmpty() || cur != null) {
                if (cur == null) {
                    cur = nodeStack.pop();
                    cur.val += preVal;
                    preVal = cur.val;
                    cur = cur.left;
                } else {
                    nodeStack.push(cur);
                    cur = cur.right;
                }
            }
            return root;
        }

        /**
         * @Description: 递归法：首先是一个中序遍历的返回来，因为二叉搜索树的右节点大于根节点大于子节点，
         * 那么采用右跟左遍历方式，并记录前一个节点的值，赋值即可
         * 其中前一个节点的值preVal是累积的
         * @Param: [root]
         */
        public TreeNode convertBST1(TreeNode root) {
            if (root == null) {
                return root;
            }
            getSumTreeBST(root);
            return root;
        }

        int preVal = 0; //因为是累积的，所以不需要放在方法内。preVal记录前一个节点的数值

        private void getSumTreeBST(TreeNode root) {
            if (root == null) {
                return;
            }
            getSumTreeBST(root.right);
            root.val += preVal;
            preVal = root.val;
            getSumTreeBST(root.left);
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