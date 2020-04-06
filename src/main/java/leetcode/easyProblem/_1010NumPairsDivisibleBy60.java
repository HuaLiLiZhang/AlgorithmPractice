package leetcode.easyProblem;

/**
 * @author: Created by zhanghl
 * @Date: 2020/3/15
 */
public class _1010NumPairsDivisibleBy60 {

		/** 
		* @Description: 在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
		 *
		 * 返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望索引的数字  i < j 且有 (time[i] + time[j]) % 60 == 0。
		 *
		 * 示例： [30,20,150,100,40] 输出： 3
		* @Param: [time] 
		* @return: int
		* @Auther: zhanghl
		* @Date: 2020/3/15 
		*/ 
		public int numPairsDivisibleBy60(int[] time) {
				int [] diffCount = new int[60];
				int count = 0;
				for(int i = 0; i< time.length;i++){
						int diff = time[i] % 60;
						if(diff == 0){
								count += diffCount[diff]; //整除的情况，也就是60 , 120, 180等
						}else	if(diffCount[60-diff] > 0){
								count += diffCount[60 - diff];
						}
						diffCount[diff] ++;
				}
				return count;
		}


		/**
		* @Description: 跟上面的解题方法是异曲同工。时间复杂度O(n), 空间复杂度为O(1)
		 *
		 * 解题思路：
		 * 整数对60取模，可能有60种余数。故初始化一个长度为60的数组，统计各余数出现的次数。
		 * 遍历time数组，每个值对60取模，并统计每个余数值（0-59）出现的个数。因为余数部分需要找到合适的cp组合起来能被60整除。
		 * 余数为0的情况，只能同余数为0的情况组合（如60s、120s等等）。0的情况出现k次，则只能在k中任选两次进行两两组合。本题解单独写了个求组合数的方法，也可以用k * (k - 1) / 2表示。
		 * 余数为30的情况同上。
		 * 其余1与59组合，2与58组合，故使用双指针分别从1和59两头向中间遍历。1的情况出现m次，59的情况出现n次，则总共有m*n种组合。
		 *
		 *
		* @Param: [time]
		* @return: int
		* @Auther: zhanghl
		* @Date: 2020/3/15
		*/
		public int numPairsDivisibleBy60MethodTwo(int[] time) {
				int count = 0;
				int[] seconds = new int[60];
				for(int t : time) {
						seconds[t % 60] += 1;
				}
				count += combination(seconds[30], 2);
				count += combination(seconds[0], 2);
				int i = 1, j = 59;
				while(i < j) {
						count += seconds[i++] * seconds[j--];
				}
				return count;
		}

		// 求组合数
		public int combination(int n, int k) {
				long result = 1;
				for(int i = 1; i <= k; i++) {
						result = result * (n - i + 1) / i;
				}
				return (int)result;
		}

}
