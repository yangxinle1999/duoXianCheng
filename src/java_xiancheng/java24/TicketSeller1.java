package java_xiancheng.java24;

import java.util.ArrayList;
import java.util.List;

/**
 * 有n张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 */
public class TicketSeller1 {
    static List<String> tickets=new ArrayList<>();  //ArrayList是线程不安全的

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (tickets.size()>0){
                    System.out.println(Thread.currentThread().getName()+"销售了--"+tickets.remove(0));
                }
            },"窗口"+i).start();
        }
    }
}

