package java_xiancheng.pro_custom;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * 面试题：写一个固定容量同步容器（多线程访问不会出问题），拥有put和get方法，以及getCount方法
 * 能够支持两个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和nofify、notifyAll来实现
 */
public class wait_notifyall<T> {
    final private LinkedList<T> lists=new LinkedList<T>();
    final private int MAX=10;   //最多10个元素
    private int count=0;

    public synchronized void put(T t){
        while (lists.size()==MAX){  //想想为什么用while而不是用if，while每次都会判断，而if只会判断一次
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        lists.add(t);
        ++count;
        this.notifyAll();//通知消费者线程进行消费，如果使用notify的话，可能叫醒的还是一个生产者，这样会死循环
    }

    public synchronized T get(){
        T t=null;
        while (lists.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t=lists.removeFirst();
        count--;
        this.notifyAll();   //通知生产者进行生产
        return t;
    }

    public static void main(String[] args) {
        wait_notifyall<String> c =new wait_notifyall<>();
        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            },"c"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName()+" "+j);
                }
            },"p"+i).start();
        }
    }
    //Predicate
}

