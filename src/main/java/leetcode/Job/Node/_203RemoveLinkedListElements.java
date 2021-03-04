package leetcode.Job.Node;

//删除链表中等于给定值 val 的所有节点。 
//
// 示例: 
//
// 输入: 1->2->6->3->4->5->6, val = 6
//输出: 1->2->3->4->5
// 
// Related Topics 链表 
// 👍 542 👎 0


import leetcode.ListNode;

public class _203RemoveLinkedListElements {
    public static void main(String[] args) {
        Solution solution = new _203RemoveLinkedListElements().new Solution();
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
         * @Description: 设置一个虚拟头结点在进行移除节点操作：
         * @Param: [head, val]
         */
        public ListNode removeElements1(ListNode head, int val) {
            ListNode dumpy = new ListNode(0);
            dumpy.next = head;
            ListNode cur = dumpy;
            while (cur.next != null) {
                if (cur.next.val == val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            return dumpy.next;
        }

        public ListNode removeElements(ListNode head, int val) {
            //删除头结点。循环
            while (head != null && head.val == val) {
                head = head.next;
            }
            ListNode cur = head;
            while (cur != null && cur.next != null) {
                if (cur.next.val == val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}