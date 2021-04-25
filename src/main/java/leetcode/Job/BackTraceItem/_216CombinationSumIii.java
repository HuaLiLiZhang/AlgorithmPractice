package leetcode.Job.BackTraceItem;

//找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。 
//
// 说明： 
//
// 
// 所有数字都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: k = 3, n = 7
//输出: [[1,2,4]]
// 
//
// 示例 2: 
//
// 输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
// 
// Related Topics 数组 回溯算法 
// 👍 296 👎 0


import java.util.ArrayList;
import java.util.List;

public class _216CombinationSumIii {
    public static void main(String[] args) {
        Solution solution = new _216CombinationSumIii().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 可以使用1-9的任意数字，不重复组成的组合
         * k个数字组成的和为n的组合
         * @Param: [k, n]
         */
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> oneChoice = new ArrayList<>();
            if (n <= 0) {
                return result;
            }
            getCombinationSum(k, n, 1, 0, result, oneChoice);
            return result;
        }

        private void getCombinationSum(int k, int n, int start, int sum, List<List<Integer>> result,
                                       List<Integer> oneChoice) {
            if (k == oneChoice.size()) {
                if (sum == n) {
                    //如果总和sum等于n,那么加入结果集result
                    result.add(new ArrayList<>(oneChoice));
                }
                //如果不等于，直接返回，不满足，不继续递归，也不会多添加元素，所以也不会出现oneChoice.size大于k的情况
                //这里也减少了递归的层数，进行了剪枝。
                return;
            }


            for (int i = start; i <= 9; i++) { //这里也可以优化一下：如果剩下的元素不够组成K个数了，那么不需要搜索了。
                // for(int i = start; i <= 9 - (k -oneChoice.size()) + 1; i++)
                oneChoice.add(i);
                getCombinationSum(k, n, i + 1, sum + i, result, oneChoice);
                oneChoice.remove(oneChoice.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}