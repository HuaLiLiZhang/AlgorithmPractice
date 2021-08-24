package leetcode.competition;

/**
 * @author: Created by zhanghl
 */
public class CheckOneBifZero {
    public boolean checkZeroOnes1(String s) {
        int oneLen = 0;
        int zeroLen = 0;
        int curOne = 0, curZero = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && s.charAt(i) != s.charAt(i - 1)) {
                curOne = 0;
                curZero = 0;
            }
            if (s.charAt(i) == '1') {
                curOne++;
            } else {
                curZero++;
            }
            oneLen = Math.max(curOne, oneLen);
            zeroLen = Math.max(zeroLen, curZero);
        }

        return oneLen > zeroLen;
    }

    public boolean checkZeroOnes(String s) {
        if (s == null || s.length() <= 0) {
            return false;
        }
        if (!s.contains("1")) {
            return false;
        }
        if (!s.contains("0")) {
            return true;
        }
        String[] split1 = s.split("0");
        String[] split0 = s.split("1");
        int count0 = 0;
        int count1 = 0;
        count0 = getCount0(split0, count0);
        count1 = getCount0(split1, count1);
        return count1 > count0;
    }

    private int getCount0(String[] splitArr, int count) {
        for (String s : splitArr) {
            if (s == null || s.length() <= 0) {
                continue;
            }
            int len = s.length();
            count = Math.max(count, len);
        }
        return count;
    }
}
