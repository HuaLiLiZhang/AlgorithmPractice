package leetcode.Job.Sort;

import static leetcode.Job.Sort.CommonFunction.println;
import static leetcode.Job.Sort.CommonFunction.swap;

/**
 * @author: Created by zhanghl
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 7, 8, 2};
        int[] newArr = quickSortAl(arr);
        println(newArr);
    }

    private static int[] quickSortAl(int[] sourceArr) {
        if (sourceArr == null || sourceArr.length == 0) {
            return sourceArr;
        }
        return quickSort(sourceArr, 0, sourceArr.length - 1);
    }

    private static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private static int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }
}
