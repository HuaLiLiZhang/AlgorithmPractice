package leetcode.competition;

/**
 * @author: Created by zhanghl
 */

//示例 1：
//
//输入：firstWord = "acb", secondWord = "cba", targetWord = "cdb"
//输出：true
//解释：
//firstWord 的数值为 "acb" -> "021" -> 21
//secondWord 的数值为 "cba" -> "210" -> 210
//targetWord 的数值为 "cdb" -> "231" -> 231
//由于 21 + 210 == 231 ，返回 true
//示例 2：
//
//输入：firstWord = "aaa", secondWord = "a", targetWord = "aab"
//输出：false
//解释：
//firstWord 的数值为 "aaa" -> "000" -> 0
//secondWord 的数值为 "a" -> "0" -> 0
//targetWord 的数值为 "aab" -> "001" -> 1
//由于 0 + 0 != 1 ，返回 false
//示例 3：
//
//输入：firstWord = "aaa", secondWord = "a", targetWord = "aaaa"
//输出：true
//解释：
//firstWord 的数值为 "aaa" -> "000" -> 0
//secondWord 的数值为 "a" -> "0" -> 0
//targetWord 的数值为 "aaaa" -> "0000" -> 0
//由于 0 + 0 == 0 ，返回 true
public class ThreeStringIsSumEqual {
    public static void main(String[] args) {
        String firstWord = "acb", secondWord = "cba", targetWord = "cdb";
        System.out.println(new ThreeStringIsSumEqual().isSumEqual(firstWord, secondWord, targetWord));
        firstWord = "aaa";
        secondWord = "a";
        targetWord = "aab";
        System.out.println(new ThreeStringIsSumEqual().isSumEqual(firstWord, secondWord, targetWord));
        firstWord = "aaa";
        secondWord = "a";
        targetWord = "aaaa";
        System.out.println(new ThreeStringIsSumEqual().isSumEqual(firstWord, secondWord, targetWord));


    }

    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int firstNum = getNum(firstWord);
        int secondNum = getNum(secondWord);
        int targetNum = getNum(targetWord);
        if (firstNum + secondNum == targetNum) {
            return true;
        }
        return false;
    }

    public int getNum(String words) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < words.length(); i++) {
            if (i == 0) {
                while (i < words.length() && words.charAt(i) - 'a' == 0) {
                    i++;
                }
                if (i >= words.length()) {
                    return 0;
                }
            }
            stringBuffer.append(words.charAt(i) - 'a');
        }
        return Integer.parseInt(stringBuffer.toString());
    }
}
