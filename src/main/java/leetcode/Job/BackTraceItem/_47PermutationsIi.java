package leetcode.Job.BackTraceItem;

//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 回溯算法 
// 👍 696 👎 0


import java.util.*;

public class _47PermutationsIi {
    public static void main(String[] args) {
        Solution solution = new _47PermutationsIi().new Solution();
        int[] nums = {1, 1, 2};
        System.out.println(solution.permuteUnique(nums));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {

            List<List<Integer>> result = new ArrayList<>();
            if (nums == null || nums.length <= 0) {
                return result;
            }
            Arrays.sort(nums);
            getAllPermutation(nums, new ArrayList<Integer>(), result, new boolean[nums.length]);
            return result;
        }

        /**
         * @Description: 每层都是从0开始搜索而不是startIndex
         * 需要used数组记录path里都放了哪些元素了
         * @Param: [nums, subPermutation, result, used]
         */
        private void getAllPermutation(int[] nums, ArrayList<Integer> subPermutation,
                                       List<List<Integer>> result, boolean[] isUsed) {
            if (subPermutation.size() == nums.length) {
                result.add(new ArrayList<>(subPermutation));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                //说明同一树层，上一个元素跟当前元素相等，并且isUsed[i - 1]==false，说明上一层已经有了这种情况，则直接跳过
                if (i != 0 && nums[i - 1] == nums[i] && !isUsed[i - 1]) {
                    continue;
                }
                //说明同一树支nums[i]使用过，跳过
                if (isUsed[i]) {
                    continue;
                }
                subPermutation.add(nums[i]);
                isUsed[i] = true;
                getAllPermutation(nums, subPermutation, result, isUsed);
                subPermutation.remove(subPermutation.size() - 1);
                isUsed[i] = false;
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}