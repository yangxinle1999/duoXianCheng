package java_xiancheng.java25;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueue1 {
    static BlockingQueue<String> strs=new ArrayBlockingQueue<>(10); //有界阻塞队列，装的元素个数有限，这里是10个最多

    static Random r=new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a"+i);
        }

        //strs.put("aaa");  //满了就会等待，程序阻塞，如果没有消费者消费的话就会无限阻塞下去
        //strs.add("aaa");   //满了add会报异常
        //strs.offer("aaa");  //满了offer不会报异常，会返回boolean值，满了之后会返回false
        strs.offer("aaa",1, TimeUnit.SECONDS); //1秒之后加不进去就不加了

        System.out.println(strs);
    }
}
