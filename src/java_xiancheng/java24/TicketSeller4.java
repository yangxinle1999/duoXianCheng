package java_xiancheng.java24;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
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
public class TicketSeller4 {
    static Queue<String> tickets=new ConcurrentLinkedQueue<>();  //并发的链表实现的队列，支持多线程

    static {  //在类加载的初始化的时候就会执行
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (true){
                    String s=tickets.poll(); //往外拿一个头数据，poll方法是原子性的,如果为空没拿到数据会返回null
                    if (s==null){ //这里虽然判断与原子操作并没有加锁，但是判断之后并没有进行
                        //类似于取数据等其他操作，所以不会有问题
                        break;
                    }else{
                        System.out.println(Thread.currentThread().getName()+"销售了--"+s);
                    }
                }
            },"窗口"+i).start();
        }
    }
}

