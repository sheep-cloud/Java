# JavaSE 多线程

## 1、程序、进程、线程的概念

### 1、基本概念：程序、进程、线程

-   程序（program）
    -   是为完成特定任务、用某种语言编写的一组指令的集合。即指**一段静态的代码**，静态对象。
-   进程（process）
    -   是程序的一次执行过程，或上**正在运行的一个程序**。动态过程：有它自身的生产、存在和消亡的过程。
        -   如：运行中的QQ，运行中的MP3播放器
        -   程序是静态的，进程是动态的
-   线程（thread）
    -   进程可进一步细化为线程，是一个程序内部的一条执行路径。
        -   若一个程序可同一时间执行多个线程，就是支持多线程的

### 2、何时需要多线程？

-   程序需要同时执行两个或多个任务。
-   程序需要实现一些需要等待的任务时，比如用户输入、文件读写操作、网络操作、搜索等。
-   需要一些后台运行的程序时。

## 2、==Java中多线程的创建和使用==

### 1、线程的创建和启动

-   Java语言的JVM允许程序运行多个线程，它通过**java.lang.Thread**类来实现。
-   Thread类的特性
    -    每个线程都是通过某个特定Thread对象的run()方法来完成操作的，经常吧run()方法的主体成为线程体
    -   通过该Thread对象的start()方法来调用这个线程

### 2、Thread类

-   构造方法
    -   Thead()：	创建新的Thread对象
    -   Thread(String threadname)：创建线程并制定线程实例名
    -   Thread(Runnable target)：制定创建线程的目标对象，它实现了Runnable接口中的run方法
    -   Thread(Runnable target, String name)：创建新的Thread对象

### 3、创建线程的两种方式

```java
package cn.colg.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 需求： 创建一个子线程，完成1-100之间自然数的输出。同样的，主线程执行同样的操作
 *
 * @author colg
 */
@Slf4j
public class Thread01 {

    public static void main(String[] args) {
        Thread01SubThread subThread = new Thread01SubThread();
        subThread.setName("子线程");
        subThread.start();

        Thread thread = Thread.currentThread();
        thread.setName("主线程");
        int length = 100;
        for (int i = 1; i <= length; i++) {
            log.info("Thread01.main() >> 线程名 : {}, 自然数 : {}", Thread.currentThread().getName(), i);
        }
    }
}

@Slf4j
class Thread01SubThread extends Thread {

    /*
     * colg [创建多线程的第一种方式：继承Thread类]
     *  1. 定义子类继承Thread类
     *  2. 子类中重写Thread类中的run()方法
     *  3. 创建Thread子类对象，即创建了线程对象
     *  4. 调用线程对象的start()方法：启动线程，调用run()方法。
     *  注意：
     *      1. 一个线程只能执行一次start()
     *      2. 不能通过Thread实现类对象的run()方法去启动一个线程
     */

    @Override
    public void run() {
        int length = 100;
        for (int i = 1; i <= length; i++) {
            log.info("Thread01.run() >> 线程名 : {}, 自然数 : {}", Thread.currentThread().getName(), i);
        }
    }
}
```



#### 1、继承Thread类

#### 2、实现Runnable类接口

### 4、继承方式和实现方式的联系与区别

### 5、Thread类的有关方法

### 6、线程的调度

### 7、线程的优先级

### 8、使用多线程的优点

### 9、线程的分类

## 3、线程的生命周期

## 4、==线程的同步==

## 5、线程的通信