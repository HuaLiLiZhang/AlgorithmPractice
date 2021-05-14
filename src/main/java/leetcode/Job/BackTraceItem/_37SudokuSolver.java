package leetcode.Job.BackTraceItem;

//编写一个程序，通过填充空格来解决数独问题。 
//
// 数独的解法需 遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 
//
// 
// 
// 
// 示例： 
//
// 
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5","."
//,".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".","."
//,"3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"
//],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],["
//.",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"
//],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["
//4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","
//6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","
//5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
//
//
// 
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 题目数据 保证 输入数独仅有一个解 
// 
// 
// 
// 
// Related Topics 哈希表 回溯算法 
// 👍 836 👎 0


public class _37SudokuSolver {
    public static void main(String[] args) {
        Solution solution = new _37SudokuSolver().new Solution();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        solution.solveSudoku(board);
        System.out.println();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void solveSudoku(char[][] board) {
            if (board == null || board.length <= 0) {
                return;
            }
            backTrackingSolveSuduKu(board);
        }

        /**
        * @Description: 解数独是不用枚举所有情况，只需要有一种结果就可以返回，所以需要返回值
         * 因为解数独找到一个符合的条件（就在树的叶子节点上）立刻就返回，相当于找从根节点到叶子节点一条唯一路径，所以需要使用bool返回值
        * @Param: [board]
        */
        private boolean backTrackingSolveSuduKu(char[][] board) {
            //「一个for循环遍历棋盘的行，一个for循环遍历棋盘的列，
            // 一行一列确定下来之后，递归遍历这个位置放9个数字的可能性！」
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    if (board[row][col] == '.') { // 跳过原始数字
                        for (char i = '1'; i <= '9'; i++) {
                            if (isValidSudoku(row, col, i, board)) { // (row, col) 这个位置放i是否合适
                                board[row][col] = i;
                                if (backTrackingSolveSuduKu(board)) { // 如果找到合适一组立刻返回
                                    return true;
                                }
                                board[row][col] = '.';
                            }
                        }
                        // 9个数都试完了，都不行，那么就返回false
                        return false;
                        // 因为如果一行一列确定下来了，这里尝试了9个数都不行，说明这个棋盘找不到解决数独问题的解！
                        // 那么会直接返回， 「这也就是为什么没有终止条件也不会永远填不满棋盘而无限递归下去！」
                    }
                }
            }
            // 遍历完没有返回false，说明找到了合适棋盘位置了
            return true;
        }


        /**
         * 判断棋盘是否合法有如下三个维度:
         * 同行是否重复
         * 同列是否重复
         * 9宫格里是否重复
         */
        private boolean isValidSudoku(int row, int col, char val, char[][] board) {
            // 同行是否重复
            for (int i = 0; i < 9; i++) {
                if (board[row][i] == val) {
                    return false;
                }
            }
            // 同列是否重复
            for (int j = 0; j < 9; j++) {
                if (board[j][col] == val) {
                    return false;
                }
            }
            // 9宫格里是否重复
            int startRow = (row / 3) * 3;
            int startCol = (col / 3) * 3;
            for (int i = startRow; i < startRow + 3; i++) {
                for (int j = startCol; j < startCol + 3; j++) {
                    if (board[i][j] == val) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}