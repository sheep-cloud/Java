package cn.colg.core.exception;

/**
 * 校验异常
 *
 * @author colg
 */
public class CheckException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CheckException(String msg) {
        super(msg);
    }

}
