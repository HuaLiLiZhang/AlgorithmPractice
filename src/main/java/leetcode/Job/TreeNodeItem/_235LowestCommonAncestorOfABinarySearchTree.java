package leetcode.Job.TreeNodeItem;

//给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
//输出: 6 
//解释: 节点 2 和节点 8 的最近公共祖先是 6。
// 
//
// 示例 2: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
//输出: 2
//解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉搜索树中。 
// 
// Related Topics 树 
// 👍 565 👎 0


public class _235LowestCommonAncestorOfABinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new _235LowestCommonAncestorOfABinarySearchTree().new Solution();
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
     * TreeNode(int x) { val = x; }
     * }
     */

    class Solution {
        /**
         * @Description:
         * @Param: [root, p, q]
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return null;
            }
            if (p.val > q.val) {
                TreeNode temp = p;
                p = q;
                q = temp;
            }
            while (root != null) {
                if (root.val < p.val) {
                    root = root.right;
                } else if (root.val > q.val) {
                    root = root.left;
                } else {
                    return root;
                }
            }
            return root; //没找着，此时也是返回null,因为循环跳出条件是null
        }

        /**
         * @Description: 递归法
         * @Param: [root, p, q]
         */
        public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
            if (p.val > q.val) {
                TreeNode temp = p;
                p = q;
                q = temp;
            }
            return findLowestCommonAncestor(root, p, q);
        }

        private TreeNode findLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return null; //没找着返回Null
            }
            if (root.val > p.val && root.val < q.val) {
                return root; //找着了，不用往深的找、返回root
            }
            if (root.val < p.val) {
                TreeNode right = findLowestCommonAncestor(root.right, p, q);
                //如果right为空，说明在右边没找着，此时也不会在搜索左边，会直接到最后的返回root
                if (right != null) {
                    return right;
                }
            }
            if (root.val > q.val) {
                TreeNode left = findLowestCommonAncestor(root.left, p, q);
                //如果left为空，说明在左边没找着，此时也不会在搜索右边，会直接到最后的返回root
                if (left != null) {
                    return left;
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