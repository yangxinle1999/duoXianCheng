package java_xiancheng.java24;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 有n张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 */
public class TicketSeller2 {
    static Vector<String> tickets=new Vector<>(); //Vector是线程安全的,所有的方法都是加锁的

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (tickets.size()>0){  //虽然size和remove方法是原子性的，但他们两个之间的判断操作是非原子性的，
                    //中间还是可能被其他线程打断，造成错误
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+"销售了--"+tickets.remove(0));
                }
            },"窗口"+i).start();
        }
    }
}

