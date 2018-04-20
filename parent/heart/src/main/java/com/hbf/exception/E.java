package com.hbf.exception;

import java.util.Map;

public class E {

    public static void FS(String obj,Object ...p) {
        throw new ServiceException(String.format(obj, p));
    }

    public static void S(String obj) {
        throw new ServiceException(obj==null?"未知错误":obj);
    }

    public static void MapS(Map<String,String> obj, String msg) {
        throw new ServiceException(obj,msg);
    }

    public static void PS(String obj) {
        throw new ParameterException(obj);
    }

    public static void DS(String obj) {
        throw new DaoException(obj);
    }



    /**
     * 获取exception详情信息
     *
     * @param e
     *            Excetipn type
     * @return String type
     */
    public static String getDetail(Exception e) {
        StringBuffer msg = new StringBuffer("null");
        if (e != null) {
            msg = new StringBuffer("");
            String message = e.toString();
            int length = e.getStackTrace().length;
            if (length > 0) {
                msg.append(message + "\n");
                for (int i = 0; i < length; i++) {
                    msg.append("\t" + e.getStackTrace()[i] + "\n");
                }
            } else {
                msg.append(message);
            }
        }
        return msg.toString();
    }
}
