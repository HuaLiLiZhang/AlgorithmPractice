package leetcode.middleProblem;

//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？ 
//
// 
//
// 网格中的障碍物和空位置分别用 1 和 0 来表示。 
//
// 
//
// 示例 1： 
//
// 
//输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
//输出：2
//解释：
//3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
// 
//
// 示例 2： 
//
// 
//输入：obstacleGrid = [[0,1],[0,0]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// m == obstacleGrid.length 
// n == obstacleGrid[i].length 
// 1 <= m, n <= 100 
// obstacleGrid[i][j] 为 0 或 1 
// 
// Related Topics 数组 动态规划 矩阵 
// 👍 641 👎 0


public class _63UniquePathsIi {
    public static void main(String[] args) {
        Solution solution = new _63UniquePathsIi().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //时间复杂度O(n * m) n m 分别为obstacleGrid 长度和宽度
        //空间复杂度O(n * m)
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if (obstacleGrid == null || obstacleGrid.length <= 0 || obstacleGrid[0].length <= 0) {
                return 0;
            }
            int row = obstacleGrid.length;
            int col = obstacleGrid[0].length;
            int[][] dp = new int[row][col];
            //对于第一行和第一列，如果中间出现的障碍物，那么障碍物后面就必然走不通了，所以dp数组的值为0
            for (int i = 0; i < col; i++) {
                if (obstacleGrid[0][i] == 1) {
                    break;
                }
                dp[0][i] = 1;
            }
            for (int i = 0; i < row; i++) {
                if (obstacleGrid[i][0] == 1) {
                    break;
                }
                dp[i][0] = 1;
            }
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    //对于中间出现的障碍物，那么此位置一定走不通，所以为0，跳过继续看其他位置
                    if (obstacleGrid[i][j] == 1) {
                        continue;
                    }
                    //其他位置的递归公式依然跟62题的无障碍的路径一样
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            return dp[row - 1][col - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}