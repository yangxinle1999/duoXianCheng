package java_xiancheng.java20;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock还可以指定公平锁（哪个线程等的时间长，哪个线程得到这把锁）
 */
public class ReentrantLock5 extends Thread {
    private static ReentrantLock lock=new ReentrantLock(true);//参数为true表示为公平锁，对比输出结果

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            lock.lock();  //上锁
            try {
                System.out.println(Thread.currentThread().getName()+"获得锁");
            } finally {
                lock.unlock();  //释放
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 r1=new ReentrantLock5();
        Thread th1=new Thread(r1);
        Thread th2=new Thread(r1);
        th1.start();
        th2.start();
    }
}

