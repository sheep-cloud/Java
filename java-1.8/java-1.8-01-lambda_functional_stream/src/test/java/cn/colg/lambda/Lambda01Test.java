package cn.colg.lambda;

import java.util.Comparator;
import java.util.TreeSet;

import org.junit.Test;

import cn.colg.BaseTest;
import lombok.extern.slf4j.Slf4j;

/**
 * Lambda 表达式体验
 *
 * @author colg
 */
@Slf4j
public final class Lambda01Test extends BaseTest{
    
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
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(2);
        log.info("treeSet : {}", treeSet);
    }

    /**
     * Lambda 表达式
     *
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        TreeSet<Integer> treeSet = new TreeSet<>((o1, o2) -> Integer.compare(o1, o2));
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(2);
        log.info("treeSet : {}", treeSet);
    }
}
