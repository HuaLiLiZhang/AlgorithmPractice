package leetcode.Job.BackTraceItem;

//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 说明： 
//
// 
// 所有数字（包括目标数）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [10,1,2,7,6,1,5], target = 8,
//所求解集为:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,5,2,1,2], target = 5,
//所求解集为:
//[
//  [1,2,2],
//  [5]
//] 
// Related Topics 数组 回溯算法 
// 👍 563 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _40CombinationSumIi {
    public static void main(String[] args) {
        Solution solution = new _40CombinationSumIi().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            //这里进行剪枝，因为此时sum加后一个候选元素大于target的情况，就不需要在回溯了，
            //如果 sum + candidates[i] > target 就终止遍历
            for (int i = startIndex; i < candidates.length && (sum + candidates[i] <= target); i++) {

                //如果后一个元素与前一个元素相同，那么循环到下一个元素的时候，该元素作为此位置的组合已经被加入集合，所以要跳过
                //比如数组[1,1,2]，target=3，组合有：
                // 第一个元素选第一个1，第二个元素选第二个1，第三个元素选第三个2，总和大于3，不符合
                // 第一个元素选第一个1，第二个元素选第三个2，符合
                // 第一个元素选第二个1，第二个元素选第三个2，已经存在此组合。
                // 所以同一个位置上若后一个元素的值等于前一个元素的值，应该跳过，因为已经获得此组合
                if (i > startIndex && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                oneChoies.add(candidates[i]);
                //这里下一层回溯的开始位置，应该是i，不是startIndex，因为跟循环内部的参数有关，
                // 循环一次后再一次循环，i就变为startIndex+1, 所以此时应该回溯参数为i
                getAllCombinationOfSum(candidates, i + 1, sum + candidates[i], target, oneChoies, result); // 关键点:不用i+1
                // 了，表示可以重复读取当前的数
                oneChoies.remove(oneChoies.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}