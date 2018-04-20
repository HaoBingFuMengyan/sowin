package com.hbf.exception;

import com.hbf.webUtils.MsgUtils;

/**
 * Description:
 *
 * @author <a href="mailto:service@frogsing.com">Sandy</a>
 * @since version1.0
 */
public class ParameterException extends RuntimeException {

    private static final long serialVersionUID = 3583566093089790852L;

    public ParameterException() {
        super();
    }

    public ParameterException(String message) {
        super(MsgUtils.getMsg(message));
    }

    public ParameterException(Throwable cause) {
        super(cause);
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }

}