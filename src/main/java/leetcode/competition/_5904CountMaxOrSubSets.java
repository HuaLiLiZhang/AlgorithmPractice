package leetcode.competition;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author mizhu
 * @date 2021/10/17 11:14
 */
public class _5904CountMaxOrSubSets {
    public static void main(String[] args) {
        int[] nums1 = {3, 1};
        System.out.println(new _5904CountMaxOrSubSets().countMaxOrSubsets(nums1));
        int[] nums = {3, 2, 1, 5};
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(3);
        arrayList.add(2);
        arrayList.add(1);
        arrayList.add(5);
        System.out.println(new _5904CountMaxOrSubSets().getOrInt(arrayList));
        System.out.println(new _5904CountMaxOrSubSets().countMaxOrSubsets(nums));

        int[] nums2 = {2, 2, 2};
        System.out.println(new _5904CountMaxOrSubSets().countMaxOrSubsets(nums2));
    }

    public int countMaxOrSubsets(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }
        getAllMaxCount(nums, 0, new ArrayList<Integer>());
        return count;
    }

    private void getAllMaxCount(int[] nums, int index, ArrayList<Integer> subSet) {
        if (!subSet.isEmpty()) {
            int temp = getOrInt(subSet);
            if (temp > curVal) {
                count = 1;
                curVal = temp;
            } else if (temp == curVal) {
                count++;
            }
        }
        for (int i = index; i < nums.length; i++) {
            subSet.add(nums[i]);
            getAllMaxCount(nums, i + 1, subSet);
            subSet.remove(subSet.size() - 1);
        }
    }

    private int getOrInt(ArrayList<Integer> subSet) {
        int sum = subSet.get(0);
        for (int i : subSet) {
            sum |= i;
        }
        return sum;
    }

    private int count = 0;
    private int curVal = 0;
}
