package leetcode.Job.BackTraceItem;

//给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是 2 。 
//
// 
//
// 示例： 
//
// 
//输入：[4, 6, 7, 7]
//输出：[[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
//
// 
//
// 提示： 
//
// 
// 给定数组的长度不会超过15。 
// 数组中的整数范围是 [-100,100]。 
// 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。 
// 
// Related Topics 深度优先搜索 
// 👍 282 👎 0


import java.util.ArrayList;
import java.util.List;

public class _491IncreasingSubsequences {
    public static void main(String[] args) {
        Solution solution = new _491IncreasingSubsequences().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 子序列的意思是不能调换数组顺序的 ，这里不是说的子集
         * @Param: [nums]
         */
        public List<List<Integer>> findSubsequences(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null || nums.length <= 0) {
                return result;
            }
            getAllSubIncressSequence(nums, 0, result, new ArrayList<>());
            return result;
        }

        private void getAllSubIncressSequence(int[] nums, int startIndex, List<List<Integer>> result,
                                              ArrayList<Integer> subSequence) {
            if (subSequence.size() >= 2) {
                result.add(new ArrayList<>(subSequence));
            }
            if (startIndex >= nums.length) {
                return;
            }
            //利用集合判断当前层的元素是否已经被使用。或者直接使用数组，因为元素值的大小范围为[-100, 100]
            //Set<Integer> set = new HashSet<>();
            boolean[] used = new boolean[201];
            for (int i = startIndex; i < nums.length; i++) {
                //例子：[1,2,3,4,5,6,7,8,9,10,1,1,1,1,1]
                //给定数组的长度不会超过15。
                // 数组中的整数范围是 [-100,100]。
                if (used[nums[i] + 100]) {
                    continue;
                }
                used[nums[i] + 100] = true; // 记录这个元素在本层用过了，本层后面不能再用了
                //这个错误的原因是，这个是基于数组已经排序的前提下，如果数组没有排序的话，就得使用一个数组记录是否该元素已经被使用
                /*if (i != startIndex && nums[i - 1] == nums[i]) {
                    continue;
                }*/
                if (!subSequence.isEmpty() && subSequence.get(subSequence.size() - 1) > nums[i]) {
                    continue;
                }
                subSequence.add(nums[i]);
                getAllSubIncressSequence(nums, i + 1, result, subSequence);
                subSequence.remove(subSequence.size() - 1);
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}