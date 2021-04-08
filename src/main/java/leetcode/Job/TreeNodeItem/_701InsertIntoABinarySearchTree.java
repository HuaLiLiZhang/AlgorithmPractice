package leetcode.Job.TreeNodeItem;

//给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值
//都不同。 
//
// 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [4,2,7,1,3], val = 5
//输出：[4,2,7,1,3,5]
//解释：另一个满足题目要求可以通过的树是：
//
// 
//
// 示例 2： 
//
// 
//输入：root = [40,20,60,10,30,50,70], val = 25
//输出：[40,20,60,10,30,50,70,null,null,25]
// 
//
// 示例 3： 
//
// 
//输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
//输出：[4,2,7,1,3,5]
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 给定的树上的节点数介于 0 和 10^4 之间 
// 每个节点都有一个唯一整数值，取值范围从 0 到 10^8 
// -10^8 <= val <= 10^8 
// 新值和原始二叉搜索树中的任意节点值都不同 
// 
// Related Topics 树 
// 👍 171 👎 0


public class _701InsertIntoABinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new _701InsertIntoABinarySearchTree().new Solution();
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
         * @Description: 时间复杂度：O(N)，其中 N为树中节点的数目。最坏情况下，我们需要将值插入到树的最深的叶子结点上，而叶子节点最深为 O(N)
         * <p>
         * 空间复杂度：O(1)。我们只使用了常数大小的空间。
         * <p>
         * 使用迭代方法
         * @Param: [root, val]
         */
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) {
                root = new TreeNode(val);
                return root;
            }
            TreeNode cur = root;
            TreeNode parent = root; // 这个很重要，需要记录上一个节点，否则无法赋值新节点
            while (cur != null) {
                parent = cur;
                if (cur.val > val) {
                    cur = cur.left;
                } else if (cur.val < val) {
                    cur = cur.right;
                }
            }
            if (parent.val > val) {
                parent.left = new TreeNode(val); // 此时是用parent节点的进行赋值
            } else {
                parent.right = new TreeNode(val); // 此时是用parent节点的进行赋值
            }
            return root;
        }

        /**
         * @Description: 递归方法，空间复杂度O(n),时间复杂度O(n)
         * @Param: [root, val]
         */
        public TreeNode insertIntoBST1(TreeNode root, int val) {
            //终止条件就是找到遍历的节点为null的时候，就是要插入节点的位置了，并把插入的节点返回。
            if (root == null) {
                root = new TreeNode(val);
                return root;
            }
            //如何通过递归函数返回值完成了新加入节点的父子关系赋值操作了，下一层将加入节点返回，本层用root->left或者root->right将其接住
            if (root.val > val) {
                root.left = insertIntoBST1(root.left, val);
                return root;
            } else if (root.val < val) {
                root.right = insertIntoBST1(root.right, val);
                return root;
            }
            return root;
        }

        /**
         * @Description: 有返回值的话，可以利用返回值完成新加入的节点与其父节点的赋值操作
         * @Param: [root, val]
         */
        public TreeNode insertIntoBST2(TreeNode root, int val) {
            if (root == null) {
                root = new TreeNode(val);
                return root;
            }
            if (root.val > val) {
                if (root.left != null) {
                    root.left = insertIntoBST2(root.left, val);
                } else {
                    root.left = new TreeNode(val);
                }
                return root;
            } else if (root.val < val) {
                if (root.right != null) {
                    root.right = insertIntoBST2(root.right, val);
                } else {
                    root.right = new TreeNode(val);
                }
                return root;
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