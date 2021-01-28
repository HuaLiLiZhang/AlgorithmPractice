//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 5524 👎 0


package leetcode.hot100;

public class _2AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new _2AddTwoNumbers().new Solution();
        ListNode head1 = new ListNode(2);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(3);
        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(4);
        solution.addTwoNumbers(head1, head2);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) {
                return l1 == null ? l2 == null ? new ListNode(0) : new ListNode(l2.val) : new ListNode(l1.val);
            }
            int bigTen = 0;
            ListNode head = new ListNode(0);
            ListNode root = head;
            /*while (l1 != null && (l2 != null)) {
                root.next = new ListNode((l1.val + l2.val + bigTen) % 10);
                bigTen = (l1.val + l2.val + bigTen) / 10;
                root = root.next;
                l1 = l1.next;
                l2 = l2.next;
            }

            while (l1 != null) {
                root.next = new ListNode((l1.val + bigTen) % 10);
                bigTen = (l1.val + bigTen) / 10;
                root = root.next;
                l1 = l1.next;
            }
            while (l2 != null) {
                root.next = new ListNode((l2.val + bigTen) % 10);
                bigTen = (l2.val + bigTen) / 10;
                root = root.next;
                l2 = l2.next;
            }*/
            while (l1 != null || l2 != null) {
                int v1 = (l1 == null ? 0 : l1.val);
                int v2 = (l2 == null ? 0 : l2.val);
                root.next = new ListNode((v1 + v2 + bigTen) % 10);
                bigTen = (v1 + v2 + bigTen) / 10;
                root = root.next;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            if (bigTen != 0) {
                root.next = new ListNode(bigTen);

            }
            return head.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}