package leetcode.middleProblem;

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
// 👍 7064 👎 0


import leetcode.ListNode;

public class _2AddTwoNumbers {
    public static void main(String[] args) {
        Solution t = new _2AddTwoNumbers().new Solution();
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
        //应该直接进位
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null && l2 == null) {
                return null;
            }
            ListNode dumpy = new ListNode(0);
            ListNode head = dumpy;
            int jinwei = 0;
            while (l1 != null || l2 != null) {
                int tempVal = 0;
                if (l1 != null && l2 != null) {
                    tempVal = l1.val + l2.val + jinwei;
                    l1 = l1.next;
                    l2 = l2.next;
                } else if (l1 != null) {
                    tempVal = l1.val + jinwei;
                    l1 = l1.next;
                } else {
                    tempVal = l2.val + jinwei;
                    l2 = l2.next;
                }
                jinwei = tempVal / 10;
                dumpy.next = new ListNode(tempVal % 10);
                dumpy = dumpy.next;
            }
            if (jinwei != 0) {
                dumpy.next = new ListNode(jinwei);
            }
            return head.next;

        }


        //这样明显大数相加会越界
        public ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
            if (l1 == null && l2 == null) {
                return null;
            }

            int len1 = 0;
            int len2 = 0;
            ListNode head1 = l1;
            ListNode head2 = l2;
            while (head1 != null) {
                len1 += 1;
                head1 = head1.next;
            }
            while (head2 != null) {
                len2 += 1;
                head2 = head2.next;
            }
            int sum = 0;
            if (len1 >= len2) {
                sum = getSum(l1, l2, len1, len2);
            } else {
                sum = getSum(l2, l1, len2, len1);
            }
            ListNode newHead = new ListNode(0);
            if (sum == 0) {
                return newHead;
            }
            ListNode temp = newHead;
            while (sum != 0) {
                temp.next = new ListNode(sum % 10);
                sum = sum / 10;
                temp = temp.next;
            }
            return newHead.next;

        }

        public int getSum(ListNode head1, ListNode head2, int len1, int len2) {
            int sum = 0;
            int prob1 = 0;
            int prob2 = 0;
            for (; len1 > 0; len1--) {
                if (len1 == len2) {
                    System.out.println(len1);
                    System.out.println(len2);
                    System.out.println(sum);
                    sum += head1.val * Math.pow(10, prob1) + head2.val * Math.pow(10, prob2);
                    System.out.println(sum);
                    prob1++;
                    prob2++;
                    len2--;
                    head1 = head1.next;
                    head2 = head2.next;
                } else {
                    sum += head1.val * Math.pow(10, prob1);
                    prob1++;
                    head1 = head1.next;
                }
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}