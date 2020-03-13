package java_xiancheng.java26;

import java.util.concurrent.*;

//furture用来记录collable的call方法的返回值，且要指定泛型
public class T06_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task=new FutureTask<>(()->{  //定义了一个未来的任务,这里面是new了一个Callable,重写了call方法
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });

        new Thread(task).start();  //Thread不能直接放Callable,所以要包装一层FutureTask

        System.out.println(task.get());//阻塞方法会一直等着task的方法执行完成，最后拿到返回值再继续

        ExecutorService service= Executors.newFixedThreadPool(5);
        Future<Integer> f=service.submit(()->{  //submit可以直接扔一个Callable，返回的结果
            //放到Furture里面，这里不需要在new一个FutureTask了。
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });


        System.out.println(f.get());//阻塞线程,等待f执行完成,得到返回的结果
        System.out.println(f.isDone()); //看f是否执行完

    }
}

