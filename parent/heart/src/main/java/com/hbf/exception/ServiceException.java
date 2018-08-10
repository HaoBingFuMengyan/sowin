package com.hbf.exception;

import com.hbf.webUtils.MsgUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Service层公用的Exception.
 *
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 *
 * @frogsing van
 */
public class ServiceException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 3583566093089790852L;

    private Map<String,String> msgmap=null;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(MsgUtils.getMsg(message));
    }
    public ServiceException(Map<String,String> map,String message) {
        super(MsgUtils.getMsg(message));
        msgmap = map;
    }
    //在外面用msgutils.get
    public ServiceException(boolean flag,String message) {
        super(message);
    }
    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public Map<String,String> getMsgmap() {
        return msgmap;
    }

}
