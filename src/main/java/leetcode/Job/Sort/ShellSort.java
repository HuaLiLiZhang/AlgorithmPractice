package leetcode.Job.Sort;

import static leetcode.Job.Sort.CommonFunction.*;

/**
 * @author: Created by zhanghl
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 7, 8, 2};
        shellSortAl(arr);
        println(arr);
    }

    private static void shellSortAl(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int gap = arr.length / 2;
        //第一层循环：依次改变gap值对列表进行分组
        while (gap >= 1) {
            //下面：利用直接插入排序的思想对分组数据进行排序
            //    #range(gap,len(L)):从gap开始
            for (int i = gap; i < arr.length; i++) {
                //range(x-gap,-1,-gap):从x-gap开始与选定元素开始倒序比较，每个比较元素之间间隔gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        swap(arr, j, j + gap);
                    }
                }
            }
            gap = gap / 2;
        }
    }
}
