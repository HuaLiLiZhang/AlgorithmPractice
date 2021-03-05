package leetcode.Test;

import java.util.concurrent.Semaphore;

/**
 * @author: Created by zhanghl
 */
public class SemaphoreDemo {

    public final static int SEM_SIZE = 10;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(SEM_SIZE);
        MyThread t1 = new MyThread("t1", semaphore);
        MyThread t2 = new MyThread("t2", semaphore);
        t1.start();
        t2.start();
        int permits = 21;
        System.out.println(Thread.currentThread().getName() + " trying to acquire");
        try {
            Thread.sleep(1000);
            semaphore.release(11);
            semaphore.acquire(permits);
            System.out.println(Thread.currentThread().getName() + " acquire successfully");
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            System.out.println("还剩下许可数量为：" + semaphore.availablePermits());
            System.out.println(Thread.currentThread().getName() + " release successfully");
        }
        //main获取5个许可证，释放一个许可，thread1,2获取3个许可，释放三个，thread2只有等main和thread其中一个释放许可才可以获取许可
        //main trying to acquire
        //t1 trying to acquire
        //t2 trying to acquire
        //t1 acquire successfully
        //main acquire successfully
        //t1 release successfully
        //main release successfully
        //t2 acquire successfully
        //t2 release successfully

        //main获取8个许可，释放一个许可，thread1,2获取3个许可，释放三个，必须等main释放之后，thread1或thread2才能获取许可，才能执行
        //
        //main trying to acquire
        //t1 trying to acquire
        //main acquire successfully
        //t2 trying to acquire
        //main release successfully
        //t1 acquire successfully
        //t1 release successfully
        //t2 acquire successfully
        //t2 release successfully
        //或者等threa1,2释放许可之后，main才能获取许可
        //main trying to acquire
        //t2 trying to acquire
        //t1 trying to acquire
        //t2 acquire successfully
        //t1 acquire successfully
        //t2 release successfully
        //t1 release successfully
        //main acquire successfully
        //main release successfully


    }
}

class MyThread extends Thread {
    private Semaphore semaphore;

    public MyThread(String name, Semaphore semaphore) {
        super(name);
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        int count = 3;
        System.out.println(Thread.currentThread().getName() + " trying to acquire");
        try {
            semaphore.acquire(count);
            System.out.println(Thread.currentThread().getName() + " acquire successfully");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(count);
            System.out.println(Thread.currentThread().getName() + " release successfully");
        }
    }
}
