package java_xiancheng.java26;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class T09_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service= Executors.newSingleThreadExecutor(); //线程池里面就只有一个线程
        //，好处是能保证任务前后一定是顺序执行的。
        for (int i = 0; i < 5; i++) {
            final int j=i;
            service.execute(()->{
                System.out.println(j+" "+Thread.currentThread().getName());
            });
        }
    }
}
