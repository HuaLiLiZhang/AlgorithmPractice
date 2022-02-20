package leetcode.competition;

import leetcode.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计各位数字之和为偶数的整数个数3
 * 合并零之间的节点4
 * 构造限制重复的字符串
 *
 * @author zhanghuali
 * @version 1.0
 * @ClassName:
 * @CreateTime: 2022/02/20 10:52:35
 * @Description:
 */
public class _281Competition {

    public static void main(String[] args) {
        System.out.println(new _6014ConstructRepeatLimitedString().repeatLimitedString("cczazcc", 3));
        System.out.println(new _6014ConstructRepeatLimitedString().repeatLimitedString("aababab", 2));


        ListNode head = new ListNode(0);
        // 输入：head = [0,3,1,0,4,5,2,0]
        // 输出：[4,11]
        ListNode head1 = new ListNode(3);
        ListNode head2 = new ListNode(1);
        ListNode head3 = new ListNode(0);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        ListNode head6 = new ListNode(2);
        ListNode head7 = new ListNode(0);
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        head5.next = head6;
        head6.next = head7;
        ListNode newHead = new _6013MergeZeroNodes().mergeNodes(head);
        while (newHead != null) {
            System.out.print(newHead.val + ", ");
            newHead = newHead.next;
        }
        System.out.println();

        System.out.println(new _6012CalculateNumOfEveryBitIsEven().countEven(4));
        System.out.println(new _6012CalculateNumOfEveryBitIsEven().countEven(30));
    }

    //
    static class _6012CalculateNumOfEveryBitIsEven {
        /**
         * * 示例 1：
         * * <p>
         * * 输入：num = 4
         * * 输出：2
         * * 解释：
         * * 只有 2 和 4 满足小于等于 4 且各位数字之和为偶数。
         * * 示例 2：
         * * <p>
         * * 输入：num = 30
         * * 输出：14
         * * 解释：
         * * 只有 14 个整数满足小于等于 4 且各位数字之和为偶数，分别是：
         * * 2、4、6、8、11、13、15、17、19、20、22、24、26 和 28 。
         *
         * @param num
         * @return
         * @author zhanghuali
         * @CreateTime: 2022/02/20 11:36:19
         * @Description:
         **/
        public int countEven(int num) {
            int count = 0;
            for (int i = 1; i <= num; i++) {
                if (isCountEven(i)) {
                    count++;
                }
            }
            return count;
        }

        private boolean isCountEven(int i) {
            int sum = 0;
            while (i != 0) {
                int n = i % 10;
                sum = sum + n;
                i = i / 10;
            }
            if (sum % 2 == 0) {
                return true;
            }
            return false;
        }
    }

    static class _6013MergeZeroNodes {
        /**
         * 示例 1：
         * <p>
         * <p>
         * 输入：head = [0,3,1,0,4,5,2,0]
         * 输出：[4,11]
         * 解释：
         * 上图表示输入的链表。修改后的链表包含：
         * - 标记为绿色的节点之和：3 + 1 = 4
         * - 标记为红色的节点之和：4 + 5 + 2 = 11
         * 示例 2：
         * <p>
         * <p>
         * 输入：head = [0,1,0,3,0,2,2,0]
         * 输出：[1,3,4]
         * 解释：
         * 上图表示输入的链表。修改后的链表包含：
         * - 标记为绿色的节点之和：1 = 1
         * - 标记为红色的节点之和：3 = 3
         * - 标记为黄色的节点之和：2 + 2 = 4
         *
         * @param head
         * @return
         * @author zhanghuali
         * @CreateTime: 2022/02/20 11:36:29
         * @Description:
         **/
        public ListNode mergeNodes(ListNode head) {
            // 输入：head = [0,3,1,0,4,5,2,0]
            // 输出：[4,11]
            if (head == null || head.next == null) {
                return head;
            }
            ListNode newHead = new ListNode(0);
            ListNode newDumpy = newHead;
            int pre = 0;
            while (head != null) {
                if (head.val == 0) {
                    if (pre != 0) {
                        newHead.next = new ListNode(pre);
                        newHead = newHead.next;
                        pre = 0;
                    }
                } else {
                    pre += head.val;
                }
                head = head.next;
            }
            return newDumpy.next;
        }
    }

    static class _6014ConstructRepeatLimitedString {
        //输入：s = "cczazcc", repeatLimit = 3
        //输出："zzcccac"
        // 输入：s = "aababab", repeatLimit = 2
        //输出："bbabaa"
        public String repeatLimitedString(String s, int repeatLimit) {
            if (s == null || s.length() <= 0) {
                return s;
            }
            int[] countArr = new int[26];
            for (char c : s.toCharArray()) {
                countArr[c - 'a'] += 1;
            }
            StringBuffer news = new StringBuffer();
            for (int i = countArr.length - 1; i >= 0; i--) {
                if (countArr[i] == 0) {
                    continue;
                }
                while (countArr[i] > 0) {
                    if (countArr[i] > repeatLimit) {
                        int li = 0;
                        while (li++ < repeatLimit) {
                            news.append((char) ('a' + i));
                            countArr[i]--;
                        }
                        int j = i - 1;
                        boolean isHave = false;
                        while (j >= 0) {
                            if (countArr[j] == 0) {
                                j--;
                            } else {
                                news.append((char)('a' + j));
                                countArr[j]--;
                                isHave = true;
                                break;
                            }
                        }
                        if (!isHave) {
                            return news.toString();
                        }
                    } else {
                        while (countArr[i] > 0) {
                            news.append((char) ('a' + i));
                            countArr[i]--;
                        }
                    }
                }

            }
            return news.toString();
        }
    }


}
