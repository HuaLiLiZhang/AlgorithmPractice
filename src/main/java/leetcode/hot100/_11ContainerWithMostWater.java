//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, 
//ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 示例 2： 
//
// 
//输入：getDepth = [1,1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：getDepth = [4,3,2,1,4]
//输出：16
// 
//
// 示例 4： 
//
// 
//输入：getDepth = [1,2,1]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// n = getDepth.length
// 2 <= n <= 3 * 104 
// 0 <= getDepth[i] <= 3 * 104
// 
// Related Topics 数组 双指针 
// 👍 2157 👎 0


package leetcode.hot100;

public class _11ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new _11ContainerWithMostWater().new Solution();
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] height2 = {1, 1};
        int[] height3 = {4, 3, 2, 1, 4};
        int[] height4 = {1, 2, 1};
        System.out.println(solution.maxArea(height1));
        System.out.println(solution.maxArea(height2));
        System.out.println(solution.maxArea(height3));
        System.out.println(solution.maxArea(height4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 容器水的值等于 min(左指针， 右指针)*左指针和右指针之间的距离
         * 移动左右指针的条件：若移动数值最大的指针，min（） 不会变，但是距离会减小，所以容器的盛水面积也会减小，所以移动最大的指针是不合理的
         * 所以应该移动最小的指针
         * @Param: [getDepth]
         */
        public int maxArea(int[] height) {
            if (height == null || height.length <= 1) {
                return 0;
            }
            int firstIndex = 0;
            int endIndex = height.length - 1;
            int maxArea = 0;
            while (firstIndex < endIndex) {
                maxArea = Math.max(maxArea, Math.min(height[firstIndex], height[endIndex]) * (endIndex - firstIndex));
                if (height[firstIndex] < height[endIndex]) {
                    firstIndex++;
                } else {
                    endIndex--;
                }
            }
            return maxArea;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}