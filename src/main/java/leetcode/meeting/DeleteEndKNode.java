package leetcode.meeting;

import leetcode.ListNode;

/**
 * @author: Created by zhanghl
 */
public class DeleteEndKNode {
    public static void main(String[] args) {

    }

    public static ListNode deleteKnode(ListNode head, int k) {
        if (head == null || k <= 0) {
            return head;
        }
        int sum = 0;
        ListNode pointPre = head;
        while (pointPre != null) {
            sum++;
            pointPre = pointPre.next;
        }
        int step = sum - k;
        if (step < 0) {
            return head;
        }
        if (step == 0) {
            ListNode tmp = head.next;
            head.next = null;
            return tmp;
        }
        ListNode deleteNodePre = head;
        while (step > 1) {
            step--;
            deleteNodePre = deleteNodePre.next;
        }
        deleteNodePre.next = deleteNodePre.next.next;
        return head;
    }
}
