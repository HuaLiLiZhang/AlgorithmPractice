package leetcode.Job.TreeNodeItem;

//给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。 
//
// 
//
// 示例： 
//
// 输入：
//
//   1
//    \
//     3
//    /
//   2
//
//输出：
//1
//
//解释：
//最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
// 
//
// 
//
// 提示： 
//
// 
// 树中至少有 2 个节点。 
// 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 
//相同 
// 
// Related Topics 树 
// 👍 237 👎 0


import java.util.ArrayList;
import java.util.Stack;

public class _530MinimumAbsoluteDifferenceInBst {
    public static void main(String[] args) {
        Solution solution = new _530MinimumAbsoluteDifferenceInBst().new Solution();
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

        public int getMinimumDifference(TreeNode root) {
            if (root == null) {
                return -1;
            }
            Stack<TreeNode> stack = new Stack<>();
            TreeNode pre = null;
            int result = Integer.MAX_VALUE;
            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    // 将访问的节点放进栈
                    stack.push(root);
                    // 指针来访问节点，访问到最底层
                    root = root.left; //左
                }
                root = stack.pop();
                if (pre == null) {
                    pre = root;
                } else { //中
                    result = Math.min(result, root.val - pre.val);
                    pre = root;
                }
                root = root.right; //右
            }
            return result;
        }


        /**
         * @Description: 「在一个有序数组上求两个数最小差值，这是不是就是一道送分题了。」
         * 笨笨的办法就是先中序遍历得到结果，在计算就可以了
         * 最直观的想法，就是把二叉搜索树转换成有序数组，然后遍历一遍数组，就统计出来最小差值了
         * @Param: [root]
         */
        public int getMinimumDifference1(TreeNode root) {
            if (root == null) {
                return -1;
            }
            ArrayList<Integer> valList = new ArrayList<>();
            dfs(root, valList);
            if (valList.size() < 2) {
                return -1;
            }
            int minVal = Integer.MAX_VALUE;
            for (int i = 1; i < valList.size(); i++) {
                minVal = Math.min(minVal, valList.get(i) - valList.get(i - 1));
            }
            return minVal;
        }

        private void dfs(TreeNode root, ArrayList<Integer> valList) {
            if (root == null) {
                return;
            }
            dfs(root.left, valList);
            valList.add(root.val);
            dfs(root.right, valList);
        }

        /**
         * @Description: 差值最小的数，可以采用中序遍历，也就是二叉搜索树顺序排列，只要计算出相邻两个节点值的差，取最小则得出
         * 但是呢：需要使用一个pre节点记录一下cur节点的前一个节点。
         * @Param: [root]
         */
        TreeNode pre = null;
        int ans = Integer.MAX_VALUE;

        public int getMinimumDifference2(TreeNode root) {
            if (root == null) {
                return -1;
            }
            getMinVal(root);
            return ans;
        }

        private void getMinVal(TreeNode root) {
            if (root == null) {
                return;
            }
            getMinVal(root.left);
            if (pre == null) {
                pre = root;
            } else {
                ans = Math.min(ans, root.val - pre.val);
                pre = root;
            }
            getMinVal(root.right);
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