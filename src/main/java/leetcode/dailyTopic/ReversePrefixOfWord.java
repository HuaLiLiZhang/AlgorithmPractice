package leetcode.dailyTopic;

import com.sun.deploy.util.StringUtils;
import jdk.internal.util.Preconditions;

import java.util.Objects;

/**
 * 翻转字符串前缀
 *
 * @author zhanghuali
 * @version 1.0
 * @ClassName: ReversePrefixOfWord
 * @CreateTime: 2022/02/02 14:07:30
 * @Description:
 */
public class ReversePrefixOfWord {
    public static void main(String[] args) {
        String word = "abcdefd";
        char ch = 'd';
        System.out.println(new ReversePrefixOfWord().reversePrefix(word, ch));
    }

    public String reversePrefix(String word, char ch) {
        if (word == null || word.length() <= 0) {
            return word;
        }
        int index = word.indexOf(ch);
        if (index <= -1) {
            return word;
        }
        int start = index;
        StringBuffer sb = new StringBuffer();
        while (index >= 0) {
            sb.append(word.charAt(index));
            index--;
        }
        sb.append(word.substring(start + 1));
        return sb.toString();
    }

}
