package java_xiancheng.java26;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class T10_SchedulePool {
    public static void main(String[] args) {
        ScheduledExecutorService service= Executors.newScheduledThreadPool(4);//用于执行定时的任务，
        //线程可以复用
        service.scheduleAtFixedRate(()->{  //以固定的频率来执行某个任务
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        },0,500,TimeUnit.MILLISECONDS);//第一个任务马上起来执行，每隔500ms执行一次
    }
}

