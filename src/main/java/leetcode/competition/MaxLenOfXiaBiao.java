package leetcode.competition;

/**
 * @author: Created by zhanghl
 */

/**
 * @Description: 给你两个 非递增 的整数数组 nums1​​​​​​ 和 nums2​​​​​​ ，数组下标均 从 0 开始 计数。
 * <p>
 * 下标对 (i, j) 中 0 <= i < nums1.length 且 0 <= j < nums2.length 。如果该下标对同时满足 i <= j 且 nums1[i] <= nums2[j] ，则称之为 有效 下标对，该下标对的 距离 为 j - i​​ 。​​
 * <p>
 * 返回所有 有效 下标对 (i, j) 中的 最大距离 。如果不存在有效下标对，返回 0 。
 * <p>
 * 一个数组 arr ，如果每个 1 <= i < arr.length 均有 arr[i-1] >= arr[i] 成立，那么该数组是一个 非递增 数组。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
 * 输出：2
 * 解释：有效下标对是 (0,0), (2,2), (2,3), (2,4), (3,3), (3,4) 和 (4,4) 。
 * 最大距离是 2 ，对应下标对 (2,4) 。
 * 示例 2：
 * <p>
 * 输入：nums1 = [2,2,2], nums2 = [10,10,1]
 * 输出：1
 * 解释：有效下标对是 (0,0), (0,1) 和 (1,1) 。
 * 最大距离是 1 ，对应下标对 (0,1) 。
 * 示例 3：
 * <p>
 * 输入：nums1 = [30,29,19,5], nums2 = [25,25,25,25,25]
 * 输出：2
 * 解释：有效下标对是 (2,2), (2,3), (2,4), (3,3) 和 (3,4) 。
 * 最大距离是 2 ，对应下标对 (2,4) 。
 * 示例 4：
 * <p>
 * 输入：nums1 = [5,4], nums2 = [3,2]
 * 输出：0
 * 解释：不存在有效下标对，所以返回 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length <= 105
 * 1 <= nums2.length <= 105
 * 1 <= nums1[i], nums2[j] <= 105
 * nums1 和 nums2 都是 非递增 数组
 * @Param:
 */
public class MaxLenOfXiaBiao {
    public static void main(String[] args) {
        int[] nums1 = {55, 30, 5, 4, 2};
        int[] nums2 = {100, 20, 10, 10, 5};
        System.out.println(new MaxLenOfXiaBiao().maxDistance(nums1, nums2));
    }

    public int maxDistance(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length <= 0 || nums2.length <= 0) {
            return 0;
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (nums1[len1 - 1] > nums2[0]) {
            return 0;
        }
        int maxLen = 0;
        int i = 0;
        int j = 0;
        while (i < len1 && j < len2) {
            if (i > j) {
                j++;
                continue;
            }
            if (nums1[i] <= nums2[j]) {
                maxLen = Math.max(maxLen, j - i);
                j++;
                continue;
            }
            i++;
        }
        return maxLen;
    }

    /**
     * @Description:
     * @Param: [nums1, nums2]
     */
    public int maxDistance1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length <= 0 || nums2.length <= 0) {
            return 0;
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (nums1[len1 - 1] > nums2[0]) {
            return 0;
        }
        int maxLen = 0;
        int j = 0;
        for (int i = 0; i < len1; i++) {
            if (i != 0 && nums1[i - 1] == nums1[i]) {
                continue;
            }
            while (j < len2) {
                //这里利用了数组不递增的特性，因为如果对于i, j的前一个区间(m,n)，
                // 对于i+1之后，j不用再从m+1开始循环遍历，j直接j++即可。
                // 因为j从m+1，到n+1，这段区间是没用的，因为j-i的值并没有增大，所以j只需要每次j++即可。
                if (nums1[i] <= nums2[j]) {
                    j++;
                } else {
                    break;
                }
            }
            maxLen = Math.max(maxLen, j - i - 1);
        }
        return maxLen;
    }
}
