package leetcode.hardProblem;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: Created by zhanghl
 */
public class _1028RecoverFromPreorder {
    /**
     * @Description: 我们从二叉树的根节点 root 开始进行深度优先搜索。
     * <p>
     * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
     * <p>
     * 如果节点只有一个子节点，那么保证该子节点为左子节点。
     * <p>
     * 给出遍历输出 S，还原树并返回其根节点 root。
     * 示例 1：
     * 输入："1-2--3--4-5--6--7"
     * 输出：[1,2,5,3,4,6,7
     * @Param: [args]
     */
    public static void main(String[] args) {
        String s = "1-2--3--4-5--6--7";
        TreeNode head = recoverFromPreorder(s);
        System.out.println(head.val);
    }

    public static TreeNode recoverFromPreorder(String S) {
        // 存储当前节点的路径
        Deque<TreeNode> path = new LinkedList<TreeNode>();
        // 存储字符串中的位置
        int pos = 0;
        while (pos < S.length()) {
            // 获取当前层数
            int level = 0;
            while (pos < S.length() && S.charAt(pos) == '-') {
                ++level;
                ++pos;
            }
            // 获取节点值
            int value = 0;
            while (pos < S.length() && Character.isDigit(S.charAt(pos))) {
                value = value * 10 + (S.charAt(pos) - '0');
                ++pos;
            }
            // 构造当前节点
            TreeNode node = new TreeNode(value);
            if (level == path.size()) {
                //如果当前节点的深度==当前路径长度（前一个节点是当前节点的父节点）
                if (!path.isEmpty()) {
                    //如果不是第一个节点，前一个节点的左子节点为当前节点
                    path.peek().left = node;
                }
            }else {
                //如果当前节点的深度！=当前路径长度（前一个节点不是当前节点的父节点）
                while (level != path.size()) {
                    //通过queue弹出其他子节点，找到当前节点的父节点
                    path.pop();
                }
                // 找到父节点，因为此时左子节点已确定，所以赋值给右子节点
                if (!path.isEmpty()) {
                    path.peek().right = node;
                }
            }
            // 放入queue中
            path.push(node);
        }
        // 全部弹出，只剩根节点
        while (path.size() > 1) {
            path.pop();
        }
        // 返回根
        return path.peek();
    }

    /** 
    * @Description: 递归解法其实更简洁：
     *
     * 定义一个全局指针cursor
     * 递归函数dfs的参数treeLevel是指当前节点的层级
     * 如果cursor指向的字符串节点的strLevel和当前treeLevel不一致，则返回null
     * 否则，创建当前节点，并递归左右子树。 
    * @Param: 
    */ 
    private int cursor = 0;
    public TreeNode recoverFromPreorder0(String S) {
        return dfs(S, 0);
    }

    private TreeNode dfs(String S, int treeLevel){
        //结束条件1
        int len = S.length();
        if(cursor >= len){
            return null;
        }
        //结束条件2
        int curLine = cursor;
        while(curLine < len && S.charAt(curLine) == '-'){
            curLine++;
        }
        int strLevel = curLine - cursor;
        if(strLevel != treeLevel){
            return null;
        }

        int curNum = curLine;
        while(curNum < len && S.charAt(curNum) != '-'){
            curNum++;
        }
        int value = Integer.valueOf(S.substring(curLine, curNum));
        cursor = curNum;
        TreeNode node = new TreeNode(value);
        node.left = dfs(S, treeLevel + 1);
        node.right = dfs(S, treeLevel + 1);
        return node;
    }


}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

