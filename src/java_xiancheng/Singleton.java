package java_xiancheng;

public class Singleton {

    private static Singleton singleton;
    private Singleton (){
    }
    public static Singleton getInstance(){
        if (singleton == null){
            synchronized(Singleton.class){   //对获取实例的方法进行同步
                if (singleton == null)
                    singleton = new Singleton();
            }
        }
        return singleton;
    }
}
