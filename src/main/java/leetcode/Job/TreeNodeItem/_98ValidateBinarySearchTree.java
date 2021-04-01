package leetcode.Job.TreeNodeItem;

//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 990 👎 0


import java.util.Stack;

public class _98ValidateBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new _98ValidateBinarySearchTree().new Solution();
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
        long maxVal = Long.MIN_VALUE;

        /**
         * @Description: 迭代法，因为二叉搜索树的左中右遍历刚好是数组的升序，所以我们使用中序遍历二叉树，判断是否是升序即可
         * 时间复杂度 : O(n)，其中 n 为二叉树的节点个数。二叉树的每个节点最多被访问一次，因此时间复杂度为 O(n)。
         * <p>
         * 空间复杂度 : O(n)，其中 n 为二叉树的节点个数。栈最多存储 n 个节点，因此需要额外的 O(n) 的空间。
         * @Param: [root]
         */
        public boolean isValidBST2(TreeNode root) {
            if (root == null) {
                return true;
            }
            Stack<TreeNode> stack = new Stack<>();
           /* while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    if (maxVal < root.val) {
                        maxVal = root.val;
                    } else {
                        // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
                        return false;
                    }
                    root = root.right;
                }
            }*/
            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if (maxVal < root.val) {
                    maxVal = root.val;
                } else {
                    return false;
                }
                root = root.right;
            }
            return true;
        }


        /**
         * @Description: 判断是否是二叉搜索树，只需要判断某一条不满足返回即可，不用全部遍历整颗二叉树，如果满足，直接返回true
         * 其中重点！！！！！！：是根节点的节点值大于其左子树的所有节点的值，小于所有右子树的所有节点的值
         * <p>
         * 陷阱1
         * 「不能单纯的比较左节点小于中间节点，右节点大于中间节点就完事了」
         * <p>
         * 陷阱2
         * 样例中最小节点 可能是int的最小值，如果这样使用最小的int来比较也是不行的。
         * 此时可以初始化比较元素为long的最小值。
         * @Param: [root]
         */


        public boolean isValidBST1(TreeNode root) {
            //确定终止条件
            if (root == null) {
                return true;
            }
            //错误：陷入了陷阱1：「不能单纯的比较左节点小于中间节点，右节点大于中间节点就完事了」
            //左子树所有节点小于中间节点，右子树所有节点大于中间节点
            /*if (root.left != null && root.left.val >= root.val) {
                return false;
            }
            if (root.right != null && root.right.val <= root.val) {
                return false;
            }
            return isValidBST1(root.left) && isValidBST1(root.right);*/
            //确定单层递归的逻辑
            boolean left = isValidBST1(root.left);
            if (maxVal < root.val) {
                // 中序遍历，验证遍历的元素是不是从小到大，此时遍历的顺序是从左子树的最小的节点开始往根节点，然后到左子树的右节点，在到右子树的左子节点然后跟然后右子节点，
                // 因为这个遍历顺序是从小到大的，所以如果存在前一个节点的数值大于后一个节点，那么就不满足二叉搜索树的定义，返回false
                // 因为跟节点大于其左子树所有节点值，此意为了判断所有根节点值是否都大于左子树节点，并且右子树的节点值都大于根节点
                //
                //       5
                //     /   \
                //    4     6
                //        /   \
                //       3     7 此树不满足二叉搜索树的定义，。因为右子树的节点不满足所有节点大于根节点的值
                maxVal = root.val;
            } else {
                return false;
            }
            boolean right = isValidBST1(root.right);
            return left && right;
        }

        /**
        * @Description: 时间复杂度 : O(n), 空间复杂度：O(n)
        * @Param: [root]
        */
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        public boolean isValidBST(TreeNode node, long lower, long upper) {
            if (node == null) {
                return true;
            }
            if (node.val <= lower || node.val >= upper) {
                return false;
            }
            return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
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