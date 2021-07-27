package leetcode.meeting;


import leetcode.CommonFunction;

/**
 * @author: Created by zhanghl
 */
public class DeleteMoreZeroAndEndFillZero {
    public static void main(String[] args) {
        int[] arr = {2, 3, 0, 0, 0, 2, 3, 0, 0, 2, 3, 0, 0};
        int[] newArr = deleteMpreZero(arr);
        CommonFunction.printArr(newArr);
        int[] arr1 = {0, 0, 0, 2, 3, 0, 0, 0, 0};
        newArr = deleteMpreZero(arr1);
        CommonFunction.printArr(newArr);
        int[] arr2 = {0, 0, 0, 2, 0, 0, 0, 0};
        newArr = deleteMpreZero(arr2);
        CommonFunction.printArr(newArr);
        int[] arr3 = {0, 0, 0, 2};
        newArr = deleteMpreZero(arr3);
        CommonFunction.printArr(newArr);
        int[] arr4 = {2, 0, 0, 0, 0};
        newArr = deleteMpreZero(arr4);
        CommonFunction.printArr(newArr);
        int[] arr5 = {2, 0, 3, 0, 4, 0};
        newArr = deleteMpreZero(arr5);
        CommonFunction.printArr(newArr);
    }

    public static int[] deleteMpreZero(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        int first = 1;
        int second = 1;
        while (second < arr.length) {
            if (arr[second] == 0 && arr[second] == arr[second - 1]) {
                second++;
                continue;
            }
            arr[first] = arr[second];
            first++;
            second++;
        }
        while (first < arr.length) {
            arr[first] = 0;
            first++;
        }
        return arr;
    }

}
