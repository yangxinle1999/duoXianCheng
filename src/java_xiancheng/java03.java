package java_xiancheng;


class java03 {
    private int count=10;
    public synchronized void m(){ //等同于在方法的代码执行时要synchronized(this)，
        //表示该方法从一开始执行到结束都要使用自身的锁对象
        count--;
        System.out.println(Thread.currentThread().getName()+" count = "+count);
    }
}

