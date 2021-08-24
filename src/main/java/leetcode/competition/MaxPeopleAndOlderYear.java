package leetcode.competition;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: Created by zhanghl
 */

/**
 * @Description: 给你一个二维整数数组 logs ，其中每个 logs[i] = [birthi, deathi] 表示第 i 个人的出生和死亡年份。
 * <p>
 * 年份 x 的 人口 定义为这一年期间活着的人的数目。第 i 个人被计入年份 x 的人口需要满足：x 在闭区间 [birthi, deathi - 1] 内。注意，人不应当计入他们死亡当年的人口中。
 * <p>
 * 返回 人口最多 且 最早 的年份。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：logs = [[1993,1999],[2000,2010]]
 * 输出：1993
 * 解释：人口最多为 1 ，而 1993 是人口为 1 的最早年份。
 * 示例 2：
 * <p>
 * 输入：logs = [[1950,1961],[1960,1971],[1970,1981]]
 * 输出：1960
 * 解释：
 * 人口最多为 2 ，分别出现在 1960 和 1970 。
 * 其中最早年份是 1960 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= logs.length <= 100
 * 1950 <= birthi < deathi <= 2050
 * @Param:
 */

public class MaxPeopleAndOlderYear {
    public static void main(String[] args) {
        int[][] logs =  {{1993,1999},{2000,2010}};
//        int[][] logs =  {{1950,1961},{1960,1971},{1970,1981}};
//        int[][] logs = {{1982, 1998}, {2013, 2042}, {2010, 2035}, {2022, 2050}, {2047, 2048}};
//        int[][] logs = {{2033, 2034}, {2039, 2047}, {1998, 2042}, {2047, 2048}, {2025, 2029}, {2005, 2044}, {1990, 1992}, {1952,
//                1956}, {1984, 2014}};
        System.out.println(new MaxPeopleAndOlderYear().maximumPopulation(logs));
    }

    public int maximumPopulation(int[][] logs) {
        int[] lenQujian = new int[2050 - 1950 + 1];
        for (int i = 0; i < logs.length; i++) {
            for (int j = logs[i][0]; j < logs[i][1]; j++) {
                lenQujian[j - 1950]++;
            }
        }
        int max = 0;
        int index = 0;
        for (int i = 0; i < lenQujian.length; i++) {
            if(lenQujian[i] > max){
                max = lenQujian[i];
                index = i;
            }
        }
        return index + 1950;
    }

    /**
    * @Description: 没通过啊，哎哎哎，不对！！！
    * @Param: [logs]
    */
    public int maximumPopulation1(int[][] logs) {
        if (logs == null || logs.length <= 0) {
            return -1;
        }
        int maxPeople = 0;
        int peopleNum = 0;
        int[] years = new int[2];
        Arrays.sort(logs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });
        for (int i = 0; i < logs.length; i++) {
            if (i == 0) {
                peopleNum = 1;
                maxPeople = 1;
                years[0] = logs[i][0];
                years[1] = logs[i][1] - 1;
                continue;
            }
            if (logs[i][0] > years[1]) {
                if (maxPeople == peopleNum) {
                    years[0] = logs[i - 1][0];
                    years[1] = logs[i - 1][1] - 1;
                }
                peopleNum = 1;
            } else {
                peopleNum++;
            }
            if (peopleNum > maxPeople) {
                maxPeople = peopleNum;
                years[0] = logs[i][0];
                years[1] = Math.min(logs[i][1] - 1, years[1]);
            }
        }
        return years[0];
    }
}
