package leetcode.Job.TreeNodeItem;

//给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和targetSum 。
//
// 叶子节点 是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,3], targetSum = 5
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], targetSum = 0
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
// Related Topics 树 深度优先搜索 
// 👍 550 👎 0


import java.util.LinkedList;
import java.util.Queue;

public class _112PathSum {
    public static void main(String[] args) {
        Solution solution = new _112PathSum().new Solution();
        TreeNode root = new _112PathSum().new TreeNode(5);
        TreeNode root1 = new _112PathSum().new TreeNode(4);
        TreeNode root2 = new _112PathSum().new TreeNode(8);
        TreeNode root3 = new _112PathSum().new TreeNode(11);
        TreeNode root4 = new _112PathSum().new TreeNode(13);
        TreeNode root5 = new _112PathSum().new TreeNode(4);
        TreeNode root6 = new _112PathSum().new TreeNode(7);
        TreeNode root7 = new _112PathSum().new TreeNode(2);
        TreeNode root8 = new _112PathSum().new TreeNode(1);
        root.left = root1;
        root.right = root2;
        root1.left = root3;
        root2.left = root4;
        root2.right = root5;
        root3.left = root6;
        root3.right = root7;
        root5.right = root8;
        solution.hasPathSum(root, 22);
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

        //迭代的方法: 此时队列里一个元素不仅要记录该节点指针，还要记录从头结点到该节点的路径数值总和
        public boolean hasPathSum1(TreeNode root, int target) {
            if (root == null) {
                return false;
            }
            Queue<TreeNode> queueNodes = new LinkedList<>();
            queueNodes.offer(root);
            Queue<Integer> queueValue = new LinkedList<>();
            queueValue.offer(root.val);
            while (!queueNodes.isEmpty()) {
                TreeNode node = queueNodes.poll();
                int temp = queueValue.poll();
                if (node.left == null && node.right == null) {
                    if (temp == target) {
                        return true;
                    }
                    //此时还没找到合适路径，不能直接返回false, 需要继续遍历
                    continue;
                }
                if (node.left != null) {
                    queueNodes.offer(node.left);
                    queueValue.offer(node.left.val + temp);
                }
                if (node.right != null) {
                    queueNodes.offer(node.right);
                    queueValue.offer(node.right.val + temp);
                }
            }
            return false;
        }


        /**
         * @Description: 首先计数器如何统计这一条路径的和呢？
         * <p>
         * 不要去累加然后判断是否等于目标和，那么代码比较麻烦，可以用递减，让计数器count初始为目标和，然后每次减去遍历路径节点上的数值。
         * <p>
         * 如果最后count == 0，同时到了叶子节点的话，说明找到了目标和。
         * <p>
         * 如果遍历到了叶子节点，count不为0，就是没找到。
         * <p>
         * 因为终止条件是判断叶子节点，所以递归的过程中就不要让空节点进入递归了。
         * <p>
         * 递归函数是有返回值的，如果递归函数返回true，说明找到了合适的路径，应该立刻返回。
         * @Param: [root, targetSum]
         */

        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return false;
            }
            return isHasPathSumTarget(root, targetSum - root.val);
        }

        private boolean isHasPathSumTarget(TreeNode root, int targetSum) {

            if (root.left == null && root.right == null && targetSum == 0) {
                return true;
            }
            if (root.left == null && root.right == null) {
                return false;
            }
            if (root.left != null) {
                //因为只需要找到一个满足条件的路径即可，不需要都满足。所以找到返回true,最后遍历所有路径结束后，没有结果在最后返回false
                if (isHasPathSumTarget(root.left, targetSum - root.left.val)) {
                    return true;
                }
            }
            if (root.right != null) {
                if (isHasPathSumTarget(root.right, targetSum - root.right.val)) {
                    return true;
                }
            }
            return false;
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