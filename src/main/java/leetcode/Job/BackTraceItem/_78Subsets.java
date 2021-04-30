package leetcode.Job.BackTraceItem;

//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
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
// nums 中的所有元素 互不相同 
// 
// Related Topics 位运算 数组 回溯算法 
// 👍 1156 👎 0


import java.util.ArrayList;
import java.util.List;

public class _78Subsets {
    public static void main(String[] args) {
        Solution solution = new _78Subsets().new Solution();
        int[] nums = {1, 2, 3};
        solution.subsets(nums);
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
        * @Description: for循环确定开始位置，递归取下一个位置，每一次递归都取结果，由于元素不重复，因此不需要排序和去重
        * @Param: [nums]
        */
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null || nums.length <= 0) {
                return result;
            }
            getAllSubsets(nums, 0, result, new ArrayList<>());
            return result;
        }

        private void getAllSubsets(int[] nums, int start, List<List<Integer>> result, List<Integer> oneSubset) {
            if (start > nums.length) {
                return;
            }
            result.add(new ArrayList<>(oneSubset));// 收集子集
            for (int i = start; i < nums.length; i++) {
                int numVal = nums[i];
                oneSubset.add(numVal);
                getAllSubsets(nums, i + 1, result, oneSubset);
                oneSubset.remove(oneSubset.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}