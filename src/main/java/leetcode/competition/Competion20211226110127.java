package leetcode.competition;

import java.util.*;

/**
 * 反转两次的数字3
 * 执行所有后缀指令4
 * 相同元素的间隔之和
 *
 * @author zhanghuali
 * @version 1.0
 * @ClassName: IsSamefterReversal
 * @CreateTime: 2021/12/26 10:53:21
 * @Description:
 */
public class Competion20211226110127 {
    public boolean isSameAfterReversals(int num) {
        if (num == 0) {
            return true;
        }
        int last = num % 10;
        if (last != 0) {
            return true;
        }
        return false;
    }

    /*public static void main(String[] args) {
        int n = 3;
        int[] startPos = {0, 1};
        String s = "RRDDLU";
        System.out.println(Arrays.toString(new Competion20211226110127().executeInstructions(n, startPos, s)));
    }*/

    public int[] executeInstructions(int n, int[] startPos, String s) {
        if (s == null || s.length() <= 0) {
            return new int[0];
        }
        int[] result = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int count = 0;
            int row = startPos[0];
            int col = startPos[1];
            for (int j = i; j < s.length(); j++) {
                char direction = s.charAt(j);
                if (direction == 'R') {
                    col++;
                }
                if (direction == 'L') {
                    col--;
                }
                if (direction == 'U') {
                    row--;
                }
                if (direction == 'D') {
                    row++;
                }
                if (row >= 0 && row < n && col >= 0 && col < n) {
                    count++;
                } else {
                    break;
                }
            }
            result[i] = count;
        }
        return result;
    }

    public static void main(String[] args) {
        //输入：arr = [2,1,3,1,2,3,3]
        //输出：[4,2,7,2,4,4,5]
        //输入：arr = [10,5,10,10]
        //输出：[5,0,3,4]
        //int[] arr = {2, 1, 3, 1, 2, 3, 3};
        int[] arr = {10, 5, 10, 10};
        System.out.println(Arrays.toString(new Competion20211226110127().getDistances(arr)));
    }

    public long[] getDistances(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return new long[0];
        }
        long[] result = new long[arr.length];

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            if (map.containsKey(key)) {
                map.get(key).add(i);
                continue;
            }
            List<Integer> va = new ArrayList<Integer>();
            va.add(i);
            map.put(key, va);
        }

        for (Integer key : map.keySet()) {
            List<Integer> list = map.get(key);
            // 计算差值
            if (list.size() == 1) {
                result[list.get(0)] = 0;
            } else {
                // 定义两个数组
                int m = list.size();
                long[] headToEnd = new long[m - 1];
                long[] endToHead = new long[m - 1];

                for (int j = 0; j < m - 1; ++j) {
                    headToEnd[j] = list.get(j + 1) - list.get(0);
                    endToHead[j] = list.get(m - 1) - list.get(j);
                }

                // 对headToEnd 和 endToHead 求取前缀和
                long[] headToEndSum = new long[m - 1];
                headToEndSum[0] = headToEnd[0];
                long[] endToHeadSum = new long[m - 1];
                endToHeadSum[m - 2] = endToHead[m - 2];
                for (int j = 1; j < m - 1; ++j) {
                    headToEndSum[j] = headToEndSum[j - 1] + headToEnd[j];
                    endToHeadSum[m - 2 - j] = endToHeadSum[m - 1 - j] + endToHead[m - 2 - j];
                }
                // 进行赋值
                for (int j = 0; j < m; ++j) {
                    long from = (j == 0) ? headToEndSum[m - 2] : (headToEndSum[m - 2] - headToEndSum[j - 1]) - (list.get(j) - list.get(0)) * (long) (m - 1 - j);
                    long end = (j == m - 1) ? endToHeadSum[0] : (endToHeadSum[0] - endToHeadSum[j]) - (list.get(m - 1) - list.get(j)) * (long) j;
                    result[list.get(j)] = from + end;
                }
            }
        }
        return result;
    }

}
