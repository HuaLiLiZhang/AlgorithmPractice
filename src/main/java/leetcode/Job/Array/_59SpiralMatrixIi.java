package leetcode.Job.Array;

//给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：[[1,2,3],[8,9,4],[7,6,5]]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 
// Related Topics 数组 
// 👍 311 👎 0


public class _59SpiralMatrixIi {
    public static void main(String[] args) {
        Solution solution = new _59SpiralMatrixIi().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
        * @Description: 生成一个 n×n 空矩阵 mat，随后模拟整个向内环绕的填入过程：
         * 定义当前左右上下边界 l,r,t,b，初始值 num = 1，迭代终止值 tar = n * n；
         * 当 num <= tar 时，始终按照 从左到右 从上到下 从右到左 从下到上 填入顺序循环，每次填入后：
         * 执行 num += 1：得到下一个需要填入的数字；
         * 更新边界：例如从左到右填完后，上边界 t += 1，相当于上边界向内缩 1。
         * 使用num <= tar而不是l < r || t < b作为迭代条件，是为了解决当n为奇数时，矩阵中心数字无法在迭代过程中被填充的问题。
         *
         *
        * @Param: [n]
        */
        public int[][] generateMatrix(int n) {
            int l = 0;
            int r = n - 1;
            int t = 0;
            int b = n - 1;
            int num = 1;
            int[][] matrix = new int[n][n];
            while (num <= n * n) {
                for (int i = l; i <= r; i++) {
                    matrix[t][i] = num++;
                }
                t++;
                for (int i = t; i <= b; i++) {
                    matrix[i][r] = num++;
                }
                r--;
                for (int i = r; i >= l; i--) {
                    matrix[b][i] = num++;
                }
                b--;
                for (int i = b; i >= t; i--) {
                    matrix[i][l] = num++;
                }
                l++;
            }
            return matrix;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}