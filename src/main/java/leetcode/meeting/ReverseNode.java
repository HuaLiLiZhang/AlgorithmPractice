package leetcode.meeting;

import leetcode.ListNode;

/**
 * @author: Created by zhanghl
 */
public class ReverseNode {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        head.next = node1;
        node1.next = node2;
        node2.next = null;
        ListNode newHead = reverseNode(head);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    public static ListNode reverseNode(ListNode head) {
        //边界条件不要忘记了
        if (head == null || head.next != null) {
            return head;
        }
        ListNode pre = null;
        ListNode next = head;
        while (next != null) {
            ListNode tmp = next.next;
            next.next = pre;
            pre = next;
            next = tmp;
        }
        return pre;
    }
}
