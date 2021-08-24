package leetcode.Job.Sort;

import static leetcode.Job.Sort.CommonFunction.*;

/**
 * @author: Created by zhanghl
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 7, 8, 2};
        selectSortAl(arr);
        println(arr);
    }

    private static void selectSortAl(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        //依次遍历序列中的每一个元素
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int minJ = i;
            //将该元素与剩下的元素依次比较寻找最小元素
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    minJ = j;
                    min = arr[j];
                }
            }
            //将比较后得到的真正的最小值赋值给当前位置
            swap(arr, i, minJ);
        }
    }
}
