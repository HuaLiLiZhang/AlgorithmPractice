package leetcode.easyProblem;

/**
 * @author: Created by zhanghl
 * @Date: 2020/3/15
 */
public class _61IsContinusNum {
		public static void main(String[] args) {
				int [] res = {0,0,1,2,5};
				System.out.println(isStraight(res));
		}


		public static boolean isStraight(int[] nums) {
				int [] all = new int[14];
				for(int i = 0;i<nums.length;i++){
						all[nums[i]] += 1;
				}
				int continusCOunt = 0;
				boolean start = false;
				for(int i = 1; i<all.length;i++){
						if(all[i]<=0 && !start){
								continue;
						}
						if(start || all[i] > 0){
								start = true;
								if(all[i] > 0){
										continusCOunt ++;
								}else {
										all[0] --;
										continusCOunt ++;
								}
								if(continusCOunt == 5 && all[0]>=0){
										return true;
								}
								if(all[0]<0){
										return false;
								}
						}

				}
				if(all[0] + continusCOunt == 5){
						return true;
				}
				return false;
		}
}
