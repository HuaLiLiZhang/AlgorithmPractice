package leetcode.easyProblem;

/**
 * @author: Created by zhanghl
 * @Date: 2020/3/21
 */
public class _693HasAlternatingBits_nebordiff {
		/**
		* @Description: 判断一个数的二进制是否是交叉01，不重复连续。
		 * 比如 5 二进制 101，返回true
		 * 比如 7 二进制 111，返回false
		* @Param: [n]
		* @return: boolean
		* @Auther: zhanghl
		* @Date: 2020/3/21
		*/
		public boolean hasAlternatingBits(int n) {
				int pre = -1;
				boolean result = false;
				while (n!=0){
						if(pre != -1 && pre == (n & 1)){
								result = false;
								return result;
						}else{
								pre = n & 1;
								n = n >> 1;
						}
				}
				return true;
		}

		/**
		* @Description: 这种方法是什么操作？
		 * 	1.现将数与自身的右移一位进行亦或判断，
		 * 	因为n的最高位肯定为1，与右移一位亦或，就是判断相邻的是否是不一样的。
		 * 	如果位相邻的都不一样，那么最后Bits的二进制肯定都是1.
		 * 	如果相邻为的不都为1，说明有一样的二进制位
		 * 	2.在判断bits与bits+1的与是否为0,
		 * 	为0的话，说明Bits的二进制肯定都是1.相邻的位都不一样
		 * 	不为0的话，说明Bits的二进制不都是1.相邻的位有存在一样的
		* @Param: [n]
		* @return: boolean
		* @Auther: zhanghl
		* @Date: 2020/3/21
		*/
		public boolean hasAlternatingBits2(int n) {
				boolean result = false;
				int bits = n^(n >> 1);
				return (bits & (bits + 1)) == 0;
		}
}
