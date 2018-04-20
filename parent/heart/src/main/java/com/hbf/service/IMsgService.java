package com.hbf.service;

/**
 * Description:
 *
 * @author <a href="mailto:service@frogsing.com">Sandy</a>
 * @since version1.0
 */
public interface IMsgService {

    String getMsg(String src);
    void newMsg(String scode,String src);


}

