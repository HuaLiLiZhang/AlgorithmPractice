package leetcode.Job.CharacterString;

//字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数
//将返回左旋转两位得到的结果"cdefgab"。 
//
// 
//
// 示例 1： 
//
// 输入: s = "abcdefg", k = 2
//输出: "cdefgab"
// 
//
// 示例 2： 
//
// 输入: s = "lrloseumgh", k = 6
//输出: "umghlrlose"
// 
//
// 
//
// 限制： 
//
// 
// 1 <= k < s.length <= 10000 
// 
// Related Topics 字符串 
// 👍 89 👎 0


public class _Offer58_IIZuoXuanZhuanZiFuChuanLcof {
    public static void main(String[] args) {
        Solution solution = new _Offer58_IIZuoXuanZhuanZiFuChuanLcof().new Solution();
        System.out.println(solution.reverseLeftWords("adcdefg", 2));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseLeftWords(String s, int n) {
            //局部反转+整体反转 达到左旋转的目的
            //可以先局部翻转，在整体翻转前n个和后n个
            char[] c = s.toCharArray();
            reverseStr(c, 0, n - 1);
            reverseStr(c, n, s.length() - 1);
            reverseStr(c, 0, s.length() - 1);
            return new String(c);
        }

        private void reverseStr(char[] c, int l, int r) {
            while (l < r) {
                char tmp = c[l];
                c[l] = c[r];
                c[r] = tmp;
                l++;
                r--;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}