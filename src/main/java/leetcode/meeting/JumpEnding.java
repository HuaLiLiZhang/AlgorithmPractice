package leetcode.meeting;

/**
 * @author: Created by zhanghl
 */
public class JumpEnding {
    public static void main(String[] args) {
        int[] arr = {2, 1, 0, 3};
        System.out.println(canJumpEnd(arr));
        int[] arr1 = {2, 2, 0, 3};
        System.out.println(canJumpEnd(arr1));
        int[] arr2 = {2, 2, 0, 1, 2, 0, 1};
        System.out.println(canJumpEnd(arr2));
        int[] arr3 = {5, 4, 3, 2, 1, 0, 3};
        System.out.println(canJumpEnd(arr3));
        int[] arr4 = {5, 2, 1};
        System.out.println(canJumpEnd(arr4));
    }

    public static boolean canJumpEnd(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return true;
        }
        int curMax = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int curDistance = i + arr[i];
            if (curDistance >= arr.length - 1) {
                return true;
            }
            curMax = Math.max(curMax, curDistance);
            if (curMax <= i) {
                return false;
            }
        }
        return true;
    }
}
