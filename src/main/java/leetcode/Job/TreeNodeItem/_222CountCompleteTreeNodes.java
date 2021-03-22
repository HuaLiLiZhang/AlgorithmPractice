package leetcode.Job.TreeNodeItem;

//给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。 
//
// 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层
//为第 h 层，则该层包含 1~ 2h 个节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3,4,5,6]
//输出：6
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目范围是[0, 5 * 104] 
// 0 <= Node.val <= 5 * 104 
// 题目数据保证输入的树是 完全二叉树 
// 
//
// 
//
// 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？ 
// Related Topics 树 二分查找 
// 👍 454 👎 0


import java.util.LinkedList;
import java.util.Queue;

public class _222CountCompleteTreeNodes {
    public static void main(String[] args) {
        Solution solution = new _222CountCompleteTreeNodes().new Solution();
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
         * @Description: 首先这个二叉树是完全二叉树，除最后一层外，其余层全部铺满；且最后一层向左停靠
         * 思路：
         * 如果根节点的左子树深度等于右子树深度，则说明左子树为满二叉树
         * 如果根节点的左子树深度大于右子树深度，则说明右子树为满二叉树
         * <p>
         * 如果知道子树是满二叉树，那么就可以轻松得到该子树的节点数目：(1<<depth) - 1;
         * depth为子树的深度；为了加快幂的运算速度，可以使用移位操作符
         * @Param: [root]
         */
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            //         1
            //       /   \
            //      2     3
            //    /   \  /  \
            //   4    5  6
            //left为左子树的高度，也就是2是左子树的根节点，高度为1，那么左子树加上根节点的个数为：2^1-1 +1= 2^1 = 2
            // left == right。这说明，左子树一定是满二叉树，因为节点已经填充到右子树了，左子树必定已经填满了。
            // 所以左子树的节点总数我们可以直接得到，是 2^left - 1，加上当前这个 root 节点，则正好是 2^left。再对右子树进行递归统计。
            //
            //         1
            //       /   \
            //      2     3
            //    /   \  /  \
            //   4
            //left != right。说明此时最后一层不满，但倒数第二层已经满了，可以直接得到右子树的节点个数。
            // 同理，右子树节点 +root 节点，总数为 2^right。再对左子树进行递归查找
            //
            int leftDepth = treeHeight(root.left);
            int rightDepth = treeHeight(root.right);
            if (leftDepth == rightDepth) {
                return countNodes(root.right) + (1 << (leftDepth));
            } else {
                return countNodes(root.left) + (1 << (rightDepth));
            }
        }


        public int countNodes3(TreeNode root) {
            //计算树的高度，
            int height = treeHeight(root);
            //如果树是空的，或者高度是1，直接返回
            if (height == 0 || height == 1) {
                return height;
            }
            //如果右子树的高度是树的高度减1，说明左子树是满二叉树，
            //左子树可以通过公式计算，只需要递归右子树就行了
            if (treeHeight(root.right) == height - 1) {
                //注意这里的计算，左子树的数量是实际上是(1 << (height - 1))-1，
                //不要把根节点给忘了，在加上1就是(1 << (height - 1))
                return (1 << (height - 1)) + countNodes3(root.right);
            } else {
                //如果右子树的高度不是树的高度减1，说明右子树是满二叉树，可以通过
                //公式计算右子树，只需要递归左子树就行了
                return (1 << (height - 2)) + countNodes3(root.left);
            }
        }

        private int treeHeight(TreeNode root) {
            return root == null ? 0 : 1 + treeHeight(root.left);
        }

        /**
         * @Description: BFS,  宽度优先遍历
         * @Param: [root]
         */
        public int countNodes2(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int countNode = 0;
            while (!queue.isEmpty()) {
                countNode++;
                TreeNode temp = queue.poll();
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            return countNode;
        }

        /**
         * @Description: Dfs 递归
         * @Param: [root]
         */
        public int countNodes1(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftNodeNum = countNodes1(root.left);
            int rightNodeNum = countNodes1(root.right);
            return 1 + leftNodeNum + rightNodeNum;
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