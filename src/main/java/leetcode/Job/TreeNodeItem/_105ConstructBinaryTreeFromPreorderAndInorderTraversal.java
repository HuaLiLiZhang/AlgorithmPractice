package leetcode.Job.TreeNodeItem;

//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组 
// 👍 957 👎 0


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class _105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new _105ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        int[] inOrder = {3, 9, 20, 15, 7};
        int[] postOrder = {9, 3, 15, 20, 7};
        solution.buildTree(inOrder, postOrder);
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
        int[] preOrder;
        int[] inOrder;
        int preIndex;
        Map<Integer, Integer> idxMap = new HashMap<Integer, Integer>();

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder.length == 0) {
                return null;
            }
            this.preOrder = preorder;
            this.inOrder = inorder;
            this.preIndex = 0;
            // 建立（元素，下标）键值对的哈希表
            int idx = 0;
            for (Integer val : inorder) {
                idxMap.put(val, idx++);
            }
            return helper(0, inorder.length - 1);
        }

        private TreeNode helper(int inLeft, int inRight) {
            if (inLeft > inRight) {
                return null;
            }
            int rootValue = preOrder[preIndex];
            TreeNode root = new TreeNode(rootValue);

            preIndex++;
            //构建左节点
            root.left = helper(inLeft, idxMap.get(rootValue) - 1);
            //构建右节点
            root.right = helper(idxMap.get(rootValue) + 1, inRight);
            return root;
        }


        //迭代
        public TreeNode buildTree1(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[0]);
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            stack.push(root);
            int inorderIndex = 0;
            for (int i = 1; i < preorder.length; i++) {
                int preorderVal = preorder[i];
                TreeNode node = stack.peek();
                if (node.val != inorder[inorderIndex]) {
                    node.left = new TreeNode(preorderVal);
                    stack.push(node.left);
                } else {
                    while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                        node = stack.pop();
                        inorderIndex++;
                    }
                    node.right = new TreeNode(preorderVal);
                    stack.push(node.right);
                }
            }
            return root;
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