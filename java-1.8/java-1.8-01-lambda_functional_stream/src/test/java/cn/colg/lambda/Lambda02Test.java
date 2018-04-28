package cn.colg.lambda;

import java.util.Comparator;
import java.util.function.Consumer;

import org.junit.Test;

import cn.hutool.core.lang.Console;

/**
 * Lambda 表达式语法
 * 
 * <pre>
 * 一、Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或Lambda操作符
 *  箭头操作符将Lambda表达式拆分成都那个两部分：
 *      左侧： Lambda表达式的参数列表
 *      右侧：Lambda表达式中所需要执行的功能，即Lambda体
 *      
 *  语法格式一：无参数，无返回值
 *      Runnable r1 = () -> Const.log("Hello Lambda!);
 *      
 *  语法格式二：有一个参数，无返回值
 *      Consumer<String> con = (x) -> Const.log(x);
 *      
 *  语法格式三：若只有一个参数，小括号可以省略不写（不推荐）
 *      Consumer<String> con = x -> Const.log(x);
 *      
 *  语法格式四：有两个以上的参数，有返回值，并且Lambda体中有多条语句
 *      Comparator<Integer> com = (x, y) -> {
 *           Const.log("函数式结构);
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
public class Lambda02Test {

    @Test
    public void test01() throws Exception {
        Console.log("cn.colg.lambda.Lambda02Test.test01()");
        // jdk 1.7之前，必须是final，默认在int前加了 final
        int num = 0;
        Runnable r = new Runnable() {

            @Override
            public void run() {
                Console.log("Hello World!{}", num);
            }
        };
        r.run();

        Console.log("----------------------------------------------------------------------------------------------------");

        // Lamdba 表达式
        Runnable r1 = () -> Console.log("Hello World!");
        r1.run();
    }

    @Test
    public void test02() throws Exception {
        Console.log("cn.colg.lambda.Lambda02Test.test02()");
        Consumer<String> con = (x) -> Console.log(x);
        con.accept("Lambda 表达式威武！");
    }

    @Test
    public void test03() throws Exception {
        Console.log("cn.colg.lambda.Lambda02Test.test03()");
        Consumer<String> con = x -> Console.log(x);
        Console.log(con);
    }

    @Test
    public void test04() throws Exception {
        Console.log("cn.colg.lambda.Lambda02Test.test04()");
        Comparator<Integer> com = (x, y) -> {
            Console.log("函数式接口");
            return Integer.compare(x, y);
        };
        Console.log(com);
    }

    @Test
    public void test05() throws Exception {
        Console.log("cn.colg.lambda.Lambda02Test.test05()");
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        Console.log(com);
    }

}
