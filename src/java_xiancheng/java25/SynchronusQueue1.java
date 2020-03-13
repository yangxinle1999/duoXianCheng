package java_xiancheng.java25;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;


public class SynchronusQueue1 {  //容量为0的队列，生产者生产的东西，消费者必须马上消费，不然出问题
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs=new SynchronousQueue<>(); //同步队列，是一种特殊的transferQueue

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa");  //put不会报错，会阻塞等待消费者消费
        //strs.add("aaa");  //add会报错，因为SynchronousQueue的容量为0，可认为满了，add会报错
        System.out.println(strs.size());
    }
}

