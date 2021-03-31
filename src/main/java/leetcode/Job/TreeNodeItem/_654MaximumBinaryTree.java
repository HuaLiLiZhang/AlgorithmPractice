package leetcode.Job.TreeNodeItem;

//给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下： 
//
// 
// 二叉树的根是数组 nums 中的最大元素。 
// 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。 
// 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。 
// 
//
// 返回有给定数组 nums 构建的 最大二叉树 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,2,1,6,0,5]
//输出：[6,3,5,null,2,0,null,null,1]
//解释：递归调用如下所示：
//- [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
//    - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
//        - 空数组，无子节点。
//        - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
//            - 空数组，无子节点。
//            - 只有一个元素，所以子节点是一个值为 1 的节点。
//    - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
//        - 只有一个元素，所以子节点是一个值为 0 的节点。
//        - 空数组，无子节点。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[3,null,2,null,1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 1000 
// nums 中的所有整数 互不相同 
// 
// Related Topics 树 
// 👍 261 👎 0


import java.util.ArrayList;

public class _654MaximumBinaryTree {
    public static void main(String[] args) {
        Solution solution = new _654MaximumBinaryTree().new Solution();
        ArrayList<String> list = new ArrayList<>(10);
        System.out.println(list.size());
        list.add("sdfds");
        System.out.println(list.size());
        int[] nums = {3,2,1,6,0,5};
        System.out.println(solution.constructMaximumBinaryTree(nums));
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
    /**
     * @Description: 构造树一般采用的是前序遍历，因为先构造中间节点，然后递归构造左子树和右子树。
     * 先找到数组中的最大值为根节点，那么根节点左右两边的数组最大值为根节点的左右节点。依次递归
     * @Param:
     */
    class Solution {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            if (nums == null || nums.length == 0) {
                return null;
            }
            return buildMaxTree(nums, 0, nums.length - 1);
        }

        private TreeNode buildMaxTree(int[] nums, int start, int end) {
            if (start > end) {
                return null;
            }
            int rootIndex = getMaxValOfNums(nums, start, end);
            TreeNode root = new TreeNode(nums[rootIndex]);
            // 左闭右闭
            root.left = buildMaxTree(nums, start, rootIndex - 1);
            root.right = buildMaxTree(nums, rootIndex + 1, end);
            return root;
        }

        private int getMaxValOfNums(int[] nums, int start, int end) {
            int maxIndex = start;
            for (int i = start; i <= end; i++) {
                if (nums[i] > nums[maxIndex]) {
                    maxIndex = i;
                }
            }
            return maxIndex;
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