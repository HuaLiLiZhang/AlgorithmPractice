package leetcode.Job.HashStruct;

//给定两个数组，编写一个函数来计算它们的交集。 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
// 
//
// 示例 2： 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4] 
//
// 
//
// 说明： 
//
// 
// 输出结果中的每个元素一定是唯一的。 
// 我们可以不考虑输出结果的顺序。 
// 
// Related Topics 排序 哈希表 双指针 二分查找 
// 👍 332 👎 0


import java.util.HashSet;
import java.util.Set;

public class _349IntersectionOfTwoArrays {
    public static void main(String[] args) {
        Solution solution = new _349IntersectionOfTwoArrays().new Solution();
    }
    /**
    
    */
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> result =  new HashSet<>();
        Set<Integer> nums1Set = new HashSet<>();
        for (int num : nums1) {
            nums1Set.add(num);
        }
        for (int num : nums2) {
            if (nums1Set.contains(num)) {
                result.add(num);
            }
        }
        int[] intersection = new int[result.size()];
        int index = 0;
        for (int num : result) {
            intersection[index++] = num;
        }
        return intersection;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}