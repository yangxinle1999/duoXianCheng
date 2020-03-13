package java_xiancheng.java26;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static javafx.scene.input.KeyCode.R;


public class T11_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        ExecutorService service= Executors.newWorkStealingPool();//工作窃取线程池
        //每一个线程都维护自己的一个队列，每个队列都积累各自的任务，当一个线程的队列任务执行
        //完之后，会主动去别的线程的任务队列中找任务干


        System.out.println(Runtime.getRuntime().availableProcessors());//看cpu是多少核，打印出来是
        //4，说明本电脑是4核的，newWorkStealingPool默认是根据cpu是多少核，就起多少线程

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));

        //由于产生的精灵线程(守护线程、后台线程)，主线程不阻塞的话，可能看不到输出，精灵
        // 线程在主线程结束之后也会一直运行
        System.in.read();
    }

    static class R implements Runnable{

        int time;

        R(int t){
            this.time=t;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time+" "+Thread.currentThread().getName());
        }
    }
}

