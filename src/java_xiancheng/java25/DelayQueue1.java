package java_xiancheng.java25;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

//多用于定时任务
public class DelayQueue1 {
    static BlockingQueue<MyTask> tasks=new DelayQueue<>();  //无界队列，可以不断往队列加，每个元素
    //都在队列中记录着还要多长时间可以从队列之中拿走，默认是排好顺序的，等待时间最长的排在最前面，先往外拿

    static class MyTask implements Delayed{

        long runningTime;

        MyTask(long rt){
            this.runningTime=rt;
        }

        @Override
        public long getDelay(TimeUnit unit) { //还有多长时间可以往外拿

            return unit.convert(runningTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS)<o.getDelay(TimeUnit.MILLISECONDS)){
                return -1;
            }else if (this.getDelay(TimeUnit.MILLISECONDS)>o.getDelay(TimeUnit.MILLISECONDS)){
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return ""+runningTime;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long now=System.currentTimeMillis();
        MyTask t1=new MyTask(now+1000); //new了5个任务，这个表示从现在开始1秒钟之后执行
        MyTask t2=new MyTask(now+2000);//2秒中之后执行
        MyTask t3=new MyTask(now+1500);
        MyTask t4=new MyTask(now+2500);
        MyTask t5=new MyTask(now+500);

        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);

        System.out.println(tasks);

        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }
    }
}

