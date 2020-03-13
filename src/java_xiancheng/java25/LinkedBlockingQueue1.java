package java_xiancheng.java25;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingQueue1 {
    static BlockingQueue<String> strs=new LinkedBlockingQueue<>(); //同步阻塞队列容器，满了就自动等待空，空了的话会继续生产

    static Random r=new Random();

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    strs.put("a"+i);    //如果满了，就会阻塞等待
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for (;;){
                    try {
                        System.out.println(Thread.currentThread().getName()+" take -"+strs.take()); //如果队列空了，
                        // 就会阻塞等待，队列有元素之后会被自动唤醒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"c"+i).start();
        }
    }
}

