package leetcode.Job.TreeNodeItem;

//给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。 
//
// 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点
//。 
//
// 示例 1: 
//
// 
//输入: 
//	Tree 1                     Tree 2                  
//          1                         2                             
//         / \                       / \                            
//        3   2                     1   3                        
//       /                           \   \                      
//      5                             4   7                  
//输出: 
//合并后的树:
//	     3
//	    / \
//	   4   5
//	  / \   \ 
//	 5   4   7
// 
//
// 注意: 合并必须从两个树的根节点开始。 
// Related Topics 树 
// 👍 649 👎 0


import java.util.Stack;

public class _617MergeTwoBinaryTrees {
    public static void main(String[] args) {
        Solution solution = new _617MergeTwoBinaryTrees().new Solution();
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
         * @Description: 递归法
         * 1.确定递归函数的参数和返回值：
         * 首先那么要合入两个二叉树，那么参数至少是要传入两个二叉树的根节点，返回值就是合并之后二叉树的根节点。
         *
         * 2.确定终止条件：
         * 因为是传入了两个树，那么就有两个树遍历的节点t1 和 t2，如果t1 == NULL 了，两个树合并就应该是 t2 了啊（如果t2也为NULL也无所谓，合并之后就是NULL）。
         * 反过来如果t2 == NULL，那么两个数合并就是t1（如果t1也为NULL也无所谓，合并之后就是NULL）。
         *
         * 3. 确定单层递归的逻辑：
         * 单层递归的逻辑就比较好些了，这里我们用重复利用一下t1这个树，t1就是合并之后树的根节点（就是修改了原来树的结构）。
         * 那么单层递归中，就要把两棵树的元素加到一起。
         * 接下来t1 的左子树是：合并 t1左子树 t2左子树之后的左子树。
         *
         * t1 的右子树：是 合并 t1右子树 t2右子树之后的右子树。
         *
         * 最终t1就是合并之后的根节点。
         * @Param: [root1, root2]
         */
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            if (root1 == null) {
                return root2;
            }
            if (root2 == null) {
                return root1;
            }
            TreeNode rootNew = new TreeNode(root1.val + root2.val);
            rootNew.left = mergeTrees(root1.left, root2.left);
            rootNew.right = mergeTrees(root1.right, root2.right);
            return rootNew;
            //也可以直接在root1上修改
            /*root1.val += root2.val;
            root1.left = mergeTrees(root1.left, root2.left);
            root1.right = mergeTrees(root1.right, root2.right);
            return root1;*/
        }


        /**
         * @Description: 迭代版、
         * 使用前序遍历，用栈存储两个树的节点：
         * 1）如果两个树为空，则直接返回null
         * <p>
         * 2）如果两个树的节点，一个数的左节点为空，另一个不为空，则赋值左节点为不为空的节点值；同理右节点
         * 3）如果两个树的节点，都不为空，则把两个节点值放入队列，继续遍历，直到赋值左节点或者右节点为不为空的节点值
         * @Param: [root1, root2]
         */
        public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
            if (root1 == null) {
                return root2;
            }
            if (root2 == null) {
                return root1;
            }
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root1);
            stack.push(root2);
            while (!stack.isEmpty()) {
                TreeNode temp2 = stack.pop();
                TreeNode temp1 = stack.pop();
                temp1.val += temp2.val;
                // 如果两棵树左节点都不为空，加入队列
                if (temp1.left != null && temp2.left != null) {
                    stack.push(temp1.left);
                    stack.push(temp2.left);
                }
                // 如果两棵树右节点都不为空，加入队列
                if (temp1.right != null && temp2.right != null) {
                    stack.push(temp1.right);
                    stack.push(temp2.right);
                }
                // 当t1的左节点 为空 t2左节点不为空，就赋值过去
                if (temp1.left == null && temp2.left != null) {
                    temp1.left = temp2.left;
                }
                // 当t1的右节点 为空 t2右节点不为空，就赋值过去
                if (temp1.right == null && temp2.right != null) {
                    temp1.right = temp2.right;
                }
            }
            return root1;
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