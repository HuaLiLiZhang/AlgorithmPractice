package leetcode.Job.Array;

//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
//
// 你可以假设数组中无重复元素。 
//
// 示例 1: 
//
// 输入: [1,3,5,6], 5
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [1,3,5,6], 2
//输出: 1
// 
//
// 示例 3: 
//
// 输入: [1,3,5,6], 7
//输出: 4
// 
//
// 示例 4: 
//
// 输入: [1,3,5,6], 0
//输出: 0
// 
// Related Topics 数组 二分查找 
// 👍 834 👎 0


public class _35SearchInsertPosition {
    public static void main(String[] args) {
        Solution solution = new _35SearchInsertPosition().new Solution();
        int[] nums = {1, 3, 5, 6};
        System.out.println(solution.searchInsert(nums, 5));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 暴力解法，时间复杂度：O(n)，空间的复杂度O(1)
         * @Param: [nums, target]
         */
        public int searchInsert1(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                // 分别处理如下三种情况
                // 目标值在数组所有元素之前
                // 目标值等于数组中某一个元素
                // 目标值插入数组中的位置
                if (nums[i] >= target) { // 一旦发现大于或者等于target的num[i]，那么i就是我们要的结果
                    return i;
                }
            }
            //最后处理第四种情况，目标值在数组所有元素之后的情况
            return nums.length; // 如果target是最大的，或者 nums为空，则返回nums的长度
        }

        /**
         * @Description: 利用二分法，时间复杂度：O(logn), 此时是左闭右闭的区间
         * @Param: [nums, target]
         */
        public int searchInsert2(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            //此时区间是左闭右闭, 循环条件left <= right
            while (left <= right) {
                int middle = left + (right - left) / 2;
                if (nums[middle] > target) {
                    right = middle - 1; //此时right=middle-1，因为是右闭区间
                } else if (nums[middle] < target) {
                    left = middle + 1;
                } else {
                    //此时处理了 目标值等于数组中某一个元素的情况
                    return middle;
                }
            }
            //在处理其他三种情况：
            // 目标值在数组所有元素之前  [0, -1]
            // 目标值等于数组中某一个元素  return middle;
            // 目标值插入数组中的位置 [left, right]，return  right + 1 或者left
            // 目标值在数组所有元素之后的情况 [len, len -1]， return right + 1 或者left
            return left; //或者right + 1
        }

        /**
         * @Description: 利用二分法，时间复杂度：O(logn), 此时是左闭右开的区间
         * @Param: [nums, target]
         */
        public int searchInsert(int[] nums, int target) {
            int left = 0;
            int right = nums.length;
            //此时区间是左闭右开, 循环条件left < right
            while (left < right) {
                int middle = left + (right - left) / 2;
                if (nums[middle] > target) {
                    right = middle; //此时right=middle-1，因为是右闭区间
                } else if (nums[middle] < target) {
                    left = middle + 1;
                } else {
                    //此时处理了 目标值等于数组中某一个元素的情况
                    return middle;
                }
            }
            //在处理其他三种情况：
            // 目标值在数组所有元素之前  [0, 0]
            // 目标值等于数组中某一个元素  return middle;
            // 目标值插入数组中的位置 [left, right]，此时left==right, return  right或者left
            // 目标值在数组所有元素之后的情况 [len+1, len +1]， return right或者left
            return left;//或者left
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}