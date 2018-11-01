package cn.colg.util;

import java.util.Collection;
import java.util.List;

import cn.colg.vo.ResultVo;

/**
 * http 请求工具类
 *
 * @author colg
 */
public class ResultVoUtil {

    /**
     * 成功时默认响应
     *
     * @return {code: 0, msg: "success"}
     * @author colg
     */
    public static ResultVo success() {
        return new ResultVo(ResultVo.SUCCESS, ResultVo.MSG_SUCCESS);
    }

    /**
     * 成功时响应消息
     *
     * @param msg 消息
     * @return {code: 0, msg: "msg"}
     * @author colg
     */
    public static ResultVo success(String msg) {
        return new ResultVo(ResultVo.SUCCESS, msg);
    }

    /**
     * 成功时默认响应
     *
     * @param data 数据
     * @return {code: 0, msg: "success", data: data}
     * @author colg
     */
    public static ResultVo success(Object data) {
        ResultVo resultVo = success();
        convertData(data, resultVo);
        return resultVo;
    }

    /**
     * 失败响应
     *
     * @param code 返回码
     * @param msg 提示信息
     * @return {code: code, msg: msg}
     * @author colg
     */
    public static ResultVo fail(Integer code, String msg) {
        return new ResultVo(code, msg);
    }

    /**
     * 转换分页信息
     *
     * @param data 数据
     * @param resultVo 响应对象
     * @author colg
     */
    private static void convertData(Object data, ResultVo resultVo) {
        if (data instanceof Collection<?>) {
            List<?> list = (List<?>)data;
            resultVo.setData(list)
                    .setTotal((long)list.size());
        } else {
            resultVo.setData(data);
        }
    }
}
