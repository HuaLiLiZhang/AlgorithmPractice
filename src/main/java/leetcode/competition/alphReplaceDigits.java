package leetcode.competition;

/**
 * @author: Created by zhanghl
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().replaceDigits("a1c1e1"));
    }

    public String replaceDigits(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                stringBuilder.append(s.charAt(i));
            } else {
                stringBuilder.append(move(s.charAt(i - 1), String.valueOf(s.charAt(i))));
            }
        }
        return stringBuilder.toString();
    }

    private char move(char start, String modeInt) {
        return (char) (97 + (start - 'a') + Integer.parseInt(modeInt));
    }
}
