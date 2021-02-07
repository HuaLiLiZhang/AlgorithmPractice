package leetcode.hot100;

//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
// Related Topics 链表 双指针 
// 👍 1200 👎 0


public class _19RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new _19RemoveNthNodeFromEndOfList().new Solution();
        ListNode head1 = new ListNode(1);
        ListNode res1 = solution.removeNthFromEnd(head1, 1);
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        ListNode res2 = solution.removeNthFromEnd(head2, 1);
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);
        head3.next.next = new ListNode(3);
        head3.next.next.next = new ListNode(4);
        head3.next.next.next.next = new ListNode(5);
        ListNode res3 = solution.removeNthFromEnd(head3, 2);
        System.out.println();
    }
    /**

     */
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
        //1.遍历两遍，计算链表长度，当遍历到第 L-n+1个节点时，它就是我们需要删除的节点，最好的是遍历到第 L-n+1个节点的上一个节点，那么，他的下一个节点就是需要删除的节点。
        //时间复杂度：O(L)，其中 L 是链表的长度
        //空间复杂度：O(1)。
        //2.栈存储链表的节点：根据栈「先进后出」的原则，我们弹出栈的第 n 个节点就是需要删除的节点，并且目前栈顶的节点就是待删除节点的前驱节点。这样一来，删除操作就变得十分方便了。
        //时间复杂度：O(L)
        //空间复杂度：O(L)
        //3.双指针：初始时 first 和 second 均指向头节点。我们首先使用 first 对链表进行遍历，遍历的次数为 n。此时，first 和 second 之间间隔了n−1 个节点，
        // 即 first 比 second 超前了 n 个节点。
        //如果我们能够得到的是倒数第 n 个节点的前驱节点而不是倒数第 n 个节点的话，删除操作会更加方便。因此我们可以考虑在初始时将 second 指向哑节点，
        // 其余的操作步骤不变。这样一来，当 first 遍历到链表的末尾时, second 的下一个节点就是我们需要删除的节点。
        //
        //
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0, head);
            ListNode second = dummy;
            ListNode first = head;
            for (int i = 0; i < n; i++) {
                first = first.next;
            }
            while (first != null) {
                second = second.next;
                first = first.next;
            }
            second.next = second.next.next;
            ListNode res = dummy.next;
            return res;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}