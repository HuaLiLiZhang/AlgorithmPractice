package leetcode.Job.TreeNodeItem;

//给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的
//根节点的引用。 
//
// 一般来说，删除节点可分为两个步骤： 
//
// 
// 首先找到需要删除的节点； 
// 如果找到了，删除它。 
// 
//
// 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。 
//
// 示例: 
//
// 
//root = [5,3,6,2,4,null,7]
//key = 3
//
//    5
//   / \
//  3   6
// / \   \
//2   4   7
//
//给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
//
//一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
//
//    5
//   / \
//  4   6
// /     \
//2       7
//
//另一个正确答案是 [5,2,6,null,4,null,7]。
//
//    5
//   / \
//  2   6
//   \   \
//    4   7
// 
// Related Topics 树 
// 👍 424 👎 0


public class _450DeleteNodeInABst {
    public static void main(String[] args) {
        Solution solution = new _450DeleteNodeInABst().new Solution();
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
         * @Description: 迭代版
         * @Param: [root, key]
         */
        public TreeNode deleteNode2(TreeNode root, int key) {
            if (root == null) {
                return root;
            }
            TreeNode cur = root;
            TreeNode pre = null;  // 记录cur的父节点，用来删除cur
            while (cur != null) {
                if (cur.val == key) {
                    break;
                }
                pre = cur;
                if (cur.val > key) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            if (pre == null) { // 如果搜索树只有头结点, 如果key等于头结点，那么删除后，返回Null
                return deleteOneNode(cur);
            }
            //如果搜索树只有头结点, 如果key不等于头结点，应该返回root，因为此时cur等于null, pre = root，只需要pre.left=null; 或pre.right=null即可
            //而如果不只有一个节点，且没有与key相等的节点，那么返回root，
            //而如果pre的左节点cur等于key,判断cur的
            //第二种情况：左右孩子都为空（叶子节点），直接删除节点， 返回NULL为根节点
            //第三种情况：删除节点的左孩子为空，右孩子不为空，删除节点，右孩子补位，返回右孩子为根节点
            //第四种情况：删除节点的右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
            //第五种情况：左右孩子节点都不为空，则将删除节点的左子树头结点（左孩子）放到删除节点的右子树的最左面节点的左孩子上，返回删除节点右孩子为新的根节点。
            if (pre.left != null && pre.left.val == key) {
                cur = deleteOneNode(cur);
                pre.left = cur;
            } else if (pre.right != null && pre.right.val == key) {
                cur = deleteOneNode(cur);
                pre.right = cur;
            }
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

        /**
         * @Description: 递归版 ：二叉搜索树的删除就涉及到结构调整了
         * //确定单层递归的逻辑
         * //这里就把平衡二叉树中删除节点遇到的情况都搞清楚。
         * //
         * //有以下五种情况：
         * //没找到删除节点：
         * //第一种情况：没找到删除的节点，遍历到空节点直接返回了
         * //找到删除的节点：
         * //第二种情况：左右孩子都为空（叶子节点），直接删除节点， 返回NULL为根节点
         * //第三种情况：删除节点的左孩子为空，右孩子不为空，删除节点，右孩子补位，返回右孩子为根节点
         * //第四种情况：删除节点的右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
         * //第五种情况：左右孩子节点都不为空，则将删除节点的左子树头结点（左孩子）放到删除节点的右子树的最左面节点的左孩子上，返回删除节点右孩子为新的根节点。
         * @Param: [root, key]
         */
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) {
                //第一种情况：没找到删除的节点，遍历到空节点直接返回了
                return root;
            }
            if (root.val == key) {
                root = deleteOneNode(root);
                return root;
            }
            if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else {
                root.right = deleteNode(root.right, key);
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