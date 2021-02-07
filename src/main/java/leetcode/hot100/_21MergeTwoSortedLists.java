package leetcode.hot100;

//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
// Related Topics 递归 链表 
// 👍 1520 👎 0


public class _21MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution solution = new _21MergeTwoSortedLists().new Solution();

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

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode head = dummy;
            while (l1 != null && l2 != null) {
                if (l1.val > l2.val) {
                    head.next = new ListNode(l2.val);
                    l2 = l2.next;
                } else {
                    head.next = new ListNode(l1.val);
                    l1 = l1.next;
                }
                head = head.next;

            }
            // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
            head.next = l1 == null ? l2 : l1;
            return dummy.next;

        }

        //当l1或l2==null时候，这时候直接head.next= l1 或l2就可以， 可以优化
        public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) {
                return l1 == null ? l2 : l1;
            }
            ListNode dummy = new ListNode(0);
            ListNode head = dummy;
            while (l1 != null || l2 != null) {
                if (l1 == null && l2 != null) {
                    head.next = new ListNode(l2.val);
                    l2 = l2.next;
                    head = head.next;
                    continue;
                }
                if (l2 == null && l1 != null) {
                    head.next = new ListNode(l1.val);
                    l1 = l1.next;
                    head = head.next;
                    continue;
                }
                if (l1.val > l2.val) {
                    head.next = new ListNode(l2.val);
                    l2 = l2.next;
                } else {
                    head.next = new ListNode(l1.val);
                    l1 = l1.next;
                }
                head = head.next;

            }
            return dummy.next;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}