package leetcode.meeting;

import leetcode.CommonFunction;
import leetcode.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author: Created by zhanghl
 */
public class AllMeetAlgorithm {

    public static String[][] getTopNStr(String[] strs, int k) {
        if (strs == null || strs.length <= 0) {
            return null;
        }
        HashMap<String, Integer> strFreq = new HashMap<>();
        for (String s : strs) {
            strFreq.put(s, strFreq.getOrDefault(s, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((a, b) -> {
            if (!a.getValue().equals(b.getValue())) {
                return b.getValue() - a.getValue();
            } else {
                return a.getKey().compareTo(b.getKey());
            }
        });

        queue.addAll(strFreq.entrySet());
        String[][] result = new String[k][2];
        for (int i = 0; i < k; i++) {
            Map.Entry<String, Integer> entry = queue.poll();
            result[i][0] = entry.getKey();
            result[i][1] = String.valueOf(entry.getValue());
        }
        return result;
    }

    public static ListNode reverseNode(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next = head;
        while (next != null) {
            ListNode temp = next.next;
            next.next = pre;
            pre = next;
            next = temp;
        }
        return pre;
    }

    public static ListNode kGroupReverse(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dumpy = new ListNode(0);
        dumpy.next = head;
        ListNode pre = dumpy;
        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return dumpy.next;
                }
            }
            ListNode next = tail.next;
            ListNode[] newHeadEnd = getReverseHeadAndEnd(head, tail);
            head = newHeadEnd[0];
            tail = newHeadEnd[1];
            pre.next = head;
            tail.next = next;
            pre = tail;
            head = tail.next;
        }
        return dumpy.next;
    }

    private static ListNode[] getReverseHeadAndEnd(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode next = p.next;
            p.next = prev;
            prev = p;
            p = next;
        }
        return new ListNode[]{tail, head};
    }

    public static boolean isJumpEnding(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return true;
        }
        int curMax = 0;
        for (int i = 0; i < arr.length; i++) {
            int curDistance = i + arr[i];
            if (curDistance >= arr.length - 1) {
                return true;
            }
            curMax = Math.max(curDistance, curMax);
            if (curMax <= i) {
                return false;
            }
        }
        return true;
    }


    public static int[] moveMoreZeroAndFillEnd(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        int first = 1;
        int second = 1;
        while (second < arr.length) {
            if (arr[second] == 0 && arr[second] == arr[second - 1]) {
                second++;
                continue;
            }
            arr[first] = arr[second];
            first++;
            second++;
        }
        while (first < arr.length) {
            arr[first++] = 0;
        }
        return arr;
    }


    public static int maxProfit(int[] price) {
        if (price == null || price.length <= 1) {
            return 0;
        }
        int curMax = Integer.MIN_VALUE;
        int preMix = Integer.MAX_VALUE;
        for (int i = 0; i < price.length; i++) {
            preMix = Math.min(preMix, price[i]);
            curMax = Math.max(curMax, price[i] - preMix);
        }
        return curMax;
    }

    public static int getNum(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int left = -1, right = 0;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (dic.containsKey(c)) {
                left = Math.max(left, dic.get(c));
            }
            dic.put(c, right);
            res = Math.max(res, right - left);
            right++;
        }
        return res;
    }

   /* public static int getIslandNum(char[][] island) {

    }*/

    public static List<List<Integer>> getJumpCombation(int n, int a, int b) {
        if (n <= 0) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        getAllCombation(n, a, b, new ArrayList<>(), result);
        return result;
    }

    private static void getAllCombation(int n, int a, int b, ArrayList<Integer> oneConba, List<List<Integer>> result) {
        if (n < 0) {
            return;
        }
        if (n == 0) {
            result.add(new ArrayList<>(oneConba));
            return;
        }
        oneConba.add(a);
        getAllCombation(n - a, a, b, oneConba, result);
        oneConba.remove(oneConba.size() - 1);
        oneConba.add(b);
        getAllCombation(n - b, a, b, oneConba, result);
        oneConba.remove(oneConba.size() - 1);
    }

    public static void main(String[] args) {
        List<List<Integer>> jumpCombation = getJumpCombation(22, 1, 2);
        System.out.println(jumpCombation.size());
        /*jumpCombation.stream().forEach(list -> {
            list.stream().forEach(c -> System.out.print(c + " "));
            System.out.println();
        });*/

        /*String[] str = {"a", "b", "c", "b", "a", "a", "c"};
        String[][] result = getTopNStr(str, 2);
        System.out.println(result);*/
        /*ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = null;*/
       /* ListNode newHead = reverseNode(head);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }*/
        /*ListNode newHeadEnd = kGroupReverse(head, 2);
        while (newHeadEnd != null) {
            System.out.println(newHeadEnd.val);
            newHeadEnd = newHeadEnd.next;
        }*/

        /*int[] arr = {2, 1, 0, 3};
        System.out.println(isJumpEnding(arr));
        int[] arr1 = {2, 2, 0, 3};
        System.out.println(isJumpEnding(arr1));
        int[] arr2 = {2, 2, 0, 1, 2, 0, 1};
        System.out.println(isJumpEnding(arr2));
        int[] arr3 = {5, 4, 3, 2, 1, 0, 3};
        System.out.println(isJumpEnding(arr3));
        int[] arr4 = {5, 2, 1};
        System.out.println(isJumpEnding(arr4));*/

        /*int[] arr = {2, 3, 0, 0, 0, 2, 3, 0, 0, 2, 3, 0, 0};
        int[] newArr = moveMoreZeroAndFillEnd(arr);
        CommonFunction.printArr(newArr);
        int[] arr1 = {0, 0, 0, 2, 3, 0, 0, 0, 0};
        newArr = moveMoreZeroAndFillEnd(arr1);
        CommonFunction.printArr(newArr);
        int[] arr2 = {0, 0, 0, 2, 0, 0, 0, 0};
        newArr = moveMoreZeroAndFillEnd(arr2);
        CommonFunction.printArr(newArr);
        int[] arr3 = {0, 0, 0, 2};
        newArr = moveMoreZeroAndFillEnd(arr3);
        CommonFunction.printArr(newArr);
        int[] arr4 = {2, 0, 0, 0, 0};
        newArr = moveMoreZeroAndFillEnd(arr4);
        CommonFunction.printArr(newArr);
        int[] arr5 = {2, 0, 3, 0, 4, 0};
        newArr = moveMoreZeroAndFillEnd(arr5);
        CommonFunction.printArr(newArr);*/

        /*String str = "";
        System.out.println(getNum(str));
        str = "jbpnbwwd";
        System.out.println(getNum(str));
        str = "bbbbbb";
        System.out.println(getNum(str));*/


    }

}
