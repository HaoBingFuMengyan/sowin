package com.hbf.utils;

import java.util.List;

/**
 * Description:
 *
 * @author <a href="mailto:service@frogsing.com">Sandy</a>
 * @since version1.0
 */
public class B {

    public static boolean Y(String obj) {
        return org.apache.commons.lang3.StringUtils.isBlank(obj);
    }

    public static boolean N(String obj) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(obj);
    }

    /**
     * 判断数字是否<=0
     * @param obj
     * @return
     */
    public static boolean NullorLessThanZero(Number obj) {
        if(obj == null){
            return true;
        }
        if(F.compareMoney(obj.doubleValue(), 0) <= 0){
            return true;
        }
        return false;
    }

    public static boolean Y(String[] list) {
        if (null == list || list.length == 0) {
            return true;
        }
        return false;
    }

    public static boolean N(String[] list) {
        if (null == list || list.length == 0) {
            return false;
        }
        return true;
    }

    public static boolean Y(List list) {
        if (null == list || list.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean N(List list) {
        if (null == list || list.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 判断数组是否包含字符串
     *
     * @param array
     * @param value
     * @return
     */
    public static boolean contains(String[] array, String value) {
        if (array == null || array.length == 0) {
            return false;
        }
        for (String str : array) {
            if (str.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 比较两个字符串是否相等,其中一个为null暂时认为不相等
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(String a, String b) {
        if (B.Y(a) || B.Y(b)) {
            return false;
        }
        return a.equals(b);
    }
}
