package org.omgcms.core.exception;

/**
 * @Author: Madfrog Yang
 * @Date: 2018/4/30 21:15
 * @Description:
 */
public class CustomSystemException extends RuntimeException {

    private String errorCode;

    private Object[] params;

    public CustomSystemException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public CustomSystemException(String errorCode, Object... params) {
        super();
        this.errorCode = errorCode;
        this.params = params;
    }

    public CustomSystemException(Throwable e, String errorCode) {
        super(e);
        this.errorCode = errorCode;
    }

    public CustomSystemException(Throwable e, String errorCode, Object... params) {
        super(e);
        this.errorCode = errorCode;
        this.params = params;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getParams() {
        return params;
    }
}
