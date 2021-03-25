package leetcode.Job.TreeNodeItem;

//根据一棵树的中序遍历与后序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics 树 深度优先搜索 数组 
// 👍 472 👎 0


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class _106ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new _106ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
        int[] inOrder = {9, 3, 15, 20, 7};
        int[] postOrder = {9, 15, 7, 20, 3};
        solution.buildTree(inOrder, postOrder);
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
        * @Description: 迭代法是一种非常巧妙的实现方法。迭代法的实现基于以下两点发现。
         *
         * 如果将中序遍历反序，则得到反向的中序遍历，即每次遍历右孩子，再遍历根节点，最后遍历左孩子。
         * 如果将后序遍历反序，则得到反向的前序遍历，即每次遍历根节点，再遍历右孩子，最后遍历左孩子。
         * 「反向」的意思是交换遍历左孩子和右孩子的顺序，即反向的遍历中，右孩子在左孩子之前被遍历。
         *
         * 因此可以使用和「105. 从前序与中序遍历序列构造二叉树」的迭代方法类似的方法构造二叉树。
         *
         * 对于后序遍历中的任意两个连续节点 u 和 v（在后序遍历中，u 在 v 的前面），根据后序遍历的流程，我们可以知道 u 和 v 只有两种可能的关系：
         *
         * 1、u 是 v 的右儿子。这是因为在遍历到 u 之后，下一个遍历的节点就是 u 的双亲节点，即 v；
         * 2、v 没有右儿子，并且 u 是 v 的某个祖先节点（或者 v 本身）的左儿子。如果 v 没有右儿子，那么上一个遍历的节点就是 v 的左儿子。
         *    如果 v 没有左儿子，则从 v 开始向上遍历 v 的祖先节点，直到遇到一个有左儿子（且 v 不在它的左儿子的子树中）的节点 v_a ，那么 u 就是 v_a的左儿子。
         *
         * 第二种关系看上去有些复杂。我们举一个例子来说明其正确性，并在例子中给出我们的迭代算法。
         *
         *
         * 时间复杂度：O(n)，其中 n 是树中的节点个数。
         *
         * 空间复杂度：O(n)，我们需要使用 O(h)（其中 h 是树的高度）的空间存储栈。这里 h < n，所以（在最坏情况下）总空间复杂度为 O(n)。
         *
         *
         *
        * @Param: [inorder, postorder]
        */
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            if (postorder == null || postorder.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(postorder[postorder.length - 1]);
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            stack.push(root);
            int inorderIndex = inorder.length - 1;
            for (int i = postorder.length - 2; i >= 0; i--) {
                int postorderVal = postorder[i];
                TreeNode node = stack.peek();
                if (node.val != inorder[inorderIndex]) {
                    node.right = new TreeNode(postorderVal);
                    stack.push(node.right);
                } else {
                    while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                        node = stack.pop();
                        inorderIndex--;
                    }
                    node.left = new TreeNode(postorderVal);
                    stack.push(node.left);
                }
            }
            return root;
        }



        int postIdx;
        int[] postorder;
        int[] inorder;
        Map<Integer, Integer> idxMap = new HashMap<Integer, Integer>();

        /**
         * @Description: 为了高效查找根节点元素在中序遍历数组中的下标，我们选择创建哈希表来存储中序序列，即建立一个（元素，下标）键值对的哈希表。
         * <p>
         * 定义递归函数 helper(in_left, in_right) 表示当前递归到中序序列中当前子树的左右边界，递归入口为helper(0, n - 1) ：
         * <p>
         * 如果 in_left > in_right，说明子树为空，返回空节点。
         * <p>
         * 选择后序遍历的最后一个节点作为根节点。
         * <p>
         * 利用哈希表 O(1) 查询当根节点在中序遍历中下标为 index。从 in_left 到 index - 1 属于左子树，从 index + 1 到 in_right 属于右子树。
         * <p>
         * 根据后序遍历逻辑，递归创建右子树 helper(index + 1, in_right) 和左子树 helper(in_left, index - 1)。
         * 注意这里有需要先创建右子树，再创建左子树的依赖关系。
         * 可以理解为在后序遍历的数组中整个数组是先存储左子树的节点，再存储右子树的节点，最后存储根节点，
         * 如果按每次选择「后序遍历的最后一个节点」为根节点，则先被构造出来的应该为右子树。
         * <p>
         * 返回根节点 root。
         * <p>
         * 复杂度分析
         * <p>
         * 时间复杂度：O(n)，其中 n 是树中的节点个数。
         * <p>
         * 空间复杂度：O(n)。我们需要使用 O(n)的空间存储哈希表，以及 O(h)（其中 h 是树的高度）的空间表示递归时栈空间。
         * 这里 h < n，所以总空间复杂度为 O(n)。
         * @Param: [inorder, postorder]
         */
        public TreeNode buildTree1(int[] inorder, int[] postorder) {
            this.postorder = postorder;
            this.inorder = inorder;
            // 从后序遍历的最后一个元素开始
            postIdx = postorder.length - 1;

            // 建立（元素，下标）键值对的哈希表
            int idx = 0;
            for (Integer val : inorder) {
                idxMap.put(val, idx++);
            }

            return helper(0, inorder.length - 1);
        }

        public TreeNode helper(int inLeft, int inRight) {
            // 如果这里没有节点构造二叉树了，就结束
            if (inLeft > inRight) {
                return null;
            }

            // 选择 postIdx 位置的元素作为当前子树根节点
            int rootVal = postorder[postIdx];
            TreeNode root = new TreeNode(rootVal);

            // 根据 root 所在位置分成左右两棵子树
            int index = idxMap.get(rootVal);

            // 下标减一
            postIdx--;
            // 构造右子树 :注意这里有需要先创建右子树，再创建左子树的依赖关系。
            root.right = helper(index + 1, inRight);
            // 构造左子树
            root.left = helper(inLeft, index - 1);
            return root;
        }


        /**
         * @Description:
         * @Param: [inorder, postorder]
         */

        public TreeNode buildTree2(int[] inorder, int[] postorder) {
            if (inorder.length == 0 || postorder.length == 0) {
                return null;
            }
            return traversal(inorder, postorder);
        }

        public TreeNode traversal(int[] inorder, int[] postorder) {
            // 第一步
            if (postorder.length == 0) {
                return null;
            }
            // 第二步：后序遍历数组最后一个元素，就是当前的中间节点
            int rootValue = postorder[postorder.length - 1];
            TreeNode root = new TreeNode(rootValue);
            //叶子节点
            if (postorder.length == 1) {
                return root;
            }
            // 第三步：找切割点
            int delimiterIndex;
            for (delimiterIndex = 0; delimiterIndex < inorder.length; delimiterIndex++) {
                if (inorder[delimiterIndex] == rootValue) {
                    break;
                }
            }
            //「难点大家应该发现了，就是如何切割，以及边界值找不好很容易乱套。」
            //
            //此时应该注意确定切割的标准，是左闭右开，还有左开又闭，还是左闭又闭，这个就是不变量，要在递归中保持这个不变量。
            //首先要切割中序数组，为什么先切割中序数组呢？
            //
            //切割点在后序数组的最后一个元素，就是用这个元素来切割中序数组的，所以必要先切割中序数组。
            //
            //中序数组相对比较好切，找到切割点（后序数组的最后一个元素）在中序数组的位置，然后切割，如下代码中我坚持左闭右开的原则：

            // 第四步：切割中序数组，得到 中序左数组和中序右数组
            int[] leftInorder = getDelimiterArr(inorder, 0, delimiterIndex - 1);
            int[] rightInorder = getDelimiterArr(inorder, delimiterIndex + 1, inorder.length - 1);
            // 第五步：切割后序数组，得到 后序左数组和后序右数组
            int[] leftPostorder = getDelimiterArr(postorder, 0, delimiterIndex - 1);
            int[] rightPostorder = getDelimiterArr(postorder, delimiterIndex, postorder.length - 2);
            // 第六步
            root.left = traversal(leftInorder, leftPostorder);
            root.right = traversal(rightInorder, rightPostorder);
            return root;
        }

        private int[] getDelimiterArr(int[] inorder, int start, int end) {
            int[] result = new int[end - start + 1];
            int k = 0;
            for (int i = start; i <= end; i++) {
                result[k++] = inorder[i];
            }
            return result;
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