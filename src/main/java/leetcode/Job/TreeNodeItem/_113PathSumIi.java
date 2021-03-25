package leetcode.Job.TreeNodeItem;

//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,3], targetSum = 5
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], targetSum = 0
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点总数在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
// 
// 
// Related Topics 树 深度优先搜索 
// 👍 449 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _113PathSumIi {
    public static void main(String[] args) {
        Solution solution = new _113PathSumIi().new Solution();
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
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> resultPath = new ArrayList<>();
            if (root == null) {
                return resultPath;
            }
            Queue<TreeNode> queueNode = new LinkedList<>();
            Queue<Integer> queueTarget = new LinkedList<>();
            Queue<ArrayList<Integer>> queuePath = new LinkedList<>();

            queueNode.offer(root);

            queuePath.offer(new ArrayList<Integer>() {{
                add(root.val);
            }});

            queueTarget.offer(targetSum - root.val);

            while (!queueNode.isEmpty()) {
                TreeNode nodeTemp = queueNode.poll();
                Integer targetTemp = queueTarget.poll();
                ArrayList<Integer> pathTemp = queuePath.poll();
                if (nodeTemp.left == null && nodeTemp.right == null && targetTemp == 0) {
                    resultPath.add(new ArrayList<>(pathTemp));
                    continue;
                }
                if (nodeTemp.left == null && nodeTemp.right == null) {
                    continue;
                }
                if (nodeTemp.left != null) {
                    queueNode.offer(nodeTemp.left);
                    queueTarget.offer(targetTemp - nodeTemp.left.val);
                    ArrayList pathLeftTemp = new ArrayList<>(pathTemp);
                    pathLeftTemp.add(nodeTemp.left.val);
                    queuePath.offer(pathLeftTemp);
                }
                if (nodeTemp.right != null) {
                    queueNode.offer(nodeTemp.right);
                    queueTarget.offer(targetTemp - nodeTemp.right.val);
                    ArrayList pathLeftTemp = new ArrayList<>(pathTemp);
                    pathLeftTemp.add(nodeTemp.right.val);
                    queuePath.offer(pathLeftTemp);
                }
            }
            return resultPath;
        }


        /**
         * @Description: 要遍历整个树，找到所有路径，「所以递归函数不要返回值！」
         * <p>
         * 时间复杂度：O(N^2)，其中 N 是树的节点数。在最坏情况下，树的上半部分为链状，
         * 下半部分为完全二叉树，并且从根节点到每一个叶子节点的路径都符合题目要求。
         * 此时，路径的数目为 O(N)，并且每一条路径的节点个数也为 O(N)，因此要将这些路径全部添加进答案中，时间复杂度为 O(N^2)
         * 空间复杂度：O(N)，其中 N是树的节点数。空间复杂度主要取决于栈空间的开销，栈中的元素个数不会超过树的节点数。
         * @Param: [root, targetSum]
         */
        public List<List<Integer>> pathSum1(TreeNode root, int targetSum) {
            List<List<Integer>> resultPath = new ArrayList<>();
            if (root == null) {
                return resultPath;
            }
            ArrayList<Integer> pathTemp = new ArrayList<>();
            pathTemp.add(root.val);
            getAllPathOfSum(root, targetSum - root.val, resultPath, pathTemp);
            return resultPath;

        }

        private void getAllPathOfSum(TreeNode root, int targetSum, List<List<Integer>> resultPath,
                                     ArrayList<Integer> oneTempPath) {
            if (root.left == null && root.right == null && targetSum == 0) {
                // 遇到了叶子节点切找到了和为sum的路径
                //这里之所以要new一个，是因为就java是引用，如果不new的话，后面改动会影响这个里面的结果。
                resultPath.add(new ArrayList<>(oneTempPath));
                return;
            }
            if (root.left == null && root.right == null) {
                // 遇到叶子节点而没有找到合适的边，直接返回
                return;
            }
            if (root.left != null) { // 左 （空节点不遍历）
                oneTempPath.add(root.left.val);
                getAllPathOfSum(root.left, targetSum - root.left.val, resultPath, oneTempPath);
                //递归返回的原因是因为遇到叶子节点，叶子节点如果找到满足的条件，那么将结果加入，此时需要将此叶子节点弹出，继续下一种可能
                oneTempPath.remove(oneTempPath.size() - 1);
            }
            if (root.right != null) { // 右 （空节点不遍历）
                oneTempPath.add(root.right.val);
                getAllPathOfSum(root.right, targetSum - root.right.val, resultPath, oneTempPath);
                //递归返回的原因是因为遇到叶子节点，叶子节点如果找到满足的条件，那么将结果加入，此时需要将此叶子节点弹出，继续下一种可能
                oneTempPath.remove(oneTempPath.size() - 1);
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