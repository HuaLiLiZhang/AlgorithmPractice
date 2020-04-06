package leetcode.hardProblem;

import java.util.HashMap;

/**
 * @author: Created by zhanghl
 * @Date: 2020/3/22
 */

public class _1074NumSubmatrixSumTarget {

		/**
		* @Description: It is actually a combination of 2 problems but slightly twisted:
		 * problem 1: Find number of sub arrays with target sum
		 * problem 2: Find largest sub matrix with target sum
		 *
		 * For problem 1: I followed the same hashmap approach as listed in this leetcode problem: https://leetcode.com/problems/subarray-sum-equals-k/solution/
		 * For problem 2: here is an excellent video which you will understand in 1 go. https://www.youtube.com/watch?time_continue=173&v=yCQN096CwWM&feature=emb_logo
		 *
		 * Now just twist problem 2 and instead of saying find Maximum Sum Rectangular Submatrix, find all subarrays with sum equal to k.
		 *
		 * If you understand problem 2 deeply then you will understand that the problem is actually very simple and not complex at all.
		* @Param: [matrix, target]
		* @return: int
		* @Auther: zhanghl
		* @Date: 2020/3/23
		*/
		public int numSubmatrixSumTarget(int[][] matrix, int target) {
				int countEqual = 0;
				for(int left = 0; left < matrix[0].length;left++){
						int [] sumRows = new int[matrix.length];
						for(int right = left; right < matrix[0].length; right++){
								for(int k = 0; k < matrix.length;k++){
										sumRows[k] += matrix[k][right];
								}
								countEqual += subarraySum(sumRows, target);
						}
				}
				return countEqual;
		}
		/**
		* @Description: 滑动窗口计算有多少矩形框内的值等于target
		* @Param: [arr, target]
		* @return: int
		* @Auther: zhanghl
		* @Date: 2020/3/23
		*/
		public int subarraySum(int[] arr, int target) {
				int sum = 0;
				HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
				map.put(0, 1);
				int curCount = 0;
				for(int i = 0; i < arr.length;i++){
						sum += arr[i];
						int sub = sum - target;
						if(map.containsKey(sub)){
								curCount += map.get(sub);
						}
						map.put(sum, map.get(sum) != null?map.get(sum) + 1 : 1);
				}
				return curCount;
		}
}
