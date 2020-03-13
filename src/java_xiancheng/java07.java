package java_xiancheng;


/**
 * 同步和非同步方法是否可以同时调用
 * 可以
 */
class java07 {
    public synchronized void m1(){  //同步方法
        System.out.println(Thread.currentThread().getName()+"m1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m1 end");
    }

    public void m2(){  //非同步方法
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m2");
    }

    public static void main(String[] args) {
        java07 t=new java07();
        new Thread(()->t.m1(),"t1").start();  //线程执行同步方法
        new Thread(()->t.m2(),"t2").start();  //线程执行非同步方法，两个方法可以同时运行

        /*new Thread(t::m1,"t1").start();
        new Thread(t::m2,"t2").start();*/
    }
}

