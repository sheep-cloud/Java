package cn.colg.util;

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
     * 成功时默认响应
     *
     * @param data 数据
     * @return {code: 0, msg: "success", data: data}
     * @author colg
     */
    public static ResultVo success(Object data) {
        return success().setData(data);
    }

    /**
     * 失败响应
     *
     * @param code 返回码
     * @param msg  提示信息
     * @return {code: code, msg: msg}
     * @author colg
     */
    public static ResultVo fail(Integer code, String msg) {
        return new ResultVo(code, msg);
    }
}
