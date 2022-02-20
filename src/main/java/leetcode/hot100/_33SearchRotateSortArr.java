package leetcode.hot100;

/**
 * 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：nums = [1], target = 0
 * 输出：-1
 *
 * @author zhanghuali
 * @version 1.0
 * @ClassName: SearchRotateSortArr
 * @CreateTime: 2022/02/02 14:24:21
 * @Description:
 */
public class _33SearchRotateSortArr {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        System.out.println(new _33SearchRotateSortArr().search(nums, target));
        System.out.println(new _33SearchRotateSortArr().search(nums, 3));
        int[] nums1 = {1};
        System.out.println(new _33SearchRotateSortArr().search(nums1, 3));
        int[] nums2 = {1, 3};
        System.out.println(new _33SearchRotateSortArr().search(nums2, 3));
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 如果中间元素小于start，说明后半部分有序
            if (nums[mid] < nums[start]) {
                // 后半部分有序的情况下，判断target是否在后半部分
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                // 如果中间元素大于等于于start，说明前半部分有序
                // 前半部分有序的情况下，判断target是否再前半部分
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }

}
