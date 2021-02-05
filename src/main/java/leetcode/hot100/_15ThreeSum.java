//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 
// 👍 2937 👎 0


package leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15ThreeSum {
    public static void main(String[] args) {
        Solution solution = new _15ThreeSum().new Solution();
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        int[] nums2 = {};
        int[] nums3 = {0};
        System.out.println(solution.threeSum(nums1));
        System.out.println(solution.threeSum(nums2));
        System.out.println(solution.threeSum(nums3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<Integer>> threeSum(int[] nums) {// 总时间复杂度：O(n^2)
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            if (nums == null || nums.length <= 2) {
                return ans;
            }

            Arrays.sort(nums); // O(nlogn)

            for (int i = 0; i < nums.length - 2; i++) { // O(n^2)
                if (nums[i] > 0) {
                    break; // 第一个数大于 0，后面的数都比它大，肯定不成立了
                }
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue; // 去掉重复情况
                }
                int target = -nums[i];
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        ans.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[left], nums[right])));

                        // 现在要增加 left，减小 right，但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3], i = 0, left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                        left++;
                        right--; // 首先无论如何先要进行加减操作
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (nums[left] + nums[right] < target) {
                        left++;
                    } else {  // nums[left] + nums[right] > target
                        right--;
                    }
                }
            }
            return ans;
        }

        /**
         * @Description: 其实第三重循环依然可以优化 ，因为第三重循环在确定了a和b的情况下，因为a+b+c=0，那么c就是唯一的；
         * 如果我们固定了前两重循环枚举到的元素 a 和 b，那么只有唯一的 c 满足 a+b+c=0。当第二重循环往后枚举一个元素 b′时，由于 b' > b
         * 那么满足 a+b'+c'=0的 c'一定有 c' < c即 c' 在数组中一定出现在 c 的左侧。
         * 也就是说，我们可以从小到大枚举 bb，同时从大到小枚举 cc，即第二重循环和第三重循环实际上是并列的关系
         * <p>
         * 时间复杂度：O(N^2)，其中 N是数组nums 的长度。
         * 空间复杂度：O(logN)
         * @Param: [nums]
         */
        public List<List<Integer>> threeSum0(int[] nums) {
            List<List<Integer>> threeSumList = new ArrayList<List<Integer>>();
            if (nums == null || nums.length < 3) {
                return threeSumList;
            }
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                if (i == 0 || nums[i] != nums[i - 1]) {
                    for (int j = i + 1; j < nums.length; j++) {
                        if (j == i + 1 || nums[j] != nums[j - 1]) {
                            int c = nums.length - 1;
                            int targetC = -nums[i] - nums[j];
                            while ((nums[c] > targetC) && (c > j + 1)) {
                                c--;
                            }
                            if ((c >= j + 1) && nums[c] == targetC) {
                                List<Integer> threeSums = new ArrayList<Integer>();
                                threeSums.add(nums[i]);
                                threeSums.add(nums[j]);
                                threeSums.add(nums[c]);
                                threeSumList.add(threeSums);
                            }
                        }
                    }
                }

            }
            return threeSumList;
        }

        /**
         * @Description: //先排序，在判断三元组，三元组元素不能重复 ,此时时间复杂度为O(n3), 空间复杂度O(n)
         * @Param: [nums]
         */
        public List<List<Integer>> threeSum1(int[] nums) {
            List<List<Integer>> threeSumList = new ArrayList<List<Integer>>();
            if (nums == null || nums.length < 3) {
                return threeSumList;
            }
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                if (i == 0 || nums[i] != nums[i - 1]) {
                    for (int j = i + 1; j < nums.length; j++) {
                        if (j == i + 1 || nums[j] != nums[j - 1]) {
                            for (int k = j + 1; k < nums.length; k++) {
                                if (k == j + 1 || nums[k] != nums[k - 1]) {
                                    if (nums[i] + nums[j] + nums[k] == 0) {
                                        List<Integer> threeSums = new ArrayList<Integer>();
                                        threeSums.add(nums[i]);
                                        threeSums.add(nums[j]);
                                        threeSums.add(nums[k]);
                                        threeSumList.add(threeSums);
                                    }
                                }
                            }
                        }
                    }
                }

            }
            return threeSumList;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}