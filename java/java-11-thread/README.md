# JavaSE 多线程

## 1. 程序, 进程, 线程的概念

### 1.1. 程序, 进程, 线程的概念

- 程序(program)
  - 是为完成特定任务, 用某种语言编写的一组指令的集合. 即指一段静态的代码, 静态对象
- 进程(process)
  - 是程序的一次执行过程, 或是正在运行的一个程序. 动态过程: 有它自身的生产, 存在和消亡的过程.(如: QQ/MP3)
    - 程序是静态的, 进程是动态的
- 线程(thread)
  - 进程可进一步细化为线程, 是一个程序内部的一条执行路径
  - 若一个程序可同一时间执行多个线程, 就是支持多线程的

### 1.2. 何时需要多线程?

- 程序需要同时执行两个或多个任务
- 程序需要实现一些需要等待的任务时, 如用户输入, 文件读写操作, 网络操作, 搜索等
- 需要一些后台运行的程序时

### 1.3. 使用多线程的优点

背景: 只使用单个线程完成多个任务(调用多个方法), 肯定比用多个线程来完成用的时间更短, 为何仍需要多线程呢?

多线程程序的优点:

1. 提高应用程序的响应. 对图形化界面更有意义, 可增强用户体验
2. 提高计算机系统CPU的利用率
3. 改善程序结构. 将既长又复杂的进程分为多个线程, 独立运行, 利于理解和修改

## 2. Java中多线程的创建和使用

### 2.1. 线程的创建和启动

- Java语言的JVM允许程序运行多个线程, 它通过`java.lang.Thread`类来实现
- Thread类的特性
  - 每个线程都是通过某个特定Thread对象的run()方法来完成操作的, 经常把run()方法的主体称为线程体
  - 通过该Thread对象的start()方法来调用这个线程

### 2.2. Thread类

- 构造方法
  - `java.lang.Thread.Thread()`: 创建新的Thread对象
  - `java.lang.Thread.Thread(String)`: 创建线程并指定线程实例名
  - `java.lang.Thread.Thread(Runnable)`: 指定创建线程的目标对象, 它实现了Runnable接口中的run方法
  - `java.lang.Thread.Thread(Runnable, String)`: 创建新的Thread对象, 并指定线程实例名

### 2.3. 创建线程的两种方式

- 继承`java.lang.Thread`类

  ```java
  package cn.colg.thread;
  
  import lombok.extern.slf4j.Slf4j;
  
  /**
   * 实现方式: 创建一个子线程, 完成1~100之间自然数的输出. 同样的, 主线程执行同样的操作
   * 
   * <pre>
   *  1. 创建线程的第一种方式: 继承 java.lang.Thread 类
   * </pre>
   *
   * @author colg
   */
  @Slf4j
  public class TestThread {
  
      public static void main(String[] args) {
          // 3. 创建Thread子类对象, 即创建了线程对象
          SubThread st = new SubThread();
          // 4. 调用线程对象start方法: 启动线程, 调用run方法
          // 一个线程只能够执行一次start()
          st.start();
  
          for (int i = 1; i <= 100; i++) {
              log.info("线程名: {}; 值: {}", Thread.currentThread().getName(), i);
          }
  
      }
  
  }
  
  // 1. 定义子类, 继承Thread类
  @Slf4j
  class SubThread extends Thread {
  
      // 2. 子类中重写Thread类中的run方法
      @Override
      public void run() {
          for (int i = 1; i <= 100; i++) {
              log.info("线程名: {}; 值: {}", Thread.currentThread().getName(), i);
          }
      }
  
  }
  ```

- 实现`java.lang.Runnable`接口

  ```java
  package cn.colg.thread;
  
  import lombok.extern.slf4j.Slf4j;
  
  /**
   * 创建一个子线程, 完成1~100之间自然数的输出. 同样的, 主线程执行同样的操作
   * 
   * <pre>
   *  2. 创建线程的第二种方式: 实现 java.lang.Runnable 接口
   * </pre>
   *
   * @author colg
   */
  @Slf4j
  public class TestRunnable {
  
      public static void main(String[] args) {
          SubRunnable sr = new SubRunnable();
          // 3. 通过Thread类有参构造器创建线程对象; 将Runnable接口的子类对象作为实际参数传递给Thread类的构造方法中
          Thread thread = new Thread(sr);
          // 4. 调用Thread类的start方法: 开启线程, 调用Runnable子类接口的run方法.
          thread.start();
  
          for (int i = 1; i <= 100; i++) {
              log.info("线程名: {}; 值: {}", Thread.currentThread().getName(), i);
          }
      }
      
  }
  
  // 1. 定义子类, 实现Runnable接口
  @Slf4j
  class SubRunnable implements Runnable {
  
      // 2. 子类中重写Runnable接口中的run方法
      @Override
      public void run() {
          for (int i = 1; i <= 100; i++) {
              log.info("线程名: {}; 值: {}", Thread.currentThread().getName(), i);
          }
      }
      
  }
  ```

  ### 2.4. 继承方式和实现方式的联系与区别

  - 区别
    - 继承Thread: 线程代码存在Thread子类run方法中
    - 实现Runnable: 线程代码存在接口子类run方法中
  - 实现方式的好处
    - 避免了单继承的局限性
    - 多个线程可以共享同一个接口实现类的对象(共享资源),  非常适合多个相同线程来处理同一份资源

## 3. 线程的生命周期

- JDK中用`java.lang.Thread.State`枚举表示了线程的几种状态

- 想要实现多线程, 必须在主线程中创建新的线程对象. java语言使用Thread类及其子类的对象来表示线程, 在它的一个完整的声明周期中要经历如下的五种状态:

  - 新建: 当一个Thread类或其子类的对象被声明并创建时, 新生的线程对象处于新建状态
  - 就绪: 处于新建状态的线程被start()后, 将进入线程队列等待CPU时间片, 此时它已具备了运行的条件
  - 运行: 当就绪的线程被调度并获得处理器资源时, 便进入运行状态, run()方法定义了线程的操作和功能
  - 阻塞: 在某种特殊情况下, 被人为挂起或执行输入输出操作时, 让出CPU并临时中止自己的执行, 进入阻塞状态
  - 死亡: 线程完成了它的全部工作或线程被提前强制性的中止

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1g1dylvgq7dj30se0c774t.jpg)

## 4. 线程的同步

- 多线程安全问题

  - 问题的原因
    - 当多条语句在操作同一个线程共享数据时, 一个线程对多条语句只执行了一部分, 还没有执行完, 另一个线程参与进来执行. 导致共享数据的错误.
  - 解决办法
    - 对多条操作共享数据的语句, 只能让一个线程都执行完, 在执行过程中, 其它线程不可以参与执行.

- Java对于多线程的安全问题提供了专业的解决方法

  - 同步代码块

    ```java
    synchronized (同步监视器) {
        // 需要被同步的代码块(即为操作共享数据的方法)
    }
    1. 共享数据: 多个线程共同操作的同一个数据(变量)
    2. 同步监视器: 由一个类的对象来充当. 哪个线程获取此监视器, 谁就执行大括号里被同步的代码. 俗称: 锁.
         注意:
             1. 要求所有的线程必须共用同一把锁
             2. 在实现的方式中, 考虑同步的话, 可以使用this来充当锁; 但是在继承的方式中, 慎用this
    ```

    

  - 同步方法

    ```java
    将操作共享数据的方法声明为 synchronized. 即此方法为同步方法, 能够保证当其中一个线程执行此方法时, 其它线程在外等待直至此线程执行完此方法.
    同步方法的锁: this
    ```

    

  - 同步原理

    - 当一个线程执行时, 把该线程锁住, 执行完毕以后释放锁, 其它线程才可以执行.

- 互斥锁

  - 在Java语言中, 引入了对象互斥锁的概念, 来保证共享数据的完整性.
    - 每个对象都对应于一个可称为"互斥锁"的标记, 这个标记用来保证在任一时刻, 只能有一个线程访问该对象.
    - 关键字`synchronized`来于对象的互斥锁联系. 当某个对象用`synchronized`修饰时, 表明该对象在任一时刻只能由一个线程访问.
    - 同步的局限性: 导致程序的执行效率要降低.
    - 同步方法: (非静态)的锁为(`this`), (静态)的锁为当前类本身(Xxx.Class)

- 释放锁的操作

  - 当前线程的同步代码块, 同方法执行结束
  - 当前线程在同步代码块, 同步方法中遇到break, return终止了改代码块, 该方法的继续执行
  - 当前线程在同步代码块, 同步方法中出现了未处理的Error或Exception, 导致异常结束
  - 当前线程在同步代码块, 同步方法中执行了线程对象的wait()方法, 当前线程暂停, 并释放锁

- 不会释放锁的操作

  - 线程执行同步代码块, 同步方法时, 程序调用`Thread.sleep()`, `Thread.yield()`方法暂停当前线程的执行
  - 线程执行同步代码块, 同步方法时, 其它线程调用了该线程的`suspend()`方法将该线程挂起, 该线程不会释放锁
    - 应尽量避免使用`suspend()挂起`, `resume()继续执行`来控制线程

- 线程同步案例

  ```java
  package cn.colg.thread;
  
  import cn.hutool.core.thread.ThreadUtil;
  import lombok.extern.slf4j.Slf4j;
  
  /**
   * 多线程案例
   * 
   * <pre>
   * 银行有一个账户.
   * 有两个储户分别向同一个账户存3000元, 每次存1000, 存3次. 每次存完打印账户余额.
   * 
   * 1. 是否涉及到多线程?
   *  是, 有两个储户(两种方式创建多线程)
   * 2. 是否有共享数据?
   *  有, 是同一个账户
   * 3. 得考虑线程同步(两种方式处理线程安全)
   * 
   * 拓展问题: 可否实现两个储户交替存钱的操作, 需要使用线程通信!
   * </pre>
   *
   * @author colg
   */
  public class TestSynchronizedAccount {
  
      public static void main(String[] args) {
          Account account = new Account();
  
          new Thread(account, "Jack").start();
          new Thread(account, "Rose").start();
      }
  
  }
  
  @Slf4j
  class Account implements Runnable {
  
      private double balance;
  
      @Override
      public void run() {
          for (int i = 0; i < 3; i++) {
              setMoney(1000);
          }
      }
  
      /**
       * 存钱
       *
       * @author colg
       */
      public synchronized void setMoney(double money) {
          notify();
          
          ThreadUtil.sleep(20);
          balance += 1000;
          log.info("储户: {}; 当前账户余额: {}", Thread.currentThread().getName(), balance);
          
          try {
              wait();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  
  }
  ```

- 线程的死锁问题

  - 死锁
    - 不同的线程分别占用对方需要的同步资源不放弃, 都在等待对方放弃自己需要的同步资源, 就形成了线程的死锁
  - 解决方法
    - 专门的算法, 原则
    - 尽量减少同步资源的定义

- 死锁案例

  ```java
  package cn.colg.thread;
  
  import cn.hutool.core.thread.ThreadUtil;
  import lombok.extern.slf4j.Slf4j;
  
  /**
   * 死锁问题, 处理线程同步时容易出现.
   * 
   * <pre>
   * 不同的线程分别占用对方需要的同步资源不放弃, 都在等待对方放弃自己需要的同步资源, 就形成了线程的死锁
   * </pre>
   *
   * @author colg
   */
  @Slf4j
  public class TestDeadLock {
  
      private static StringBuffer sb1 = new StringBuffer();
      private static StringBuffer sb2 = new StringBuffer();
  
      public static void main(String[] args) {
          new Thread(new Runnable() {
  
              @Override
              public void run() {
                  synchronized (sb1) {
                      ThreadUtil.sleep(20);
                      sb1.append("A");
                      synchronized (sb2) {
                          sb2.append("B");
                          log.info("线程名: {}; sb1: {}; sb2: {}", Thread.currentThread().getName(), sb1, sb2);
                      }
                  }
              }
          }).start();
  
          new Thread(new Runnable() {
              @Override
              public void run() {
                  synchronized (sb2) {
                      ThreadUtil.sleep(20);
                      sb1.append("C");
                      synchronized (sb1) {
                          sb2.append("D");
                          log.info("线程名: {}; sb1: {}; sb2: {}", Thread.currentThread().getName(), sb1, sb2);
                      }
                  }
              }
          }).start();
      }
  
  }
  ```

  

## 5. 线程的通信

### 5.1. 线程通信方法

- `java.lang.Object.wait()`: 令当前线程挂起并放弃CPU, 同步资源, 使别的线程可访问并修改共享资源, 而当前线程排队等候再次对资源的访问; 当前线程暂停, 并释放锁
- `java.lang.Object.notify()`: 唤醒正在排队等待同步资源的线程中优先级最高者结束等待
- `java.lang.Object.notifyAll()`: 唤醒正在排队等待同步资源的所有线程结束等待
- `java.lang.Object`提供的这三个方法只有在`synchronized`代码块或者方法中才能使用, 否则会报异常



### 5.2. 线程通信案例

```java
package cn.colg.thread;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程通信
 * 
 * <pre>
 * 使用两个线程打印1~100. 线程1, 线程2 交替打印
 * 
 * 线程通信:
 * java.lang.Object.wait():             一旦一个线程执行到wait(), 就释放当前的锁.
 * java.lang.Object.notify():           唤醒wait的一个线程
 * java.lang.Object.notifyAll():        唤醒wait的所有线程
 * 
 * </pre>
 *
 * @author colg
 */
public class TestThreadCommunication {

    public static void main(String[] args) {
        PrintNum pn = new PrintNum();

        new Thread(pn, "A").start();;
        new Thread(pn, "B").start();
    }

}

@Slf4j
class PrintNum implements Runnable {

    private int num = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify();
                
                if (num <= 100) {
                    ThreadUtil.sleep(20);
                    log.info("线程名: {}; 当前值: {}", Thread.currentThread().getName(), num++);
                } else {
                    break;
                }
                
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
```

## 6, 生产者/消费者案例

生产者(Producer)将产品交给店员(Clerk), 而消费者(Consumer)从店员处取走商品.

店员一次只能持有固定数量的产品(如: 20), 如果生产者试图生产更多的产品, 店员会叫生产者停一下,

如果店中有空位放产品了再通知生产者继续生产, 如果店中没有产品了,店员会告诉消费者等一下, 如果店中有产品了再通知消费者来取走产品.

- 这里可能出现两个问题
  - 生产者比消费者快时, 消费者会漏掉一些数据没有取到
  - 消费者比生产者快时, 消费者会取相同的数据

- 案例代码

  ```java
  package cn.colg.thread;
  
  import cn.hutool.core.thread.ThreadUtil;
  import lombok.AllArgsConstructor;
  import lombok.extern.slf4j.Slf4j;
  
  /**
   * 生产者和消费者案例
   * 
   * <pre>
   * 
   * 1. 是否涉及多线程的问题?
   *  是, 生产者和消费者
   * 2. 是否有共享数据?
   *  有, 产品数量; 考虑线程的安全
   * 3. 是否涉及到线程的通信?
   *  是, 存在着生产者和消费者的通信
   * </pre>
   *
   * @author colg
   */
  public class TestProductorAndConsumer {
  
      public static void main(String[] args) {
          Clerk clerk = new Clerk();
          Producer p1 = new Producer(clerk);
          Consumer c1 = new Consumer(clerk);
          
          new Thread(p1, "生产者A").start();
          new Thread(c1, "消费者A").start();
          
          Producer p2 = new Producer(clerk);
          new Thread(p2, "生产者B").start();
      }
      
  }
  
  /**
   * 店员
   *
   * @author colg
   */
  @Slf4j
  class Clerk {
  
      /** 商品数量 */
      private int product;
  
      /**
       * 生产商品
       *
       * @author colg
       */
      public synchronized void addProduct() {
          if (product >= 20) {
              log.info("已经生产够20个了, 生产者你停一下!");
              try {
                  wait();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          } else {
              product++;
              log.info("线程名: {}; 生产了第 {} 个商品", Thread.currentThread().getName(), product);
              notifyAll();
          }
      }
  
      /**
       * 消费商品
       *
       * @author colg
       */
      public synchronized void consumeProduct() {
          if (product <= 0) {
              log.info("已经没有商品了, 消费者你等一下!");
              try {
                  wait();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          } else {
              log.info("线程名: {}; 消费了第 {} 个商品", Thread.currentThread().getName(), product);
              product--;
              notifyAll();
          }
      }
  
  }
  
  /**
   * 生产者
   *
   * @author colg
   */
  @Slf4j
  @AllArgsConstructor
  class Producer implements Runnable {
  
      private Clerk clerk;
  
      @Override
      public void run() {
          log.info("生产者开始生产商品");
          while (true) {
              ThreadUtil.sleep(1000);
              clerk.addProduct();
          }
      }
  
  }
  
  /**
   * 消费者
   *
   * @author colg
   */
  @Slf4j
  @AllArgsConstructor
  class Consumer implements Runnable {
  
      private Clerk clerk;
  
      @Override
      public void run() {
          log.info("消费者开始消费产品");
          while (true) {
              ThreadUtil.sleep(1000);
              clerk.consumeProduct();
          }
      }
  
  }
  ```

  