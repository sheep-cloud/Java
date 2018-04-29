# Java1.8 新特性

- 速度更快
- 代码更少（增加了新的语法Lambda表达式）
- 强大的Stream API
- 便于并行
- 最大化减少空指针异常

## 1、Lambda 表达式

### 1. 为什么使用 Lambda 表达式
Lambda 是一个匿名函数，我们可以把 Lambda 表达式理解为是一段可以传递的代码（将代码 像数据一样进行传递）。可以写出更简洁、更 灵活的代码。作为一种更紧凑的代码风格，使 Java的语言表达能力得到了提升。

### 2. 从匿名类到 Labmbda 的转换
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqtuf5ajpfj30w409mglr.jpg)

### 3. Lambda 表达式语法

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqqgp1789oj30np0l7gm7.jpg)

### 4. Lambda 常用方法
#### 1. ForEach
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqsy7xkap4j30pw0nsq3k.jpg)

## 2、函数式接口

### 1. 什么是函数式接口
- 只包含一个抽象方法的接口，称为**函数式接口**。
- 可以通过 Lambda 表达式来创建该接口的对象。（弱Lambda表达式抛出一个受检异常，那么该异常需要在目标接口的抽象方法上进行声明）。
- 可以在任意函数式接口上使用`@FunctionalInterface`注解，这样做可以检查它是否是一个函数式接口，同时javadoc也会包含一条声明，说明这个接口是一个函数式接口。

### 2. 自定义函数式接口
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqqh3289qbj30fu0anglm.jpg)
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqqh3f3n4vj30az08it8m.jpg)

### 3. 作为参数传递
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqqh581fr9j30he0fuwer.jpg)

## 3、方法引用与构造器引用

## 4、Stream API

## 5、接口中的默认方法与静态方法

## 6、新时间日期 API

## 7、其他新特性