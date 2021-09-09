package leetcode.easyProblem;

//给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选
//择同一个索引 i。） 
//
// 以这种方式修改数组后，返回数组可能的最大和。 
//
// 
//
// 示例 1： 
//
// 输入：A = [4,2,3], K = 1
//输出：5
//解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。
// 
//
// 示例 2： 
//
// 输入：A = [3,-1,0,2], K = 3
//输出：6
//解释：选择索引 (1, 2, 2) ，然后 A 变为 [3,1,0,2]。
// 
//
// 示例 3： 
//
// 输入：A = [2,-3,-1,5,-4], K = 2
//输出：13
//解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 10000 
// 1 <= K <= 10000 
// -100 <= A[i] <= 100 
// 
// Related Topics 贪心 数组 排序 
// 👍 102 👎 0


import java.util.Arrays;
import java.util.stream.IntStream;

public class _1005MaximizeSumOfArrayAfterKNegations {
    public static void main(String[] args) {
        Solution solution = new _1005MaximizeSumOfArrayAfterKNegations().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //优化，按照绝对值大小排序
        public int largestSumAfterKNegations(int[] nums, int k) {
            if (nums == null || nums.length <= 0) {
                return 0;
            }
            nums = IntStream.of(nums).boxed().sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                    .mapToInt(Integer::intValue).toArray();
            for (int i = 0; i < nums.length; i++) {
                if (k > 0 && nums[i] < 0) {
                    nums[i] = -nums[i];
                    k--;
                }
                if (k == 0) {
                    break;
                }
            }
            if (k % 2 == 1) {
                //因为是从大到小的排序，所以应该改最后一个；
                nums[nums.length - 1] = -nums[nums.length - 1];
            }
            return Arrays.stream(nums).sum();

        }


        public int largestSumAfterKNegations_sortTwo(int[] nums, int k) {
            if (nums == null || nums.length <= 0) {
                return 0;
            }
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                int curNum = nums[i];
                if (k == 0) {
                    break;
                }
                if (k > 0 && curNum < 0) {
                    nums[i] = -curNum;
                    k--;
                }
            }
            if (k % 2 == 1) {
                Arrays.sort(nums);
                nums[0] = -nums[0];
            }
            /*int sum = 0;
            for (int i : nums) {
                sum += i;
            }*/
            return Arrays.stream(nums).sum();
        }

        //按照数组大小排序，从小到大排序，然后没有负数以后还要再次排序，看k的大小
        public int largestSumAfterKNegations_复杂(int[] nums, int k) {
            if (nums == null || nums.length <= 0) {
                return 0;
            }
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                int curNum = nums[i];
                if (k == 0) {
                    break;
                }
                if (k > 0) {
                    if (curNum < 0) {
                        nums[i] = -curNum;
                        k--;
                    } else if (curNum == 0) {
                        k = 0;
                        break;
                    } else {
                        if (k % 2 != 0) {
                            Arrays.sort(nums);
                            nums[0] = -nums[0];
                            k = 0;
                            break;
                        } else {
                            k = 0;
                        }
                    }
                }
            }
            if (k > 0) {
                Arrays.sort(nums);
                nums[0] = -nums[0];
            }
            int sum = 0;
            for (int i : nums) {
                sum += i;
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}