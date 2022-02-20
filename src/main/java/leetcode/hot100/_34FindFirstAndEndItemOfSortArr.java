package leetcode.hot100;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 * @author zhanghuali
 * @version 1.0
 * @ClassName: _34FindFirstAndEndItemOfSortArr
 * @CreateTime: 2022/02/05 17:02:43
 * @Description:
 */
public class _34FindFirstAndEndItemOfSortArr {
    public static void main(String[] args) {

    }

    public int[] searchRange(int[] nums, int target) {
        int firstIndex = -1;
        int endIndex = -1;
        int[] result = {firstIndex, endIndex};
        if (nums == null || nums.length <= 0) {
            return result;
        }
        firstIndex = findStartEndIndex(nums, target, true);
        endIndex = findStartEndIndex(nums, target, false);
        if (firstIndex == -1 || endIndex == -1 || (firstIndex == endIndex)) {
            return result;
        }
        result[0] = firstIndex;
        result[1] = endIndex;
        return result;
    }

    private int findStartEndIndex(int[] nums, int target, boolean isLower) {
        // lower如果为true表示找到第一个小于等于target的index，lower如果为false，找到第一个大于等于target的index
        int index = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                if (isLower) {
                    index = mid;
                    end = mid - 1;
                    continue;
                } else {
                    index = mid;
                    start = mid + 1;
                    continue;
                }
            }

            if (nums[mid] >= nums[start]) {
                // 前半部分有序
                if (isLower) {
                    if (target == nums[start]) {
                        return start;
                    }
                    if (target > nums[start] && target <= nums[mid]) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                } else {
                    if (target == nums[end]) {
                        return end;
                    }
                    if (target > nums[start] && target >= nums[mid]) {
                        end = mid - 1;
                    } else {

                    }
                }
            } else {
                // 后半部分有序
                if (isLower && target == nums[end]) {
                    return start;
                }
                if (isLower) {
                    if (target > nums[start] && target <= nums[end]) {
                        end = mid - 1;
                    } else {

                    }
                } else {
                    if (target > nums[start] && target <= nums[end]) {
                        end = mid - 1;
                    } else {

                    }
                }

            }
        }
        return index;
    }

}
