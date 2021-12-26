package leetcode.competition;


/**
 * @author mizhu
 * @date 2021/12/12 11:38
 */
public class _5953SubArrayRangeSum {
    public static void main(String[] args) {
        //int[] nums = {4, -2, -3, 4, 1};
        int[] nums = {-31372, 86677, 70463, 37727, -91683, -41347, -90576, -82174, -84198, -5148, -12591, -34156, 49770, 9666, -77075, -57678, -31101, -47531, -86306, -91337, -89507, -24917, -87692, -39171, 98075, 17787, -42549, 34352, -70752, 71832, 70055, -1026, 3784, 2190, -36669, 959, 50619, 97129, 77088, 54049, 51707, 72052, 59230, -96834, -14048, -9619, 84853, 99362, 69885, 74086, -28737, 23060, -63323, 13156, -72998, 94336, -75409, 58266, -86800, -54564, 80773, 40687, -47207, 43609, -56556, 21192, -48024, -58907, 1629, -65561, -68397, 31862, -2201, -34966, 43542, -59201, -3637, -21936, -93559, 49435, 23249, -54299, 70508, -90795, -3620, -33894, 43927, 10208, -7390, 86931, 48175, 81859, 95058, -16614, 38066, -99361, 63621, -99285, -47111, 29933, 73901, 60455, 46586, -84117, 35256, -89853, 33383, -91662, 82979, -48835, -93877, -80929, -98904, -47773, 69451, 85183, -14449, -51496, 75765, 35062, 12456, 35254, -16363, 80792, -3414, 9244, 62961, -52057, 56344, -50277, -26870, -63323, 54993, 75596, -93637, -78526, -3058, -30560, 82233, -50795, -5290, -641, -83040, 13524, 86725, 23735, 29280, 43938, -43995, -8992, -83717, -62090, 74538, 58682, -56550, -8638, 61528, -87974};
        //2293369763
        System.out.println(new _5953SubArrayRangeSum().subArrayRanges(nums));
    }

    public long subArrayRanges(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        long sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int min = nums[i];
            int max = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                sum += max - min;
            }
        }
        return sum;
    }

    //超时呀,什么鬼思路，太怪啦！！！！！！！！！
    public long subArrayRanges_1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        long sum = 0;
        for (int i = 1; i < nums.length; i++) {

            for (int j = 0; j < nums.length; j++) {
                if (j + i < nums.length) {
                    sum += getMaxSubtractMin(nums, j, j + i);

                }
            }
        }
        return sum;
    }

    private int getMaxSubtractMin(int[] nums, int from, int to) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = from; i <= to; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        if (max == Integer.MIN_VALUE || min == Integer.MAX_VALUE) {
            return 0;
        }
        return max - min;
    }

}
