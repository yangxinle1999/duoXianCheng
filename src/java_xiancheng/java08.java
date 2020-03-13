package java_xiancheng;

import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 容易产生脏读问题(dirtyRead)，读到在写的过程中还没有完成的数据
 */
class java08 {
    private String name;
    private double balance;

    public synchronized void set(String name,double balance){
        this.name=name;  //业务代码可能在两个赋值之间执行
        try {
            Thread.sleep(2000);   //睡两秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance=balance;
    }

    public synchronized double getBalance(String name) {
        return this.balance;  //加上synchronized会防止脏读
    }

    public static void main(String[] args) {
        java08 a=new java08();
        new Thread(()->a.set("张三",100.0)).start();

        try {
            TimeUnit.SECONDS.sleep(1);  //睡一秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("张三"));  //主线程执行该方法，若getBalance
        //不加锁，因为它只是睡了一秒，而另一个线程的set方法会睡2秒，这样会造成脏读，加上锁之后
        //主线程也要等待这把锁，避免了脏读

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("张三"));

    }
}

