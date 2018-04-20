package com.hbf.utils;

import java.util.Calendar;
import java.util.Date;


public class T {
    public static Date now() {
        return DateUtils.getCurrentDateTime();
    }


    public static Date today() {
        return DateUtils.getToday();
    }

    public static Date todaybegin(){
        return DateUtils.getDate(T.today());
    }

    public static Date todayend(){
        return DateUtils.getDayEnd(T.today());
    }

    public static Date dateofbegin(Date date){
        return DateUtils.getDate(date);
    }

    public static Date dateofend(Date date){
        return DateUtils.getDayEnd(date);
    }

    public static String snow(String format) {
        return DateUtils.dateToString(DateUtils.getCurrentDateTime(), format);
    }

    public static String toDatetime(Date d) {
        return DateUtils.dateToString(DateUtils.getCurrentDateTime(),
                "yyyy-MM-dd hh:mm:ss");
    }
    public static String toS(Date d) {
        return DateUtils.dateToString(DateUtils.getCurrentDateTime(),
                "yyyyMMddhhmmss");
    }
    public static String snow() {

        return snow("yyyy-MM-dd hh:mm:ss");
    }

//    public static boolean isDebug() {
//        return "true".equals(Props.get("debug"));
//    }

    public static Date Date() {
        return DateUtils.getToday();
    }

    public static int dayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 把日期修改为 day日
     *
     * @param date
     * @param day
     * @return
     */
    public static Date toDayOfMonth(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int year = c.get(Calendar.YEAR);
        int mon = c.get(Calendar.MONTH);

        c.set(year, mon, day);

        return c.getTime();
    }
}
