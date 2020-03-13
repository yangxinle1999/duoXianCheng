package java_xiancheng.java25;
import java.util.concurrent.LinkedTransferQueue;


public class TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs=new LinkedTransferQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //strs.transfer("aaa"); //没有消费者的话会阻塞下去，及扔了的东西必须处理掉
        strs.put("aaa");  //不会阻塞

        /*new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }
}
