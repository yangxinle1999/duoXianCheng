package java_xiancheng;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决同样的问题（类似于++，--等简单的数字运算）的更高效的方法，使用AtomXXX类
 * AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的，因为在
 * 两个原子操作之间也是有可能被其他线程插入操作的。
 * 比如：
 *  if (count.get()<100)
 *  count.incrementAndGet();    //替代count++的，是原子操作
 *  此时count=99，有两个线程都执行到了这里，判断都<100，都进行了count++操作，此时值就为101了
 */
class java15 {
    AtomicInteger count=new AtomicInteger(0);
    /*synchronized*/ void m(){  //Atomic效率比synchronized高得多
        for (int i = 0; i < 10000000; i++) {
//            if (count.get()<1000000)
            count.incrementAndGet();    //替代count++的，是原子操作
        }
    }

    public static void main(String[] args) {
        java15 t=new java15();

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

