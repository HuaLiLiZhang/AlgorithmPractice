package leetcode.competition;

import java.util.PriorityQueue;

/**
 * @author: Created by zhanghl
 */
public class ProcessTask {
    private class Server {
        int index;
        int priority;
        int freeTime;

        public Server(int i, int priority, int freeTime) {
            this.index = i;
            this.priority = priority;
            this.freeTime = freeTime;
        }
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<Server> freeQueue = new PriorityQueue<>((s1, s2) -> {
            if (s1.priority != s2.priority) {
                return s1.priority - s2.priority;
            } else {
                return s1.index - s2.index;
            }
        });
        // 存放当前不空闲的server
        PriorityQueue<Server> busyQueue = new PriorityQueue<>((s1, s2) -> {
            if (s1.freeTime != s2.freeTime) {
                return s1.freeTime - s2.freeTime;
            } else if (s1.priority != s2.priority) {
                return s1.priority - s2.priority;
            } else {
                return s1.index - s2.index;
            }
        });
        for (int i = 0; i < servers.length; i++) {
            Server server = new Server(i, servers[i], 0);
            freeQueue.offer(server);
        }

        int[] ans = new int[tasks.length];
        for (int i = 0; i < ans.length; i++) {
            while (!busyQueue.isEmpty() && busyQueue.peek().freeTime <= i) {
                freeQueue.offer(busyQueue.poll());
            }
            Server curSer;
            if (freeQueue.isEmpty()) {
                curSer = busyQueue.poll();
                curSer.freeTime = curSer.freeTime + tasks[i];
            } else {
                curSer = freeQueue.poll();
                curSer.freeTime = i + tasks[i];
            }
            ans[i] = curSer.index;

            if (curSer.freeTime > i + 1) {
                busyQueue.offer(curSer);
            } else {
                freeQueue.offer(curSer);
            }
        }

        return ans;
    }

    public String maxValue(String n, int x) {
        boolean positive = true;
        if (n.charAt(0) == '-') {
            positive = false;
        }

        if (positive) {
            for (int i = 0; i <= n.length() - 1; i++) {
                if (n.charAt(i) - '0' < x) {
                    return n.substring(0, i) + x + n.substring(i);
                }
            }
            return n + x;
        } else {
            for (int i = 1; i <= n.length() - 1; i++) {
                if (n.charAt(i) - '0' > x) {
                    return n.substring(0, i) + x + n.substring(i);
                }
            }
            return n + x;
        }
    }

    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int index = 0;
        int first = 0, second = 0, target = 0;
        for (; index < firstWord.length(); index++) {
            first = first * 10 + firstWord.charAt(index) - 'a';
        }

        index = 0;
        for (; index < secondWord.length(); index++) {
            second = second * 10 + secondWord.charAt(index) - 'a';
        }

        index = 0;
        for (; index < targetWord.length(); index++) {
            target = target * 10 + targetWord.charAt(index) - 'a';
        }

        return first + second == target;
    }
}
