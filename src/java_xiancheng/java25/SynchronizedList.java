package java_xiancheng.java25;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class SynchronizedList {
    List<String> srts=new ArrayList<>(); //未加锁的list
    List<String> strsSync= Collections.synchronizedList(srts);  //返回加锁的list
}

