package leetcode.Job.TreeNodeItem;

//给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。 
//
// 假定 BST 有如下定义： 
//
// 
// 结点左子树中所含结点的值小于等于当前结点的值 
// 结点右子树中所含结点的值大于等于当前结点的值 
// 左子树和右子树都是二叉搜索树 
// 
//
// 例如： 
//给定 BST [1,null,2,2], 
//
//    1
//    \
//     2
//    /
//   2
// 
//
// 返回[2]. 
//
// 提示：如果众数超过1个，不需考虑输出顺序 
//
// 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内） 
// Related Topics 树 
// 👍 288 👎 0


import java.util.ArrayList;
import java.util.List;

public class _501FindModeInBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new _501FindModeInBinarySearchTree().new Solution();
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
        //如果不是二叉搜索树，最直观的方法一定是把这个树都遍历了，用map统计频率，把频率排个序，最后取前面高频的元素的集合。
        // 空间复杂度妥妥的O(n)




        /**
        * @Description:  //方法二：Morris 中序遍历
         * 接着上面的思路，我们用 Morris 中序遍历的方法把中序遍历的空间复杂度优化到 O(1)。
         * 我们在中序遍历的时候，一定先遍历左子树，然后遍历当前节点，最后遍历右子树。
         * 在常规方法中，我们用递归回溯或者是栈来保证遍历完左子树可以再回到当前节点，但这需要我们付出额外的空间代价。
         * 我们需要用一种巧妙地方法可以在 O(1) 的空间下，遍历完左子树可以再回到当前节点。
         * 我们希望当前的节点在遍历完当前点的前驱之后被遍历，我们可以考虑修改它的前驱节点的 right 指针。
         * 当前节点的前驱节点的right 指针可能本来就指向当前节点（前驱是当前节点的父节点），也可能是当前节点左子树最右下的节点。
         * 如果是后者，我们希望遍历完这个前驱节点之后再回到当前节点，可以将它的 right 指针指向当前节点。
         *
         *
        * @Param: [root]
        */
        int base, count, maxCount;
        List<Integer> answer = new ArrayList<Integer>();

        //必然要求：能够遍历所有节点，并按照升序或降序的方式(这样相邻节点的值相等，方便找众数)
        //需要空间复杂度为O(1)，翻译一下：即不能使用DFS，因为DFS的函数栈也算进O(n)
        //使用Morris算法或者将二叉树线索化的好处？
        // 把二叉树变成链表(抽象)，这样无需递归即可按升序访问到所有节点，即能统计众数

        public int[] findMode(TreeNode root) {
            //备份root节点，记录节点前缀pre
            TreeNode cur = root, pre = null;
            while (cur != null) {
                //(1)查找前继，如为空则直接update当前节点，cur指向下一个节点
                if (cur.left == null) {
                    update(cur.val);
                    cur = cur.right;
                    continue;
                }
                //(2)更新当前节点cur的前缀
                pre = cur.left;
                //(3)当不为null，(并且pre.right!=cur，说明未线索化)，定位到需要线索化连接的地方
                while (pre.right != null && pre.right != cur) {
                    //定位到最右子节点，
                    pre = pre.right;
                }
                //(4)最右子节点为null，说明是未线索化，则进行线索化。并指向cur.left继续线索化
                if (pre.right == null) {
                    //cur为后继
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    update(cur.val);
                    cur = cur.right;
                }
            }
            int[] mode = new int[answer.size()];
            for (int i = 0; i < answer.size(); ++i) {
                mode[i] = answer.get(i);
            }
            return mode;
        }

        public void update(int x) {
            if (x == base) {
                ++count;
            } else {
                count = 1;
                base = x;
            }
            if (count == maxCount) {
                answer.add(base);
            }
            if (count > maxCount) {
                maxCount = count;
                answer.clear();
                answer.add(base);
            }
        }

        /**
         * @Description: 「既然是搜索树，它中序遍历就是有序的」。
         * 方法一：中序遍历
         * 此时空间复杂度为O(n), 时间复杂度为O(n)
         * 在二叉树：搜索树的最小绝对差中我们就使用了pre指针和cur指针的技巧，这次又用上了。
         * 弄一个指针指向前一个节点，这样每次cur（当前节点）才能和pre（前一个节点）作比较。
         * <p>
         * 而且初始化的时候pre = NULL，这样当pre为NULL时候，我们就知道这是比较的第一个元素。
         * @Param: [root]
         */

        int baseVal = Integer.MAX_VALUE;
        int count_cur;
        int max_Count = 0;
        List<Integer> answer_all = new ArrayList<>();

        public int[] findMode1(TreeNode root) {
            dfs(root);
            int[] result = new int[answer_all.size()];
            for (int i = 0; i < answer_all.size(); i++) {
                result[i] = answer_all.get(i);
            }
            return result;
        }

        private void dfs(TreeNode root) {
            if (root == null) {
                return;
            }
            //遍历左子节点,遍历到最左的节点，也就是中序遍历的第一个节点，也是二叉搜索树的最小值的节点
            dfs(root.left);

            if (root.val == baseVal) {
                count_cur++;
            } else {
                //最小值节点，也就是二叉搜索树中序第一个节点的初始化，初始化count=1，baseVal = root.val
                //后续判断是有多少个相等的值，找到众数
                count_cur = 1;
                baseVal = root.val;
            }
            if (max_Count == count_cur) {
                //说明此时有多个众数
                answer_all.add(baseVal);
            } else if (max_Count < count_cur) {
                //说明此时找到一个更多的众数，此时前面的众数无效，替换为当前的众数值
                answer_all.clear();
                answer_all.add(baseVal);
                max_Count = count_cur;
            }
            //遍历右节点
            dfs(root.right);
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