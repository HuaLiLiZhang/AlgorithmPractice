package leetcode.competition;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: Created by zhanghl
 */
public class SubsetXORSum1 {
    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 6, 7, 8};
//
        //5,1,6    28
        //{1, 3};     6
        System.out.println(new SubsetXORSum1().subsetXORSum(nums));
    }

    int sum = 0;

    public int subsetXORSum(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        Arrays.sort(nums);
        getAllSubSetsXORSum(nums, 0, new ArrayList<>());
        return sum;
    }

    private void getAllSubSetsXORSum(int[] nums, int startIndex, ArrayList<Integer> subSet) {
        sum += getSum(subSet);
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            /*if (i != startIndex && nums[i - 1] == nums[i]) {
                continue;
            }*/
            subSet.add(nums[i]);
            getAllSubSetsXORSum(nums, i + 1, subSet);
            subSet.remove(subSet.size() - 1);
        }
    }

    private int getSum(ArrayList<Integer> subSet) {
        if (subSet.size() == 0) {
            return 0;
        }
        if (subSet.size() == 1) {
            return subSet.get(0);
        } else {
            int sum = subSet.get(0);
            for (int i = 1; i < subSet.size(); i++) {
                sum ^= subSet.get(i);
            }
            return sum;
        }
    }
}
