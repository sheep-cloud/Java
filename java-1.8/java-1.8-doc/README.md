# Java1.8 新特性

- 速度更快
- 代码更少（增加了新的语法Lambda表达式）
- 强大的Stream API
- 便于并行
- 最大化减少空指针异常 Optional

## 1. Lambda 表达式

### 1.1. 为什么使用 Lambda 表达式

Lambda 是一个**匿名函数**，我们可以把 Lambda 表达式理解为是**一段可以传递的代码**（将代码像数据一样进行传递）。可以写出更简洁、更 灵活的代码。作为一种更紧凑的代码风格，使 Java的语言表达能力得到了提升。

### 1.2. 从匿名类到 Labmbda 的转换

```java
package cn.colg.lambda;

    @Test
    public void test01() throws Exception {
        // jdk 1.7之前，必须是final，jdk 1.8默认在变量前加了 final
        String value = "jdk 新特性";
        ThreadUtil.execute(new Runnable() {

            @Override
            public void run() {
                log.info("colg : {}", value);
            }
        });
        
        // Lamdba 表达式
        ThreadUtil.execute(() -> log.info("colg : {}", value));
    }
```

```java
2018-12-10 09:57:42.014 - INFO [pool-2-thread-1] cn.colg.lambda.Lambda02Test              : colg : jdk 新特性
2018-12-10 09:57:42.057 - INFO [pool-2-thread-1] cn.colg.lambda.Lambda02Test              : colg : jdk 新特性
2018-12-10 09:57:42.057 - INFO [           main] cn.colg.BaseTest                         : ------------------------------------------------------------------------------------------
```

### 1.3. Lambda 表达式语法

```java
/**
 * Lambda 表达式语法
 * 
 * <pre>
 * 一、Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或Lambda操作符
 *  箭头操作符将Lambda表达式拆分成都那个两部分：
 *      左侧：Lambda表达式的参数列表
 *      右侧：Lambda表达式中所需要执行的功能，即Lambda体
 *      
 *  语法格式一：无参数，无返回值
 *      Runnable r1 = () -> log.info("Hello Lambda!");
 *      
 *  语法格式二：有一个参数，无返回值
 *      Consumer<String> con = (x) -> log.info(x);
 *      
 *  语法格式三：若只有一个参数，小括号可以省略不写（不推荐）
 *      Consumer<String> con = x -> log.info(x);
 *      
 *  语法格式四：有两个以上的参数，有返回值，并且Lambda体中有多条语句
 *      Comparator<Integer> com = (x, y) -> {
 *           log.info("函数式结构");
 *           return ...;
 *      }
 *      
 *  语法格式五：若Lambda体中只有一条语句，return和大括号都可以省略不写（不推荐）
 *      Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 *      
 *  语法格式六：Lamdba表达式的参数列表的数据类型可以省略不写因为JVM编译器通过上下文推断出，数据类型，即"类型推断"
 *  
 *  上联：左右遇一括号省
 *  下联：左侧推断类型省
 *  横批：能省则省
 *  
 *  二、Lamdba 表达式需要"函数式接口"的支持
 *  函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用注解@FuncationlInterface修饰
 *      可以检查是否是函数式接口。
 * </pre>
 *
 * @author colg
 */
```

### 1.4. Lambda 常用方法

#### 1.4.1. ForEach

```java
package cn.colg.foreach;

import java.util.List;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import lombok.extern.slf4j.Slf4j;

/**
 * Java1.8 ForEach 测试
 *
 * @author colg
 */
@Slf4j
public final class ForEachTest extends BaseTest{
    
    @Test
    public void testArray() throws Exception {
        // 新建一个Array
        String[] strings = {"Jack", "Rose", "Tom", "Jax"};
        List<String> list = CollUtil.newArrayList(strings);
        list.forEach(Console::log);
    }

    /**
     * ForEach 遍历集合
     *
     * @throws Exception
     */
    @Test
    public void testForEachCollection() throws Exception {
        // 新建一个ArrayList
        List<String> list = CollUtil.newArrayList("Jack", "Rose", "Tom", "Jax");
        // 1. 推荐
        list.forEach(Console::log);
        log.info("---------------------------------------------------------------------");
        // 2. 过滤
        list.stream().filter(str -> !"Jack".equals(str))
                     .forEach(Console::log);
    }

    /**
     * 遍历Map，Dict（字典对象，扩充了HashMap中的方法）
     *
     * @throws Exception
     */
    @Test
    public void testDict() throws Exception {
        /*
         * 构造时必须指定初始容量：
         *  负载因子：static final float DEFAULT_LOAD_FACTOR = 0.75f;
         *  存储个数：(存储的元素个数 / 负载因子) + 1
         */
        Dict dict = new Dict(6).set("Jack", 18)
                               .set("Rose", 20)
                               .set("Tom", 22)
                               .set("Jax", 25);

        dict.forEach((key, value) -> Console.log(key + ": {}", value));
    }
}
```

```java
Jack
Rose
Tom
Jax
2018-12-10 10:06:03.894 - INFO [           main] cn.colg.BaseTest                         : ----------------------------------------------------------------------------------------
Jack: 18
Rose: 20
Tom: 22
Jax: 25
2018-12-10 10:06:03.899 - INFO [           main] cn.colg.BaseTest                         : ----------------------------------------------------------------------------------------
Jack
Rose
Tom
Jax
2018-12-10 10:06:03.901 - INFO [           main] cn.colg.foreach.ForEachTest              : ----------------------------------------------------------------------------------------
Rose
Tom
Jax
2018-12-10 10:06:03.905 - INFO [           main] cn.colg.BaseTest                         : ----------------------------------------------------------------------------------------
```

## 2. 函数式接口

### 2.1. 什么是函数式接口

- 只包含一个抽象方法的接口，称为**函数式接口**。
- 可以通过 Lambda 表达式来创建该接口的对象。（若Lambda表达式抛出一个受检异常，那么该异常需要在目标接口的抽象方法上进行声明）。
- 可以在任意函数式接口上使用`@FunctionalInterface`注解，这样做可以检查它是否是一个函数式接口，同时javadoc也会包含一条声明，说明这个接口是一个函数式接口。

### 2.2. 自定义函数式接口

```java
/**
 * 过滤对象信息的接口
 * 
 * <pre>
 * 函数式接口：
 *  接口中只有一个抽象方法的接口，称为函数式接口。
 *  '@FunctionalInterface'： 检查是否是函数式接口。
 * </pre>
 *
 * @author colg
 * @param <T>
 */
@FunctionalInterface
public interface MyStrategy<T> {

    /**
     * 返回比较后的结果
     *
     * @param t
     * @return
     */
    boolean compartor(T t);
}
```

```java
/**
 * 函数式接口
 *
 * @author colg
 */
@FunctionalInterface
public interface MyFun<T> {

    /**
     * 根据传入的num执行运算
     *
     * @param num
     * @return
     */
    Integer getValue(Integer num);
}
```

### 2.3. 作为参数传递

```java
package cn.colg.lambda;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.colg.functional.MyFun;
import lombok.extern.slf4j.Slf4j;

/**
 * Lambda 表达式测试
 *
 * @author colg
 */
@Slf4j
public class Lambda03Test extends BaseTest{

    /**
     * 对一个数进行运算
     *
     * @param num
     * @param mf
     * @return
     */
    private <T> Integer operation(Integer num, MyFun<T> mf) {
        return mf.getValue(num);
    }

    @Test
    public void test01() throws Exception {
        Integer result = operation(10, new MyFun<Integer>() {
            
            @Override
            public Integer getValue(Integer num) {
                return num * 10;
            }
        });
        log.info("result : {}", result);
        
        // 参数列表：(x)
        // 方法体：x * x
        result = operation(10, (x) -> x * 10);
        log.info("result : {}", result);

        result = operation(200, x -> x + 200);
        log.info("result : {}", result);
    }
}
```

```java
2018-12-10 10:54:41.288 - INFO [           main] cn.colg.lambda.Lambda03Test              : result : 100
2018-12-10 10:54:41.347 - INFO [           main] cn.colg.lambda.Lambda03Test              : result : 100
2018-12-10 10:54:41.348 - INFO [           main] cn.colg.lambda.Lambda03Test              : result : 400
2018-12-10 10:54:41.348 - INFO [           main] cn.colg.BaseTest                         : ------------------------------------------------------------------------------------------
```

### 2.4. java内置四大核心函数式接口

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqtvk2eao9j30pb0cz40o.jpg)

## 3. 方法引用与构造器引用

### 3.1. 方法引用

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqtxzl28poj30oq0dpwg8.jpg)

```java
package cn.colg.method;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.colg.entity.Employee;
import cn.hutool.core.lang.Console;

/**
 * 方法引用：若Lambda体的内容有方法已经实现了，我们可以使用"方法引用"（可以理解为方法引用是Lambda表达式的另外一种表现形式）
 * 
 * <pre>
 * 主要有三种语法格式：
 *  对象::实例方法名
 *  类::静态方法名
 *  类::实例方法名
 * </pre>
 *
 * @author colg
 */
public class MethodRefTest extends BaseTest{

    /**
     * 方法引用
     *
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {
        String value = "colg";

        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept(value);
        Consumer<String> consumer2 = x -> Console.log(x);
        consumer2.accept(value);

        // 类::实例方法名
        Consumer<String> consumer3 = System.out::println;
        consumer3.accept(value);
        // 类::静态方法名
        Consumer<String> consumer4 = Console::log;
        consumer4.accept(value);
    }

    /**
     * 对象::实例方法名
     *
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        Employee employee = new Employee("colg", 28, 9999.99);

        Supplier<String> supplier = () -> employee.getName();
        Console.log(supplier.get());

        // 方法引用
        Supplier<String> supplier2 = employee::getName;
        Console.log(supplier2.get());
    }
}
```

### 3.2. 构造器引用

```java
package cn.colg.constructor;

import java.util.function.Supplier;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.colg.entity.Employee;
import lombok.extern.slf4j.Slf4j;

/**
 * 构造器引用：与函数式接口相结合，自动与函数式接口中方法兼容。可以把构造器引用赋值给定义的方法，与构造器参数列表要与接口中抽象方法的参数列表一致！
 * 
 * <pre>
 * 语法格式：
 *  ClassName::new
 * </pre>
 *
 * @author colg
 */
@Slf4j
public class ConstructorRefTest extends BaseTest{

    @Test
    public void test01() throws Exception {
        Supplier<Employee> supplier = () -> new Employee();
        Employee employee = supplier.get();
        log.info("employee : {}", employee);

        // 构造器引用
        supplier = Employee::new;
        employee = supplier.get();
        log.info("employee : {}", employee);
    }
}
```

## 4. Stream API

### 4.1. 了解 Stream

Java8中有两大最为重要的改变。第一个是 **Lambda 表达式**；另外一个则是 **Stream API(java.util.stream.*)**。

Stream 是 Java8 中处理集合的关键抽象概念，它可以指定你希望对集合进行的操作，可以执行非常复杂的查找、过滤和映射数据等操作。

使用Stream API 对集合数据进行操作，就类似于使用 SQL 执行的数据库查询。也可以使用 Stream API 来并行执行操作。

简而言之，Stream API 提供了一种高效且易于使用的处理数据的方式。

### 4.2. Stream 到底是什么呢？

流（Stream）是数据通道，用于操作数据源（集合、数组等）所生成的元素序列。

**集合讲的是数据，流讲的是计算**

```ini
注意：
1. Stream 自己不会存储元素。
2. Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
3. Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
```

### 4.3. Stream 操作的三个步骤

- 创建Stream
  -  一个数据源（如：集合、数组），获取一个流

- 中间操作
  -   一个中间操作链，对数据源的数据进行处理

- 终止操作（终端操作）
  - 一个终止操作，执行中间操作链，并产生结果
    ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqtynso36mj30pi04jwet.jpg)

#### 4.3.1. 创建Stream

```java
package cn.colg.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.colg.entity.Employee;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;

/**
 * Stream API 1.创建Stream 测试
 * 
 * <pre>
 * 一、Stream 的三个操作步骤：
 *  1. 创建Stream
 *  
 *  2. 中间操作
 *  
 *  3. 终止操作（终端操作）
 * </pre>
 *
 * @author colg
 */
public class StreamApi01Test extends BaseTest{

    @SuppressWarnings("unused")
    @Test
    public void test01() throws Exception {
        // 1. 可以通过Collection系列集合提供的stream()或parallelStream()
        List<Employee> list = new ArrayList<>();
        Stream<Employee> stream = list.stream();

        // 2. 通过Arrays中的静态方法stream(...)获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(employees);

        // 3. 通过Stream的静态方法of()
        Stream<String> stream3 = Stream.of("colg", "cloud", "java");

        // 4. 创建无限流
        //  1) 迭代
        Stream<Integer> stream4 = Stream.iterate(0, x -> x + 1);
        stream4.limit(10)
               .forEach(Console::log);
        
        // 4. 创建无限流
        //  2) 生成
        Stream<Integer> stream5 = Stream.generate(() -> RandomUtil.randomInt(100, 1000));
        stream5.limit(10)
               .forEach(Console::log);
    }
}
```

#### 4.3.2. 中间操作

```java
package cn.colg.stream;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.colg.entity.Employee;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import lombok.extern.slf4j.Slf4j;

/**
 * Stream API 2.中间操作 测试
 *
 * @author colg
 */
@Slf4j
public class StreamApi02Test extends BaseTest{
    
    /** 初始化员工信息 */
    private List<Employee> employees = CollUtil.newArrayList(
            new Employee("Jack", 18, 2222.99),
            new Employee("Rose", 28, 5555.99),
            new Employee("Tom", 50, 4444.99),
            new Employee("Jax", 16, 3333.99),
            new Employee("Luo", 40, 7777.99),
            new Employee("Luo", 8, 7777.99),
            new Employee("Luo", 8, 7777.99)
        );
    
    /*
     * 筛选与切片：
     *  filter：       接收Lambda，从流中排除某些元素。
     *  limit：          截断流，使其元素不超过给定数量。  
     *  skip(n)：    跳过元素，返回一个扔掉前n个元素的流。若流中元素不是n个，则返回一个空流。与limit(n)互补
     *  distinct： 筛选，通过流所生成元素的hashCode()和equals去除重复元素
     *  
     * 映射：
     *  map：                接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     *  flatMap：    接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     *  
     * 排序：
     *  sorted()：   自然排序
     *  sorted(Comparator comparator)：  定制排序
     */
    
    /// ---------------------------------------------------------------------------------

    
    /**
     * filter： 接收Lambda，从流中排除某些元素。
     *
     * @throws Exception
     */
    @Test
    public void testFilter() throws Exception {
        Console.log("cn.colg.StreamApi02Test.testFilter()");
        // 创建 stream
        Stream<Employee> stream = employees.stream();
        
        // 中间操作：不会执行任何操作
        stream = stream.filter(e -> {
            Console.log("Stream API的中间操作");
            return e.getAge() > 35;
        });
        
        // 终止操作：一次性执行全部内容，即"惰性求职"
        // 内部迭代：迭代操作由Stream API完成
        stream.forEach(Console::log);
    }
    
    /**
     * limit：    截断流，使其元素不超过给定数量。
     *
     * @throws Exception
     */
    @Test
    public void testLimit() throws Exception {
        employees.stream()
                 .limit(2)
                 .forEach(Console::log);
    }
    
    /**
     * skip(n)：    跳过元素，返回一个扔掉前n个元素的流。若流中元素不是n个，则返回一个空流。与limit(n)互补
     *
     * @throws Exception
     */
    @Test
    public void testSkip() throws Exception {
        employees.stream()
                 .skip(2L)
                 .forEach(Console::log);
    }
    
    /**
     * SkipLimit：集合分页，skip((页码-1) * 每页显示的条数).limit(每页显示的条数)
     *
     * @throws Exception
     * @author colg
     */
    @Test
    public void testSkipLimit() throws Exception {
        employees.stream()
                 .skip(2)
                 .limit(2)
                 .forEach(Console::log);
    }
    
    /**
     * distinct： 筛选，通过流所生成元素的hashCode()和equals去除重复元素
     *
     * @throws Exception
     */
    @Test
    public void testDistinct() throws Exception {
        employees.stream()
                 .distinct()
                 .forEach(Console::log);
    }
    
    /**
     * map：                接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     *
     * @throws Exception
     */
    @Test
    public void testMap() throws Exception {
        List<String> list = CollUtil.newArrayList("colg", "cloud", "java");
        list.stream()
            .map(str -> str.toUpperCase())
            .forEach(Console::log);
        log.info("--------------------------------------------------------------------");
        
        employees.stream()
                 .map(Employee::getName)
                 .forEach(Console::log);
    }
    
    /**
     * sorted()：   自然排序</br>
     * sorted(Comparator comparator)：  定制排序
     *
     * @throws Exception
     */
    @Test
    public void testSorted() throws Exception {
        List<String> list = CollUtil.newArrayList("colg", "cloud", "java");
        list.stream()
            .sorted()
            .forEach(Console::log);
        
        employees.stream()
                 .sorted((e1, e2) -> {
                     if (e1.getAge() == e2.getAge()) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return e1.getAge().compareTo(e2.getAge());
                    }
                 }).forEach(Console::log);
    }
}
```

#### 4.3.3. 终止操作

```java
package cn.colg.stream;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.colg.entity.Employee;
import cn.colg.enums.Status;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Stream API 3.终止操作（终端操作） 测试
 *
 * @author colg
 */
@Slf4j
public class StreamApi03Test extends BaseTest{

    /** 初始化员工信息 */
    private List<Employee> employees = CollUtil.newArrayList(
            new Employee("Jack", 18, 2222.99, Status.FREE),
            new Employee("Rose", 28, 5555.99, Status.BUSY),
            new Employee("Tom", 50, 4444.99, Status.VOCATION),
            new Employee("Jax", 16, 3333.99, Status.FREE),
            new Employee("Luo", 8, 7777.99, Status.BUSY)
        );
    
    /*
     * 查找与匹配：
     *  allMatch：       检查是否匹配所有元素
     *  anyMatch：       检查是否至少匹配一个元素
     *  noneMatch：    检查是否没有匹配所有元素
     *  findFirst：    返回第一个元素
     *  findAny：          返回当前流中的任意元素
     *  count：                返回流中元素的总个数
     *  max：                      返回流中最大值
     *  min：                      返回流中最小值
     *  collect：          收集，将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     */
    
    /// ----------------------------------------------------------------------------------------------------

    @Test
    public void testMatchFind() throws Exception {
        Set<Employee> set = employees.stream()
                                     .collect(Collectors.toSet());
        log.info("set : {}", set);
        
        log.info("---------------------------------------------------------------------");
        
        boolean allMatch = employees.stream()
                                    .allMatch(e -> e.getStatus().equals(Status.BUSY));
        log.info("allMatch : {}", allMatch);
        
        log.info("---------------------------------------------------------------------");
        
        boolean anyMatch = employees.stream()
                                    .anyMatch(e -> e.getStatus().equals(Status.BUSY));
        log.info("anyMatch : {}", anyMatch);
        
        log.info("---------------------------------------------------------------------");
        
        boolean noneMatch = employees.stream()
                                     .noneMatch(e -> e.getStatus().equals(Status.BUSY));
        log.info("noneMatch : {}", noneMatch);
        
        log.info("---------------------------------------------------------------------");
        
        Optional<Employee> findFirst = employees.stream()
                                                .findFirst();
        Employee employee = findFirst.orElse(new Employee());
        log.info("employee : {}", employee);
        
        log.info("---------------------------------------------------------------------");
        
        Optional<Employee> findAny = employees.parallelStream()
                                              .filter(e -> e.getStatus().equals(Status.FREE))
                                              .findAny();
        employee = findAny.orElse(new Employee());
        log.info("employee : {}", employee);
    }
}
```

```java
2018-12-10 10:58:41.567 - INFO [           main] cn.colg.stream.StreamApi03Test           : set : [Employee(name=Tom, age=50, salary=4444.99, status=VOCATION), Employee(name=Luo, age=8, salary=7777.99, status=BUSY), Employee(name=Rose, age=28, salary=5555.99, status=BUSY), Employee(name=Jack, age=18, salary=2222.99, status=FREE), Employee(name=Jax, age=16, salary=3333.99, status=FREE)]
2018-12-10 10:58:41.573 - INFO [           main] cn.colg.stream.StreamApi03Test           : ----------------------------------------------------------------------------------------
2018-12-10 10:58:41.575 - INFO [           main] cn.colg.stream.StreamApi03Test           : allMatch : false
2018-12-10 10:58:41.575 - INFO [           main] cn.colg.stream.StreamApi03Test           : ----------------------------------------------------------------------------------------
2018-12-10 10:58:41.575 - INFO [           main] cn.colg.stream.StreamApi03Test           : anyMatch : true
2018-12-10 10:58:41.576 - INFO [           main] cn.colg.stream.StreamApi03Test           : ----------------------------------------------------------------------------------------
2018-12-10 10:58:41.576 - INFO [           main] cn.colg.stream.StreamApi03Test           : noneMatch : false
2018-12-10 10:58:41.576 - INFO [           main] cn.colg.stream.StreamApi03Test           : ----------------------------------------------------------------------------------------
2018-12-10 10:58:41.578 - INFO [           main] cn.colg.stream.StreamApi03Test           : employee : Employee(name=Jack, age=18, salary=2222.99, status=FREE)
2018-12-10 10:58:41.579 - INFO [           main] cn.colg.stream.StreamApi03Test           : ----------------------------------------------------------------------------------------
2018-12-10 10:58:41.582 - INFO [           main] cn.colg.stream.StreamApi03Test           : employee : Employee(name=Jack, age=18, salary=2222.99, status=FREE)
2018-12-10 10:58:41.582 - INFO [           main] cn.colg.BaseTest                         : ----------------------------------------------------------------------------------------
```

## 5. 接口中的默认方法与静态方法

## 6. 新时间日期 API

## 7. 其他新特性