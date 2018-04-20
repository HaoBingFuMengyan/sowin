package com.hbf.webUtils;

import com.hbf.exception.E;
import com.hbf.service.IMsgService;
import com.hbf.spring.SpringContextHolder;
import com.hbf.utils.MD5;


public class MsgUtils {

    private static IMsgService s;
    private static Object o=new Object();

    /**
     * 兼容性函數,不可再調用
     * @param msg
     * @param para
     * @return
     */
    public static String pastMsg(String msg, String... para) {

        return msg;
    }
    /**
     * 根据msg的md5值去数据库找对应的值
     * @param msg
     * @param para
     * @return
     */
    public static String getMsg(String msg, String... para) {
        if(!msg.matches("[a-zA-Z\\-_]+"))
            return msg;
        if(s==null){
            synchronized (o) {
                if(s==null)
                    s= SpringContextHolder.getBean(IMsgService.class);
                if(s==null)
                    E.S("IMsgService的实现类找不到!");
            }

        }
        String scode;
        if(msg.length()<25)
            scode=msg;
        else
            scode= MD5.encode(msg);

        String ss=s.getMsg(scode);
        if(ss==null)
        {
            s.newMsg(scode, msg);

            return msg;
        }

        if(ss.trim().length()==0){

            return msg;
        }
        else
            return ss;


    }
}
