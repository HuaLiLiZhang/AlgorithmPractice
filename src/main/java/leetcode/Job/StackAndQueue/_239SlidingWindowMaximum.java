package leetcode.Job.StackAndQueue;

//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回滑动窗口中的最大值。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,-1], k = 1
//输出：[1,-1]
// 
//
// 示例 4： 
//
// 
//输入：nums = [9,11], k = 2
//输出：[11]
// 
//
// 示例 5： 
//
// 
//输入：nums = [4,-2], k = 2
//输出：[4] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// -104 <= nums[i] <= 104 
// 1 <= k <= nums.length 
// 
// Related Topics 堆 Sliding Window 
// 👍 890 👎 0


import java.util.Deque;
import java.util.LinkedList;

public class _239SlidingWindowMaximum {
    public static void main(String[] args) {
        Solution solution = new _239SlidingWindowMaximum().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
        * @Description: 遍历数组，将 数 存放在双向队列中，并用 L,R 来标记窗口的左边界和右边界。
         * 队列中保存的并不是真的 数，而是该数值对应的数组下标位置，并且数组中的数要从大到小排序。
         * 如果当前遍历的数比队尾的值大，则需要弹出队尾值，直到队列重新满足从大到小的要求。
         * 刚开始遍历时，L 和 R 都为 0，有一个形成窗口的过程，此过程没有最大值，L 不动，R 向右移。
         * 当窗口大小形成时，L 和 R 一起向右移，每次移动时，判断队首的值的数组下标是否在 [L,R] 中，
         * 如果不在则需要弹出队首的值，当前窗口的最大值即为队首的数。
         *
        * @Param: [nums, k]
        */
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length < 2) {
                return nums;
            }
            int[] maxArrs = new int[nums.length - k + 1];
            Deque<Integer> queue = new LinkedList<>();//双端队列存储的是位置
            for (int i = 0; i < k; i++) {
                while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                    queue.pollLast();
                }
                queue.offerLast(i);
            }
            maxArrs[0] = nums[queue.peekFirst()];
            for (int i = k; i < nums.length; i++) {
                while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                    queue.pollLast();
                }
                queue.offerLast(i);
                while (queue.peekFirst() <= (i - k)) {
                    queue.pollFirst();
                }
                maxArrs[i - k + 1] = nums[queue.peekFirst()];
            }
            return maxArrs;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}