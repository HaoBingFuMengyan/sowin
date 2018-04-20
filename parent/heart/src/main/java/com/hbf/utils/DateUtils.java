package com.hbf.utils;


import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 *
 * @since version1.0
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * 日期格式化类
     *
     * @since version1.0
     */
    public static class FormatConstants {
        public static final DateFormat DATE_FORMAT = new SimpleDateFormat(
                "yyyy-MM-dd", java.util.Locale.CHINA);
        public static final DateFormat TIME_FORMAT = new SimpleDateFormat(
                "HH:mm:ss", java.util.Locale.CHINA);
        public static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", java.util.Locale.CHINA);
    }

    /**
     * 返回当前时间
     *
     * @return 返回当前时间
     */
    public static Date getCurrentDateTime() {
        java.util.Calendar calNow = java.util.Calendar.getInstance();
        java.util.Date dtNow = calNow.getTime();
        return dtNow;
    }

    /**
     * 返回当前时间的数字
     *
     * @return 返回当前时间的数字
     */
    public static String getCurrentTimeNumber() {
        return new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new java.util.Date());
    }

    /**
     * 返回当前时间的数字
     *
     * @return 返回当前时间的数字
     */
    public static String getCurrentTimeNumberRandom() {
        return new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new java.util.Date()) + new Random(100000).nextInt();
    }

    public static int getCurrentUnixTime() throws Exception {
        long epoch = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .parse("01/01/1970 00:00:00").getTime() / 1000;
        return Integer.parseInt(Long.toString(System.currentTimeMillis() / 1000
                - epoch));
    }

    /**
     * @return 返回今天日期，时间部分为0。例如：2006-4-8 00:00:00
     */
    public static Date getToday() {
        return truncate(new Date(), Calendar.DATE);
    }

    /**
     * @return 返回今天日期，时间部分为23:59:59.999。例如：2006-4-19 23:59:59.999
     */
    public static Date getTodayEnd() {
        return getDayEnd(new Date());
    }

    /**
     * 将字符串转换为日期（不包括时间）
     *
     * @param dateString
     *            "yyyy-MM-dd"格式的日期字符串
     * @return 日期
     */
    public static Date convertToDate(String dateString) {
        try {
            return FormatConstants.DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 检查字符串是否为日期格式yyyy-MM-dd
     *
     * @param dateString
     * @return true=是；false=否
     */
    public static boolean checkDateString(String dateString) {
        return (convertToDate(dateString) != null);
    }

    /**
     * 将字符串转换为日期（包括时间）
     *
     * @param dateTimeString "yyyy-MM-dd HH:mm:ss"格式的日期字符串
     * @return 日期时间
     */
    public static Date convertToDateTime(String dateTimeString) {
        try {
            return FormatConstants.DATE_TIME_FORMAT.parse(dateTimeString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将字符串转换为日期（包括时间）
     *
     * @param dateString
     *            "dd/MM/yyyy HH:mm"格式的日期字符串
     * @return 日期
     */
    public static Date convertSimpleToDateTime(String dateString) {
        try {
            return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm")
                    .parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 检查字符串是否为日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @param dateTimeString
     * @return true=是；false=否
     */
    public static boolean checkDateTimeString(String dateTimeString) {
        return (convertToDateTime(dateTimeString) != null);
    }

    /**
     * 获取月底
     *
     * @param year
     *            年 4位年度
     * @param month
     *            月 1~12
     * @return 月底的Date对象。例如：2006-3-31 23:59:59.999
     */
    public static Date getMonthEnd(int year, int month) {
        StringBuffer sb = new StringBuffer(10);
        Date date;
        if (month < 12) {
            sb.append(Integer.toString(year));
            sb.append("-");
            sb.append(month + 1);
            sb.append("-1");
            date = convertToDate(sb.toString());
        } else {
            sb.append(Integer.toString(year + 1));
            sb.append("-1-1");
            date = convertToDate(sb.toString());
        }
        date.setTime(date.getTime() - 1);
        return date;
    }
    /**
     * 计算两个日期之间相差的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetween(Date date1,Date date2)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期之间相差的天数 ，包含开始日期和结束日期
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetweenFull(Date date1,Date date2)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days))+1;
    }

    /**
     * 获取月底
     *
     * @param when
     *            要计算月底的日期
     * @return 月底的Date对象。例如：2006-3-31 23:59:59.999
     */
    public static Date getMonthEnd(Date when) {
        Assert.notNull(when, "date must not be null !");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(when);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return getMonthEnd(year, month);
    }

    /**
     * 获取月初
     *
     * @param year
     *            年 4位年度
     * @param month
     *            月 1~12
     * @return 月底的Date对象。例如：2006-3-31 23:59:59.999
     */
    public static Date getMonthbegin(int year, int month,int day) {
        StringBuffer sb = new StringBuffer(10);
        Date date;
        if (month < 12) {
            sb.append(Integer.toString(year));
            sb.append("-");
            sb.append(month + 1);
            sb.append("-");
            sb.append(1);
            date = convertToDate(sb.toString());
        } else {
            sb.append(Integer.toString(year + 1));
            sb.append("-1-1");
            sb.append("-");
            sb.append(1);
            date = convertToDate(sb.toString());
        }

        return date;
    }
    /**
     * 获取月初
     *
     * @param when
     *            要计算月初的日期
     * @return 月初的Date对象。例如：2006-3-1 00:00:00.000
     */
    public static Date getMonthStart(Date when) {
        Assert.notNull(when, "date must not be null !");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(when);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        //return new Date(year-1900,month,1);
        return	getMonthbegin(year, month,1);
    }
    /**
     * 日期格式转换为字符串
     *
     * @param date
     * @return
     */
    public static String dateTimeParseString(Date date) {
        java.text.DateFormat df = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 日期格式转换为字符串
     *
     * @param date
     * @return
     */
    public static String dateParseString(Date date) {
        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }



    /**
     * 获取给定日的最后一刻。
     *
     * @param when
     *            给定日
     * @return 最后一刻。例如：2006-4-19 23:59:59.999
     */
    public static Date getDayEnd(Date when) {
        Date date = truncate(when, Calendar.DATE);
        date = addDays(date, 1);
        date.setTime(date.getTime() - 1);
        return date;
    }

    /**
     * 获取给定日的第一刻。
     *
     * @param when
     *            给定日
     * @return 第一刻。例如：2006-4-19 00:00:00
     */
    public static Date getDate(Date when) {
        return truncate(when, Calendar.DATE);
    }

    /**
     * 日期加法
     *
     * @param when
     *            被计算的日期
     * @param field
     *            the time field. 在Calendar中定义的常数，例如Calendar.DATE
     * @param amount
     *            加数
     * @return 计算后的日期
     */
    public static Date add(Date when, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(when);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 计算给定的日期加上给定的天数。
     *
     * @param when
     *            给定的日期
     * @param amount
     *            给定的天数
     * @return 计算后的日期
     */
    public static Date addDays(Date when, int amount) {

        return add(when, Calendar.DAY_OF_YEAR, amount);
    }

    /**
     * 计算给定的日期加上给定的分钟数。
     *
     * @param when
     *            给定的日期
     * @param amount
     *            给定的分钟数
     * @return 计算后的日期
     */
    public static Date addMinutes(Date when, int amount) {

        return add(when, Calendar.MINUTE, amount);
    }

    /**
     * 计算给定的日期加上给定的秒数。
     *
     * @param when
     *            给定的日期
     * @param amount
     *            给定的秒数
     * @return 计算后的日期
     */
    public static Date addSeconds(Date when, int amount) {

        return add(when, Calendar.SECOND, amount);
    }

    /**
     * 计算给定的日期加上给定的月数。
     *
     * @param when
     *            给定的日期
     * @param amount
     *            给定的月数
     * @return 计算后的日期
     */
    public static Date addMonths(Date when, int amount) {
        return add(when, Calendar.MONTH, amount);
    }

    /**
     * 获取当前时段：早上、上午、下午、晚上、凌晨
     *
     * @return 当前时段
     */
    @SuppressWarnings("deprecation")
    public static String getTimePeriod() {
        String period = "";
        Date now = getCurrentDateTime();
        int hour = now.getHours();
        if (hour >= 0 && hour < 6) {
            period = "凌晨";
        } else if (hour >= 6 && hour < 8) {
            period = "早上";
        } else if (hour >= 8 && hour < 12) {
            period = "上午";
        } else if (hour >= 12 && hour < 18) {
            period = "下午";
        } else if (hour >= 18) {
            period = "晚上";
        }
        return period;
    }

    /**
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getTimePriTenDay(Date date, int n) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return convertToDateTime(df.format(new Date(date.getTime() + n * 24
                * 60 * 60 * 1000)));
    }

    /**
     * 将Date对象类型转化为日期(2006-09-17 5:20:5)的字符串
     *
     * @param date
     * @return String
     */
    public static String dateToString(Date date) {

        try {
            if (date != null)
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        } catch (Exception e) {
            e.printStackTrace();
            System.gc();
        }
        return null;
    }

    /**
     * 将String 类型的转化为日期格式(2006-09-17 5:20:5)
     *
     * @param dateStr
     * @return Date
     */
    public static Date stringToDate(String dateStr) {

        try {
            if (dateStr != null)
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            System.gc();
        }
        return null;
    }

    /**
     * 字符串转为时间类型
     *
     * @param dateStr
     *            <String>
     * @return Date
     */
    public static final Date parseDate(String dateStr) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static final Date parseDateByFormat(String dateStr, String format) {

        SimpleDateFormat df = new SimpleDateFormat(format);

        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到具体时间如：几天前；几小时前；几分钟前；几秒钟前
     *
     * @param time
     *            传入一个Date类型的日期
     * @return 根据当天当时当秒解析出距离当天当时当秒的字符串 String
     */
    public static String getTimeInterval(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Long dateDiff = sdf.parse(sdf.format(new Date())).getTime()
                    - sdf.parse(sdf.format(time)).getTime();

            Long day = dateDiff / (24 * 60 * 60 * 1000);

            if (day > 0) {
                return day + "天前";
            }

            Long hour = dateDiff / (60 * 60 * 1000);

            if (hour > 0) {
                return hour + "小时前";
            }

            Long minute = dateDiff / (60 * 1000);

            if (minute > 0) {
                return minute + "分钟前";
            }

            Long second = dateDiff / 1000;

            return second + "秒前";
        } catch (Exception e) {
            e.printStackTrace();
            return "未知";
        }
    }

    public static String getAfterTime(Date time) {

        try {

            Long dateDiff = time.getTime()-T.now().getTime();

            Long day = dateDiff / (24 * 60 * 60 * 1000);

            if (day > 0) {
                return day + "天后";
            }

            Long hour = dateDiff / (60 * 60 * 1000);

            if (hour > 0) {
                return hour + "小时后";
            }

            Long minute = dateDiff / (60 * 1000);

            if (minute >=5) {
                return minute + "分钟后";
            }
            else
                return  "5分钟内";
        } catch (Exception e) {
            e.printStackTrace();
            return "未知";
        }
    }

    public static String getLeftTime(Date time) {

        try {

            Long dateDiff = time.getTime()-T.now().getTime();

            Long day = dateDiff / (24 * 60 * 60 * 1000);

            if (day > 0) {
                return day + "天";
            }

            Long hour = dateDiff / (60 * 60 * 1000);

            if (hour > 0) {
                return hour + "小时";
            }

            Long minute = dateDiff / (60 * 1000);

            if (minute >=5) {
                return minute + "分钟";
            }
            else
                return  "5分钟";
        } catch (Exception e) {
            e.printStackTrace();
            return "未知";
        }
    }

    /**
     * 由当前时间返回yyyy-mm-dd格式的日期字符串
     *
     * @return String
     */
    public static String getStringOfTodayDate() {

        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(d);
    }

    /**
     * 将Date对象类型转化为指定的格式字符串
     *
     * @param date
     *            <Date>日期
     * @param format
     *            <String>格式
     * @return String
     */
    public static String dateToString(Date date, String format) {

        try {
            if (date != null)
                return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            e.printStackTrace();
            System.gc();
        }
        return null;
    }

    /**
     * 将Date对象类型转化为指定的格式字符串
     *
     * @param date
     *            <Date>日期
     * @return String
     */
    public static String dateToUTCString(Date date) {

        try {
            Calendar cal = Calendar.getInstance(TimeZone.getDefault());// TimeZone.getDefault()获取主机的默认
            // TimeZone，即时区偏移量。
            cal.setTime(date);
            long temp = date.getTime() + cal.getTimeZone().getRawOffset();

            return temp + "";
        } catch (Exception e) {
            e.printStackTrace();
            System.gc();
        }
        return null;
    }

    /**
     * 系统日期减去传入日期得到天数
     *
     * @param date1
     *            传入日期
     * @return 天数 long
     */
    public static long sub(Date date1) {
        Date d2 = convertToDate(dateParseString(new Date()));
        Date d1 = convertToDate(dateParseString(date1));
        long day = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 日期相减得到年数
     *
     * @param date1
     *            相减的日期
     * @return
     */
    public static long subDateGetYear(Date date1) {
        Date d2 = convertToDate(dateParseString(new Date()));
        Date d1 = convertToDate(dateParseString(date1));
        long day = (d2.getYear() - d1.getYear());
        return day + 1;
    }

    /**
     * 计算两个日期间的月份
     *
     * @param date1
     *            起始日期
     * @param date2
     *            结束日期
     * @return
     */
    public static long subDateGetMonth(Date date1, Date date2) {
        DateCalculate culate = DateCalculate.calculate(date1, date2);
        return culate.getDifferenceOfMonths();
    }

    /**
     * 计算两个日期间的月份,不满一个月按一个月计算
     *
     * @param date1
     *            起始日期
     * @param date2
     *            结束日期
     * @return
     */
    public static long subDateGetFullMonth(Date date1, Date date2) {
        DateCalculate culate = DateCalculate.calculate(date1, date2);
        long month = culate.getDifferenceOfMonths();
        if (culate.getDifferenceOfDays() > 0)
            month++;
        return month;
    }

    /**
     * 将日期转换成中文的日期格式：2012年2月2日 转换成： 二O一二年二月二日
     *
     * @param date
     * @return
     */
    public static String dateToChinese(Date date) {
        String rtnStr = "";
        String curDate = DateUtils.dateToString(date, "yyyy年MM月dd日");
        for (int i = 0; i < curDate.length(); i++) {
            String s = String.valueOf(curDate.charAt(i));
            try {
                rtnStr += StringHelper.numToChinese(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                rtnStr += s;
            }
        }
        return rtnStr;
    }

    public static String getCurrentMMonth() {
        String curDate = DateUtils.dateToString(new Date(), "M-d");
        return curDate.split("-")[0];
    }

    public static String getCurrentDDay() {
        String curDate = DateUtils.dateToString(new Date(), "M-d");
        return curDate.split("-")[1];
    }

    public static String getWeekOfDate() {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
    /**
     * 星期几
     * @param d
     * @return
     */
    public static int getWeekOfDate(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int w = cal.get(Calendar.DAY_OF_WEEK) ;
        return w;
    }

    /**
     * 截取现有日期上加上时间   时间格式为  HH:mm:ss
     * @param date
     * @param time
     * @return
     */
    public static Date getAddTimeDate(Date date,String time){
        if(B.Y(time))
            return date;
        String[] index=time.split(":");
        if(index.length == 1){
            if(time.length()>2)
                return null;
            time=time+":00:00";
        }else if(index.length == 2){
            if(time.length() > 5)
                return null;
            time=time+":00";
        }else if(index.length == 3){
            if(time.length() > 8)
                return null;
        }else
            return null;
        return DateUtils.stringToDate(DateUtils.dateToString(date, "yyyy-MM-dd")+" "+time);
    }

    /**
     * 获取当年的第一天
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取当年
     * @return
     */
    public static int getCurrYear(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return currentYear;
    }

    /**
     * 获取任意年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取任意年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 获取一段时间内所有的月份，返回list
     * @param minDate    开始日期
     * @param maxDate	  结束日期
     * @param dateFormart    要返回的时间的格式，类似yyyy-MM
     * @return
     * @throws ParseException
     */
    public static List<String> getMonthBetween(Date minDate, Date maxDate,String dateFormart) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormart);//格式化为年月
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(minDate);
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
        max.setTime(maxDate);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }


    /**
     * 计算两个日期之间相差的天数，包含头尾，不考虑时分秒
     *
     * @param begin
     * @param end
     * @return
     */
    public static int daysBetweenWithoutSFM(Date begin, Date end) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        Date bd = DateUtils.parseDateByFormat(sf.format(begin), "yyyyMMdd");
        Date ed = DateUtils.parseDateByFormat(sf.format(end), "yyyyMMdd");
        return DateUtils.daysBetweenFull(bd, ed);
    }


    public static void main(String[] args) {
        Date bd = DateUtils.parseDateByFormat("20170613 00:00:00", "yyyyMMdd HH:mm:ss");
        Date ed = DateUtils.parseDateByFormat("20170627 00:00:00", "yyyyMMdd HH:mm:ss");


        System.out.println(daysBetweenWithoutSFM(bd,ed));
    }

    /**
     * 根据当前日期获得所在月的日期区间(月初和月末日期)
     * @return
     */
    public static String[] getMonth(Date date){
        String[] month = new String[2];
        month[0] = new SimpleDateFormat("yyyy-MM-dd").format(getMonthStart(date));
        month[1] = new SimpleDateFormat("yyyy-MM-dd").format(getMonthEnd(date));
        return month;
    }
}
