package leetcode.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author: Created by zhanghl
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        List<Thread> threads = new ArrayList<>(3);
        threads.add(new Thread(new Customer(countDownLatch, "张三")));
        threads.add(new Thread(new Customer(countDownLatch, "李四")));
        threads.add(new Thread(new Customer(countDownLatch, "王五")));
        for (Thread thread : threads) {
            thread.start();
        }
        Thread.sleep(200);
        new Thread(new Waitress(countDownLatch, "♥小芳♥")).start();
    }
    //运行以后的结果应该是这样的：
    //
    //15:25:53.015 王五出发去饭店
    //15:25:53.015 李四出发去饭店
    //15:25:53.015 张三出发去饭店
    //15:25:53.062 ♥小芳♥等待顾客
    //15:25:54.341 张三到了饭店
    //15:25:54.358 李四到了饭店
    //15:25:56.784 王五到了饭店
    //15:25:56.784 ♥小芳♥开始上菜
    //可以看到，服务员小芳在调用await方法时一直阻塞着，一直等到三个顾客都调用了countDown方法才继续执行。



    //如果有一个顾客迟迟没到，饭店都打样了，也不能一直等啊，应该这么办？
    //
    //我：可以使用await方法的另一个重载，传入等待的超时时间，比如服务员只等3秒钟，可以把服务员类中的
    //
    //latch.await();
    //改成：
    //
    //latch.await(3, TimeUnit.SECONDS);
    //运行结果可能是这样的：
    //
    //17:24:40.915 张三出发去饭店
    //17:24:40.915 李四出发去饭店
    //17:24:40.915 王五出发去饭店
    //17:24:40.948 ♥小芳♥等待顾客
    //17:24:43.376 李四到了饭店
    //17:24:43.544 王五到了饭店
    //17:24:43.951 ♥小芳♥开始上菜
    //17:24:44.762 张三到了饭店
}
