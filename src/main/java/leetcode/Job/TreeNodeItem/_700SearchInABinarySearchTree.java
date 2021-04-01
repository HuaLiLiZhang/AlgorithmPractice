package leetcode.Job.TreeNodeItem;

//给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。 
//
// 例如， 
//
// 
//给定二叉搜索树:
//
//        4
//       / \
//      2   7
//     / \
//    1   3
//
//和值: 2
// 
//
// 你应该返回如下子树: 
//
// 
//      2     
//     / \   
//    1   3
// 
//
// 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。 
// Related Topics 树 
// 👍 118 👎 0


public class _700SearchInABinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new _700SearchInABinarySearchTree().new Solution();
        TreeNode root = new _700SearchInABinarySearchTree().new TreeNode(4);
        root.left = new _700SearchInABinarySearchTree().new TreeNode(2);
        root.left.left = new _700SearchInABinarySearchTree().new TreeNode(1);
        root.left.right = new _700SearchInABinarySearchTree().new TreeNode(3);
        root.right = new _700SearchInABinarySearchTree().new TreeNode(7);

        System.out.println(solution.searchBST(root, 5));
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
        public TreeNode searchBST(TreeNode root, int target) {
            if (root == null) {
                return null;
            }
            //由于二叉搜索树的特性，我们不需要栈或队列来记住他的顺序，因为本身的特性已经知道往哪走了
            while (root != null) {
                if (root.val == target) {
                    return root;
                }
                if (root.val > target) {
                    root = root.left;
                } else { //此处只能使用else，因为如果再用一个if，如果满足前面一个if，root = root.left,若此时为空，此时的if (root.val < target)会报空指针
                    root = root.right;
                }
            }
            return null;

            /*Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode temp = stack.pop();
                if (temp.val == target) {
                    return temp;
                }
                if (temp.val > target && temp.left != null) {
                    stack.push(temp.left);
                }
                if (temp.val < target && temp.right != null) {
                    stack.push(temp.right);
                }
            }
            return null;*/
        }

        /**
         * @Description: 二叉搜索树，需要找到节点值等于target的子树
         * 第二种方法：第一种的升级，确切的说，是利用了二叉搜索树的特性，减少时间复杂度，
         * 因为二叉搜索树的特性：
         * 二叉搜索树是一个有序树：
         * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
         * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
         * 它的左、右子树也分别为二叉搜索树
         * <p>
         * 全树遍历，如果遇到节点值等于target，返回节点值即可
         * 因为是遇到一个就返回，不用遍历全部子树，所以需要返回值
         * @Param: [root, target]
         */
        public TreeNode searchBST2(TreeNode root, int target) {
            if (root == null) {
                return null;
            }
            if (root.val == target) {
                return root;
            }
            TreeNode targetRoot = null;
            if (target > root.val) {
                targetRoot = searchBST2(root.right, target);
            } else {
                targetRoot = searchBST2(root.left, target);
            }
            return targetRoot;
        }

        /**
         * @Description: 二叉搜索树，需要找到节点值等于target的子树
         * 第一种方法：
         * 全树遍历，如果遇到节点值等于target，返回节点值即可
         * 因为是遇到一个就返回，不用遍历全部子树，所以需要返回值
         * @Param: [root, target]
         */
        public TreeNode searchBST1(TreeNode root, int target) {
            if (root == null) {
                return null;
            }
            if (root.val == target) {
                return root;
            }
            TreeNode leftTarget = searchBST1(root.left, target);
            if (leftTarget != null) {
                return leftTarget;
            }
            return searchBST1(root.right, target);
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