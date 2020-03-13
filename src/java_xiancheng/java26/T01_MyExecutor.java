package java_xiancheng.java26;

import java.util.concurrent.Executor;

/**
 * 认识Executor（执行器，用来执行任务，是一个接口）,Executor内部只有一个方法execute,里面接受
 * Runnable,我们需要重写给他一个Runnable.
 */
public class T01_MyExecutor implements Executor {
    public static void main(String[] args) {
        new T01_MyExecutor().execute(()->{
            System.out.println("hello executor");
        });
    }

    @Override
    public void execute(Runnable command){  //重写方法来执行任务
        new Thread(command).run();  //新起一个线程来执行command方法
        //command.run();
    }
}
