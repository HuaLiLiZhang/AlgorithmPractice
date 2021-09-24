package leetcode.middleProblem;

//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 104 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 104 
// 
// Related Topics 数组 排序 
// 👍 1111 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _56MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new _56MergeIntervals().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //时间复杂度：O(nlogn) ，有一个快排
        //空间复杂度：O(1)，我没有算result数组（返回值所需容器占的空间）
        //这种方式是左边界从小到大排序
        public int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length <= 1) {
                return intervals;
            }
            List<int[]> result = new ArrayList<>();
            Arrays.sort(intervals, (o1, o2) -> {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            });
            //记录起始位置，因为是左区间从小到大排序的，左边的一定是最小的。
            int start = intervals[0][0];
            for (int i = 1; i < intervals.length; i++) {
                //判断下一个区间是否与前面的区间重合，如果不重合，那么加入结果集中，并且更新start起始位置的值等于当前的起始位置
                if (intervals[i][0] > intervals[i - 1][1]) {
                    result.add(new int[]{start, intervals[i - 1][1]});
                    start = intervals[i][0];
                } else {
                    //如果下一个区间与前面的区间重合，那么把数组当前区间的右边界进行更新，更新为上一个区间右边界与当前区间右边界的最大值
                    intervals[i][1] = Math.max(intervals[i][1], intervals[i - 1][1]);
                }
            }
            result.add(new int[]{start, intervals[intervals.length - 1][1]});
            return result.toArray(new int[result.size()][2]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}