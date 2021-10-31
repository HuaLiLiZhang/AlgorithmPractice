package leetcode.competition;

import leetcode.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author mizhu
 * @date 2021/10/31 10:41
 */
public class _1031competition {
    public int smallestEqual(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;
    }

    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }
        ArrayList<Integer> indexList = new ArrayList<>();
        ListNode pre = head;
        ListNode cur = head.next;
        ListNode next = head.next.next;
        int i = 1;
        while (next != null) {
            if ((pre.val < cur.val && next.val < cur.val) || (pre.val > cur.val && next.val > cur.val)) {
                indexList.add(i);
            }
            ListNode temp = next.next;
            pre = cur;
            cur = next;
            next = temp;
            i++;
        }
        if (indexList.size() < 2) {
            return new int[]{-1, -1};
        }
        int min = Integer.MAX_VALUE;
        int max = indexList.get(indexList.size() - 1) - indexList.get(0);
        for (int j = 1; j < indexList.size(); j++) {
            min = Math.min(min, indexList.get(j) - indexList.get(j - 1));
        }
        return new int[]{min, max};
    }

    public static void main(String[] args) {
        int[] num1 = {1, 3};
        int start = 6;
        int goal = 4;
        System.out.println(new _1031competition().minimumOperations(num1, start, goal));
        int[] num2 = {2, 4, 12};
        start = 2;
        goal = 12;
        System.out.println(new _1031competition().minimumOperations(num2, start, goal));
        int[] num3 = {3, 5, 7};
        start = 0;
        goal = -4;
        System.out.println(new _1031competition().minimumOperations(num3, start, goal));
        int[] num4 = {2, 8, 16};
        start = 0;
        goal = 1;
        System.out.println(new _1031competition().minimumOperations(num4, start, goal));
        int[] num5 = {1};
        start = 0;
        goal = 3;
        System.out.println(new _1031competition().minimumOperations(num5, start, goal));
    }

    public int minimumOperations(int[] nums, int start, int goal) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        if (start == goal) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        set.add(start);
        Queue<Integer> queue = new LinkedList<>();
        int step = 0;
        queue.add(start);
        while (!queue.isEmpty()) {
            int queueLen = queue.size();
            step++;
            for (int j = 0; j < queueLen; j++) {
                int t = queue.poll();
                for (int i = 0; i < nums.length; i++) {
                    int a = t + nums[i];
                    int b = t - nums[i];
                    int c = t ^ nums[i];
                    if (a == goal || b == goal || c == goal) {
                        return step;
                    }
                    if (a >= 0 && a <= 1000 && set.add(a)) {
                        queue.add(a);
                    }
                    if (b >= 0 && b <= 1000 && set.add(b)) {
                        queue.add(b);
                    }
                    if (c >= 0 && c <= 1000 && set.add(c)) {
                        queue.add(c);
                    }
                }
            }
        }
        return -1;
    }
}
