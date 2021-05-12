package leetcode.Job.BackTraceItem;

//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
//
//
// 示例 2：
//
//
//输入：nums = [0]
//输出：[[],[0]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10
//
//
//
// Related Topics 数组 回溯算法
// 👍 568 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _90SubsetsIi {
    public static void main(String[] args) {
        Solution solution = new _90SubsetsIi().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null || nums.length <= 0) {
                return result;
            }
            Arrays.sort(nums);
            getAllSubSetsWithDup(nums, 0, new ArrayList<>(), result);
            return result;
        }

        private void getAllSubSetsWithDup(int[] nums, int startIndex, ArrayList<Integer> subSet,
                                          List<List<Integer>> result) {
            result.add(new ArrayList<>(subSet));
            if (startIndex >= nums.length) {
                return;
            }
            for (int i = startIndex; i < nums.length; i++) {
                if (i != startIndex && nums[i - 1] == nums[i]) {
                    continue;
                }
                subSet.add(nums[i]);
                getAllSubSetsWithDup(nums, i + 1, subSet, result);
                subSet.remove(subSet.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}