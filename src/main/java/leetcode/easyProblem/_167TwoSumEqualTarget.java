package leetcode.easyProblem;

import java.util.HashMap;

/**
 * @author: Created by zhanghl
 * @Date: 2020/3/17
 */
public class _167TwoSumEqualTarget {

		/**
		* @Description: 由于数组已经排好序，所以使用O(n)的时间和空间复杂度就很浪费。
		 * 输入: numbers = [2, 7, 11, 15], target = 9
		 * 输出: [1,2]
		 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2
		* @Param: [numbers, target]
		* @return: int[]
		* @Auther: zhanghl
		* @Date: 2020/3/17
		*/
		public int[] twoSum(int[] numbers, int target) {
				int [] twoIndex = new int[2];
				HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
				for(int i = 0 ;i < numbers.length;i++){
						int diff = target - numbers[i];
						if(map.containsKey(diff)){
								twoIndex[0] = map.get(diff);
								twoIndex[1] = i +1;
								return twoIndex;
						}
						map.put(numbers[i], i + 1);
				}
				return twoIndex;

		}


		/**
		* @Description: 下面采用O(n)的时间复杂度和O(1)的空间复杂度解题
		* @Param: [numbers, target]
		* @return: int[]
		* @Auther: zhanghl
		* @Date: 2020/3/17
		*/
		public int[] twoSum2(int[] numbers, int target) {
				int startPos = 0;
				int endPos = numbers.length-1;
				while (startPos < endPos){
						if(numbers[startPos] + numbers[endPos] > target){
								endPos --;
						}else if(numbers[startPos] + numbers[endPos] < target){
								startPos ++;
						}else{
								return new int[]{startPos+1, endPos+1};
						}
				}
				return new int[]{};

		}
}
