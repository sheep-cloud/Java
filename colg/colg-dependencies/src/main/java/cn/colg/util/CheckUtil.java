package cn.colg.util;

import java.util.Collection;
import java.util.Map;

import cn.colg.exception.CheckException;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

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
     * Object 为null时，抛出校验异常
     * 
     * @param value 校验的对象
     * @param msg 消息提示
     * @author colg
     */
    public static void checkNotNull(Object value, String msg) {
        if (value == null) {
            throwFail(msg);
        }
    }

    /**
     * 对象不为 null 时，抛出校验异常
     *
     * @param value 校验的对象
     * @param msg 消息提示
     * @author colg
     */
    public static void checkNull(Object value, String msg) {
        if (value != null) {
            throwFail(msg);
        }
    }

    /**
     * 字符串为空白时，抛出校验异常，空白的定义如下：<br>
     * 1、String: null 2、String: 为不可见字符（如空格） <br>
     * 3、String: "" <br>
     *
     * @param value 校验的字符串
     * @param msg 错误消息
     * @author colg
     */
    public static void checkNotNull(String value, String msg) {
        if (StrUtil.isBlank(value)) {
            throwFail(msg);
        }
    }

    /**
     * 字符串为非空白时，抛出校验异常，空白的定义如下：<br>
     * 1、String: null 2、String: 为不可见字符（如空格） <br>
     * 3、String: "" <br>
     * 
     * @param value 校验的字符串
     * @param msg 消息提示
     * @author colg
     */
    public static void checkNull(String value, String msg) {
        if (StrUtil.isNotBlank(value)) {
            throwFail(msg);
        }
    }

    /**
     * 为空时，抛出校验异常
     *
     * @param collection 校验的集合
     * @param msg 消息提示
     * @author colg
     */
    public static void checkNotNull(Collection<?> collection, String msg) {
        if (CollUtil.isEmpty(collection)) {
            throwFail(msg);
        }
    }

    /**
     * Collection 为非空时，抛出校验异常
     *
     * @param collection 校验的集合
     * @param msg 消息提示
     * @author colg
     */
    public static void checkNull(Collection<?> collection, String msg) {
        if (CollUtil.isNotEmpty(collection)) {
            throwFail(msg);
        }
    }

    /**
     * Map 为空时，抛出校验异常
     *
     * @param map 校验的map
     * @param msg 消息提示
     * @author colg
     */
    public static void checkNotNull(Map<?, ?> map, String msg) {
        if (MapUtil.isEmpty(map)) {
            throwFail(msg);
        }
    }

    /**
     * Map 为非空时，抛出校验异常
     *
     * @param map 校验的map
     * @param msg 消息提示
     * @author colg
     */
    public static void checkNull(Map<?, ?> map, String msg) {
        if (MapUtil.isNotEmpty(map)) {
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
