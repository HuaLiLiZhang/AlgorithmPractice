package leetcode;

import java.util.Arrays;

/**
 * @author: Created by zhanghl
 */
public class CommonFunction {
    public static void printArr(int[] arr) {
        Arrays.stream(arr).forEach(System.out::print);
        System.out.println();
    }
}
