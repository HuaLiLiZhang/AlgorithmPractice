package leetcode.competition;

import java.util.*;

/**
 * TODO 类的实现描述
 *
 * @author zhanghuali
 * @version 1.0
 * @ClassName: _293_competition
 * @CreateTime: 2022/05/15 10:49:10
 * @Description:
 */
public class _293_competition {

    public static void main(String[] args) {
        _293_competition competition = new _293_competition();
        CountIntervals countIntervals = new CountIntervals();
        countIntervals.add(2, 3);
        countIntervals.add(7, 10);
        System.out.println(countIntervals.count());
        countIntervals.add(5, 8);
        System.out.println(countIntervals.count());


        /*int[] candidates = {16, 17, 71, 62, 12, 24, 14};
        System.out.println(competition.largestCombination(candidates));
        int[] candidates1 = {8, 8};
        System.out.println(competition.largestCombination(candidates1));*/
        /*int bottom = 2, top = 9;
        int[] special = {4, 6};
        System.out.println(competition.maxConsecutive(bottom, top, special));

        bottom = 6;
        top = 8;
        int[] special1 = {7, 6, 8};
        System.out.println(competition.maxConsecutive(bottom, top, special1));*/

        /*String[] words = {"abba", "baba", "bbaa", "cd", "cd"};
        System.out.println(competition.removeAnagrams(words));
        String[] words1 = {"a", "b", "c", "d", "e"};
        System.out.println(competition.removeAnagrams(words1));*/

    }

    static class CountIntervals {
        private PriorityQueue<int[]> priorityQueue = new PriorityQueue<>();
        TreeSet<int[]> tree = new TreeSet<>((a, b) -> a[1] - b[1]);
        int sum;

        public CountIntervals() {
            sum = 0;
        }

        public void add(int left, int right) {
            while (!tree.isEmpty()) {
                int[] tem = tree.ceiling(new int[]{0, left - 1}); //找出大于等于这个值的元素，没有为空
                if (tem == null || tem[0] > right + 1) {
                    break;    //没有相交的区间就退出
                }
                //合并两个相交区间
                int[] x = new int[]{Math.min(left, tem[0]), Math.max(right, tem[1])};
                left = x[0];
                right = x[1];
                //删除tem区间
                tree.remove(tem);
                sum -= tem[1] - tem[0] + 1;
            }
            sum += right - left + 1;
            tree.add(new int[]{left, right});
        }

        public int count() {
            return sum;
        }
    }


    static class CountIntervals_chaoshi {
        /**
         * 输入
         * ["CountIntervals", "add", "add", "count", "add", "count"]
         * [[], [2, 3], [7, 10], [], [5, 8], []]
         * 输出
         * [null, null, null, 6, null, 8]
         **/
        List<int[]> priorityQueue = null;
        int count = 0;

        public CountIntervals_chaoshi() {
            priorityQueue = new ArrayList<>();
        }

        public void add(int left, int right) {
            if (priorityQueue.isEmpty()) {
                priorityQueue.add(new int[]{left, right});
                count = right - left + 1;
            } else {
                priorityQueue.add(new int[]{left, right});
                count = 0;
                priorityQueue.sort((o1, o2) -> o1[0] - o2[0]);
                int preStart = priorityQueue.get(0)[0];
                List<int[]> result = new ArrayList<>();
                for (int i = 1; i < priorityQueue.size(); i++) {
                    int[] preElement = priorityQueue.get(i - 1);
                    int[] curElement = priorityQueue.get(i);
                    if (curElement[0] > preElement[1]) {
                        result.add(new int[]{preStart, preElement[1]});
                        count += preElement[1] - preStart + 1;
                        preStart = curElement[0];
                    } else {
                        curElement[1] = Math.max(preElement[1], curElement[1]);
                        priorityQueue.set(i - 1, curElement);
                    }
                }
                result.add(new int[]{preStart, priorityQueue.get(priorityQueue.size() - 1)[1]});
                count += priorityQueue.get(priorityQueue.size() - 1)[1] - preStart + 1;
                priorityQueue = new ArrayList<>(result);
            }
        }

        public int count() {
            return count;
        }
    }

    public int largestCombination(int[] candidates) {
        boolean[][] toBit = new boolean[candidates.length][28 + 1];
        for (int k = 0; k < candidates.length; k++) {
            for (int i = toBit[0].length - 1; i >= 0; i--) {
                toBit[k][i] = ((candidates[k] >> i) & 1) == 1;
            }
        }
        int maxOneCount = Integer.MIN_VALUE;
        for (int i = 0; i < toBit[0].length; i++) {
            int oneCount = 0;
            for (int j = 0; j < toBit.length; j++) {
                if (toBit[j][i]) {
                    oneCount++;
                }
            }
            maxOneCount = Math.max(maxOneCount, oneCount);
        }
        return maxOneCount;
    }


    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int before = bottom;
        int behind = top;
        int start = special.length - 1;
        int end = 0;
        for (int i = 0; i < special.length; i++) {
            if (special[i] >= before && special[i] <= behind) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        int max = Integer.MIN_VALUE;
        if (start > end) {
            return top - bottom + 1;
        }
        max = Math.max(special[start] - bottom, top - special[end]);
        for (int i = start; i <= end; i++) {
            int s = special[i];
            if (s >= before && s <= top) {
                max = Math.max(max, s - before - 1);
                before = s;
            }
        }
        return max;
    }


    public List<String> removeAnagrams(String[] words) {
        List<String> list = new ArrayList<>();
        String before = "";
        for (String word : words) {
            if (word.length() != before.length()) {
                list.add(word);
                before = word;
                continue;
            }
            if (isAnagrams(before, word)) {
                continue;
            }
            list.add(word);
            before = word;
        }
        return list;

    }

    private boolean isAnagrams(String before, String word) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < before.length(); i++) {
            char c = before.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < before.length(); i++) {
            char s = word.charAt(i);
            if (!map.containsKey(s) || map.get(s) == 0) {
                return false;
            }
            map.put(s, map.getOrDefault(s, 0) - 1);
        }
        return true;
    }

}
