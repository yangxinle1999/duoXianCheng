package java_xiancheng;

import java.util.ArrayList;
import java.util.List;

/**
 * 对比上个程序，可以同synchronized解决，synchronized可以保证可见性和原子性，volatile只保证可见性
 */
class java14 {
    /*volatile*/ int count=0;
    synchronized void m(){  //这样效率比较低
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        java14 t=new java14();  //10个线程共享一个t对象，所以每次只有一个线程执行m方法

        List<Thread> threads=new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m,"thread-"+i));
        }

        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }
}

