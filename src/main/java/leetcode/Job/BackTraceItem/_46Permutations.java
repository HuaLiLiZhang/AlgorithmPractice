package leetcode.Job.BackTraceItem;

//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
// Related Topics 回溯算法 
// 👍 1343 👎 0


import java.util.ArrayList;
import java.util.List;

public class _46Permutations {
    public static void main(String[] args) {
        Solution solution = new _46Permutations().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null || nums.length <= 0) {
                return result;
            }
            getAllPermutation(nums, new ArrayList<Integer>(), result, new boolean[nums.length]);
            return result;

        }

        /**
        * @Description:
         * 每层都是从0开始搜索而不是startIndex
         * 需要used数组记录path里都放了哪些元素了
        * @Param: [nums, subPermutation, result, used]
        */
        private void getAllPermutation(int[] nums, ArrayList<Integer> subPermutation,
                                       List<List<Integer>> result, boolean[] used) {
            if (subPermutation.size() == nums.length) {
                result.add(new ArrayList<>(subPermutation));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) {
                    continue;
                }
                subPermutation.add(nums[i]);
                used[i] = true;
                getAllPermutation(nums, subPermutation, result, used);
                subPermutation.remove(subPermutation.size() - 1);
                used[i] = false;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}