package leetcode.Job.TreeNodeItem;

//给定一个二叉树，返回它的 后序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [3,2,1] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 
// 👍 541 👎 0


import java.util.ArrayList;
import java.util.List;

public class _145BinaryTreePostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new _145BinaryTreePostorderTraversal().new Solution();
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
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> allVal = new ArrayList<>();
            getPostOrder(root, allVal);
            return allVal;
        }

        private void getPostOrder(TreeNode root, List<Integer> allVal) {
            if (root == null) {
                return;
            }
            getPostOrder(root.left, allVal);
            getPostOrder(root.right, allVal);
            allVal.add(root.val);
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