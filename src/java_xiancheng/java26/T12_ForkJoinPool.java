package java_xiancheng.java26;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;


public class T12_ForkJoinPool {  //切分合并线程池
    static int[] nums=new int[1000000];
    static final int MAX_NUM=50000;
    static Random r=new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i]=r.nextInt(100);
        }
        System.out.println(Arrays.stream(nums).sum());  //方式1，数组求和
    }

    /*static class AddTask extends RecursiveAction{  //RecursiveAction可以理解为
    //递归的归并排序，无返回值

        int start,end;

        AddTask(int s,int e){
            this.start=s;
            this.end=e;
        }

        @Override
        protected void compute() {
            if (end-start<=MAX_NUM){ //分给的任务小于最小任务50000，就不再分别的线程了，直接计算
                long sum=0L;
                for (int i = start; i < end; i++) {
                    sum+=nums[i];
                }
                System.out.println("from："+start+" to:"+end+" = "+sum);
            }else{  //分给的任务比50000长，就进行切割
                int middle=start+(end-start)/2;

                AddTask subTask1=new AddTask(start,middle); //分割给两个子任务
                AddTask subTask2=new AddTask(middle,end);

                subTask1.fork();  //fork表示启动线程
                subTask2.fork();
            }
        }
    }*/

    static class AddTask extends RecursiveTask<Long>{  //RecursiveTask有返回值，Long是返回值类型

        int start,end;

        AddTask(int s,int e){
            this.start=s;
            this.end=e;
        }

        @Override
        protected Long compute() {
            if (end-start<=MAX_NUM){
                long sum=0L;
                for (int i = start; i < end; i++) {
                    sum+=nums[i];
                }
                return sum;
            }

            int middle=start+(end-start)/2;

            AddTask subTask1=new AddTask(start,middle);
            AddTask subTask2=new AddTask(middle,end);

            subTask1.fork(); //分叉启动线程
            subTask2.fork();

            return subTask1.join()+subTask2.join(); //把每个返回值进行合并汇总

        }
    }

    public static void main(String[] args) throws IOException {
        ForkJoinPool fjp=new ForkJoinPool();
        AddTask task=new AddTask(0,nums.length);
        fjp.execute(task);
        long result=task.join(); //拿到总结果
        System.out.println(result);

        //System.in.read();
    }

}

