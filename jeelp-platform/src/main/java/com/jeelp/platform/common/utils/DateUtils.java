package com.jeelp.platform.common.utils;

import org.apache.ibatis.exceptions.PersistenceException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Jeelp技术平台
 * @<p>Title: DateUtils.java
 * @<p>DateUtils 日期处理工具类
 * @<p>Copyright: jeelp Copyright (c) 2013</p>
 * @<p>author: sirius lee
 * @<p> 创建时间：2021-12-19
 * @<p>version 1.0
 */
public class DateUtils {

    /**
     * 预定的日期格式
     */
    public static final String[] dateFormat = {
            "yyyy-MM-dd HH:mm:ss",       //0
            "yyyy/MM/dd HH:mm:ss",       //1
            "yyyy年MM月dd日HH时mm分ss秒",//2
            "yyyy-MM-dd",                //3
            "yyyy/MM/dd",                //4
            "yy-MM-dd",                  //5
            "yy/MM/dd",                  //6
            "yyyy年MM月dd日",            //7
            "HH:mm:ss",                  //8
            "yyyyMMddHHmmss",            //9
            "yyyyMMdd",                  //10
            "yyyy.MM.dd",                //11
            "yy.MM.dd",                  //12
            "yyyyMMddHHmmssSSS",         //13
            "yyyy-MM-dd HH:mm:ss:SSS",   //14
            "yyyyMMddHHmm",              //15
            "yyyy-MM-dd HH:mm"			 //16
    };


    /**
     * 线程绑定的日期格式转换器缓存
     */
    private final static ThreadLocal<Map<String, SimpleDateFormat>> dateFormatersCache = new ThreadLocal<Map<String, SimpleDateFormat>>();

    /**
     * 获取当前系统时间
     *
     * @Description 获取当前系统时间
     * @return Calendar 返回Calendar日期对象
     */
    public static Calendar getSystemCurrentTime() {
        return Calendar.getInstance();
    }

    // ======================日期转字符串基础格式化方法=======================

    private static SimpleDateFormat getDateFormater(String format) {
        Map<String, SimpleDateFormat> dateFormaters = dateFormatersCache.get();
        SimpleDateFormat dateFormater = null;
        boolean formatersIsNull = false;

        if (dateFormaters == null) {
            dateFormaters = new HashMap<String, SimpleDateFormat>(3, 0.2f);
            dateFormatersCache.set(dateFormaters);
            formatersIsNull = true;
        }

        if (formatersIsNull || (dateFormater = dateFormaters.get(format)) == null) {
            dateFormater = new SimpleDateFormat(format);
            dateFormaters.put(format, dateFormater);
        }

        return dateFormater;
    }

    private static SimpleDateFormat getDateFormater(int format) {
        return getDateFormater(dateFormat[format]);
    }

    // ======================日期转字符串基础方法=======================================

    /**
     *
     * Calendar日期转指定日期格式的字符串
     *
     * @Description Calendar日期转指定日期格式的字符串<br>
     * 示例：
     * <pre>
     * Calendar calendar = Calendar.getInstance();
     * String result = DateUtils.toDateStrByFormat(calendar,"yyyy-MM-dd HH:mm:ss");
     * </pre>
     * @param date 日期对象
     * @param format 指定的日期格式，可参考日期数组<code>dateFormat</code>
     * @return String 返回日期字符串，如果需要转换的Calendar日期对象为null，那么返回null
     */
    public static String toDateStrByFormat(Calendar date, String format) {
        if (date == null) {
            return null;
        }
        return getDateFormater(format).format(date.getTime());
    }

    /**
     *
     * Calendar日期转指定日期格式的字符串
     *
     * @Description Calendar日期转指定日期格式的字符串<br>
     * 	示例：
     *  <pre>
     * Calendar calendar = Calendar.getInstance();
     * String result = DateUtils.toDateStrByFormatIndex(calendar,1);
     * </pre>
     * @param date 日期对象
     * @param format 指定的日期格式，对应日期数组<code>dateFormat[format]</code>的值
     * @return String 返回日期字符串，如果需要转换的Calendar日期对象为null，那么返回null
     */
    public static String toDateStrByFormatIndex(Calendar date, int format) {
        return toDateStrByFormat(date, dateFormat[format]);
    }

    /**
     * java.util.Date日期转指定日期格式的字符串
     *
     * @Description java.util.Date日期转指定日期格式的字符串<br>
     * @param date 日期对象
     * @param format 指定的日期格式，对应日期数组<code>dateFormat[format]</code>的值
     * @return String 返回日期字符串，如果需要转换的java.util.Date日期对象为null，那么返回null
     */
    public static String toDateStrByFormat(Date date, String format) {
        if (date == null) {
            return null;
        }
        return getDateFormater(format).format(date.getTime());
    }

    /**
     *
     * java.util.Date日期转指定格式的字符串
     *
     * @Description java.util.Date日期转指定格式的字符串<br>
     * @param date 日期对象
     * @param format 指定的日期格式，对应日期数组<code>dateFormat[format]</code>的值
     * @return String 返回日期字符串，如果需要转换的java.util.Date日期对象为null，那么返回null
     */
    public static String toDateStrByFormatIndex(Date date, int format) {
        return toDateStrByFormat(date, dateFormat[format]);
    }

    // ======================日期转字符串方法======================================================================================

    /**
     *
     * Calendar日期转日期字符串
     *
     * @Description Calendar日期转日期字符串，格式为"yyyy-MM-dd HH:mm:ss"
     * @param date 日期对象
     * @return String 返回日期字符串，如果需要转换的Calendar日期对象为null，那么返回null
     */
    public static String toDateTimeStr(Calendar date) {
        return toDateStrByFormatIndex(date, 0);
    }

    /**
     *
     * Calendar日期转指定日期格式的字符串
     *
     * @Description Calendar日期转指定日期格式的字符串
     * @param format 指定的日期格式，对应日期数组<code>dateFormat[format]</code>的值
     * @param date 日期对象
     * @return String 返回日期字符串，如果需要转换的Calendar日期对象为null，那么返回null
     */
    public static String toDateTimeStr(int format, Calendar date) {
        return toDateStrByFormatIndex(date, format);
    }
    /**
     *
     * Calendar日期转日期字符串
     *
     * @Description Calendar日期转日期字符串，格式为"yyyy-MM-dd"
     * @param date 日期对象
     * @return String 返回日期字符串，如果需要转换的Calendar日期对象为null，那么返回null
     */
    public static String toDateStr(Calendar date) {
        return toDateStrByFormatIndex(date, 3);
    }

    /**
     *
     * java.util.Date日期转指定格式的日期字符串
     *
     * @Description java.util.Date日期转换成指定格式的日期字符串
     * @param date 日期对象
     * @param format 指定的日期格式，对应日期数组<code>dateFormat[format]</code>的值
     * @return String 返回日期字符串，如果需要转换的java.util.Date日期对象为null，那么返回null
     */
    public static String dateToString(Date date, int format) {
        return toDateStrByFormatIndex(date, format);
    }

    /**
     *
     * java.util.Date日期转日期字符串
     *
     * @Description java.util.Date日期转日期字符串，格式为"yyyy-MM-dd"
     * @param date 日期对象
     * @return String 返回日期字符串，如果需要转换的java.util.Date日期对象为null，那么返回null
     */
    public static String dateToString(Date date) {
        return toDateStrByFormatIndex(date, 3);
    }

    // ======================xx转Date方法======================================================================================

    /**
     *
     *  Calendar日期转java.util.Date日期
     *
     * @Description Calendar日期转java.util.Date日期
     * @param c 需要转换的Calendar日期
     * @return Date 返回java.util.Date日期
     */
    public static Date convertCalendarToDate(Calendar c) {
        Date d = new Date();
        d.setTime(c.getTimeInMillis());
        return d;
    }

    /**
     *
     * 日期字符串转java.util.Date日期
     *
     * @Description 日期字符串转java.util.Date日期，格式为"yyyy-MM-dd"
     * @param dateStr 日期字符串
     * @return Date 返回java.util.Date日期，如果需要转换的日期字符串为null或者长度等于0，那么返回null
     * @throws PersistenceException
     */
    public static Date StringToDate(String dateStr) throws PersistenceException {
        return parseDate(dateStr, 3);
    }

    /**
     *
     * 日期字符串转指定格式的java.util.Date日期
     *
     * @Description 日期字符串转指定格式的java.util.Date日期
     * @param dateStr 需要转换的日期字符串
     * @param format 指定的日期格式，对应日期数组<code>dateFormat[format]</code>的值
     * @return Date 返回java.util.Date日期，如果需要转换的日期字符串为null或者长度等于0，那么返回null
     * @throws PersistenceException
     */
    public static Date parseDate(String dateStr, int format) throws PersistenceException {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }

        try {
            return getDateFormater(format).parse(dateStr);
        } catch (ParseException ex) {
            return parseDate(dateStr, format + 1);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new PersistenceException("UT-07001:日志字符串" + dateStr + "格式不正确，格式:" + dateFormat[format], ex);
        }
    }

    // ======================xx转Calendar方法======================================================================================

    /**
     *
     * java.util.Date转Calendar
     *
     * @Description java.util.Date转Calendar<br>
     *示例：
     * <pre>
     * Date date = new Date();
     * Calendar result = DateUtils.convUtilDateToUtilCalendar(date);
     * </pre>
     * @param date java.util.Date对象
     * @return Calendar 返回Calendar日期对象，如果需要转换的Date日期对象为null，那么返回null
     */
    public static Calendar convUtilDateToUtilCalendar(Date date) {
        if (date == null) {
            return null;
        }

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(date.getTime());

        return gc;
    }

    /**
     *
     * java.sql.Timestamp转Calendar
     *
     * @Description java.sql.Timestamp转Calendar，返回的结果为Calendar对象<br>
     * 示例：
     * <pre>
     * Timestamp timestamp = new Timestamp(new Date().getTime());
     * Calendar result = DateUtils.convSqlTimestampToUtilCalendar(timestamp);
     * </pre>
     * @param date java.util.Date对象
     * @return Calendar 返回Calendar日期对象，如果需要转换的Timestamp日期对象为null，那么返回null
     */
    public static Calendar convSqlTimestampToUtilCalendar(Timestamp date) {
        if (date == null) {
            return null;
        }
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(date.getTime());
        return gc;
    }

    /**
     *
     * 日期字符串转Calendar
     *
     * @Description 日期字符串转Calendar，返回的结果为Calendar对象<br>
     * 示例：
     * <pre>
     * Calendar result = DateUtils.parseDate("2012-12-12");
     * </pre>
     * @param dateStr 日期字符串
     * @return Calendar 返回Calendar日期对象
     * @throws PersistenceException
     */
    public static Calendar parseDate(String dateStr) throws PersistenceException {
        Date result = parseDate(dateStr, 0);
        Calendar cal = null;

        if (result != null) {
            cal = new GregorianCalendar(0, 0, 0, 0, 0, 0);
            cal.setTime(result);
        }

        return cal;
    }

    // ======================日期转Timestamp方法======================================================================================

    /**
     * java.util.Date转java.sql.Timestamp
     *
     * @Description java.util.Date转java.sql.Timestamp
     * @param date 需要转换的java.util.Date对象
     * @return Timestamp 返回Timestamp日期对象，如果需要转换的java.util.Date日期对象为null，那么返回null
     */
    public static Timestamp convUtilDateToSqlTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    /**
     *
     * Calendar日期对象转Timestamp日期对象
     *
     * @Description Calendar日期对象转Timestamp日期对象
     * @param calendar 需要转换的Calendar日期对象
     * @return Timestamp 返回Timestamp日期对象，如果需要转换的Calendar日期对象为null，那么返回null
     */
    public static Timestamp convUtilCalendarToSqlTimestamp(Calendar date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTimeInMillis());
    }

    /**
     *
     * Calendar日期对象转Timestamp日期对象
     *
     * @Description Calendar日期对象转Timestamp日期对象
     * @param calendar 需要转换的Calendar日期对象
     * @return Timestamp 返回Timestamp日期对象
     * @Time    创建时间:2011-12-29上午11:07:31
     * @history 修订历史（历次修订内容、修订人、修订时间等）
     */
    public static Timestamp parseTimestamp(Calendar calendar) {
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     *
     * 日期字符串转java.sql.Timestamp
     *
     * @Description 日期字符串转java.sql.Timestamp，格式为"yyyy-MM-dd"
     * @param dateStr 日期字符串
     * @return Timestamp 返回Timestamp日期对象
     * @throws PersistenceException
     * @Time    创建时间:2011-12-29上午11:07:35
     * @history 修订历史（历次修订内容、修订人、修订时间等）
     */
    public static Timestamp parseTimestamp(String dateStr) throws PersistenceException {
        try {
            return new Timestamp(getDateFormater(3).parse(dateStr).getTime());
        } catch (ParseException ex) {
            throw new PersistenceException("日志字符串" + dateStr + "格式不正确，格式:" + dateFormat[3], ex);
        }
    }

    /**
     *
     * 根据指定日期格式，日期字符串转java.sql.Timestamp
     *
     * @Description 根据指定日期格式，日期字符串转java.sql.Timestamp
     * @param dateStr 需要转换的日期字符串
     * @param format 指定的日期格式，对应日期数组<code>dateFormat[format]</code>的值
     * @return Timestamp 返回Timestamp日期对象
     * @throws PersistenceException
     */
    public static Timestamp parseTimestamp(String dateStr, int format) throws Exception {
        try {
            return new Timestamp(getDateFormater(format).parse(dateStr).getTime());
        } catch (Exception ex) {
            throw new Exception("日志字符串" + dateStr + "格式不正确，格式:" + dateFormat[format], ex);
        }
    }

    // ======================日期计算方法======================================================================================

    /**
     *
     * 获取两个Calendar日期对象的天数差
     *
     * @Description 计算两个Calendar日期对象的天数差,返回int型天数差
     * @param d1 需要计算的日期
     * @param d2 需要计算的日期
     * @return int 返回两个Calendar日期对象的天数差
     */
    public static int calendarMinus(Calendar d1, Calendar d2) {
        if (d1 == null || d2 == null) {
            return 0;
        }

        d1.set(11, 0);
        d1.set(12, 0);
        d1.set(13, 0);

        d2.set(11, 0);
        d2.set(12, 0);
        d2.set(13, 0);

        long t1 = d1.getTimeInMillis();
        long t2 = d2.getTimeInMillis();
        //logger.debug("DateUtils: d1 = " + toDateTimeStr(d1) + "(" + t1 + ")");
        //logger.debug("DateUtils: d2 = " + toDateTimeStr(d2) + "(" + t2 + ")");
        long daylong = 86400000L;
        t1 -= t1 % daylong;
        t2 -= t2 % daylong;

        long t = t1 - t2;
        int value = (int) (t / daylong);

        return value;
    }

    /**
     *
     * 获取两个Calendar日期对象的天数差
     *
     * @Description 计算两个Calendar日期对象的天数差,返回long型天数差
     * @param d1  需要计算的日期
     * @param d2  需要计算的日期
     * @return long 返回两个Calendar日期对象的天数差
     */
    public static long calendarminus(Calendar d1, Calendar d2) {
        if (d1 == null || d2 == null) {
            return 0L;
        }
        return (d1.getTimeInMillis() - d2.getTimeInMillis()) / 86400000L;
    }

    /**
     *
     * 给定任意日期Calendar对象，计算所在月天数
     *
     * @Description 给定任意日期Calendar对象，计算所在月天数
     * @param date 需要计算的日期
     * @return int 返回所在月的天数
     */
    public static int calcMonthDays(Calendar date) {
        Calendar t1 = (Calendar) date.clone();
        Calendar t2 = (Calendar) date.clone();
        int year = date.get(1);
        int month = date.get(2);
        t1.set(year, month, 1);
        t2.set(year, month + 1, 1);
        t2.add(6, -1);
        return calendarMinus(t2, t1) + 1;
    }

    private static int calcDays(long t1, long t2) {
        long millis = t1 - t2;
        if (millis == 0) {
            return 0;
        }
        return (int) (millis / (24 * 3600 * 1000));
    }

    /**
     *
     * 获取两个Calendar日期对象的天数差
     *
     * @Description 计算两个Calendar日期对象的天数差,返回int型天数差
     * @param c1  需要计算的日期
     * @param c2  需要计算的日期
     * @return int 返回两个Calendar日期对象的天数差
     */
    public static int calcDays(Calendar c1, Calendar c2) {
        return calcDays(c1.getTimeInMillis(), c2.getTimeInMillis());
    }

    /**
     *
     * 获取两个java.util.Date日期对象的天数差
     *
     * @Description 计算两个java.util.Date日期对象的天数差，返回int型天数差
     * @param d1  需要计算的日期
     * @param d2  需要计算的日期
     * @return int 返回两个日期对象的天数差
     */
    public static int calcDays(Date d1, Date d2) {
        return calcDays(d1.getTime(), d2.getTime());
    }

    /**
     *
     * 给定任意日期Calendar对象，计算所在月的最后一天
     *
     * @Description 给定任意日期Calendar对象，计算所在月的最后一天，返回的计算结果为一个Calendar对象<br>
     * 示例：
     * <pre>
     * Calendar calendar = Calendar.getInstance();
     * Calendar result = DateUtils.lastDay(calendar);
     * </pre>
     * @param c Calendar日期对象
     * @return Calendar 返回Calendar日期对象
     */
    public static Calendar lastDay(Calendar c) {
        Calendar t = (Calendar) c.clone();
        t.set(Calendar.DAY_OF_MONTH, 1);
        t.add(Calendar.MONTH, 1);
        t.add(Calendar.DAY_OF_MONTH, -1);
        return t;
    }

    /**
     * 给定任意日期字符串，计算所在月的最后一天
     *
     * @Description 给定任意日期字符串，计算所在月的最后一天，返回的计算结果为一个Calendar对象<br>
     * 示例：
     * <pre>
     * Calendar result = DateUtils.lastDay("2012-12-12");
     * </pre>
     * @param dateStr 日期字符串
     * @return Calendar 返回Calendar日期对象
     * @throws PersistenceException
     */
    public static Calendar lastDay(String dateStr) throws PersistenceException {
        Calendar t = parseDate(dateStr);
        t.set(Calendar.DAY_OF_MONTH, 1);
        t.add(Calendar.MONTH, 1);
        t.add(Calendar.DAY_OF_MONTH, -1);
        return t;

    }

    /**
     * 给定任意日期，计算所在季的季起日期和季终日期
     *
     * @Description 给定任意日期，计算所在季的季起日期和季终日期，返回的计算结果为一个Calendar日期数组，Calendar[0]为季起日期，Calendar[1]为季终日期<br>
     * 示例：
     * <pre>
     * Calendar calendar = Calendar.getInstance();
     * DateUtils.calcAQuarter(calendar)[0].getTime();//季起日期
     * DateUtils.calcAQuarter(calendar)[1].getTime();//季终日期
     * </pre>
     * @param day 需要计算的Calendar日期对象
     * @return Calendar[] 返回为Calendar日期数组，Calendar[0]为季起日期，Calendar[1]为季终日期
     */
    public static Calendar[] calcAQuarter(Calendar day) {
        Calendar[] quarter = new Calendar[2];

        int month = 0;
        quarter[0] = (Calendar) day.clone();
        month = quarter[0].get(Calendar.MONTH);

        if (month < 3) {
            month = 0;
        } else if (month < 6) {
            month = 3;
        } else if (month < 9) {
            month = 6;
        } else {
            month = 9;
        }

        quarter[0].set(Calendar.MONTH, month);
        quarter[0].set(Calendar.DAY_OF_MONTH, 1);

        quarter[1] = (Calendar) quarter[0].clone();
        quarter[1].set(Calendar.MONTH, month + 2);
        quarter[1] = lastDay(quarter[1]);

        return quarter;
    }


}
