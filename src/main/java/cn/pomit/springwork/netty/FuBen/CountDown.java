package cn.pomit.springwork.netty.FuBen;
/**

 */
import java.util.concurrent.TimeUnit;
/**
 * Description: 倒计时实现方式1：使用ScheduledExecutor实现
 * 								使用两个线程；
 */
import java.util.concurrent.*;

public class CountDown {
    //记录倒计时时间
    private volatile int limitSec;
    //记录倒计时当下时间
    private int curSec;

    public CountDown(int limitSec) throws InterruptedException {
        this.limitSec = limitSec;
        this.curSec = limitSec;
        System.out.println("count down form " + limitSec);
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(new Task(), 0, 1, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(limitSec);
        exec.shutdownNow();
        System.out.println("Time out！");
    }

    private class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Time remains " + --curSec + " s");
        }
    }

    //Test
    public static void main(String[] args) throws InterruptedException {
        new CountDown(10);
    }
}