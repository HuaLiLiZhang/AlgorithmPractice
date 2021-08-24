package leetcode.meeting;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Created by zhanghl
 */
public class NumIsland {
    public static void main(String[] args) {

    }

    //dfs
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length <= 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int numIslands = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == '1') {
                    numIslands++;
                    dfs(grid, r, c);
                }
            }
        }
        return numIslands;
    }

    /**
     * @Description: 我们可以将二维网格看成一个无向图，竖直或水平相邻的 1之间有边相连。
     * 为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为 1，则以其为起始节点开始进行深度优先搜索。
     * 在深度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。
     * 最终岛屿的数量就是我们进行深度优先搜索的次数。
     * //复杂度分析
     * //
     * //时间复杂度：O(MN)，其中 M 和 N 分别为行数和列数。
     * //空间复杂度：O(MN)，在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到 MN。
     * @Param: [grid, r, c]
     */
    private static void dfs(char[][] grid, int r, int c) {
        int row = grid.length;
        int col = grid[0].length;
        if (r < 0 || c < 0 || r >= row || c >= col || grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    //Bfs
    /**
    * @Description:
     * BFS思路：遇到为1的节点，将其周围的节点以及周围节点作为中心节点进行四周搜索，搜索的节点放入队列中，
     * 依次取出，然后判断其周围节点是否为'1'，为1将节点加入队列，并且设置数组此节点的位置为'0',
     * 继续搜索至队列为空，则找到了一个岛屿。
     * 时间复杂度：O(MN)，其中 M 和 N 分别为行数和列数。
     * 空间复杂度：O(min(M, N))，在最坏情况下，整个网格均为陆地, 队列的大小可以达到 min(M,N)。
    * @Param: [grid]
    */
    public static int getNumIsland(char[][] grid) {
        if (grid == null || grid.length <= 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int numIsland = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == '1') {
                    numIsland++;
                    grid[r][c] = '0';
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(r * col + c);
                    while (!queue.isEmpty()) {
                        int id = queue.remove();
                        int colT = id % col;
                        int rowT = id / col;
                        if ((rowT - 1) >= 0 && grid[rowT - 1][colT] == '1') {
                            queue.add((rowT - 1) * col + colT);
                            grid[rowT - 1][colT] = '0';
                        }
                        if ((rowT + 1) < row && grid[rowT + 1][colT] == '1') {
                            queue.add((rowT + 1) * col + colT);
                            grid[rowT + 1][colT] = '0';
                        }
                        if ((colT - 1) >= 0 && grid[rowT][colT - 1] == '1') {
                            queue.add((rowT * col + colT - 1));
                            grid[rowT][colT - 1] = '0';
                        }
                        if ((colT + 1) < col && grid[rowT][colT + 1] == '1') {
                            queue.add((rowT * col + colT + 1));
                            grid[rowT][colT + 1] = '0';
                        }
                    }
                }
            }
        }
        return numIsland;
    }
}
