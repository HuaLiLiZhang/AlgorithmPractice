package leetcode.competition;

import java.util.Arrays;

/**
 * TODO 类的实现描述
 *
 * @author zhanghuali
 * @version 1.0
 * @ClassName: _294_competition
 * @CreateTime: 2022/05/22 11:03:08
 * @Description:
 */
public class _294_competition {
    public static void main(String[] args) {
        _294_competition competition = new _294_competition();
        int[][] stockPrices3 = {{1, 1}, {499999999, 2}, {999999998, 3}, {1000000000, 5}};
        System.out.println(competition.minimumLines(stockPrices3));

        int[][] stockPrices2 = {{1, 1000000000}, {1000000000, 1000000000}, {999999999, 1}, {2, 999999999}};
        System.out.println(competition.minimumLines(stockPrices2));

        /*输入：stockPrices = [[1,7],[2,6],[3,5],[4,4],[5,4],[6,3],[7,2],[8,1]]
        输出：3*/
        int[][] stockPrices1 = {{1, 7}, {2, 6}, {3, 5}, {4, 4}, {5, 4}, {6, 3}, {7, 2}, {8, 1}};
        System.out.println(competition.minimumLines(stockPrices1));

        /*输入：stockPrices = [[3,4],[1,2],[7,8],[2,3]]
        输出：1*/
        int[][] stockPrices = {{3, 4}, {1, 2}, {7, 8}, {2, 3}};
        System.out.println(competition.minimumLines(stockPrices));

       /* 输入：capacity = [2,3,4,5], rocks = [1,2,4,4], additionalRocks = 2
        输出：3

        输入：capacity = [10,2,2], rocks = [2,2,0], additionalRocks = 100
        输出：3*/
        /*int[] capacity = {2, 3, 4, 5};
        int[] rocks = {1, 2, 4, 4};
        int additionalRocks = 2;

        int[] capacity1 = {10, 2, 2};
        int[] rocks1 = {2, 2, 0};
        int additionalRocks1 = 100;

        System.out.println(competition.maximumBags(capacity, rocks, additionalRocks));
        System.out.println(competition.maximumBags(capacity1, rocks1, additionalRocks1));


        String s = "foobar";
        char letter = 'o';
        System.out.println(competition.percentageLetter(s, letter));
        String s1 = "jjjj";
        char letter1 = 'k';
        System.out.println(competition.percentageLetter(s1, letter1));*/
    }

    /*public int totalStrength(int[] strength) {

    }*/

    public int minimumLines(int[][] stockPrices) {
        if (stockPrices.length <= 1) {
            return 0;
        }
        if (stockPrices.length <= 2) {
            return 1;
        }
        Arrays.sort(stockPrices, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        });
        // 判断三点是否在一条直线上
        int count = 1;
        for (int i = 2; i < stockPrices.length; i++) {
            long x1 = (long) stockPrices[i][0] - stockPrices[i - 1][0];
            long y1 = (long) stockPrices[i][1] - stockPrices[i - 1][1];
            long x2 = (long) stockPrices[i][0] - stockPrices[i - 2][0];
            long y2 = (long) stockPrices[i][1] - stockPrices[i - 2][1];
            // 判断i-2,i-1,i三点是否同线
            if (x1 * y2 != x2 * y1) {
                count++;
            }
        }
        return count;
    }


    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int[] need = new int[capacity.length];
        int count = 0;
        for (int i = 0; i < capacity.length; i++) {
            need[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(need);
        for (int i = 0; i < need.length; i++) {
            if (additionalRocks >= need[i]) {
                additionalRocks -= need[i];
                count++;
            }
        }
        return count;
    }


    public int percentageLetter(String s, char letter) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == letter) {
                count++;
            }
        }
        return count * 100 / s.length();
    }


}
