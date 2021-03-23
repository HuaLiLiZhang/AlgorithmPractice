package leetcode.Job.TreeNodeItem;

//给定一个二叉树，返回所有从根节点到叶子节点的路径。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 输入:
//
//   1
// /   \
//2     3
// \
//  5
//
//输出: ["1->2->5", "1->3"]
//
//解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3 
// Related Topics 树 深度优先搜索 
// 👍 469 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _257BinaryTreePaths {
    public static void main(String[] args) {
        Solution solution = new _257BinaryTreePaths().new Solution();
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
         * @Description: 我们也可以用广度优先搜索来实现。
         * 我们维护一个队列，存储节点以及根到该节点的路径。
         * 一开始这个队列里只有根节点。在每一步迭代中，我们取出队列中的首节点，如果它是叶子节点，则将它对应的路径加入到答案中。
         * 如果它不是叶子节点，则将它的所有孩子节点加入到队列的末尾。当队列为空时广度优先搜索结束，我们即能得到答案。
         * <p>
         * 时间复杂度：O(N^2)其中 N 表示节点数目。分析同方法一。
         * 空间复杂度：O(N^2)其中 N 表示节点数目。在最坏情况下，队列中会存在 N 个节点，保存字符串的队列中每个节点的最大长度为 N，故空间复杂度为 O(N^2)
         * @Param: [root]
         */
        public List<String> binaryTreePaths1(TreeNode root) {
            List<String> resultPath = new ArrayList<>();
            if (root == null) {
                return resultPath;
            }
            Queue<TreeNode> nodes = new LinkedList<>();
            Queue<String> paths = new LinkedList<>();
            nodes.offer(root);
            paths.offer(Integer.toString(root.val));
            while (!nodes.isEmpty()) {
                TreeNode temp = nodes.poll();
                String pathTemp = paths.poll();
                if (temp.left == null && temp.right == null) {
                    resultPath.add(pathTemp);
                    continue;
                }
                if (temp.left != null) {
                    nodes.offer(temp.left);
                    paths.offer(pathTemp + "->" + temp.left.val);
                }
                if (temp.right != null) {
                    nodes.offer(temp.right);
                    paths.offer(pathTemp + "->" + temp.right.val);
                }
            }
            return resultPath;
        }

        /**
         * @Description: 最直观的方法是使用深度优先搜索。在深度优先搜索遍历二叉树时，我们需要考虑当前的节点以及它的孩子节点。
         * <p>
         * 如果当前节点不是叶子节点，则在当前的路径末尾添加该节点，并继续递归遍历该节点的每一个孩子节点。
         * 如果当前节点是叶子节点，则在当前路径末尾添加该节点后我们就得到了一条从根节点到叶子节点的路径，将该路径加入到答案即可。
         * @Param: [root]
         */
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> resultPath = new ArrayList<>();
            if (root == null) {
                return resultPath;
            }
//            getAllPaths(root, resultPath, new StringBuilder());
            dfs(root, new StringBuilder(), resultPath);
            return resultPath;
        }

        /**
         * @Description: 时间复杂度：O(N^2)，其中 N 表示节点数目。在深度优先搜索中每个节点会被访问一次且只会被访问一次，
         * 每一次会对 path 变量进行拷贝构造，时间代价为 (N)，故时间复杂度为 O(N^2)
         * <p>
         * 空间复杂度：O(N^2)
         * <p>
         * 关于时间复杂度。题解中，如果每个节点都进行String的拷贝，那最坏情况出现在退化为链表的树时，
         * 这时候由于String长度与节点个数正相关，而每个节点都会进行拷贝，所以渐进复杂度为O(N^2)；
         * <p>
         * 而如果使用可变数组（StringBuilder等），那可以避免非叶子节点的拷贝，
         * 这样退化为链表时，时间复杂度反而最好，为O(N)；
         * 最坏时间复杂度出现在完全二叉树：叶子节点为N/2个，路径长度为logN，所以时间复杂度为O(N/2 + N/2 * logN) ~ O(NlogN)。
         * @Param: [root, resultPath, path]
         */
        private void getAllPaths(TreeNode root, List<String> resultPath, StringBuilder path) {
            if (root == null) {
                return;
            }
            path.append(root.val);
            if (root.left == null && root.right == null) {
                resultPath.add(path.toString());  // 把路径加入到答案中
                return;
            }
            path.append("->");
            if (root.left != null) {
                getAllPaths(root.left, resultPath, new StringBuilder(path)/*.append("->")*/);
            }
            if (root.right != null) {
                getAllPaths(root.right, resultPath, new StringBuilder(path)/*.append("->")*/);
            }
        }


        private void dfs(TreeNode root, StringBuilder cur, List<String> paths) {
            if (root == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                paths.add(cur.toString() + root.val);
                return;
            }
            int sz = cur.length();
            cur.append(root.val).append("->");
            dfs(root.left, cur, paths);
            dfs(root.right, cur, paths);
            cur.delete(sz, cur.length());
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