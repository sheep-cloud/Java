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
     * 表达式的结果为 false 时，抛出校验异常
     *
     * @param bool 校验条件
     * @param msg 消息提示
     * @author colg
     */
    public static void checkTrue(boolean bool, String msg) {
        if (!bool) {
            throwFail(msg);
        }
    }

    /**
     * 表达式的结果为 true 时，抛出校验异常
     *
     * @param bool 校验条件
     * @param msg 消息提示
     * @author colg
     */
    public static void checkFalse(boolean bool, String msg) {
        if (bool) {
            throwFail(msg);
        }
    }

    /**
     * 对象，字符串，集合为空白时，抛出校验异常，空白的定义如下： <br>
     * 1、Object: 为 null <br>
     * 2、String: 为不可见字符（如空格） <br>
     * 3、String: "" <br>
     * 4、Collection/Map: size()==0 <br>
     *
     * @param value 需要校验的对象，字符串，集合
     * @param msg 错误消息提示
     * @author colg
     */
    public static void checkNotNull(Object value, String msg) {
        if (value == null) {
            throwFail(msg);
        }

        if (value instanceof String) {
            // 校验 String
            String str = (String)value;
            if (str.trim().length() == 0) {
                throwFail(msg);
            }
        } else if (value instanceof Collection<?>) {
            // 校验 Collection
            Collection<?> coll = (Collection<?>)value;
            if (coll.size() == 0) {
                throwFail(msg);
            }
        } else if (value instanceof Map<?, ?>) {
            // 校验 Map
            Map<?, ?> map = (Map<?, ?>)value;
            if (map.size() == 0) {
                throwFail(msg);
            }
        }
    }

    /**
     * 对象不为 null 时，抛出校验异常，空白的定义如下： <br>
     *
     * @param value 需要校验的对象
     * @param msg 错误消息提示
     * @author colg
     */
    public static void checkNull(Object value, String msg) {
        if (value != null) {
            throwFail(msg);
        }
    }

    /**
     * 抛出校验异常
     *
     * @param msg 错误提示消息
     * @author colg
     */
    public static void throwFail(String msg) {
        throw new CheckException(msg);
    }
}
