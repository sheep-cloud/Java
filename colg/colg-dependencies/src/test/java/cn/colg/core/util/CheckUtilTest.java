package cn.colg.core.util;

import static cn.colg.core.util.CheckUtil.check;
import static cn.colg.core.util.CheckUtil.notNull;

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
     * Test method for {@link cn.colg.core.util.CheckUtil#notNull(java.lang.Object, java.lang.String)}.
     */
    @Test(expected = CheckException.class)
    public final void testNotNull() {
        String obj = null;
        notNull(obj, "error");

        String str = "";
        notNull(str, "error");

        String space = "  ";
        notNull(space, "error");
    }

}
