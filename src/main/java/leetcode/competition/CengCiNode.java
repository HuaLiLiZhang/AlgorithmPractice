package leetcode.competition;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Created by zhanghl
 */
public class CengCiNode {
    public void printNode(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queueNode = new LinkedList<>();
        queueNode.add(root);
        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            System.out.println(node.val);
            if (node.left != null) {
                queueNode.add(node.left);
            }
            if (node.right != null) {
                queueNode.add(node.right);
            }
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int v) {
        this.val = v;
    }
}
