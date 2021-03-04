package leetcode.Job.HashStruct;

//编写一个算法来判断一个数 n 是不是快乐数。 
//
// 「快乐数」定义为： 
//
// 
// 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。 
// 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。 
// 如果 可以变为 1，那么这个数就是快乐数。 
// 
//
// 如果 n 是快乐数就返回 true ；不是，则返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：19
//输出：true
//解释：
//12 + 92 = 82
//82 + 22 = 68
//62 + 82 = 100
//12 + 02 + 02 = 1
// 
//
// 示例 2： 
//
// 
//输入：n = 2
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 231 - 1 
// 
// Related Topics 哈希表 数学 
// 👍 541 👎 0


import java.util.HashSet;
import java.util.Set;

public class _202HappyNumber {
    public static void main(String[] args) {
        Solution solution = new _202HappyNumber().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @Description: 题目中说了会 「无限循环」，那么也就是说「求和的过程中，sum会重复出现，这对解题很重要！」
         * <p>
         * 「当我们遇到了要快速判断一个元素是否出现集合里的时候，就要考虑哈希法了。」
         * <p>
         * 所以这道题目使用哈希法，来判断这个sum是否重复出现，如果重复了就是return false， 否则一直找到sum为1为止。
         * @Param: [n]
         */
        public boolean isHappy(int n) {
            Set<Integer> allNum = new HashSet<>();
            while (true) {
                int sum = getNumSum(n);
                if (sum == 1) {
                    return true;
                } else {
                    if (allNum.contains(sum)) {
                        return false;
                    } else {
                        allNum.add(sum);
                    }
                }
                n = sum;
            }
        }

        private int getNumSum(int n) {
            int sum = 0;
            while (n != 0) {
                sum += (n % 10) * (n % 10);
                n = n / 10;
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}