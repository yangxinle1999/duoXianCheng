package java_xiancheng.java19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题：
 * 实现一个容器，提供两个方法：add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * 给lists添加volatile之后，t2能够接到通知，但是t2线程的死循环很浪费cpu，如果不用死循环怎么做呢？
 *
 * 分析下面这个程序，能完成这个功能吗？
 */
public class my2 {
    //添加volatile，使t2能够得到通知（但仍然会有两个问题，因为两个方法没加锁，可能造成数据
    // 不精确，同时第二个线程一直while判断中，很浪费cpu）
    volatile List lists=new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {

        my2 c=new my2();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add "+i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(()->{
            while (true){
                if (c.size()==5){
                    break;
                }
            }
            System.out.println("t2结束");
        },"t2").start();
    }
}

