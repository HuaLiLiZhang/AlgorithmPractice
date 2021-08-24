package leetcode.Job.Sort;

import java.util.Arrays;

import static leetcode.Job.Sort.CommonFunction.println;
import static leetcode.Job.Sort.CommonFunction.swap;

/**
 * @author: Created by zhanghl
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 7, 8, 2};
        int[] newArr = bubbleSortAl(arr);
        println(newArr);
    }

    private static int[] bubbleSortAl(int[] sourceArr) {
        if (sourceArr == null || sourceArr.length == 0) {
            return sourceArr;
        }
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArr, sourceArr.length);
        //序列长度为length，需要执行length-1轮交换
        for (int i = 1; i < arr.length; i++) {
            //对于每一轮交换，都将序列当中的左右元素进行比较
            //#每轮交换当中，由于序列最后的元素一定是最大的，因此每轮循环到序列未排序的位置即可
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }
}
