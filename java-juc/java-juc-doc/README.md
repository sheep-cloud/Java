# java-juc 多线程集合

## 1. Java JUC简介

- 在Java5.0提供了`java.uti.concurrent`(简称JUC)包, 在此包中增加了在并发编程中很常用的实用工具类, 用于定义类似于线程的自定义子系统, 包括线程池, 异步IO和轻量级任务框架. 提供可调的, 灵活的线程池. 还提供了设计用于多线程上下文中的Collection实现等.

## 2. volatile 关键字 - 内存可见性

### 2.1. 内存可见性

- 内存可见性（Memory Vislbility）是指当某个线程正在使用对象状态而另一个线程在同时修改该状态，需要确保当一个线程修改了对象状态后，其他线程能够看到发生的状态变化。

- 可见性错误是指当读操作与写操作在不同的线程中执行时，我们无法确保执行读操作的线程能适时的看到其他线程写入的值，有时甚至是根本不可能的事情。

- 我们可以通过同步来保证对象被安全的发布。除此之外我们也可以使用一种更加轻量级的volatile变量。

### 2.2. volatile 关键字

- Java提供了一种稍弱的同步机制，即volatile变量，用来确保将变量的更新操作通知到其他线程。
- 可以将volatile看作一个轻量级的锁，但是又与锁有些不同：
  - 对于多线程，不是一种互斥关系
  - 不能保证变量状态的"原子性操作"

## 3. 原子变量 - CAS算法

### 3.1. 原子变量

- 类的小工具包，支持在单个变量上解除锁的线程安全编程。事实上，此包中的类可将 volatile 值、字段和数组元素的概念扩展到那些也提供原子条件更新操作的类。 
- 类 AtomicBoolean、 AtomicInteger、 AtomicLong 和 AtomicReference 的实例各自提供对相应类型单个变量的访问和更新。每个类也为该类型提供适当的实用工具方法。 
- AtomicIntegerArray、 AtomicLongArray 和 AtomicReferenceArray 类进一步扩展了原子操作，对这些类型的数组提供了支持。这些类在为其数组元素提供 volatile 访问语义方
  面也引人注目，这对于普通数组来说是不受支持的。 
- **核心方法： boolean compareAndSet(expectedValue, updateValue)** 
- `java.util.concurrent.atomic` 包下提供了一些原子操作的常用类: 
  - AtomicBoolean 、 AtomicInteger 、 AtomicLong 、 AtomicReference 
  - AtomicIntegerArray 、 AtomicLongArray 
  - AtomicMarkableReference 
  - AtomicReferenceArray 
  - AtomicStampedReference 

### 3.2. CAS算法

- CAS(Compare-And-Swap) 是一种硬件对并发的支持, 针对多处理器操作而设计的处理器中的一种特殊指令, 用于管理对共享数据的并发访问.
- CAS 是一种无锁的非阻塞算法的实现
- CAS 包含了 3 个操作数
  - V 需要读写的内存值
  - A 进行比较的值
  - B 拟写入的新值
  - 当且仅当 V 的值等于 A 时, CAS通过原子方式用新值 B 来更新 V 的值, 否则不会执行任何操作

## 4. ConcurrentHashMap 锁分段机制

- Java5.0在`java.util.concurrent`包中提供了多种并发容器类来改进同步容器的性能.
- `ConcurrentHashMap`同步容器类是Java 5增加的一个线程安全的哈希表. 对于多线程的操作, 介于HashMap与Hashtable之间. 内部采用"锁分段"机制替代Hashtable的独占锁. 进而提高性能.
- 此外还提供了设计用于多线程上下文中的Collection实现:
  - ConcurrentHashMap、 ConcurrentSkipListMap、 ConcurrentSkipListSet、
    CopyOnWriteArrayList 和 CopyOnWriteArraySet。 
    - 当期望许多线程访问一个给定collection 时
      - ConcurrentHashMap 通常优于同步的 HashMap
      - ConcurrentSkipListMap 通常优于同步的 TreeMap。 
    - 当期望的读数和遍历远远
      大于列表的更新数时
      -  CopyOnWriteArrayList 优于同步的 ArrayList。 

## 5. CountDownLatch 闭锁

- Java5.0在`java.util.concurrent`包中提供了多种并发容器类来改进同步容器的性能.
- CountDownLatch一个同步辅助类, 在完成一组正在其他线程中执行的操作之前, 它允许一个或多个线程一直等待.
- 闭锁可以延迟线程的进度直到其到达终止状态, 闭锁可以用来确保某些活动直到其他活动都完成才继续执行
  - 确保某个计算在其需要的所有资源都被初始化之后才能继续执行;
  - 确保某个服务在其依赖的所有其他服务都已启动之后才启动;
  - 等待直到某个操作所有参与者都准备就绪再继续执行.

## 6. 实现 Callable 接口

- Java5.0在`java.util.concurrent`提供了一个新的创建执行线程的方式: Callable 接口
- Callable 接口类似于 Runnable, 两者都是为那些其实例可能被另一个线程执行的类设计的. 但是 Runnable 不会返回结果, 并且无法抛出经过检查的异常.
- Callable 需要依赖 FutureTask, FutureTask 也可以用作闭锁.

## 7. Lock 同步锁

- 在Java5.0以前, 协调共享对象的访问时可以使用的机制只有synchronized和volatile. Java5.0后增加了一些新的机制, 但并不是一种替代内置锁的方法, 而是当内置锁不适用时, 作为一种可选择的高级功能.
- ReentrantLock实现了Lock接口, 并提供了与synchronized相同的互斥性和内存可见性. 但相较于synchronized提供了更高的处理锁的灵活性.

## 8. Condition 控制线程通信

- Condition接口描述了可能会与锁有关联的条件变量. 这些变量在用法上与使用`Object.wait()`访问的隐式监视器类似, 但提供了更强大的功能. 需要特别指出的是, 单个Lock可能与多个Condition对象关联. 为了避免兼容性问题, Condition方法的名称与对应的Object版本中的不同.
- 在Condition对象中, 与wait, notify和notifyAll方法对应的分别是await, signal和singalAll.
- Condition实例实质上被绑定到一个锁上. 要为特定Lock实例获得Condition实例, 请使用其newCondition()方法

## 9. 线程按序交替

- 编写一个程序, 开启3个线程, 这3个线程的ID分别为A, B, C, 每个线程将自己的ID在屏幕上打印10遍, 要求输出的结果必须按顺序显示. 如: ABCABCABC... 依次递归

  ```java
  package cn.colg.juc;
  
  import java.util.concurrent.locks.Condition;
  import java.util.concurrent.locks.Lock;
  import java.util.concurrent.locks.ReentrantLock;
  
  import lombok.extern.slf4j.Slf4j;
  
  /**
   * 线程按序交替
   * 
   * <pre>
   * 需求:
   *  编写一个程序, 开启3个线程, 这3个线程的ID分别为A, B, C, 每个线程将自己的ID在屏幕上打印10遍, 要求输出的结果必须按顺序显示.
   *  如: ABCABCABC... 依次递归
   * </pre>
   *
   * @author colg
   */
  @Slf4j
  public class TestABCAlternate {
  
      public static void main(String[] args) {
          AlternateDemo alternateDemo = new AlternateDemo();
  
          new Thread(new Runnable() {
  
              @Override
              public void run() {
                  for (int i = 0; i < 10; i++) {
                      alternateDemo.loopA(i);
                  }
              }
          }, "A").start();
          
          new Thread(new Runnable() {
  
              @Override
              public void run() {
                  for (int i = 0; i < 10; i++) {
                      alternateDemo.loopB(i);
                  }
              }
          }, "B").start();
          
          new Thread(new Runnable() {
  
              @Override
              public void run() {
                  for (int i = 0; i < 10; i++) {
                      alternateDemo.loopC(i);
                      
                      log.info("------------------------------------------------------------------------------------------");
                  }
              }
          }, "C").start();
      }
  
  }
  
  @Slf4j
  class AlternateDemo {
  
      /** 当前正在执行线程的标记 */
      private int number = 1;
  
      private Lock lock = new ReentrantLock();
      private Condition condition1 = lock.newCondition();
      private Condition condition2 = lock.newCondition();
      private Condition condition3 = lock.newCondition();
  
      /**
       * 循环打印A
       *
       * @param totalLoop 循环第几轮
       * @author colg
       */
      public void loopA(int totalLoop) {
          lock.lock();
  
          try {
              // 1. 判断
              if (number != 1) {
                  condition1.await();
              }
  
              // 2. 打印
              for (int i = 0; i < 5; i++) {
                  log.info("线程名: {}; 当前值: {}; 当前第几轮: {}", Thread.currentThread().getName(), i, totalLoop);
              }
  
              // 3. 唤醒
              number = 2;
              condition2.signal();
          } catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
              lock.unlock();
          }
      }
  
      /**
       * 循环打印B
       *
       * @param totalLoop 循环第几轮
       * @author colg
       */
      public void loopB(int totalLoop) {
          lock.lock();
  
          try {
              // 1. 判断
              if (number != 2) {
                  condition2.await();
              }
  
              // 2. 打印
              for (int i = 0; i < 5; i++) {
                  log.info("线程名: {}; 当前值: {}; 当前第几轮: {}", Thread.currentThread().getName(), i, totalLoop);
              }
  
              // 3. 唤醒
              number = 3;
              condition3.signal();
          } catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
              lock.unlock();
          }
      }
  
      /**
       * 循环打印C
       *
       * @param totalLoop 循环第几轮
       * @author colg
       */
      public void loopC(int totalLoop) {
          lock.lock();
  
          try {
              // 1. 判断
              if (number != 3) {
                  condition3.await();
              }
  
              // 2. 打印
              for (int i = 0; i < 5; i++) {
                  log.info("线程名: {}; 当前值: {}; 当前第几轮: {}", Thread.currentThread().getName(), i, totalLoop);
              }
  
              // 3. 唤醒
              number = 1;
              condition1.signal();
          } catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
              lock.unlock();
          }
      }
  }
  ```

  

## 10. ReadWriteLock 读写锁

- ReadWriteLock维护了一对相关的锁, 一个用于只读操作, 另一个用于写入操作. 只要没有writer, 读取锁可以由多个reader线程同时保持. 写入锁是独占的.
- ReadWriteLock读取操作通常不会改变共享资源, 但执行写入操作时, 必须独占方式来获取锁. 对于读取操作占多数的数据结构. ReadWriteLock能提供比独占锁更高的并发性. 而对于只读的数据结构, 其中包含的不变性可以不需要考虑加锁操作.

## 11. 线程八锁

- 非静态方法的锁默认为 this, 静态方法的锁为对应的 Class 实例
- 在某一时刻内, 只能有一个线程持有锁, 无论几个方法.

## 12. 线程池

- 第四种获取线程的方法: 线程池, 一个ExecutorService, 它使用可能的几个线程池之一执行每个提交的任务, 通常使用Executors工厂方法配置.
- 线程池可以解决两个不同问题: 由于减少了每个任务调用的开销, 它们通常可以在执行大量异步任务时提供增强的性能, 并且还可以提供绑定和管理资源(包括执行任务集时使用的线程)的方法. 每个ThreadPoolExecutor还维护者一些基本的统计数据, 如完成的任务数.
- 为了便于跨大量上下文使用, 此类提供了很多可调整的参数和扩展钩子(hook). 但是, 强烈建议程序员使用较为方便的Executors工厂方法
  - `java.util.concurrent.Executors.newCachedThreadPool()`: 无界线程池, 可以进行自动线程回收
  - `java.util.concurrent.Executors.newFixedThreadPool(int)`: 固定大小线程池
  - `java.util.concurrent.Executors.newSingleThreadScheduledExecutor()`: 单个后台线程
  - 它们均为大多数使用场景预定义了设置.

## 13. 线程调度

- `java.util.concurrent.Executors.newScheduledThreadPool(int)`: 可安排在给定的延迟后运行或定期执行的命令

## 14. ForkJoinPool 分支/合并框架 工作窃取

```ini
todo
```

