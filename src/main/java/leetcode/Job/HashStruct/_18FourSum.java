package leetcode.Job.HashStruct;

//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
// d 的值与 target 相等？找出所有满足条件且不重复的四元组。 
//
// 注意：答案中不可以包含重复的四元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [], target = 0
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 200 
// -109 <= nums[i] <= 109 
// -109 <= target <= 109 
// 
// Related Topics 数组 哈希表 双指针 
// 👍 760 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _18FourSum {
    public static void main(String[] args) {
        Solution solution = new _18FourSum().new Solution();
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        System.out.println(solution.fourSum(nums1, 0));
        int[] nums2 = {-2, -1, -1, 1, 1, 2, 2};
        System.out.println(solution.fourSum(nums2, 0));
        int [] nums3 = {1,-2,-5,-4,-3,3,3,5};
        System.out.println(solution.fourSum(nums3, -11));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 四数之和，和三数之和是一个思路，都是使用双指针法, 基本解法就是在三数之和 的基础上再套一层for循环。
         * <p>
         * 但是有一些细节需要注意，例如：不要判断nums[k] > target 就返回了，三数之和 可以通过 nums[i] > 0 就返回了，因为 0 已经是确定的数了，四数之和这道题目 target是任意值。（大家亲自写代码就能感受出来）
         * <p>
         * 三数之和的双指针解法是一层for循环num[i]为确定值，然后循环内有left和right下表作为双指针，找到nums[i] + nums[left] + nums[right] == 0。
         * <p>
         * 四数之和的双指针解法是两层for循环nums[k] + nums[i]为确定值，依然是循环内有left和right下表作为双指针，找出nums[k] + nums[i] + nums[left] + nums[right] == target的情况，三数之和的时间复杂度是O(n^2)，四数之和的时间复杂度是O(n^3) 。
         * <p>
         * 那么一样的道理，五数之和、六数之和等等都采用这种解法。
         * <p>
         * 对于三数之和双指针法就是将原本暴力O(n^3)的解法，降为O(n^2)的解法，四数之和的双指针解法就是将原本暴力O(n^4)的解法，降为O(n^3)的解法。
         * @Param: [nums, target]
         */
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null || nums.length <= 0) {
                return result;
            }
            Arrays.sort(nums);
            //这个判断是一定不能加的，因为和为任意数，不是0，
            // target如果为负数，如-11， nums = [-2,-5,-4,-3,-2,1,3,3,5],但是有一个组合为[[-5, -4, -3, 1]]和为-11
            /*if (nums[0] > target) {
                return result;
            }*/
            for (int i = 0; i < nums.length - 3; i++) {
                //找到第一个元素，第一个元素不能重复
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                for (int j = i + 1; j < nums.length - 2; j++) {
                    //找到第二个元素，第二个元素不能重复
                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }
                    //第三个和第四个元素的和为target - nums[i] - nums[j]；那么开始找剩下元素的两数之和
                    int remains = target - nums[i] - nums[j];
                    int left = j + 1;
                    int right = nums.length - 1;
                    while (left < right) {
                        if (nums[left] + nums[right] == remains) {
                            result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                            //找到第三个元素，第三个元素不能重复
                            while (left < right && nums[left] == nums[left + 1]) {
                                left++;
                            }
                            //没有重复的话，left继续往前加1
                            left++;
                            //找到第四个元素，第四个元素不能重复
                            while (left < right && nums[right] == nums[right - 1]) {
                                right--;
                            }
                            //没有重复的话，right继续往后减1
                            right--;
                        } else if (nums[left] + nums[right] > remains) {
                            right--;
                        } else {
                            left++;
                        }
                    }
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}