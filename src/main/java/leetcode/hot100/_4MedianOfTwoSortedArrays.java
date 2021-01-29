//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
// Related Topics 数组 二分查找 分治算法 
// 👍 3632 👎 0


package leetcode.hot100;

public class _4MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new _4MedianOfTwoSortedArrays().new Solution();
        int[] nums1 = {0};
        int[] nums2 = {};
        System.out.println(solution.findMedianSortedArrays(nums1, nums2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 中位数：使用二分法
         * 时间复杂度：O(log min(m, n)), 空间复杂度：O(1)
         * left_part          |         right_part
         * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
         * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
         * 将两个数据进项分割，满足：
         * 1、 当两个数组长度之和为偶数时，划线两边的元素个数相等，并且左边的元素最大值小于等于右边元素的最小值,
         * 那么中位数就是左边元素最大值和右边元素最小值的平均值：
         * len(left_part)=len(right_part)
         * max(left_part)≤min(right_part)
         * median= (max(left_part)+min(right_part)) / 2
         * ​
         * <p>
         * 2、 当两个数组长度之和为奇数事，划线的左边元素比右边元素多一个，并且也要满足左边的元素最大值小于等于右边元素的最小值，
         * 那么中位数就是左边元素的最大值：
         * len(left_part)=len(right_part)+1
         * max(left_part)≤min(right_part)
         * median=max(left_part)
         * <p>
         * 也就是在确定i的情况下，j也就确定了
         * 在数组长度和为偶数的情况下，i = t, j = (m+n)/2
         * 在数组长度和为奇数的情况下，i = t, j = (m+n+1)/2
         * 但由于是向下取整，在为偶数的时候，j = (m+n+1)/2
         * 所以找到i 的值，j = (m+n+1)/2 -i
         * 并且要满足：num1[i-1]<=num2[j] 和num2[j-1] <=num1[i]
         * @Param:
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                int[] temp = nums1;
                nums1 = nums2;
                nums2 = temp;
            }
            int m = nums1.length;
            int n = nums2.length;
            //分割线左边额所有元素要满足的个数：m  + (n-m+1)/2; //防止整形溢出
            int totalLeft = (m + n + 1) / 2;

            //数组num1在去见[0,m]里面找到恰当的分割线：
            // 使得nums1[i-1]<=nums2[j]; && nums2[j-1] <= nums1[i],取反进行
            int left = 0;
            int right = m;
            while (left < right) {
                int i = left + (right - left + 1) / 2; //i进行二分查找即可
                int j = totalLeft - i;
                if (nums1[i - 1] > nums2[j]) {
                    //下一轮搜索区间[left , i-1]
                    right = i - 1;
                } else {
                    //下一轮搜索区间[i , right]
                    //在[left(i), right], 会进入是循环，
                    // 所有要在i赋值的时候加1,不会执行到i=0的下标， 所以i-1这个不会越界
                    left = i;
                }
            }
            int i = left;
            int j = totalLeft - i;
            int leftNum1Max = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int rightNum1Min = i == m ? Integer.MAX_VALUE : nums1[i];
            int leftNum2Max = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int rightNum2Min = j == n ? Integer.MAX_VALUE : nums2[j];
            if ((m + n) % 2 == 1) {
                return Math.max(leftNum1Max, leftNum2Max);
            } else {
                return (double) (Math.max(leftNum1Max, leftNum2Max) + Math.min(rightNum1Min, rightNum2Min)) / 2;
            }
        }


        /**
         * @Description:
         * @Param: [nums1, nums2]
         */
        public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
            int length1 = nums1.length, length2 = nums2.length;
            int totalLength = length1 + length2;
            if (totalLength % 2 == 1) {
                int midIndex = totalLength / 2;
                double median = getKthElement(nums1, nums2, midIndex + 1);
                return median;
            } else {
                int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
                double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
                return median;
            }
        }

        public int getKthElement(int[] nums1, int[] nums2, int k) {
            /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
             * 这里的 "/" 表示整除
             * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
             * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
             * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
             * 这样 pivot 本身最大也只能是第 k-1 小的元素
             * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
             * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
             * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
             */

            int length1 = nums1.length, length2 = nums2.length;
            int index1 = 0, index2 = 0;
            int kthElement = 0;

            while (true) {
                // 边界情况
                if (index1 == length1) {
                    return nums2[index2 + k - 1];
                }
                if (index2 == length2) {
                    return nums1[index1 + k - 1];
                }
                if (k == 1) {
                    return Math.min(nums1[index1], nums2[index2]);
                }

                // 正常情况
                int half = k / 2;
                int newIndex1 = Math.min(index1 + half, length1) - 1;
                int newIndex2 = Math.min(index2 + half, length2) - 1;
                int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
                if (pivot1 <= pivot2) {
                    k -= (newIndex1 - index1 + 1);
                    index1 = newIndex1 + 1;
                } else {
                    k -= (newIndex2 - index2 + 1);
                    index2 = newIndex2 + 1;
                }
            }
        }


        /**
         * @Description: 时间复杂度：O(m+n) 的解法，空间复杂度：O(1)
         * @Param: [nums1, nums2]
         */
        public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
            if (nums1 == null || nums1.length == 0 && (nums2.length == 1)) {
                return nums2[0];
            }
            if (nums2 == null || nums2.length == 0 && (nums1.length == 1)) {
                return nums1[0];
            }
            int len1 = nums1.length;
            int len2 = nums2.length;
            int sumLen = len1 + len2;
            int before = 0;
            int after = 0;
            if (sumLen % 2 == 0) {
                before = sumLen / 2 - 1;
                after = before + 1;
            } else {
                before = sumLen / 2;
                after = before;
            }
            int index = -1;
            double sum = 0;
            for (int i = 0, j = 0; i < len1 || j < len2; ) {
                if (i >= len1) {
                    index++;
                    if (index == before) {
                        sum += nums2[j];
                    }
                    if (index == after) {
                        sum += nums2[j];
                    }
                    j++;
                    continue;
                }
                if (j >= len2) {
                    index++;
                    if (index == before) {
                        sum += nums1[i];
                    }
                    if (index == after) {
                        sum += nums1[i];
                    }
                    i++;
                    continue;
                }
                if (nums1[i] <= nums2[j]) {
                    index++;
                    if (index == before) {
                        sum += nums1[i];
                    }
                    if (index == after) {
                        sum += nums1[i];
                    }
                    i++;
                } else {
                    index++;
                    if (index == before) {
                        sum += nums2[j];
                    }
                    if (index == after) {
                        sum += nums2[j];
                    }
                    j++;
                }
            }
            return sum / 2;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}