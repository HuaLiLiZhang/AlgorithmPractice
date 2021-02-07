package leetcode.hot100;

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 深度优先搜索 递归 字符串 回溯算法 
// 👍 1119 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _17LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new _17LetterCombinationsOfAPhoneNumber().new Solution();
        String str1 = "";
        String str2 = "2";
        String str3 = "23";
        System.out.println(solution.letterCombinations(str1));
        System.out.println(solution.letterCombinations(str2));
        System.out.println(solution.letterCombinations(str3));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 数字到号码的映射
        final HashMap<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        //回溯函数
        public List<String> letterCombinations(String digits) {
            List<String> conbiantions = new ArrayList<String>();
            StringBuilder conbiantion = new StringBuilder();
            if (digits == null || digits.length() <= 0) {
                return conbiantions;
            }

            backtrack(digits, conbiantions, 0, conbiantion, phoneMap);
            return conbiantions;
        }

        // 此题只能用回溯法来做，不能用for，因为输入数字的个数不确定
// 时间复杂度O(3^m + 4^n) m为输入数字中对应三个字符的个数，n为四个
// 空间复杂度O(m + n) 层数最大为数字的长度
// 为什么不能用for循环来做？因为输入数字个数不确定，所以不知道有几层for循环，所以做不了
// 为了遍历所有可能，这题只能用回溯法来做
// 首先可以画出一个树的结构，树根是出发点，第一层表示第一个数字代表的字母，以此类推
// 我们用index表示当前遍历到的层数，初始化index = 0表示现在我们准备考察第一个数字
// 在回溯的过程中，我们需要准备两个东西，一个是ans用来保存最终的答案，还有一个是str
// ans是，当发现回溯出界的时候，即访问到叶子的下一层时，我们将那个时刻的str放入ans
// 而str就是一个动态的字符串，可以理解为是一个stack<char>，层数增加是入栈，返回上一层时出栈
// 如何写好traceback函数呢？首先得确定这个函数得有哪些东西？
// 1. 为了确定每一层都有哪些字母，我们需要输入digits和keyboard
// 2. 为了更新ans和str，我们需要输入这俩哥们儿
// 3. 为了知道现在到哪一层了，我们还得输入index以表示当前层数
// 函数里面的内容应该首先判断边界条件？这很重要，何为边界条件？即啥时候往上跳？
// 我们发现，当我们访问到叶子的下一层时，就该回溯了，此时回溯到的是叶子！
// 此时的index绝对等于digits.size()，因为叶子哪一层是digits.size() - 1
// 如果发现到叶子的下一层了，我们就该更新ans，因为我们发现了一个新的答案，同时往上跳
// 你可能要问了，我能不能在digits.size()这一层的时候就让str弹栈啊？答案是不阔以！
// 你想的很美，觉得此时这一种可能性已经结束了，就想着弹栈。但是你想一下弹栈的时刻仅仅只包括从index到index - 1这一种情况吗？
// 对于叶子结点，由于其没有孩子了，所以在叶子节点处对应的弹栈就是就是从index回来后，我们需要将叶子节点弹出来
// 对于内部结点，我们在什么情况下应该将内部节点弹栈呢？答案是：所有儿子孙子重孙子都被访问过的时候！
// 上面这句话好难理解啊！为此，我们定义：状态 = 结点的位置 + 所有子树是否遍历完毕，结点确定了层数也就确定了，而“所有子树是否遍历完毕”代表是“继续向下”（未进入子树），或者是“刚从子树回到该结点”（子树全部遍历完毕）
// 每种状态是唯一的，并且所有结点（包括内部和叶子）都只会被访问两次，分别对应上面讲的两种状态
// 说白了，我们就是在对所有“状态”进行遍历
// 当“当前状态”为“所有子树遍历完毕”时，就说明以当前状态作为初始状态搜索的所有可能性已经被我们挖掘干净，就要把当前节点给踢出去，即对str进行弹栈
// 回到上面的主题，所以你现在明白为什么不能“只”在index -> index - 1的弹栈了吧？
// 可能又有人要问了，如何用变成语言来描述上面讲的“所有子树遍历完毕”呢？
// 对于每个结点，如果我们发现不是在index层，第一步就是先找出这一层有几种可能性，即keyboard[digits[index]]
// 有了上面的这个string（每个字符代表一种可能性），我们就能往下探索了
// 为什么是往下探索？想一想我们现在的“状态”！是不是属于当前节点的状态1（即未进入子树）？
// 下面就是进入子树，然后对子树进行相同的操作，那traceback的本质就是用来考察第index层的
// 在进入每一个子树之前，我们要先把当前这种可能存进str，然后再进入子树（即调用自身）
// 如果上面的“调用自身”结束了，就说明“那个子树进入了状态2”，就要往上跳，那个子树没用了，就要对str进行弹栈
        private void backtrack(String digits, List<String> conbiantions, int index, StringBuilder conbiantion, HashMap<Character, String> phoneMap) {
            if (index == digits.length()) {
                conbiantions.add(conbiantion.toString());
                return;
            } else {
                char digit = digits.charAt(index);
                String letters = phoneMap.get(digit);
                for (int i = 0; i < letters.length(); i++) {
                    conbiantion.append(letters.charAt(i));
                    backtrack(digits, conbiantions, index + 1, conbiantion, phoneMap);
                    conbiantion.deleteCharAt(index);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}