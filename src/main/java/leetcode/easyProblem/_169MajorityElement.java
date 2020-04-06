package leetcode.easyProblem;

import java.util.Arrays;

/**
 * @author: Created by zhanghl
 * @Date: 2020/3/12
 */
public class _169MajorityElement {

		public int majorityElement(int[] nums) {
				int count = 0;
				Integer candidate = null;
				for(int i = 0 ;i < nums.length;i++){
						if(count==0){
								candidate = nums[i];
						}
						count += (nums[i]==candidate)? 1:-1;
				}
				return candidate;
		}


		/**
		* @Description: 给你一个正整数数组 nums，你需要从中任选一些子集，然后将子集中每一个数乘以一个 任意整数，并求出他们的和。
		 *
		 * 假如该和结果为 1，那么原数组就是一个「好数组」，则返回 True；否则请返回 False。
		 *
		 * 示例 1：
		 *
		 * 输入：nums = [12,5,7,23]
		 * 输出：true
		 * 解释：挑选数字 5 和 7。
		 * 5*3 + 7*(-2) = 1
		 * 示例 2：
		 *
		 * 输入：nums = [29,6,10]
		 * 输出：true
		 * 解释：挑选数字 29, 6 和 10。
		 * 29*1 + 6*(-3) + 10*(-1) = 1
		 *
		* @Param: [nums]
		* @return: boolean
		* @Auther: zhanghl
		* @Date: 2020/3/15
		*/
		public boolean isGoodArray(int[] nums){
				int res = nums[0];
				for(int i = 1;i<nums.length;i++){
						res = gcb(res, nums[i]);
				}
				return  res == 1;
		}

		private int gcb(int res, int num) {
				return num == 0? res : gcb(num, res % num);
		}

		/**
		* @Description:思路：满足这个等式 ax + by = 1,有正整数解，那么a和b一定互为质数。
		 * 裴蜀定理：\forall a,b\in \mathbb{Z}, gcd(a, b) = 1 \Leftrightarrow \exists x,y\in \mathbb{Z}, s.t. ax+by=1.∀a,b∈Z,gcd(a,b)=1⇔∃x,y∈Z,s.t.ax+by=1.
		 * 利用裴蜀定理，自左至右求出最大公因数即可
		 * 若最大公因数为1，一定存在两两互质的最大公因数，可以(使用这两个互质的公因数)实现「好数组」
		 * 注：最大公因数可以通过有限次乘法运算求出
		 * 若最大公因数不为1，所有数都有共同的(大于1的)公因数，不能实现「好数组」
		 *
		* @Auther: zhanghl
		* @Date: 2020/3/15
		*/
}

