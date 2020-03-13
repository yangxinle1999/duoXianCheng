package java_xiancheng;

class java05 implements Runnable {

    private int count=10;

    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+" count = "+count);
    }

    public static void main(String[] args) {
        java05 t=new java05();  //只new了一个对象，所有线程共享该对象
        for (int i = 0; i < 5; i++) {  //会造成线程重入问题，在count--和
            //System.out.println(Thread.currentThread().getName()+" count = "+count);
            //之间可能会有线程打断，造成数据错误
            new Thread(t,"Thread"+i).start();
        }
    }
}

