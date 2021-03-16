package leetcode.Job.StackAndQueue;

//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。 
// 你可以按任意顺序返回答案。 
// 
// Related Topics 堆 哈希表 
// 👍 678 👎 0


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _347TopKFrequentElements {
    public static void main(String[] args) {
        Solution solution = new _347TopKFrequentElements().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> mapFreq = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                mapFreq.put(nums[i], mapFreq.getOrDefault(nums[i], 0) + 1);
            }
            PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });
            for (Integer key : mapFreq.keySet()) {
                int count = mapFreq.get(key);
                if (priorityQueue.size() == k) {
                    if (priorityQueue.peek()[1] < count) {
                        priorityQueue.poll();
                        priorityQueue.offer(new int[]{key, count});
                    }
                } else {
                    priorityQueue.offer(new int[]{key, count});
                }
            }
            int[] result = new int[k];
            for (int i = 0; i < k; i++) {
                result[i] = priorityQueue.peek()[0];
                priorityQueue.poll();
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}