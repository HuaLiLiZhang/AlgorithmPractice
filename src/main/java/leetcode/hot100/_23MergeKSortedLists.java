package leetcode.hot100;

//给你一个链表数组，每个链表都已经按升序排列。 
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
// Related Topics 堆 链表 分治算法 
// 👍 1129 👎 0


import java.util.PriorityQueue;

public class _23MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new _23MergeKSortedLists().new Solution();
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
         * @Description: 方法三：使用优先队列合并
         * 思路
         * <p>
         * 这个方法和前两种方法的思路有所不同，我们需要维护当前每个链表没有被合并的元素的最前面一个，k 个链表就最多有 k 个满足这样条件的元素，
         * 每次在这些元素里面选取 val 属性最小的元素合并到答案中。在选取最小元素的时候，我们可以用优先队列来优化这个过程。
         * @Param:
         */
        class Status implements Comparable<Status> {
            int val;
            com.zhanghuali.leetcode.editor.cn.ListNode ptr;

            Status(int val, com.zhanghuali.leetcode.editor.cn.ListNode ptr) {
                this.val = val;
                this.ptr = ptr;
            }

            @Override
            public int compareTo(Status status2) {
                return this.val - status2.val;
            }
        }

        PriorityQueue<Status> queue = new PriorityQueue<Status>();

        public com.zhanghuali.leetcode.editor.cn.ListNode mergeKLists(com.zhanghuali.leetcode.editor.cn.ListNode[] lists) {
            for (com.zhanghuali.leetcode.editor.cn.ListNode node : lists) {
                if (node != null) {
                    queue.offer(new Status(node.val, node));
                }
            }
            com.zhanghuali.leetcode.editor.cn.ListNode head = new com.zhanghuali.leetcode.editor.cn.ListNode(0);
            com.zhanghuali.leetcode.editor.cn.ListNode tail = head;
            while (!queue.isEmpty()) {
                Status f = queue.poll();
                tail.next = f.ptr;
                tail = tail.next;
                if (f.ptr.next != null) {
                    queue.offer(new Status(f.ptr.next.val, f.ptr.next));
                }
            }
            return head.next;
        }


        /**
         * @Description: 方法二： 用分治的方法进行合并。
         * 将 k 个链表配对并将同一对中的链表合并；
         * 第一轮合并以后， k 个链表被合并成了k /2 个链表，平均长度为2n /k, 然后是 k /4 个链表，k /8 个链表等等；
         * 重复这一过程，直到我们得到了最终的有序链表。
         * 时间复杂度：O(kn*logk)
         * 空间复杂度：O(logk)
         * [lists]
         */
        public com.zhanghuali.leetcode.editor.cn.ListNode mergeKLists2(com.zhanghuali.leetcode.editor.cn.ListNode[] lists) {
            return merge(lists, 0, lists.length - 1);
        }

        private com.zhanghuali.leetcode.editor.cn.ListNode merge(com.zhanghuali.leetcode.editor.cn.ListNode[] lists, int left, int right) {
            if (left == right) {
                return lists[left];
            } else if (left > right) {
                return null;
            } else {
                int mid = (left + right) / 2;
                return mergeTwoLists(merge(lists, left, mid), merge(lists, mid + 1, right));
            }
        }


        /**
         * @Description: 从链表列表的第一个开始遍历，依次获取两个链表合并后的结果
         * 渐进时间复杂度为 O(k^2 n)
         * 空间复杂度：没有用到与 k 和 n 规模相关的辅助空间，故渐进空间复杂度为 O(1)
         * @Param: [lists]
         */
        public com.zhanghuali.leetcode.editor.cn.ListNode mergeKLists1(com.zhanghuali.leetcode.editor.cn.ListNode[] lists) {
            if (lists == null || lists.length <= 0) {
                return null;
            }
            if (lists.length == 1) {
                return lists[0];
            }
            com.zhanghuali.leetcode.editor.cn.ListNode dummy = new com.zhanghuali.leetcode.editor.cn.ListNode(-10 ^ 4);
            com.zhanghuali.leetcode.editor.cn.ListNode newHead = dummy;
            int before = 0;
            while (before < lists.length) {
                newHead = mergeTwoLists(newHead, lists[before]);
                before++;
            }
            return newHead.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        public com.zhanghuali.leetcode.editor.cn.ListNode mergeTwoLists(com.zhanghuali.leetcode.editor.cn.ListNode l1, com.zhanghuali.leetcode.editor.cn.ListNode l2) {
            com.zhanghuali.leetcode.editor.cn.ListNode dummy = new com.zhanghuali.leetcode.editor.cn.ListNode(-10 ^ 4);
            com.zhanghuali.leetcode.editor.cn.ListNode head = dummy;
            while (l1 != null && l2 != null) {
                if (l1.val > l2.val) {
                    head.next = new com.zhanghuali.leetcode.editor.cn.ListNode(l2.val);
                    l2 = l2.next;
                } else {
                    head.next = new com.zhanghuali.leetcode.editor.cn.ListNode(l1.val);
                    l1 = l1.next;
                }
                head = head.next;

            }
            // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
            head.next = l1 == null ? l2 : l1;
            return dummy.next;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}