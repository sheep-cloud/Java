package cn.colg.bean;

import org.junit.Test;

import cn.colg.BaseTest;

/**
 * Student 实体 测试
 *
 * @author colg
 */
public class StudentTest extends BaseTest {

    /**
     * Test method for {@link cn.colg.bean.Student#wali()}.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testWali() {
        Person person = new Student();
        person.wali();
    }

    /**
     * Test method for {@link cn.colg.bean.Student#eat()}.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testEat() {
        Person person = new Student();
        person.eat();
    }

}
