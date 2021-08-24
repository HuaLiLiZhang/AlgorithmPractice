package leetcode.meeting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: Created by zhanghl
 */
public class TopNStringBySort {
    public static void main(String[] args) {

        PriorityQueue<AlphaCount> priorityQueue = new PriorityQueue<AlphaCount>((a, b) -> {
            if (a.count != b.count) {
                //求最大的topN应该用大顶堆，大顶堆的根节点存的是树上节点最大值，
                // 如果新加入的节点值大于根节点的值，
                // 那么替换根节点的值，重新构造大顶堆，大顶堆代表着降序，所以使用b - a

                //求最小的topN应该建立小顶堆，小顶堆的根节点存的是树上节点的最小值，
                // 如果新加入的节点小于根节点的值，替换根节点的值，所以小顶堆代表着升序，所以a-b。
                return a.count - b.count;//升序 a-b, 降序b-a
            } else {
                return b.str.compareTo(a.str);
            }
        });
        priorityQueue.add(new AlphaCount("a", 3));
        priorityQueue.add(new AlphaCount("f", 4));
        priorityQueue.add(new AlphaCount("g", 5));
        priorityQueue.add(new AlphaCount("d", 3));
        priorityQueue.add(new AlphaCount("c", 3));
        priorityQueue.poll();
        priorityQueue.poll();
        priorityQueue.poll();
        priorityQueue.poll();
        System.out.println(priorityQueue);
    }

}

class AlphaCount {
    String str;
    int count;

    public AlphaCount(String str, int count) {
        this.count = count;
        this.str = str;
    }
}
