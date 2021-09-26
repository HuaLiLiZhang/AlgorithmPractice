package leetcode.middleProblem;

//给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。 
//
// （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。） 
//
// 示例 1: 
//
// 输入: N = 10
//输出: 9
// 
//
// 示例 2: 
//
// 输入: N = 1234
//输出: 1234
// 
//
// 示例 3: 
//
// 输入: N = 332
//输出: 299
// 
//
// 说明: N 是在 [0, 10^9] 范围内的一个整数。 
// Related Topics 贪心 数学 
// 👍 207 👎 0


public class _738MonotoneIncreasingDigits {
    public static void main(String[] args) {
        Solution solution = new _738MonotoneIncreasingDigits().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //局部最优：遇到strNum[i - 1] > strNum[i]的情况，让strNum[i - 1]--，然后strNum[i]给为9，
        // 可以保证这两位变成最大单调递增整数。
        //
        //全局最优：得到小于等于N的最大单调递增的整数。
        //
        //但这里局部最优推出全局最优，还需要其他条件，即遍历顺序，和标记从哪一位开始统一改成9。
        //从后往前遍历，那么要记住从哪个位置开始变9，
        // 因为一旦哪个位置变9，前面的位置只要出现一个不是升序的数字，后面都得变9,因为要满足<=后面的

        public int monotoneIncreasingDigits(int n) {
            String s = String.valueOf(n);
            String[] num = s.split("");
            int flag = num.length;//记录从哪个位置开始变9，因为前面只要有一个位置不是升序的数字，那么后面都必须为9才能保证最大
            for (int i = num.length - 1; i > 0; i--) {
                if (Integer.parseInt(num[i - 1]) > Integer.parseInt(num[i])) {
                    flag = i;
                    num[i - 1] = Integer.parseInt(num[i - 1]) - 1 + "";
                }
            }
            for (int i = flag; i < num.length; i++) {
                num[i] = "9";
            }
            return Integer.parseInt(String.join("", num));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}