package leetcode.easyProblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author: Created by zhanghl
 * @Date: 2020/3/12
 */
public class _1TwoNumberSum {
		/** 
		* @Description:
		 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
		 *
		 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
		 *
		 * 示例:
		 *
		 * 给定 nums = [2, 7, 11, 15], target = 9
		 *
		 * 因为 nums[0] + nums[1] = 2 + 7 = 9
		 * 所以返回 [0, 1]
		 *
		* 解题思路：这种解题思路的话，空间复杂度为o(n),时间复杂度为O(n²)，不推荐
		* @Auther: zhanghl
		* @Date: 2020/3/12 
		*/ 
		public int[] twoSum(int[] nums, int target) {
				ArrayList<Integer> arr = new ArrayList<Integer>();
				//既然浪费了空间，在时间上就要更快，所以要找搜索速度更快的数据结构，当然是hashmap啦
				for(int i : nums){
						arr.add(i);
				}
				for(int i = 0;i < nums.length;i++){
						int diffValue = target - nums[i];
						if(arr.contains(diffValue)){
								for(int j = i+1;j<arr.size();j++){
										if(diffValue == arr.get(j)){
												return new int[]{i, j};
										}
								}
						}
				}
				return new int[]{-1,-1};
		}

		public int[] twoSum2(int[] nums, int target) {
				HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
				for(int i = 0;i < nums.length;i++){
						int diff = target - nums[i];
						if(map.containsKey(diff)){
								//因为map里面的元素肯定是先加入的，所以呢找到的索引值也应该map中get的索引在前，i在后
								return new int[]{map.get(diff), i};
						}
						map.put(nums[i], i);
				}

				return new int[]{-1, -1};
		}
}
