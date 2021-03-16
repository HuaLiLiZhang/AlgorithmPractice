package leetcode.Job.Node;

//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1546 👎 0


import leetcode.ListNode;

public class _206ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new _206ReverseLinkedList().new Solution();
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
        /**
         * @Description: 首先定义一个cur指针，指向头结点，再定义一个pre指针，初始化为null。
         * <p>
         * 然后就要开始反转了，首先要把 cur->next 节点用tmp指针保存一下，也就是保存一下这个节点。
         * <p>
         * 为什么要保存一下这个节点呢，因为接下来要改变 cur->next 的指向了，将cur->next 指向pre ，此时已经反转了第一个节点了。
         * <p>
         * 接下来，就是循环走如下代码逻辑了，继续移动pre和cur指针。
         * <p>
         * 最后，cur 指针已经指向了null，循环结束，链表也反转完毕了。此时我们return pre指针就可以了，pre指针就指向了新的头结点。
         * @Param: [head]
         */
        public ListNode reverseList1(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode pre = null;
            ListNode cur = head;
            while (cur != null) {
                ListNode temp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = temp;
            }
            return pre;
        }

        /**
         * @Description: 递归
         * @Param:
         */
        public ListNode reverseList(ListNode head) {
            ListNode pre = null;
            ListNode cur = head;
            return reverse(pre, cur);
        }

        public ListNode reverse(ListNode pre, ListNode cur) {
            if (cur == null) {
                return pre;
            }
            ListNode tmp = cur.next;
            cur.next = pre;
//            pre = cur;
//            cur = tmp;
            return reverse(cur, tmp);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}