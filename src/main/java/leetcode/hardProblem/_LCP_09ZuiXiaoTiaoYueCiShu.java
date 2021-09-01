package leetcode.hardProblem;

//为了给刷题的同学一些奖励，力扣团队引入了一个弹簧游戏机。游戏机由 N 个特殊弹簧排成一排，编号为 0 到 N-1。初始有一个小球在编号 0 的弹簧处。若小球
//在编号为 i 的弹簧处，通过按动弹簧，可以选择把小球向右弹射 jump[i] 的距离，或者向左弹射到任意左侧弹簧的位置。也就是说，在编号为 i 弹簧处按动弹簧，
//小球可以弹向 0 到 i-1 中任意弹簧或者 i+jump[i] 的弹簧（若 i+jump[i]>=N ，则表示小球弹出了机器）。小球位于编号 0 处的弹簧时不
//能再向左弹。 
//
// 为了获得奖励，你需要将小球弹出机器。请求出最少需要按动多少次弹簧，可以将小球从编号 0 弹簧弹出整个机器，即向右越过编号 N-1 的弹簧。 
//
// 示例 1： 
//
// 
// 输入：jump = [2, 5, 1, 1, 1, 1] 
//
// 输出：3 
//
// 解释：小 Z 最少需要按动 3 次弹簧，小球依次到达的顺序为 0 -> 2 -> 1 -> 6，最终小球弹出了机器。 
// 
//
// 限制： 
//
// 
// 1 <= jump.length <= 10^6 
// 1 <= jump[i] <= 10000 
// 
// Related Topics 广度优先搜索 线段树 数组 动态规划 
// 👍 54 👎 0


import java.util.LinkedList;
import java.util.Queue;

public class _LCP_09ZuiXiaoTiaoYueCiShu {
    public static void main(String[] args) {
        Solution solution = new _LCP_09ZuiXiaoTiaoYueCiShu().new Solution();
        System.out.println(solution.minJump(new int[]{2, 5, 1, 1, 1, 1}));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //向右扩展： idx -> idx + jump[idx]
        //向左扩展： {0， idx-1}
        //广度优先搜索
        public int minJump(int[] jump) {
            int n = jump.length;
            Queue<int[]> queue = new LinkedList<>(); //当前的idx的位置以及调动的次数d
            queue.add(new int[]{0, 0});//初始化第一个位置0，按动次数为0
            //某一个位置已经被扩展那么就不需要继续扩展，BFS最短路
            //记录某个位置已经被扩展了，设置为true
            boolean[] seen = new boolean[n];
            seen[0] = true;
            int preidx = 0;
            while (!queue.isEmpty()) {
                int[] auto = queue.poll();
                //向右扩展
                int next = auto[0] + jump[auto[0]];
                if (next > n - 1) {
                    return auto[1] + 1;
                }
                if (!seen[next]) {
                    seen[next] = true;
                    queue.add(new int[]{next, auto[1] + 1});
                }
                //向左扩展
                //可以从for(0, -> auto[0])遍历，将没有没扩展的位置进行扩展，O(n^2)
                //设置当前位置为true，并将位置和次数加入queue
                //优化
                while (preidx < auto[0]) {
                    if (!seen[preidx]) {
                        seen[preidx] = true;
                        queue.add(new int[]{preidx, auto[1] + 1});
                    }
                    preidx++;
                }
            }
            //最终小球无法跳跃到最后的位置，那么返回-1
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}