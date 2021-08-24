package leetcode.competition;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author: Created by zhanghl
 */
public class SeatManager {
    public static void main(String[] args) {
        SeatManager seatManager = new SeatManager(3); // 初始化 SeatManager ，有 5 个座位。
        System.out.println(seatManager.reserve());    // 所有座位都可以预约，所以返回最小编号的座位，也就是 1 。
        System.out.println(seatManager.reserve());     // 可以预约的座位为 [2,3,4,5] ，返回最小编号的座位，也就是 2 。
        seatManager.unreserve(1); // 将座位 2 变为可以预约，现在可预约的座位为 [2,3,4,5] 。
        seatManager.unreserve(2); // 将座位 2 变为可以预约，现在可预约的座位为 [2,3,4,5] 。
        System.out.println(seatManager.reserve());    // 可以预约的座位为 [2,3,4,5] ，返回最小编号的座位，也就是 2 。
        seatManager.unreserve(1); // 将座位 2 变为可以预约，现在可预约的座位为 [2,3,4,5] 。
        System.out.println(seatManager.reserve());    // 可以预约的座位为 [3,4,5] ，返回最小编号的座位，也就是 3 。
        seatManager.unreserve(1); // 将座位 2 变为可以预约，现在可预约的座位为 [2,3,4,5] 。
    }

    private final Integer nSeat;
    private Integer low = 0;
    boolean[] arr;

    public SeatManager(int n) {
        nSeat = n;
        arr = new boolean[nSeat];
        Arrays.fill(arr, true);
    }

    public int reserve() {
        if (low < nSeat && arr[low]) {
            arr[low] = false;
            low++;
            return low;
        }
        for (int i = low + 1; i < nSeat; i++) {
            if (arr[i]) {
                arr[i] = false;
                low = i + 1;
                return low;
            }
        }
        return low;
    }

    public void unreserve(int seatNumber) {
        if (seatNumber - 1 < nSeat) {
            arr[seatNumber - 1] = true;
            low = Math.min(seatNumber - 1, low);
        }
    }
}
