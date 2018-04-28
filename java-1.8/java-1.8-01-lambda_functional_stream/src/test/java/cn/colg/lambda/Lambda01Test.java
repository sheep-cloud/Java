package cn.colg.lambda;

import java.util.Comparator;
import java.util.TreeSet;

import org.junit.Test;

import cn.hutool.core.lang.Console;

/**
 * Lambda 表达式体验
 *
 * @author colg
 */
public final class Lambda01Test {
    /**
     * 匿名内部类
     *
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {
        Comparator<Integer> comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(01, 02);
            }
        };

        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
        Console.log(treeSet);
    }

    /**
     * Lambda 表达式
     *
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
        Console.log(treeSet);
    }
}
