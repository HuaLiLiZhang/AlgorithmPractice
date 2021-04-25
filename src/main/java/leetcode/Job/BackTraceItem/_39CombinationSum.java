package leetcode.Job.BackTraceItem;

//给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的数字可以无限制重复被选取。 
//
// 说明： 
//
// 
// 所有数字（包括 target）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1： 
//
// 输入：candidates = [2,3,6,7], target = 7,
//所求解集为：
//[
//  [7],
//  [2,2,3]
//]
// 
//
// 示例 2： 
//
// 输入：candidates = [2,3,5], target = 8,
//所求解集为：
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//] 
//
// 
//
// 提示： 
//
// 
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// candidate 中的每个元素都是独一无二的。 
// 1 <= target <= 500 
// 
// Related Topics 数组 回溯算法 
// 👍 1315 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _39CombinationSum {
    public static void main(String[] args) {
        Solution solution = new _39CombinationSum().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> oneChoies = new ArrayList<>();
            if (target <= 0 || candidates == null || candidates.length == 0) {
                return result;
            }
            //首先要对数组进行排序
            Arrays.sort(candidates);
            getAllCombinationOfSum(candidates, 0, 0, target, oneChoies, result);
            return result;
        }

        private void getAllCombinationOfSum(int[] candidates, int startIndex, int sum, int target,
                                            List<Integer> oneChoies,
                                            List<List<Integer>> result) {
            //循环条件已经做了大于target的情况，这里就不需要判断返回了。少了一层递归深度
            /*if (sum > target) {
                return;
            }*/
            if (sum == target) {
                result.add(new ArrayList<>(oneChoies));
                return;
            }
            for (int i = startIndex; i < candidates.length && (sum + candidates[i] <= target); i++) {
                //这里进行剪枝，因为此时sum加后一个候选元素大于target的情况，就不需要在回溯了，
                //如果 sum + candidates[i] > target 就终止遍历
                oneChoies.add(candidates[i]);
                //这里下一层回溯的开始位置，应该是i，不是startIndex，因为跟循环内部的参数有关，
                // 循环一次后再一次循环，i就变为startIndex+1, 所以此时应该回溯参数为i
                getAllCombinationOfSum(candidates, i, sum + candidates[i], target, oneChoies, result); // 关键点:不用i+1了，表示可以重复读取当前的数
                oneChoies.remove(oneChoies.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}