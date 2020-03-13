package java_xiancheng;

class java06 implements Runnable {

    private int count=10;

    @Override
    public synchronized void run() {  //原子操作
        count--;
        System.out.println(Thread.currentThread().getName()+" count = "+count);
    }

    public static void main(String[] args) {
        java06 t=new java06();
        for (int i = 0; i < 5; i++) {  //加上锁之后变成原子操作，不可分，解决上个问题
            new Thread(t,"Thread"+i).start();
        }
    }
}

