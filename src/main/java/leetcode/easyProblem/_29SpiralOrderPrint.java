package leetcode.easyProblem;

import java.util.Arrays;

/**
 * @author: Created by zhanghl
 * @Date: 2020/3/17
 */
public class _29SpiralOrderPrint {
		public static void main(String[] args) {
				int [][] m = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
				int [] r = spiralOrder(m);
				System.out.println(Arrays.asList(r));
		}

		/** 
		* @Description: 设置四个边界： l, r, t, b
		 * 算法流程：
		 * 空值处理： 当 matrix 为空时，直接返回空列表 [] 即可。
		 * 初始化： 矩阵 左、右、上、下 四个边界 l , r , t , b ，用于打印的结果列表 res 。
		 * 循环打印： “从左向右、从上向下、从右向左、从下向上” 四个方向循环，每个方向打印中做以下三件事 （各方向的具体信息见下表） ；
		 * 根据边界打印，即将元素按顺序添加至列表 res 尾部；
		 * 边界向内收缩 11 （代表已被打印）；
		 * 判断是否打印完毕（边界是否相遇），若打印完毕则跳出。
		 * 返回值： 返回 res 即可。
		 * 打印方向	1. 根据边界打印	2. 边界向内收缩	3. 是否打印完毕
		 * 从左向右	左边界l ，右边界 r	上边界 t 加 1	是否 t > b
		 * 从上向下	上边界 t ，下边界b	右边界 r 减 1	是否 l > r
		 * 从右向左	右边界 r ，左边界l	下边界 b 减 1	是否 t > b
		 * 从下向上	下边界 b ，上边界t	左边界 l 加 1	是否 l > r
		 *
		* @Param: [matrix] 
		* @return: int[]
		* @Auther: zhanghl
		* @Date: 2020/3/17 
		*/ 
		public static int[] spiralOrder(int[][] matrix) {
				if(matrix == null || matrix.length<=0){
						return new int []{};
				}
				int l = 0;
				int r = matrix[0].length-1;
				int t = 0;
				int b = matrix.length-1;
				int[] newPrint = new int[(r+1) * (b+1)];
				int i = 0;
				while (true){
						for(int k = l; k <= r;k++){
								newPrint[i++] = matrix[t][k];
						}
						if(++t>b){
								break;
						}
						for(int k = t;k<=b;k++){
								newPrint[i++] = matrix[k][r];
						}
						if(--r<l){
								break;
						}
						for(int k = r;k>=l;k--){
								newPrint[i++] = matrix[b][k];
						}
						if(--b<t){
								break;
						}
						for(int k = b; k >= t;k--){
								newPrint[i++] = matrix[k][l];
						}
						if(++l>r){
								break;
						}
				}

				return newPrint;
		}
}
