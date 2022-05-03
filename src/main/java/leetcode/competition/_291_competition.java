package leetcode.competition;

import java.util.*;

/**
 * TODO 类的实现描述
 *
 * @author zhanghuali
 * @version 1.0
 * @ClassName: _291_competition
 * @CreateTime: 2022/05/03 15:35:13
 * @Description:
 */
public class _291_competition {

    public static void main(String[] args) {
        String s = "abbca"; // rs = 28;
        System.out.println(appealSum(s));
        s = "code"; // rs = 20;
        System.out.println(appealSum(s));
        /*int[] nums1 = {2, 3, 3, 2, 2};
        int k = 2, p = 2;
        System.out.println(countDistinct(nums1, k, p));
        int[] nums2 = {1, 2, 3, 4};
        k = 4;
        p = 1;
        System.out.println(countDistinct(nums2, k, p));
        int[] nums3 = {6, 15, 3, 18, 10, 10, 10, 10, 5};
        k = 3;
        p = 16;
        System.out.println(countDistinct(nums3, k, p));
        int[] nums0 = {10, 2, 17, 7, 20};
        k = 1;
        p = 10;
        System.out.println(countDistinct(nums0, k, p));*/
        /*int[] cards = {3, 4, 2, 3, 4, 7};
        int[] cards1 = {1, 0, 5, 3};
        System.out.println(minimumCardPickup(cards));
        System.out.println(minimumCardPickup(cards1));
*/
        /*String number = "123";
        char digit = '3';
        System.out.println(removeDigit(number, digit));
        number = "1231";
        digit = '1';
        System.out.println(removeDigit(number, digit));
        number = "551";
        digit = '5';
        System.out.println(removeDigit(number, digit));*/
    }

    public static String removeDigit(String number, char digit) {
        String max = "";
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (c == digit) {
                String temp = number.substring(0, i) + number.substring(i + 1);
                max = max.compareTo(temp) > 0 ? max : temp;
            }
        }
        return max;
    }


    public static int minimumCardPickup(int[] cards) {
        /*int len = cards.length;
        int[][] dp = new int[len + 1][len + 1];
        for (int i = 0; i < len + 1; i++) {
            dp[0][i] = i;
            dp[i][0] = i;
        }*/
        Map<Integer, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < cards.length; i++) {
            int c = cards[i];
            if (map.containsKey(c)) {
                Integer re = map.get(c);
                min = Math.min(min, i - re + 1);
                map.put(c, i);
            } else {
                map.put(c, i);
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }

    public static int countDistinct(int[] nums, int k, int p) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            String temp = nums[i] + "_";
            int zenchu = nums[i] % p;
            boolean addT = zenchu != 0 || k >= 1;
            if (addT) {
                result.add(temp);
            }
            String before = temp;
            int cnt = zenchu != 0 ? 0 : 1;
            for (int j = i + 1; j < nums.length; j++) {
                before += nums[j] + "_";
                cnt = nums[j] % p == 0 ? cnt + 1 : cnt;
                addT = k >= cnt;
                if (addT) {
                    result.add(before);
                }
            }
        }
        System.out.println(String.join(",", result));
        return result.size();
    }

    /**
     提示 1-1
     将所有子串按照其末尾字符的下标分组。

     提示 1-2
     考虑两组相邻的子串：以 s[i−1] 结尾的子串、以 s[i] 结尾的子串。

     提示 1-3
     以 s[i] 结尾的子串，可以看成是以s[i−1] 结尾的子串，在末尾添加上 s[i] 组成。

     上面这一串提示是思考子串统计类问题的通用技巧之一。

     提示 2-1
     从左往右遍历 ss，考虑将 s[i] 添加到以 ]s[i−1] 结尾的子串的末尾。添加后，这些子串的引力值会增加多少？

     提示 2-2
     分类讨论：

     如果 s[i] 之前没有遇到过，那么这些子串的引力值都会增加 1，这些子串的引力值之和会增加 i，再加上 1，即 s[i] 单独组成的子串的引力值；
     如果 s[i] 之前遇到过，设其上次出现的下标为 j，那么向子串 s[0..i-1],\ s[1..i-1],\ s[2..i-1],s[j..i-1] 的末尾添加 s[i]后，这些子串的引力值是不会变化的，
     因为 s[i] 已经在s[j] 处出现过了；而子串 s[j+1..i-1],\ s[j+2..i-1],s[i-1..i-1]由于不包含字符 s[i]，这些子串的引力值都会增加 1，因此有i−j−1 个子串的引力值会增加 1，这些子串的引力值之和会增加 i−j−1，再加上 1，即 s[i] 单独组成的子串的引力值。
     模拟上述过程，遍历 ss 的过程中用一个变量 sumG 维护以 s[i] 结尾的子串的引力值之和，同时用一个数组 pos 记录每个字符最近一次出现的下标。
     累加遍历中的 sumG 即为答案。
     **/
    public static long appealSum(String s) {
        long sum = 0;
        long ans = 0;
        int[] idx = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            sum += i - idx[index] + 1;
            ans += sum;
            idx[index] = i + 1;
        }
        return ans;
    }

    public static int countDistinct_超时(int[] nums, int k, int p) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> temp = Collections.singletonList(nums[i]);
            int zenchu = nums[i] % p;
            boolean addT = zenchu != 0 || k >= 1;
            boolean isContains = result.contains(temp);
            if (!isContains && addT) {
                result.add(temp);
            }
            List<Integer> before = new ArrayList<>(temp);
            int cnt = zenchu != 0 ? 0 : 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (isContains || !addT) {
                    before = new ArrayList<>(before);
                } else {
                    before = new ArrayList<>(result.get(result.size() - 1));
                }
                before.add(nums[j]);
                isContains = result.contains(before);
                cnt = nums[j] % p == 0 ? cnt + 1 : cnt;
                addT = k >= cnt;
                if (!isContains && addT) {
                    result.add(before);
                }
            }
        }
        return result.size();
    }

    public static boolean canZenChuCount(List<Integer> list, int k, int p) {
        int c = 0;
        for (int i : list) {
            if (i % p == 0) {
                c++;
            }
        }
        return c <= k;
    }


}
