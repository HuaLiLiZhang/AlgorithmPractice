package leetcode.hardProblem;

//给定一个二叉树，我们在树的节点上安装摄像头。 
//
// 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。 
//
// 计算监控树的所有节点所需的最小摄像头数量。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[0,0,null,0,0]
//输出：1
//解释：如图所示，一台摄像头足以监控所有节点。
// 
//
// 示例 2： 
//
// 
//
// 输入：[0,0,null,0,null,0,null,null,0]
//输出：2
//解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
// 
//
// 
//提示： 
//
// 
// 给定树的节点数的范围是 [1, 1000]。 
// 每个节点的值都是 0。 
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 
// 👍 328 👎 0


import leetcode.Job.TreeNode;

public class _968BinaryTreeCameras {
    public static void main(String[] args) {
        Solution solution = new _968BinaryTreeCameras().new Solution();
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
         * @Description: 覆盖树的节点：从叶子节点开始，叶子节点的父节点肯定是要放摄像头，因为可以最少，如果从根节点开始，根节点也不必须放摄像头
         * @Param: [root]
         */
        private int result = 0;

        //节点只有三种状态：无覆盖0，有摄像头1，有覆盖2
        public int minCameraCover(TreeNode root) {
            if (reverseTravel(root) == 0) {
                result++;
            }
            return result;
        }

        //通过后序遍历能够从叶子节点开始，
        // 如果左右节点都是有覆盖，那么此时根节点需要放摄像头，返回1；
        // 如果左右节点有一个无覆盖，那么此时根节点也要放摄像头，返回1；
        // 如果左右节点有一个有摄像头，那么此时根节点是有覆盖，不用放摄像头，返回2.
        private int reverseTravel(TreeNode root) {
            // 空节点，该节点有覆盖
            if (root == null) {
                return 2;
            }
            int left = reverseTravel(root.left);
            int right = reverseTravel(root.right);
            // 情况1
            // 左右节点都有覆盖，返回根节点为0,无覆盖，因为不一定在此节点放摄像头，有可能在根节点，上头节点放摄像头
            if (left == 2 && right == 2) {
                return 0;
            }

            // 情况2
            // left == 0 && right == 0 左右节点无覆盖
            // left == 1 && right == 0 左节点有摄像头，右节点无覆盖
            // left == 0 && right == 1 左节点有无覆盖，右节点摄像头
            // left == 0 && right == 2 左节点无覆盖，右节点覆盖
            // left == 2 && right == 0 左节点覆盖，右节点无覆盖
            if (left == 0 || right == 0) {
                result++;
                return 1;
            }

            // 情况3
            // left == 1 && right == 2 左节点有摄像头，右节点有覆盖
            // left == 2 && right == 1 左节点有覆盖，右节点有摄像头
            // left == 1 && right == 1 左右节点都有摄像头
            // 其他情况前段代码均已覆盖
            if (left == 1 || right == 1) {
                return 2;
            }

            // 以上代码我没有使用else，主要是为了把各个分支条件展现出来，这样代码有助于读者理解
            // 这个 return -1 逻辑不会走到这里。
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}