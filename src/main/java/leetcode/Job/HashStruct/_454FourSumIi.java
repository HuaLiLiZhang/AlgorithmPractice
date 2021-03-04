package leetcode.Job.HashStruct;

//给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[
//l] = 0。 
//
// 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最
//终结果不会超过 231 - 1 。 
//
// 例如: 
//
// 
//输入:
//A = [ 1, 2]
//B = [-2,-1]
//C = [-1, 2]
//D = [ 0, 2]
//
//输出:
//2
//
//解释:
//两个元组如下:
//1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
//2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
// 
// Related Topics 哈希表 二分查找 
// 👍 338 👎 0


import java.util.HashMap;

public class _454FourSumIi {
    public static void main(String[] args) {
        Solution solution = new _454FourSumIi().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 首先定义 一个map，key放a和b两数之和，value 放a和b两数之和出现的次数。
         * 遍历大A和大B数组，统计两个数组元素之和，和出现的次数，放到map中。
         * 定义int变量count，用来统计a+b+c+d = 0 出现的次数。
         * 在遍历大C和大D数组，找到如果 0-(c+d) 在map中出现过的话，就用count把map中key对应的value也就是出现次数统计出来。
         * 最后返回统计值 count 就可以了
         * @Param: [A, B, C, D]
         */
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            HashMap<Integer, Integer> abSumCountMap = new HashMap<>();
            int count = 0;
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    int sumAB = A[i] + B[j];
                    /*if (abSumCountMap.containsKey(sumAB)) {
                        abSumCountMap.put(sumAB, abSumCountMap.get(sumAB) + 1);
                    } else {
                        abSumCountMap.put(sumAB, 1);
                    }*/
                    abSumCountMap.put(sumAB, abSumCountMap.getOrDefault(sumAB, 0) + 1);
                }
            }
            for (int i = 0; i < C.length; i++) {
                for (int j = 0; j < D.length; j++) {
                    int sumCD = C[i] + D[j];
                    if (abSumCountMap.containsKey(0 - sumCD)) {
                        count += abSumCountMap.get(0 - sumCD);
                    }
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}