package leetcode.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: Created by zhanghl
 */
public class Waitress implements Runnable {
    private CountDownLatch downLatch;
    private String name;

    public Waitress(CountDownLatch downLatch, String name) {
        this.downLatch = downLatch;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

            System.out.println(sdf.format(new Date()) + " " + name + "等待顾客");
            downLatch.await(3, TimeUnit.SECONDS);
            System.out.println(sdf.format(new Date()) + " " + name + "开始上菜");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
