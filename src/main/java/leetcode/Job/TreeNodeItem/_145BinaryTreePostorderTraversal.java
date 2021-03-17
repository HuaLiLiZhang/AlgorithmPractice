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
import java.util.Collections;
import java.util.List;
import java.util.Stack;

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
            if (root == null) {
                return allVal;
            }
            getPostOrder(root, allVal);

            return allVal;
        }

        /**
         * @Description: 直接使用stack和标记已经遍历过的指针
         * @Param: [root, allVal]
         */
        private void getPostOrder(TreeNode root, List<Integer> allVal) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode pre = null;
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if (root.right == null || root.right == pre) {
                    allVal.add(root.val);
                    pre = root;
                    root = null;
                } else {
                    stack.push(root);
                    root = root.right;
                }
            }
        }

        /**
         * @Description: 先序遍历是中左右，后续遍历是左右中，
         * 那么我们只需要调整一下先序遍历的代码顺序，就变成中右左的遍历顺序，然后在反转result数组，输出的结果顺序就是左右中了，
         * @Param: [root, allVal]
         */
        private void getPostOrder2(TreeNode root, List<Integer> allVal) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode top = stack.pop();
                if (top != null) {
                    allVal.add(top.val);
                } else {
                    continue;
                }
                stack.push(top.left);
                stack.push(top.right);
            }
            Collections.reverse(allVal);
        }

        private void getPostOrder1(TreeNode root, List<Integer> allVal) {
            if (root == null) {
                return;
            }
            getPostOrder1(root.left, allVal);
            getPostOrder1(root.right, allVal);
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