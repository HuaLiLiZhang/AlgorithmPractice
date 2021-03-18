package leetcode.Job.TreeNodeItem;

//翻转一棵二叉树。 
//
// 示例： 
//
// 输入： 
//
//      4
//   /   \
//  2     7
// / \   / \
//1   3 6   9 
//
// 输出： 
//
//      4
//   /   \
//  7     2
// / \   / \
//9   6 3   1 
//
// 备注: 
//这个问题是受到 Max Howell 的 原问题 启发的 ： 
//
// 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。 
// Related Topics 树 
// 👍 788 👎 0


import java.util.Stack;

public class _226InvertBinaryTree {
    public static void main(String[] args) {
        Solution solution = new _226InvertBinaryTree().new Solution();
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
        public TreeNode invertTree(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            if (root == null) {
                return root;
            }
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode temp = stack.pop();
                swap(temp);
                if(temp.right!=null){
                    stack.push(temp.right);
                }
                if(temp.left!=null){
                    stack.push(temp.left);
                }
            }
            return root;
        }

        public TreeNode invertTree1(TreeNode root) {
            if (root == null) {
                return root;
            }
            swap(root);
            invertTree1(root.left);
            invertTree1(root.right);
            return root;
        }

        private void swap(TreeNode root) {
            TreeNode leftTmp = root.left;
            root.left = root.right;
            root.right = leftTmp;
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