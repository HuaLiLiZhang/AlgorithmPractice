package leetcode.competition;

import java.util.*;

/**
 * @author: Created by zhanghl
 */
public class MostCloserHome {
    /**
     * @Description: 5733. 最近的房间 显示英文描述
     * 通过的用户数289
     * 尝试过的用户数866
     * 用户总通过次数317
     * 用户总提交次数2244
     * 题目难度Hard
     * 一个酒店里有 n 个房间，这些房间用二维整数数组 rooms 表示，其中 rooms[i] = [roomIdi, sizei] 表示有一个房间号为 roomIdi 的房间且它的面积为 sizei 。每一个房间号 roomIdi 保证是 独一无二 的。
     * <p>
     * 同时给你 k 个查询，用二维数组 queries 表示，其中 queries[j] = [preferredj, minSizej] 。第 j 个查询的答案是满足如下条件的房间 id ：
     * <p>
     * 房间的面积 至少 为 minSizej ，且
     * abs(id - preferredj) 的值 最小 ，其中 abs(x) 是 x 的绝对值。
     * 如果差的绝对值有 相等 的，选择 最小 的 id 。如果 没有满足条件的房间 ，答案为 -1 。
     * <p>
     * 请你返回长度为 k 的数组 answer ，其中 answer[j] 为第 j 个查询的结果。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：rooms = [[2,2],[1,2],[3,2]], queries = [[3,1],[3,3],[5,2]]
     * 输出：[3,-1,3]
     * 解释：查询的答案如下：
     * 查询 [3,1] ：房间 3 的面积为 2 ，大于等于 1 ，且号码是最接近 3 的，为 abs(3 - 3) = 0 ，所以答案为 3 。
     * 查询 [3,3] ：没有房间的面积至少为 3 ，所以答案为 -1 。
     * 查询 [5,2] ：房间 3 的面积为 2 ，大于等于 2 ，且号码是最接近 5 的，为 abs(3 - 5) = 2 ，所以答案为 3 。
     * 示例 2：
     * <p>
     * 输入：rooms = [[1,4],[2,3],[3,5],[4,1],[5,2]], queries = [[2,3],[2,4],[2,5]]
     * 输出：[2,1,3]
     * 解释：查询的答案如下：
     * 查询 [2,3] ：房间 2 的面积为 3 ，大于等于 3 ，且号码是最接近的，为 abs(2 - 2) = 0 ，所以答案为 2 。
     * 查询 [2,4] ：房间 1 和 3 的面积都至少为 4 ，答案为 1 因为它房间编号更小。
     * 查询 [2,5] ：房间 3 是唯一面积大于等于 5 的，所以答案为 3 。
     * <p>
     * <p>
     * 提示：
     * <p>
     * n == rooms.length
     * 1 <= n <= 105
     * k == queries.length
     * 1 <= k <= 104
     * 1 <= roomIdi, preferredj <= 107
     * 1 <= sizei, minSizej <= 107
     * @Param:
     */
    public static void main(String[] args) {
        int[][] rooms = {{1, 4}, {2, 3}, {3, 5}, {4, 1}, {5, 2}};
//        int[][] rooms = {{2, 2}, {1, 2}, {3, 2}};
        int[][] queries = {{2, 3}, {2, 4}, {2, 5}};
//        int[][] queries = {{3, 1}, {3, 3}, {5, 2}};
        System.out.println(Arrays.toString(new MostCloserHome().closestRoom(rooms, queries)));
    }

    public int[] closestRoom(int[][] rooms, int[][] queries) {
        if (queries == null || queries.length == 0 && queries[0].length == 0) {
            return null;
        }
        int[] result = new int[queries.length];
        ArrayList<Integer> arr = new ArrayList<>();
        Arrays.sort(rooms, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o2[0] - o1[0];
                }
                return o2[1] - o1[1]; //从大到小排序
            }
        });
        Arrays.sort(queries, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o2[0] - o1[0];
                }
                return o2[1] - o1[1]; //从大到小排序
            }
        });
        for (int i = 0; i < queries.length; i++) {
            for (int j = 0; j < rooms.length; j++) {
                if (queries[i][1] <= rooms[j][1]) {
                    arr.add(rooms[j][0]);
                } else {
                    break;
                }
            }
            int closetRoomId = getHalfSort(arr, queries[i][0]);
            result[i] = closetRoomId;
            arr.clear();
        }
        return result;
    }

    private int getHalfSort(ArrayList<Integer> priorQueueId, int preferId) {
        if (priorQueueId.size() == 0) {
            return -1;
        }
        Collections.sort(priorQueueId);
        if (preferId > priorQueueId.get(priorQueueId.size() - 1)) {
            return priorQueueId.get(priorQueueId.size() - 1);
        }
        if (preferId < priorQueueId.get(0)) {
            return priorQueueId.get(0);
        }
        if (priorQueueId.contains(preferId)) {
            return preferId;
        }
        int start = 0;
        int end = priorQueueId.size();
        while (start < end) {
            int mid = start + (end - start) / 2;
            int temp = priorQueueId.get(mid);
            if (temp == preferId) {
                return temp;
            }
            if (temp > preferId) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if (start >= 0 && start < priorQueueId.size()) {
            return priorQueueId.get(start);
        }
        return -1;
    }

    public int[] closestRoom1(int[][] rooms, int[][] queries) {
        if (queries == null || queries.length == 0 && queries[0].length == 0) {
            return null;
        }
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = Integer.MAX_VALUE;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < rooms.length; j++) {
                if (queries[i][1] <= rooms[j][1]) {
                    if (min > Math.abs(queries[i][0] - rooms[j][0])) {
                        min = Math.abs(queries[i][0] - rooms[j][0]);
                        idx = rooms[j][0];
                    } else if (min == Math.abs(queries[i][0] - rooms[j][0])) {
                        if (rooms[j][0] < idx) {
                            idx = rooms[j][0];
                        }
                    }
                }
            }
            result[i] = idx == Integer.MAX_VALUE ? -1 : idx;
        }
        return result;
    }
}
