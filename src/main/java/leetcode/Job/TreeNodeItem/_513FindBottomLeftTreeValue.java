package leetcode.Job.TreeNodeItem;

//给定一个二叉树，在树的最后一行找到最左边的值。 
//
// 示例 1: 
//
// 
//输入:
//
//    2
//   / \
//  1   3
//
//输出:
//1
// 
//
// 
//
// 示例 2: 
//
// 
//输入:
//
//        1
//       / \
//      2   3
//     /   / \
//    4   5   6
//       /
//      7
//
//输出:
//7
// 
//
// 
//
// 注意: 您可以假设树（即给定的根节点）不为 NULL。 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 160 👎 0


import java.util.LinkedList;
import java.util.Queue;

public class _513FindBottomLeftTreeValue {
    public static void main(String[] args) {
        Solution solution = new _513FindBottomLeftTreeValue().new Solution();
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
        int maxLeftValue = 0;
        int maxLen = Integer.MIN_VALUE;

        public int findBottomLeftValue(TreeNode root) {
            if (root == null) {
                return -1;
            }

            getLeftValue(root, 0);
            return maxLeftValue;
        }

        /**
         * @Description: 时间复杂度O(n)，空间复杂度O(n)
         * 思路：其实树的左下角的值，无非是左子树的最左叶子节点；或者是右子树的最左叶子节点，而选这两个中的哪个值，是根据左右子树的最深的高度决定的，
         * 左右子树最深的那个最左叶子节点
         * 所以只需要判断左右子树哪个更深，则选哪个。
         * <p>
         * <p>
         * 只有叶子节点可能是需要的返回值res，所以递归的中止条件为当遇到叶子节点才return
         * 只有当前叶子节点的深度比之前的更大，才更新res
         * PS：因为要大于才会更新，所以每一层只会更新一次，先会遍历到左边的节点，所以只会更新每层最左侧的节点，符合题目要求
         * 因为要比较深度，在递归中除了节点作为参数外，还需要增加深度参数
         * 在res中增加一项专门用于存储深度， 即 res = [叶子节点值，叶子节点深度]
         * 最后返回的是res[0]
         * @Param: [root, leftLen]
         */
        private void getLeftValue(TreeNode root, int curLen) {
            if (root.left == null && root.right == null) {
                if (curLen > maxLen) {
                    maxLen = curLen;
                    maxLeftValue = root.val;
                }
                return;
            }
            //    2
            //   / \
            //  1   3
            //如果左右子树高度相等，那么maxLeftValue也只会等于左节点的值，因为是if (curLen > maxLen)才更新。
            ////        1
            ////       / \
            ////      2   3
            ////     /   / \
            ////    4   5   6
            ////       /
            ////      7
            //如果右子树高度大于左子树，那么右子树的此时节点的左子树的高度还是大于此时节点的右子树，
            // 那么maxLeftValue也只会等于此时节点的左节点的值，因为是if (curLen > maxLen)才更新。
            if (root.left != null) {
                curLen++;
                getLeftValue(root.left, curLen);
                curLen--;
            }
            if (root.right != null) {
                curLen++;
                getLeftValue(root.right, curLen);
                curLen--;
            }
            return;
        }

        /**
         * @Description: 时间复杂度O(n)，空间复杂度O(n)
         * 用队列存储节点，先进先出
         * 从左往右遍历，也就是在往队列中添加数据时，先添加左子节点，再添加右子节点
         * 当队列为空时，循环结束，最后一个循环遍历到的第一个节点就是最左边的节点
         * 返回最左边节点的值
         * @Param: [root]
         */
        public int findBottomLeftValue1(TreeNode root) {
            if (root == null) {
                return -1;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int leftValue = -1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode temp = queue.poll();
                    if (i == 0) {
                        leftValue = temp.val;
                    }
                    if (temp.left != null) {
                        queue.add(temp.left);
                    }
                    if (temp.right != null) {
                        queue.add(temp.right);
                    }
                }
            }
            return leftValue;
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