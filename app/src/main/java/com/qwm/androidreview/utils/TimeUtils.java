package com.qwm.androidreview.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <b>Project:</b> YuantaiApplication<br>
 * <b>Create Date:</b> 2016/10/19<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 *     时间工具类
 *
 *     一些字符时间的表标识说明
 *        G 年代标志符
 *        y 年
 *        M 月
 *        d 日
 *        h 时 在上午或下午 (1~12)
 *        H 时 在一天中 (0~23)
 *        m 分
 *        s 秒
 *        S 毫秒
 *        E 星期
 *        D 一年中的第几天
 *        F 一月中第几个星期几
 *        w 一年中第几个星期
 *        W 一月中第几个星期
 *        a 上午 / 下午 标记符
 *        k 时 在一天中 (1~24)
 *        K 时 在上午或下午 (0~11)
 *        z 时区
 */
public class TimeUtils {
    public static final String DEFAULT_SERVER_TIMETYPE = "MMM d, yyyy HH:mm:ss aa";


    /**
     * string类型转换为long类型
     * @param strTime 要转换的String类型的时间
     * @param formatType strTime的时间格式和formatType的时间格式必须相同
     * @return
     */
    public static long stringToLong(String strTime, String formatType){
        return stringToLong(strTime,formatType,Locale.CHINESE);
    }

    public static long stringToLong(String strTime, String formatType,Locale locale){
        Date date = stringToDate(strTime, formatType,locale); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    public static long stringToLongDefaultServceTime(String strTime){
        return stringToLong(strTime,DEFAULT_SERVER_TIMETYPE,Locale.ENGLISH);
    }

    //
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同

    /**
     * string类型转换为date类型
     * @param strTime strTime要转换的string类型的时间，
     * @param formatType formatType要转换的格式
     * @return
     */
    public static Date stringToDate(String strTime, String formatType) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        try{
            date = formatter.parse(strTime);
        }catch (Exception e){}
        return date;
    }

    public static Date stringToDate(String strTime, String formatType,Locale locale) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType,locale);
        Date date = null;
        try{
            date = formatter.parse(strTime);
        }catch (Exception e){}
        return date;
    }

    /**
     * date类型转换为long类型
     * @param date
     * @return date要转换的date类型的时间
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 字符串转为long ，默认的方式
     * 如：2016-10-18 16:00:00.0
     * @return
     */
    public static long stringToLongDefault(String timestr){
        String formatType = "yyyy-MM-dd HH:mm:ss.S";
        return stringToLong(timestr,formatType);
    }

    /**
     *  date类型转换为String类型
     * @param data Date类型的时间
     * @param formatType formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * @return
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }
    public static String dateToString(Date data, String formatType,Locale locale) {
        return new SimpleDateFormat(formatType,locale).format(data);
    }

    /**
     * long类型转换为String类型
     * @param currentTime 要转换的long类型的时间
     * @param formatType 要转换的string类型的时间格式
     * @return
     */
    public static String longToString(long currentTime, String formatType){
        return longToString(currentTime,formatType,Locale.CHINESE);
    }
    public static String longToString(long currentTime, String formatType,Locale locale){
        Date date = longToDate(currentTime, formatType,locale); // long类型转成Date类型
        String strTime = dateToString(date, formatType,locale); // date类型转成String
        return strTime;
    }

    /**
     * long转换为Date类型
     * @param currentTime currentTime要转换的long类型的时间
     * @param formatType formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * @return
     */
    public static Date longToDate(long currentTime, String formatType){
        return longToDate(currentTime,formatType,Locale.CHINESE);
    }

    public static Date longToDate(long currentTime, String formatType,Locale locale){
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType,locale); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType,locale); // 把String类型转换为Date类型
        return date;
    }

    private final static long SECOND_MS = 1000;//一秒鐘毫秒數
    private final static long MINUTE_MS = 60*SECOND_MS;//一分鐘毫秒數
    private final static long HOUR_MS = 60*MINUTE_MS;//一小時毫秒數
    private final static long DAY_MS = 24*HOUR_MS;//一天毫秒數
    private final static long WEEK_MS = 7*DAY_MS;//一周毫秒數
    private final static long MONTH_MS = 30*DAY_MS;//一月毫秒數
    private final static long YEAR_MS = 365*DAY_MS;//一年毫秒數

    public static String getStringTimeByLongTime(long time){
        long spanceTime = System.currentTimeMillis()-time;
        String timeStr = "刚刚";
        if(spanceTime>YEAR_MS){
            timeStr = (spanceTime/YEAR_MS)+"年前";
        }else  if(spanceTime>MONTH_MS){
            timeStr = (spanceTime/MONTH_MS)+"月前";
        }else  if(spanceTime>WEEK_MS){
            timeStr = (spanceTime/WEEK_MS)+"周前";
        }else  if(spanceTime>DAY_MS){
            timeStr = (spanceTime/DAY_MS)+"天前";
        }else if(spanceTime>HOUR_MS){
            timeStr = (spanceTime/HOUR_MS)+"小时前";
        }else if(spanceTime>MINUTE_MS){
            timeStr = (spanceTime/MINUTE_MS)+"分钟前";
        }else {
            timeStr = "刚刚";
        }
        return timeStr;
    }

    /**
     * 装换为汉字的时长 单位 毫秒
     * @param spanceTime
     * @return
     */
    public static String getRaceStringTimeByLongTime(long spanceTime){
        String timeStr = "";
        if(spanceTime>DAY_MS){
            timeStr = (spanceTime/DAY_MS)+"天";
        }else if(spanceTime>HOUR_MS){
            timeStr = (spanceTime/HOUR_MS)+"小时";
            long minute = spanceTime%HOUR_MS/MINUTE_MS;
            timeStr = minute>0?timeStr+minute+"分钟":timeStr;
        }else if(spanceTime>MINUTE_MS){
            timeStr = (spanceTime/MINUTE_MS)+"分钟";
            long second = spanceTime%MINUTE_MS/SECOND_MS;
            timeStr = second>0?timeStr+second+"秒":timeStr;
        }else{
            timeStr = (spanceTime/SECOND_MS)+"秒";
        }
        return timeStr;
    }
}
