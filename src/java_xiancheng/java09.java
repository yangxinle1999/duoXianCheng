package java_xiancheng;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另一个同步方法，同一个线程已经拥有了某个对象的值，再次申请的时候仍然会得到该对象的锁
 * 也就是说synchronized获得的锁是可重入的，即synchronized支持重入锁
 * 死锁：第一个线程需要先锁定A对象，再锁定B对象；另一个线程先锁定B对象，再锁定A对象，此时，
 * 第一个线程把A对象锁了，第二个线程把B锁了，两个线程都在等待两一个线程释放锁，但都进行不下去，
 * 从而造成死锁。
 */
class java09 {
    synchronized void m1(){
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
    }

    synchronized void m2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("m2");
    }
}

