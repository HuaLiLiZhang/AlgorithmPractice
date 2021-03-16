package leetcode.Job.DoublePoint;

/**
 * @author: Created by zhanghl
 */
public class _05StringReplaceSpace {
    public static void main(String[] args) {
        ReplaceSpace replaceSpace = new _05StringReplaceSpace().new ReplaceSpace();
        System.out.println(replaceSpace.replaceBlack("We are h"));

    }

    class ReplaceSpace {
        /**
         * @Description: 首先扩充数组到每个空格替换成"%20"之后的大小。
         * <p>
         * 然后从后向前替换空格，也就是双指针法，过程如下：
         * <p>
         * i指向新长度的末尾，j指向旧长度的末尾。
         * <p>
         * 从前向后填充就是O(n^2)的算法了，因为每次添加元素都要将添加元素之后的所有元素向后移动。
         * <p>
         * 其实很多数组填充类的问题，都可以先预先给数组扩容带填充后的大小，然后在从后向前进行操作。
         * @Param: [s]
         */
        public String replaceBlack(String s) {
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    count++;
                }
            }
            char[] newS = new char[s.length() + count * 2];
            int j = newS.length - 1;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == ' ') {
                    newS[j] = '0';
                    newS[--j] = '2';
                    newS[--j] = '%';
                    j--;
                    continue;
                }
                newS[j] = s.charAt(i);
                j--;
            }
            return new String(newS);
        }
    }
}
