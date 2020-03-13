package java_xiancheng;

class java02 {
    private int count=10;
    public void m(){
        synchronized (this){ //如果单纯为了加一把锁向上个程序似的new
            //一个锁对象，不干别的会很麻烦，可以使用this锁定自身，
            // 任何线程要执行下面的代码，必须先拿到this的锁
            count--;
            System.out.println(Thread.currentThread().getName()+" count = "+count);
        }
    }
}

