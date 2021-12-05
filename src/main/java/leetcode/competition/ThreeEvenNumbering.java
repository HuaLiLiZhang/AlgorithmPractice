package leetcode.competition;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ThreeEvenNumbering {
    public static void main(String[] args) {
        int[] arr = new ThreeEvenNumbering().findEvenNumbers(new int[]{0,2,0,0});
        System.out.println(Arrays.toString(arr));
        int[] arr1 = new ThreeEvenNumbering().findEvenNumbers(new int[]{2,1,3,0});
        System.out.println(Arrays.toString(arr1));
    }
    public int[] findEvenNumbers(int[] digits) {
        Set<Integer> result = new HashSet<Integer>();

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == 0) {
                continue;
            }
            for (int j = 0; j < digits.length; j++) {
                if (j == i) {
                    continue;
                }
                for (int k = 0; k < digits.length; k++) {
                    if (k == i || k == j || digits[k] % 2 != 0) {
                        continue;
                    }
                    int r1 = digits[i] * 100 + digits[j] * 10 + digits[k];
                    result.add(r1);
                }
            }
        }

        int[] newArr = new int[result.size()];
        int index = 0;
        for (int i : result) {
            newArr[index++] = i;
        }
        Arrays.sort(newArr);
        return newArr;
    }
}
