package leetcode.hot100;

//实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。 
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。 
//
// 必须 原地 修改，只允许使用额外常数空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[1,2,3]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,5]
//输出：[1,5,1]
// 
//
// 示例 4： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 
// 👍 936 👎 0


import java.util.Arrays;

public class _31NextPermutation {
    public static void main(String[] args) {
        Solution solution = new _31NextPermutation().new Solution();
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {3, 2, 1};
        int[] nums3 = {1, 5, 1};
        int[] nums4 = {1};
        int[] nums5 = {2, 3, 1};
        solution.nextPermutation(nums1);
        System.out.println(Arrays.toString(Arrays.stream(nums1).toArray()));
        solution.nextPermutation(nums2);
        System.out.println(Arrays.toString(Arrays.stream(nums2).toArray()));
        solution.nextPermutation(nums3);
        System.out.println(Arrays.toString(Arrays.stream(nums3).toArray()));
        solution.nextPermutation(nums4);
        System.out.println(Arrays.toString(Arrays.stream(nums4).toArray()));
        solution.nextPermutation(nums5);
        System.out.println(Arrays.toString(Arrays.stream(nums5).toArray()));

    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 方法一：两遍扫描
         * 思路及解法
         * <p>
         * 注意到下一个排列总是比当前排列要大，除非该排列已经是最大的排列。我们希望找到一种方法，能够找到一个大于当前序列的新序列，且变大的幅度尽可能小。具体地：
         * <p>
         * 我们需要将一个左边的「较小数」与一个右边的「较大数」交换，以能够让当前排列变大，从而得到下一个排列。
         * <p>
         * 同时我们要让这个「较小数」尽量靠右，而「较大数」尽可能小。当交换完成后，「较大数」右边的数需要按照升序重新排列。这样可以在保证新排列大于原来排列的情况下，使变大的幅度尽可能小。
         * 时间复杂度：O(N)，其中 N 为给定序列的长度。我们至多只需要扫描两次序列，以及进行一次反转操作。
         * <p>
         * 空间复杂度：O(1)，只需要常数的空间存放若干变量。
         * @Param: [nums]
         */
        public void nextPermutation(int[] nums) {
            int start = nums.length - 1;
            for (; start > 0; start--) {
                while (start > 0 && nums[start - 1] >= nums[start]) {
                    start--;
                }
                if (start == 0) {
                    break;
                }
                int letterSmaller = nums[start - 1];
                int end = nums.length - 1;
                while (nums[end] <= letterSmaller) {
                    end--;
                }
                swap(nums, start - 1, end);
                break;
            }
            reverse(nums, start);
        }

        private void reverse(int[] nums, int start) {
            int left = start;
            int right = nums.length - 1;
            while (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }

        private void swap(int[] nums, int left, int right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}