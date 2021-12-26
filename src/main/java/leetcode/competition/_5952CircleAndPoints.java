package leetcode.competition;

/**
 * @author mizhu
 * @date 2021/12/12 10:50
 */
public class _5952CircleAndPoints {
    public static void main(String[] args) {
        String s = "B0B6G0R6R0R6G9";
        System.out.println(new _5952CircleAndPoints().countPoints(s));
    }
    public int countPoints(String rings) {
        if (rings == null || rings.length() < 6 || rings.length() % 2 != 0) {
            return 0;
        }

        int[][] circleCount = new int[10][3];
        for (int i = 1; i < rings.length(); i += 2) {
            int row = (int) rings.charAt(i) - 48;
            if (rings.charAt(i - 1) == 'R') {
                circleCount[row][0] = 1;
            }
            if (rings.charAt(i - 1) == 'G') {
                circleCount[row][1] = 1;
            }
            if (rings.charAt(i - 1) == 'B') {
                circleCount[row][2] = 1;
            }
        }
        int count = 0;
        for (int i = 0; i < circleCount.length; i++) {
            if (circleCount[i][0] == 1 && circleCount[i][1] == 1 && circleCount[i][2] == 1) {
                count += 1;
            }
        }
        return count;

    }
}
