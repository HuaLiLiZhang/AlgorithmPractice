package leetcode.Job.BackTraceItem;

//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 回溯算法 
// 👍 877 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _51NQueens {
    public static void main(String[] args) {
        Solution solution = new _51NQueens().new Solution();
        System.out.println(solution.solveNQueens(4));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<String>> res = new ArrayList<>();

        /**
         * @Description: n皇后问题：不能同行、不能同列，不能在对角，45度和135度
         * @Param: [n]
         */
        public List<List<String>> solveNQueens(int n) {
            //用chessboard存储n皇后的棋盘，判断某些位置是否已经存放了Q,并且输出可能结果
            char[][] chessboard = new char[n][n];
            for (char[] c : chessboard) {
                Arrays.fill(c, '.');
            }
            backTrack(n, 0, chessboard);
            return res;
        }


        /**
        * @Description: 找到n皇后的所有返回结果，那么不需要返回值的 ，因为符合一种情况就加入结果集
        * @Param: [n, row, chessboard]
        */
        public void backTrack(int n, int row, char[][] chessboard) {
            if (row == n) {
                res.add(Array2List(chessboard));
                return;
            }

            for (int col = 0; col < n; col++) {
                if (isValid(row, col, n, chessboard)) {
                    chessboard[row][col] = 'Q';
                    backTrack(n, row + 1, chessboard);
                    chessboard[row][col] = '.';
                }
            }

        }


        public List Array2List(char[][] chessboard) {
            List<String> list = new ArrayList<>();

            for (char[] c : chessboard) {
                list.add(String.copyValueOf(c));
            }
            return list;
        }


        public boolean isValid(int row, int col, int n, char[][] chessboard) {
            //行不用检查了，因为在单层搜索的过程中，每一层递归，只会选for循环（也就是同一行）里的一个元素，所以不用去重了。
            // 检查列，也就是行递增，列不变
            for (int i = 0; i < n; ++i) {
                if (chessboard[i][col] == 'Q') {
                    return false;
                }
            }

            // 检查45度对角线
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (chessboard[i][j] == 'Q') {
                    return false;
                }
            }

            // 检查135度对角线
            for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++) {
                if (chessboard[i][j] == 'Q') {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}