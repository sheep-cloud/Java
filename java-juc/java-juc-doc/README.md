# java-juc 多线程集合

## 1. Java JUC简介

- 在Java5.0提供了java.util.concurrent(简称JUC)包，在此包中增加了在并发编程中很常用的实用工具类，用于定义类似于线程的自定义子系统，包括线程池、异步IO和轻量级任务框架。提供可调的、灵活的线程池。还提供了设计用于多线程上下文中的Collection实现等。

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

- 类的小工具包，支持在单个变量上解除锁的线程安全编程。事实上，此包中的类可
  将 volatile 值、字段和数组元素的概念扩展到那些也提供原子条件更新操作的类。 
- 类 AtomicBoolean、 AtomicInteger、 AtomicLong 和 AtomicReference 的实例各自提供对
  相应类型单个变量的访问和更新。每个类也为该类型提供适当的实用工具方法。 
- AtomicIntegerArray、 AtomicLongArray 和 AtomicReferenceArray 类进一步扩展了原子操
  作，对这些类型的数组提供了支持。这些类在为其数组元素提供 volatile 访问语义方
  面也引人注目，这对于普通数组来说是不受支持的。 
- **核心方法： boolean compareAndSet(expectedValue, updateValue)** 
- java.util.concurrent.atomic 包下提供了一些原子操作的常用类: 
  - AtomicBoolean 、 AtomicInteger 、 AtomicLong 、 AtomicReference 
  - AtomicIntegerArray 、 AtomicLongArray 
  - AtomicMarkableReference 
  - AtomicReferenceArray 
  - AtomicStampedReference 

### 3.2. CAS算法

- CAS (Compare-And-Swap) 是一种硬件对并发的支持，针对多处理器
  操作而设计的处理器中的一种特殊指令，用于管理对共享数据的并
  发访问。 
- CAS 是一种无锁的非阻塞算法的实现。 
- CAS 包含了 3 个操作数： 
  - 需要读写的内存值 V 
  - 进行比较的值 A 
  - 拟写入的新值 B 
- 当且仅当 V 的值等于 A 时， CAS 通过原子方式用新值 B 来更新 V 的
  值，否则不会执行任何操作。 

## 4. ConcurrentHashMap 锁分段机制

- Java 5.0 在 java.util.concurrent 包中提供了多种并发容器类来改进同步容器
  的性能。 
- ConcurrentHashMap 同步容器类是Java 5 增加的一个线程安全的哈希表。对
  与多线程的操作，介于 HashMap 与 Hashtable 之间。内部采用“锁分段”
  机制替代 Hashtable 的独占锁。进而提高性能。 
- 此包还提供了设计用于多线程上下文中的 Collection 实现： 
  - ConcurrentHashMap、 ConcurrentSkipListMap、 ConcurrentSkipListSet、
    CopyOnWriteArrayList 和 CopyOnWriteArraySet。 
    - 当期望许多线程访问一个给
      定 collection 时， ConcurrentHashMap 通常优于同步的 HashMap，
      ConcurrentSkipListMap 通常优于同步的 TreeMap。 
    - 当期望的读数和遍历远远
      大于列表的更新数时， CopyOnWriteArrayList 优于同步的 ArrayList。 

## 5. CountDownLatch 闭锁

## 6. 实现 Callable 接口

## 7. Lock 同步锁

## 8. Condition 控制线程通信

## 9. 线程按序交替

## 10. ReadWriteLock 读写锁

## 11. 线程八锁

## 12. 线程池

## 13. 线程调度

## 14. ForkJoinPool 分支/合并框架 工作窃取