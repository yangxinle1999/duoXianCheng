package java_xiancheng;

class java04 {
    private static int count=10;

    public synchronized static void m(){    //这里等同于synchronized(java04.class)
        count--;
        System.out.println(Thread.currentThread().getName()+" count = "+count);
    }

    public static void mm(){
        synchronized (java04.class){ //这里写synchronized(this)是否可以?不可以，
            //因为静态的方法或者属性没有对象，就没有this
            count--;
        }
    }
}

