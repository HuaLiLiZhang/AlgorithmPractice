package leetcode.easyProblem;

/**
 * @author: Created by zhanghl
 * @Date: 2020/3/11
 */
public class _1013CanThreePartsEqualSum {
		public static void main(String[] args) {
				int [] A = {12,-4,16,-5,9,-3,3,8,0};
				boolean isCanThreePart = canThreePartsEqualSum(A);
				System.out.println(isCanThreePart);
		}

		protected static boolean canThreePartsEqualSum(int[] A) {
				if(A == null || A.length < 3){
						return false;
				}
				int sum = 0;
				for(int i : A){
						sum += i;
				}
				if(sum % 3 !=0){
						return false;
				}
				int count = 0;
				int cur = 0;
				for(int i = 0;i<A.length-1;i++){
						cur += A[i];
						if(cur == (sum / 3)){
								count++;
								cur=0;
								if(count == 2){
										return true;
								}
						}
				}
				return false;
		}

		/**
		* @Description:
		 * 给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
		 *  * 形式上，如果可以找出索引 i+1 < j 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 就可以将数组三等分。
		 *  * 示例 1：
		 *  *
		 *  * 输出：[0,2,1,-6,6,-7,9,1,2,0,1]
		 *  * 输出：true
		 *  * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
		 *  *
		 *
		 *  思路：现求出整个数组的和，和不能整除3则返回false
		 *  否则：1.从数组第一个元素开始连加cur，（一定要先加入第一个数组元素的值在判断，因为会有可能sum=0的情况，会误判）
		 *  		 2.然后判断这个值cur是否等于sum/3,如果等于，那个计数加一，并且将cur设置为0,继续加
		 *  		 3.然后在循环里面，在cur等于sum/3的条件里面或外面都可，判断是否计数等于2，如果等于，则返回true
		 *  		 （因为如果在循环外面的话，会有一种情况{12,-4,16,-5,9,-3,3,8,0}，
		 *  		 最后一个元素等于0，那么count就会等于3，而返回false，导致错误）
		 *
		 *
		*/
}
