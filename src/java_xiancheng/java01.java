package java_xiancheng;

class java01 {
    private int count =10;
    private Object o=new Object();

    public void m(){
        synchronized (o){   //任何线程要执行下面的代码，必须先拿到o的锁,锁的是堆内存中的
            //对象，不是代码块，也不是栈内存中的局部变量表，当执行到synchronized大括号结束的时候
            //会自动释放锁，其他线程就可以拿到这把锁，执行代码
            count--;
            System.out.println(Thread.currentThread().getName()+" count = "+count);
        }
    }
}

