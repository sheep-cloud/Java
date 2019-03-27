package cn.colg.juc;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.extern.slf4j.Slf4j;

/**
 * 锁分段机制, CopyOnWriteArrayList/ColpyOnWriteArraySet: 写入并复制
 * 
 * <pre>
 *  注意: 添加操作多时, 效率低, 因为每次添加时都会进行复制, 开销会非常大. 并发迭代操作多时可以选择
 * </pre>
 *
 * @author colg
 */
public class Test04CopyOnWriteArrayList {

    public static void main(String[] args) {
        HelloThread helloThread = new HelloThread();

        for (int i = 0; i < 10; i++) {
            new Thread(helloThread).start();
        }
    }
}

@Slf4j
class HelloThread implements Runnable {

    // private static List<String> list = Collections.synchronizedList(new ArrayList<>());
    private static List<String> list = new CopyOnWriteArrayList<>();
    

    static {
        list.add("A");
        list.add("B");
        list.add("C");
    }

    @Override
    public void run() {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            log.info("next: {}", next);
            
            list.add("D");
        }
    }

}
