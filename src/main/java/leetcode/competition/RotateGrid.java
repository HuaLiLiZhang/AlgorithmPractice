package leetcode.competition;

import java.util.Arrays;

/**
 * @author: Created by zhanghl
 */
public class RotateGrid {
    public static void main(String[] args) {
//        输入：grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
//        输出：[[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
//        解释：上图展示了矩阵在执行循环轮转操作时每一步的状态。
//        int[][] nums = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int k = 2;
//        [[10,1,4,8],[6,6,3,10],[7,4,7,10],[1,10,6,1],[2,1,1,10],[3,8,9,2],[7,1,10,10],[7,1,4,9],[2,2,4,2],[10,7,5,10]]
//        1
        int[][] nums = {{10, 1, 4, 8}, {6, 6, 3, 10}, {7, 4, 7, 10}, {1, 10, 6, 1}, {2, 1, 1, 10}, {3, 8, 9, 2}, {7, 1, 10, 10}, {7, 1, 4, 9}, {2, 2, 4, 2}, {10, 7, 5, 10}};
        k = 1;
        int[][] result = new RotateGrid().rotateGrid(nums, k);
        System.out.println();
    }

    public int[][] rotateGrid(int[][] grid, int k) {
        if (grid == null) {
            return null;
        }
        int left = 0;
        int right = grid[0].length - 1;
        int floor = 0;
        int bottom = grid.length - 1;
        while (left <= right && floor <= bottom) {
            int len = (right - left + 1) * 2 + (bottom - floor - 1) * 2;
            int offset = k % len;
            if (offset != 0) {
                int[] values = new int[len * 2];
                int j = 0;
                for (int i = right; i >= left; i--) {
                    values[j++] = grid[floor][i];
                }
                for (int i = floor + 1; i <= bottom; i++) {
                    values[j++] = grid[i][left];
                }
                for (int i = left + 1; i <= right; i++) {
                    values[j++] = grid[bottom][i];
                }
                for (int i = bottom - 1; i > floor; i--) {
                    values[j++] = grid[i][right];
                }
                for (; j < values.length; j++) {
                    values[j] = values[j - len];
                }


                int start = len - offset;
                for (int i = right; i >= left; i--) {
                    grid[floor][i] = values[start++];
                }
                for (int i = floor + 1; i <= bottom; i++) {
                    grid[i][left] = values[start++];
                }
                for (int i = left + 1; i <= right; i++) {
                    grid[bottom][i] = values[start++];
                }
                for (int i = bottom - 1; i > floor; i--) {
                    grid[i][right] = values[start++];
                }
            }
            left++;
            right--;
            floor++;
            bottom--;
        }
        return grid;
    }
}
