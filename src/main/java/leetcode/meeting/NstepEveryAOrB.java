package leetcode.meeting;

/**
 * @author: Created by zhanghl
 */
public class NstepEveryAOrB {
    private final static NstepEveryAOrB nstepEveryAOrB = new NstepEveryAOrB();

    private static class company {
        private static NstepEveryAOrB nstepEveryAOrB = new NstepEveryAOrB();
    }

    public static NstepEveryAOrB getInstance() {
        return company.nstepEveryAOrB;
    }

    public static void main(String[] args) {
        System.out.println(getAllCombanation(3, 1, 2));
        System.out.println(getAllCombanation(3, 1, 3));
        System.out.println(getAllCombanation(6, 1, 2));
        System.out.println(getAllCombanation(6, 3, 2));
        System.out.println(getCombatiionsCount(3, 1, 2)); //默认a = 1, b = 2
        System.out.println(getCombatiionsCount(6, 1, 2)); //默认a = 1, b = 2
    }

    //这种方式，从顶向下的方式，也就是先算fn = fn-1 + fn-2, 而fn-1 = fn-2 + fn-3，有很多重复计算，
    // 正确的应该从底向上。
    public static int getAllCombanation(int n, int a, int b) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        int fa = getAllCombanation(n - a, a, b);
        int fb = getAllCombanation(n - b, a, b);
        return fa + fb;
    }

    /**
     * @Description: 自底向上计算，这里默认a = 1, b = 2;
     * @Param: [n, a, b]
     */
    public static int getCombatiionsCount(int n, int a, int b) {
        a = 1;
        b = 2;
        int p = 0;
        int q = 0;
        int r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
