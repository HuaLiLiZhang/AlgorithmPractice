package leetcode.competition;

/**
 * @author mizhu
 * @date 2021/10/17 10:36
 */
public class _5907CheckSentenceNumIsIncres {
    public static void main(String[] args) {
        String s = "hello world 5 x 5";
        System.out.println(areNumbersAscending(s));
        s = "unset is at 7 51 pm overnight lows will be in the low 50 and 60 s";
        System.out.println(areNumbersAscending(s));
        s = "1 box has 3 blue 4 red 6 green and 12 yellow marbles";
        System.out.println(areNumbersAscending(s));
        s = "4 5 11 26";
        System.out.println(areNumbersAscending(s));
    }

    public static boolean areNumbersAscending(String s) {
        int cur = Integer.MAX_VALUE;
        int pre = Integer.MIN_VALUE;
        StringBuffer sb = new StringBuffer();
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                sb.append(c);
                continue;
            }
            if (c == ' ' && sb.length() > 0) {
                cur = Integer.parseInt(sb.toString());
                if (cur <= pre) {
                    return false;
                }
                pre = cur;
                cur = 0;
                sb = new StringBuffer();
                continue;
            }
        }
        if (sb.length() > 0) {
            cur = Integer.parseInt(sb.toString());
            if (cur <= pre) {
                return false;
            }
        }
        return true;
    }
}
