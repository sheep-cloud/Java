# Java1.8 新特性

- 速度更快
- 代码更少（增加了新的语法Lambda表达式）
- 强大的Stream API
- 便于并行
- 最大化减少空指针异常

## 1. Lambda 表达式

### 1.1. 为什么使用 Lambda 表达式

Lambda 是一个匿名函数，我们可以把 Lambda 表达式理解为是一段可以传递的代码（将代码 像数据一样进行传递）。可以写出更简洁、更 灵活的代码。作为一种更紧凑的代码风格，使 Java的语言表达能力得到了提升。

### 1.2. 从匿名类到 Labmbda 的转换

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqtuf5ajpfj30w409mglr.jpg)

### 1.3. Lambda 表达式语法

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqqgp1789oj30np0l7gm7.jpg)

### 1.4. Lambda 常用方法

#### 1.4.1. ForEach

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqsy7xkap4j30pw0nsq3k.jpg)

## 2. 函数式接口

### 2.1. 什么是函数式接口

- 只包含一个抽象方法的接口，称为**函数式接口**。
- 可以通过 Lambda 表达式来创建该接口的对象。（弱Lambda表达式抛出一个受检异常，那么该异常需要在目标接口的抽象方法上进行声明）。
- 可以在任意函数式接口上使用`@FunctionalInterface`注解，这样做可以检查它是否是一个函数式接口，同时javadoc也会包含一条声明，说明这个接口是一个函数式接口。

### 2.2. 自定义函数式接口

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqqh3289qbj30fu0anglm.jpg)
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqqh3f3n4vj30az08it8m.jpg)

### 2.3. 作为参数传递

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqqh581fr9j30he0fuwer.jpg)

### 2.4. java内置四大核心函数式接口

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqtvk2eao9j30pb0cz40o.jpg)

## 3. 方法引用与构造器引用

### 3.1. 方法引用

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqtxzl28poj30oq0dpwg8.jpg)
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqty0grv1lj30js0n9t9d.jpg)

### 3.2. 构造器引用

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqty9obdntj30pi0c6jrm.jpg)

## 4. Stream API

### 4.1. 了解 Stream

Java8中有两大最为重要的改变。第一个是 Lambda 表达式；另外一个则是 Stream API(java.util.stream.*)。

Stream 是 Java8 中处理集合的关键抽象概念，它可以指定你希望对集合进行的操作，可以执行非常复杂的查找、过滤和映射数据等操作。

使用Stream API 对集合数据进行操作，就类似于使用 SQL 执行的数据库查询。也可以使用 Stream API 来并行执行操作。

简而言之，Stream API 提供了一种高效且易于使用的处理数据的方式。

### 4.2. Stream 到底是什么呢？

流（Stream）是数据通道，用于操作数据源（集合、数组等）所生成的元素序列。

**集合讲的是数据，流讲的是计算**

```properties
注意：
1. Stream 自己不会存储元素。
2. Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
3. Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
```

### 4.3. Stream 操作的三个步骤

1. 创建Stream：	一个数据源（如：集合、数组），获取一个流
2. 中间操作：      一个中间操作链，对数据源的数据进行处理
3. 终止操作（终端操作）：一个终止操作，执行中间操作链，并产生结果
  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqtynso36mj30pi04jwet.jpg)

## 5. 接口中的默认方法与静态方法

## 6. 新时间日期 API

## 7. 其他新特性