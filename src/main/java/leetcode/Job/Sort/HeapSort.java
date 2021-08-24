package leetcode.Job.Sort;

import java.util.Arrays;

import static leetcode.Job.Sort.CommonFunction.println;
import static leetcode.Job.Sort.CommonFunction.swap;

/**
 * @author: Created by zhanghl
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 7, 8, 2};
        int [] newArr = heapSortAl(arr);
        println(newArr);
    }

    private static int[] heapSortAl(int[] sourceArr) {
        if (sourceArr == null || sourceArr.length == 0) {
            return sourceArr;
        }
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArr, sourceArr.length);

        int len = arr.length;

        buildMaxHeap(arr, len);

        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
        return arr;
    }

    private static void buildMaxHeap(int[] arr, int len) {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            heapify(arr, i, len);
        }
    }

    private static void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, len);
        }
    }


}
