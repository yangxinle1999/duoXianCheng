package java_xiancheng;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁，
 * 也就是说synchronized获得的锁是可重入的
 * 这里是继承中可能发生的情形，子类调用父类同步方法，new出来的都是子类对象
 */
class java10 {
    synchronized void m(){
        System.out.println("m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("m end");
    }

    public static void main(String[] args) {
        new TT().m();
    }
}

class TT extends java10{
    @Override
    synchronized void m(){
        System.out.println("child m start");
        super.m();  //子类的同步方法可以调用父类的同步方法（重入锁的另一种实现方法，子类
        //和父类使用的都是同一个对象即子类对象 ）
        System.out.println("chile m end");
    }
}
