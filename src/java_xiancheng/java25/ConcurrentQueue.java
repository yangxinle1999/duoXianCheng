package java_xiancheng.java25;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> strs=new ConcurrentLinkedQueue<>(); //定义一个单向的队列

        for (int i = 0; i < 10; i++) {
            strs.offer("a"+i); //类似于原来的add,但是队列元素超出之后不会报错
        }

        System.out.println(strs);

        System.out.println(strs.size());

        System.out.println(strs.poll()); //拿走一个元素
        System.out.println(strs.size());

        System.out.println(strs.peek());
        System.out.println(strs.size());

        //双端队列是Deque
    }
}

