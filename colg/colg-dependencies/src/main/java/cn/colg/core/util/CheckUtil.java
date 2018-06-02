package cn.colg.core.util;

import java.util.Collection;
import java.util.Map;

import cn.colg.core.exception.CheckException;

/**
 * 校验工具
 *
 * @author colg
 */
public final class CheckUtil {

    private CheckUtil() {}

    /**
     * 表达式的结果为false时，抛出校验异常
     *
     * @param condition 校验条件
     * @param msg 错误消息提示
     */
    public static void check(boolean condition, String msg) {
        if (!condition) {
            fail(msg);
        }
    }

    /**
     * 对象，字符串，集合为空白时，抛出校验异常，空白的定义如下：<br>
     * 1、Object: 为null <br>
     * 2、String: 为不可见字符（如空格） <br>
     * 3、String: ""<br>
     * 4、Collection/Map: size()==0 <br>
     *
     * @param value 需要校验的对象，字符串，集合
     * @param msg 错误消息提示
     */
    public static void notNull(Object value, String msg) {
        if (value == null) {
            fail(msg);
        }

        if (value instanceof String) {
            // 校验 String
            String str = (String)value;
            if (str.trim().length() == 0) {
                fail(msg);
            }
        } else if (value instanceof Collection<?>) {
            // 校验 Collection
            Collection<?> coll = (Collection<?>)value;
            if (coll.size() == 0) {
                fail(msg);
            }
        } else if (value instanceof Map<?, ?>) {
            // 校验 Map
            Map<?, ?> map = (Map<?, ?>)value;
            if (map.size() == 0) {
                fail(msg);
            }
        }
    }

    /**
     * 抛出校验异常
     *
     * @param msg 错误提示消息
     */
    private static void fail(String msg) {
        throw new CheckException(msg);
    }
}
