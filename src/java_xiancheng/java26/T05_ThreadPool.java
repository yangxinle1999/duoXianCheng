package java_xiancheng.java26;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的概念（一堆线程装在某个容器里面，等待运行）
 */
public class T05_ThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service= Executors.newFixedThreadPool(5);   //起固定个数的线程池
        //在有任务使用的时候启动线程,没任务使用不启动 ，线程可重复使用,execute submit,线程池
        //的好处是:一个任务执行完了线程不会马上销毁,而是等待其他任务,这样就不用新起线程了,效率很高
       //一个线程池的一堆线程维护者两个队列,一个是还未执行的任务队列,一个是任务结束的队列.
        for (int i = 0; i < 6; i++) {
            service.execute(()->{ //这里调用execute方法,接受一个Runnable方法
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);

        service.shutdown();//线程池的正常关闭，等待所有任务执行完才关闭
        System.out.println(service.isTerminated()); //看线程里面的任务是否都执行完了
        System.out.println(service.isShutdown()); //看线程池是否关闭(shutdown了并不代表任务执行完了,
        //只是代表正在关闭的过程中 )
        System.out.println(service);

        TimeUnit.SECONDS.sleep(5);
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);


    }
}

