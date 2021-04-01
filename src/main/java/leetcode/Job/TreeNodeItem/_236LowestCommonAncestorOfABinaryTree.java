package leetcode.Job.TreeNodeItem;

//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
// 
//
// 示例 2： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], p = 1, q = 2
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 105] 内。 
// -109 <= Node.val <= 109 
// 所有 Node.val 互不相同 。 
// p != q 
// p 和 q 均存在于给定的二叉树中。 
// 
// Related Topics 树 
// 👍 1042 👎 0


public class _236LowestCommonAncestorOfABinaryTree {
    public static void main(String[] args) {
        Solution solution = new _236LowestCommonAncestorOfABinaryTree().new Solution();
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
        //说明:
        //
        //所有节点的值都是唯一的。
        //p、q 为不同节点且均存在于给定的二叉树中。
        //

        /**
         * @Description: 「如果找到一个节点，发现左子树出现结点p，右子树出现节点q，
         * 或者 左子树出现结点q，右子树出现节点p，那么该节点就是节点p和q的最近公共祖先。」
         * 使用后序遍历，回溯的过程，就是从低向上遍历节点，一旦发现如何这个条件的节点，就是最近公共节点了。
         * <p>
         * 那么我给大家归纳如下三点」：
         * <p>
         * 求最小公共祖先，需要从底向上遍历，那么二叉树，只能通过后序遍历（即：回溯）实现从低向上的遍历方式。
         * <p>
         * 在回溯的过程中，必然要遍历整颗二叉树，即使已经找到结果了，依然要把其他节点遍历完，因为要使用递归函数的返回值（也就是代码中的left和right）做逻辑判断。
         * <p>
         * 要理解如果返回值left为空，right不为空为什么要返回right，为什么可以用返回right传给上一层结果。
         * <p>
         * 可以说这里每一步，都是有难度的，都需要对二叉树，递归和回溯有一定的理解。
         * @Param: [root, p, q]
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == q || root == p || root == null) {
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            //「如果left 和 right都不为空，说明此时root就是最近公共节点。这个比较好理解」
            //
            //「如果left为空，right不为空，就返回right，说明目标节点是通过right返回的，反之依然」
            if (left != null && right != null) {
                return root;
            }
            if (left == null && right != null) {
                return right;
            } else if (left != null && right == null) {
                return left;
            } else {
                return null;
            }
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