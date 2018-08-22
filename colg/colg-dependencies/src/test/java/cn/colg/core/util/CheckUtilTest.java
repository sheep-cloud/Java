package cn.colg.core.util;

import static cn.colg.util.CheckUtil.checkFalse;
import static cn.colg.util.CheckUtil.checkNotNull;
import static cn.colg.util.CheckUtil.checkNull;
import static cn.colg.util.CheckUtil.checkTrue;
import static cn.colg.util.CheckUtil.throwFail;

import java.util.ArrayList;

import org.junit.Test;

import cn.colg.exception.CheckException;

/**
 * 校验工具测试
 *
 * @author colg
 */
public class CheckUtilTest {

    public static final String MSG = "ERROR";

    /**
     * Test method for {@link cn.colg.util.CheckUtil#checkTrue(boolean, java.lang.String)}.
     */
    @Test(expected = CheckException.class)
    public final void testCheckTrue() {
        boolean bool = false;
        checkTrue(bool, MSG);
    }

    /**
     * Test method for {@link cn.colg.util.CheckUtil#checkFalse(boolean, java.lang.String)}.
     */
    @Test(expected = CheckException.class)
    public final void testCheckFalse() {
        boolean bool = true;
        checkFalse(bool, MSG);
    }

    /**
     * Test method for {@link cn.colg.util.CheckUtil#checkNotNull(java.lang.Object, java.lang.String)}.
     */
    @Test(expected = CheckException.class)
    public final void testheckNotNull() {
        Object value = null;
        checkNotNull(value, MSG);
    }

    /**
     * Test method for {@link cn.colg.util.CheckUtil#checkNull(java.lang.Object, java.lang.String)}.
     */
    @Test(expected = CheckException.class)
    public final void testCheckNull() {
        Object value = new ArrayList<>();
        checkNull(value, MSG);
    }

    /**
     * Test method for {@link cn.colg.util.CheckUtil#throwFail(java.lang.String)}.
     */
    @Test(expected = CheckException.class)
    public final void testThrowFail() {
        throwFail(MSG);
    }

}
