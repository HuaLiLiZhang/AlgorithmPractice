package leetcode.competition;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * TODO 类的实现描述
 *
 * @author zhanghuali
 * @version 1.0
 * @ClassName: _6037_competition
 * @CreateTime: 2022/04/10 10:38:49
 * @Description:
 */
public class _6037_competition {


    public static int largestInteger(int num) {
        int[] arr = new int[10];
        Arrays.fill(arr, -1);
        int a = num;
        int index = 9;
        while (a / 10 != 0) {
            arr[index--] = (a % 10);
            a = a / 10;
        }
        arr[index] = a;
        System.out.println(Arrays.toString(Arrays.stream(arr).toArray()));
        System.out.println(index);
        int newNum = 0;
        for (; index < arr.length; index++) {
            int before = arr[index];
            boolean oushu = before % 2 == 0;
            for (int j = index + 1; j < arr.length; j++) {
                before = arr[index];
                int after = arr[j];
                boolean oushu1 = after % 2 == 0;
                if (oushu && oushu1 || (!oushu && !oushu1)) {
                    if (after > before) {
                        swap(arr, index, j);
                    }
                }
            }
            newNum = arr[index] + newNum * 10;
        }


        return newNum;
    }

    private static void swap(int[] arr, int index, int j) {
        int temp = arr[index];
        arr[index] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {


//        System.out.println(largestInteger(1234));
//        System.out.println(largestInteger(4173));
//        System.out.println(largestInteger(65875));
//        System.out.println(minimizeResult("999+999"));
//        System.out.println(minimizeResult("12+34"));
//        System.out.println(minimizeResult("247+38"));
        //示例 1：
        //
        //输入：expression = "247+38"
        //输出："2(47+38)"
        //解释：表达式计算得到 2 * (47 + 38) = 2 * 85 = 170 。
        //注意 "2(4)7+38" 不是有效的结果，因为右括号必须添加在 '+' 的右侧。
        //可以证明 170 是最小可能值。
        //示例 2：
        //
        //输入：expression = "12+34"
        //输出："1(2+3)4"
        //解释：表达式计算得到 1 * (2 + 3) * 4 = 1 * 5 * 4 = 20 。
        //示例 3：
        //
        //输入：expression = "999+999"
        //输出："(999+999)"
        //解释：表达式计算得到 999 + 999 = 1998 。
        int[] arr1 = {0, 4};
        int[] arr2 = {6, 3, 3, 2};
        int[] arr3 = {24,5,64,53,26,38};

        System.out.println(maximumProduct(arr1, 5));
        System.out.println(maximumProduct(arr2, 2));
        System.out.println(maximumProduct(arr3, 54));
        //示例 1：
        //
        //输入：nums = [0,4], k = 5
        //输出：20
        //解释：将第一个数增加 5 次。
        //得到 nums = [5, 4] ，乘积为 5 * 4 = 20 。
        //可以证明 20 是能得到的最大乘积，所以我们返回 20 。
        //存在其他增加 nums 的方法，也能得到最大乘积。
        //示例 2：
        //
        //输入：nums = [6,3,3,2], k = 2
        //输出：216
        //解释：将第二个数增加 1 次，将第四个数增加 1 次。
        //得到 nums = [6, 4, 3, 3] ，乘积为 6 * 4 * 3 * 3 = 216 。
        //可以证明 216 是能得到的最大乘积，所以我们返回 216 。
        //存在其他增加 nums 的方法，也能得到最大乘积。
    }


    // 向表达式添加括号后的最小结果
    public static String minimizeResult(String expression) {
        String[] strArr = expression.split("\\+");
        int first = Integer.parseInt(strArr[0]);
        int end = Integer.parseInt(strArr[1]);
        int[] arrF = new int[10];
        int[] arrE = new int[10];
        int firstIndex = transArr(arrF, first);
        int endIndex = transArr(arrE, end);
        int min = Integer.MAX_VALUE;
        String ex = "";
        int zuoKuoHaoBefore = 0;
        int zuoKuoHaoAfter = 0;
        int youKuoHaoBefore = 0;
        int youKuoHaoAfter = 0;
        int fisrtLen = firstIndex;
        int endLen = endIndex;
        //输入：expression = "247+38"
        for (; firstIndex < arrF.length; firstIndex++) {
            for (int j = endIndex; j < arrE.length; j++) {
                zuoKuoHaoBefore = (firstIndex - fisrtLen == 0) ? -1 : (first / (int) Math.pow(10, arrF.length - firstIndex));
                zuoKuoHaoAfter = (firstIndex - fisrtLen == 0) ? first : (first % (int) Math.pow(10, arrF.length - firstIndex));
                youKuoHaoBefore = (j - endLen == 0) ? end : (end / (int) Math.pow(10, arrE.length - j));
                youKuoHaoAfter = (j - endLen == 0) ? -1 : (end % (int) Math.pow(10, arrE.length - j));
                int temp = 0;
                if (zuoKuoHaoBefore == -1) {
                    temp = (zuoKuoHaoAfter + youKuoHaoBefore);
                } else {
                    temp = zuoKuoHaoBefore * (zuoKuoHaoAfter + youKuoHaoBefore);
                }
                if (youKuoHaoAfter == -1) {
                    temp = temp;
                } else {
                    temp = temp * youKuoHaoAfter;
                }
                if (temp < min) {
                    min = temp;
                    if (zuoKuoHaoBefore == -1) {
                        ex = "(" + zuoKuoHaoAfter;
                    } else {
                        ex = zuoKuoHaoBefore + "(" + zuoKuoHaoAfter;
                    }
                    if (youKuoHaoAfter == -1) {
                        ex += "+" + youKuoHaoBefore + ")";
                    } else {
                        ex += "+" + youKuoHaoBefore + ")" + youKuoHaoAfter;
                    }
                }
            }
        }
        return ex;
    }

    private static int transArr(int[] arr, int a) {
        Arrays.fill(arr, -1);
        int index = 9;
        while (a / 10 != 0) {
            arr[index--] = (a % 10);
            a = a / 10;
        }
        arr[index] = a;
        return index;
    }

    public static int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            priorityQueue.offer(nums[i]);
        }
        while (k > 0) {
            int t = priorityQueue.poll();
            priorityQueue.offer(t + 1);
            k--;
        }
        // 数值较大，使用Long，然后对结果取余数
        long sum = 1;
        while (!priorityQueue.isEmpty()) {
            sum = sum * priorityQueue.poll() % 1000000007;
        }
        return (int)sum;

    }

}
