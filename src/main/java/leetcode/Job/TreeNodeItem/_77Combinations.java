package leetcode.Job.TreeNodeItem;

//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 569 👎 0


import java.util.ArrayList;
import java.util.List;

public class _77Combinations {
    public static void main(String[] args) {
        Solution solution = new _77Combinations().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * @Description: 要解决 n为100，k为50的情况，暴力写法需要嵌套50层for循环，那么回溯法就用递归来解决嵌套层数的问题。
         * 递归来做层叠嵌套（可以理解是开k层for循环），每一次的递归中嵌套一个for循环，那么递归就可以用于解决多层嵌套循环的问题了。
         * 此时递归的层数大家应该知道了，例如：n为100，k为50的情况下，就是递归50层。
         * @Param: [n, k]
         */
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> result = new ArrayList<>(); // 存放符合条件结果的集合
            List<Integer> list = new ArrayList<>(); // 用来存放符合条件结果
            if (k <= 0 || k > n) {
                return result;
            }
            getCombineResult(1, n, k, result, list);
            return result;
        }

        /**
         * @Description: 每次从集合中 选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围
         * 可以发现n相当于宽度，k相当于深度，每次到达叶子节点，就开始结果收集，叶子节点的条件就是收集的节点数等于k.
         * 函数除了n和k两个参数外，还有一个参数，代表从本层递归中，集合从哪里开始遍历，也就是start
         * @Param: [start, end, k, result, list]
         */
        private void getCombineResult(int start, int end, int k, List<List<Integer>> result, List<Integer> list) {
            if (list.size() == k) {
                result.add(new ArrayList<>(list));
                return;
            }
            for (int i = start; i <= end - (k - list.size()) + 1; i++) { //优化的地方：因为后面的数字已经筹不齐k个数字了
                list.add(i);
                //下一个开始应该是i+1，end不变
                getCombineResult(i + 1, end, k, result, list);
                list.remove(list.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}