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
        //[1,2,3,4,5]
        //2
        //[1,2,3,4]
        //5
        int[] nums = {1,2,3,4,5};
        int[] nums1 = {1,2,3,4};
        System.out.println(new _6015CountPairCanByDivision().coutPairs(nums, 2));
        System.out.println(new _6015CountPairCanByDivision().coutPairs(nums1, 5));
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
                                news.append((char) ('a' + j));
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

    /**
     * 给你一个下标从 0 开始、长度为 n 的整数数组 nums 和一个整数 k ，返回满足下述条件的下标对 (i, j) 的数目：
     * <p>
     * 0 <= i < j <= n - 1 且
     * nums[i] * nums[j] 能被 k 整除。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3,4,5], k = 2
     * 输出：7
     * 解释：
     * 共有 7 对下标的对应积可以被 2 整除：
     * (0, 1)、(0, 3)、(1, 2)、(1, 3)、(1, 4)、(2, 3) 和 (3, 4)
     * 它们的积分别是 2、4、6、8、10、12 和 20 。
     * 其他下标对，例如 (0, 2) 和 (2, 4) 的乘积分别是 3 和 15 ，都无法被 2 整除。
     * 示例 2：
     * <p>
     * 输入：nums = [1,2,3,4], k = 5
     * 输出：0
     * 解释：不存在对应积可以被 5 整除的下标对。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 105
     * 1 <= nums[i], k <= 105
     *
     * @param
     * @author zhanghuali
     * @return
     * @CreateTime: 2022/02/20 16:30:39
     * @Description:
     **/
    static class _6015CountPairCanByDivision {
        // 统计每个数字和k的最大公约数，只要最大公约数之乘积可以被k整除就行
        public static long coutPairs(int[] nums, int k) {
            int length = nums.length;
            int cnt = 0;
            Map<Integer, Integer> gcdCntMap = new HashMap<>();
            for (int num : nums) {
                if (num % k == 0) {
                    cnt++;
                } else {
                    // 最大公因数
                    int gcd = gcd(k, num);
                    gcdCntMap.put(gcd, gcdCntMap.getOrDefault(gcd, 0) + 1);
                }
            }
            // 本身就能整除的，配上任何不能整除的数数都能整除
            long ans = (long) cnt * (length - cnt);
            // 本身能整除的配上能整除的
            ans += (long) cnt * (cnt - 1) >> 1;
            // 凑不能整除的，凑因子相乘如果能整除
            long tmp = 0;
            for (Integer key : gcdCntMap.keySet()) {
                if (key == 1) {
                    continue;
                }
                Integer count = gcdCntMap.get(key);
                for (Integer nextKey : gcdCntMap.keySet()) {
                    if ((key * nextKey) % k == 0) {
                        if (key.equals(nextKey)) {
                            // 自己乘自己，会算两遍
                            tmp += (long) count * (count - 1);
                        } else {
                            // 有可能i*j 或者j*i会计算两遍
                            tmp += (long) count * gcdCntMap.get(nextKey);
                        }
                    }
                }
            }
            return ans + (tmp >> 1);
        }
        // 求最大公因数
        private static int gcd(int k, int j) {
            return j != 0 ? gcd(j, k % j) : k;
        }

        public long coutPairs_超时(int[] nums, int k) {
            if (nums == null || nums.length <= 1) {
                return 0;
            }
            int count = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length - 1; i++) {
                int x = nums[i];
                if (map.containsKey(x) || x % k == 0) {
                    map.put(x, 1);
                    count += nums.length - i - 1;
                    continue;
                }
                for (int j = i + 1; j < nums.length; j++) {
                    int y = nums[j];
                    if (map.containsKey(y) || y % k == 0) {
                        count++;
                        map.put(y, 1);
                        continue;
                    }
                    int mu = x * y;
                    if (map.containsKey(mu) || mu % k == 0) {
                        map.put(mu, 1);
                        count++;
                    }
                }
            }
            return count;
        }
    }


}
