package leetcode.Job.TreeNodeItem;

//给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。 
//
// 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-10,-3,0,5,9]
//输出：[0,-3,9,-10,null,5]
//解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
//
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,3]
//输出：[3,1]
//解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// nums 按 严格递增 顺序排列 
// 
// Related Topics 树 深度优先搜索 
// 👍 739 👎 0


import java.util.LinkedList;
import java.util.Queue;

public class _108ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new _108ConvertSortedArrayToBinarySearchTree().new Solution();
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
         * @Description: 迭代法：利用三个队列 来模拟不断分割的过程，一个队列用于存储节点，另一个队列用于存储左区间下标，最后一个队列用于存储右区间的下标
         * 可以理解为分治, 也相当于层次遍历
         * @Param: [nums]
         */
        public TreeNode sortedArrayToBST(int[] nums) {
            if (nums == null || nums.length == 0) {
                return null;
            }
            Queue<TreeNode> nodeQueue = new LinkedList<>();
            Queue<Integer> leftIndex = new LinkedList<>();
            Queue<Integer> rightIndex = new LinkedList<>();
            TreeNode root = new TreeNode(0);
            nodeQueue.offer(root);
            leftIndex.offer(0);
            rightIndex.offer(nums.length - 1);
            while (!nodeQueue.isEmpty()) {
                int left = leftIndex.poll();
                int right = rightIndex.poll();
                int mid = left + (right - left) / 2;
                TreeNode curNode = nodeQueue.poll();
                curNode.val = nums[mid]; // 将mid对应的元素给中间节点
                if (left <= mid - 1) { //处理左节点，因为是左闭右闭，所以这里是 可以等于的
                    leftIndex.add(left);
                    rightIndex.add(mid - 1);
                    curNode.left = new TreeNode(0);
                    nodeQueue.add(curNode.left);
                }
                if (right >= mid + 1) { //处理右节点,因为是左闭右闭，所以这里是 可以等于的
                    leftIndex.add(mid + 1);
                    rightIndex.add(right);
                    curNode.right = new TreeNode(0);
                    nodeQueue.add(curNode.right);
                }
            }
            return root;
        }

        /**
         * @Description: 本题思路就是：利用有序数据的中间节点作为根节点，左右作为左子树和右子树，依次找下一个区间的中间节点
         * 这里就要求知道左右区间边界，才能得到中间节点，所以需要传入递归参数就是，数组，以及左右边界
         * <p>
         * 时间复杂度：O(n)，其中 n 是数组的长度。每个数字只访问一次。
         * <p>
         * 空间复杂度：O(\log n)，其中 n 是数组的长度。
         * 空间复杂度不考虑返回值，因此空间复杂度主要取决于递归栈的深度，递归栈的深度是 O(\log n)。
         * @Param: [nums]
         */
        public TreeNode sortedArrayToBST1(int[] nums) {
            if (nums == null || nums.length == 0) {
                return null;
            }
            return getRootOfBalancedBST(nums, 0, nums.length - 1);
        }

        /**
         * @Description: 那么本题要构造二叉树，依然用递归函数的返回值来构造中节点的左右孩子。
         * <p>
         * 再来看参数，首先是传入数组，然后就是左下表left和右下表right，
         * 我们在二叉树：构造二叉树登场！中提过，在构造二叉树的时候尽量不要重新定义左右区间数组，而是用下表来操作原数组。
         * <p>
         * 「我这里定义的是左闭右闭区间，在不断分割的过程中，也会坚持左闭右闭的区间，这又涉及到我们讲过的循环不变量」。
         * <p>
         * 这里定义的是左闭右闭的区间，所以当区间 left > right的时候，就是空节点了。
         * @Param: [nums, left, right]
         */
        private TreeNode getRootOfBalancedBST(int[] nums, int left, int right) {
            if (left > right) {
                return null;
            }
            //int mid = (right + left) / 2; //这种写法容易越界
            //改进
            //这里int mid = left + ((right - left) / 2);的写法相当于是如果数组长度为偶数，中间位置有两个元素，取靠左边的。
            //或者int mid = left + ((right - left + 1) / 2);的写法相当于如果数组长度为偶数，中间位置有两个元素，取靠右边的
            int mid = left + (right - left) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = getRootOfBalancedBST(nums, left, mid - 1);
            root.right = getRootOfBalancedBST(nums, mid + 1, right);
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