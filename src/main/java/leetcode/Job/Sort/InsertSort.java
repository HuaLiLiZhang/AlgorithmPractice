package leetcode.Job.Sort;

import java.util.Arrays;

import static leetcode.Job.Sort.CommonFunction.*;

/**
 * @author: Created by zhanghl
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 7, 8, 2};
        InsertSort.insertSortAl(arr);
        println(arr);
    }

    public static void insertSortAl(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
         //遍历数组中的所有元素，其中0号索引元素默认已排序，因此从1开始
        for (int i = 1; i < arr.length; i++) {
            //将该元素与已排序好的前序数组依次比较，如果该元素小，则交换
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
        /*for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            for (; j >= 0 && arr[j] > temp; j--) {
                arr[j + 1] = arr[j];
            }
            if (j != i - 1) {
                arr[j + 1] = temp;
            }
        }*/
    }



}
