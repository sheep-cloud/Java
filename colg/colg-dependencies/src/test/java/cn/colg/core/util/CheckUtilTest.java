package cn.colg.core.util;

import static cn.colg.core.util.CheckUtil.check;
import static cn.colg.core.util.CheckUtil.notNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.colg.core.exception.CheckException;

/**
 * 校验工具 测试
 *
 * @author colg
 */
public class CheckUtilTest {

    /**
     * Test method for {@link cn.colg.core.util.CheckUtil#check(boolean, java.lang.String)}.
     */
    @Test(expected = CheckException.class)
    public final void testCheck() {
        check(false, "error");
    }

    /**
     * Test method for {@link cn.colg.core.util.CheckUtil#notNull(Object, java.lang.String)}.
     */
    @Test(expected = CheckException.class)
    public final void testNotNullObject() {
        String value = null;
        notNull(value, "error");
    }

    @Test
    public void testNotNullString() throws Exception {
        String value = "a";
        notNull(value, "error");
    }

    @Test(expected = CheckException.class)
    public void testNotNullCollection() throws Exception {
        List<?> list = new ArrayList<>();
        notNull(list, "error");
    }

    @Test(expected = CheckException.class)
    public void testNotNullMap() throws Exception {
        Map<?, ?> map = new HashMap<>();
        notNull(map, "error");
    }

    @Test
    public void testName() throws Exception {
        Character a = ' ';
        notNull(a, "error");
    }
}
