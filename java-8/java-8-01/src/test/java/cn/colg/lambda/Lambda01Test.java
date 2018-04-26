package cn.colg.lambda;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.TreeSet;

import org.junit.Test;

/**
 * Lambda 表达式体验
 *
 * @author colg
 */
public class Lambda01Test {

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
        assertNotNull(treeSet);
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
        assertNotNull(treeSet);
    }

}
