package com.zks.sas.tool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {
    /**
     * 字符串是否是一个正确的日期格式
     * @param str
     * @return
     */
    public static boolean checkDateStr(String str) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(str);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    /**
     * 把yyyy-MM-dd格式的字符串转换为日期
     * @param str 要转换的字符串
     * @return 日期
     */
    public static Date convertToDate(String str) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 把用户指定格式的字符串转为日期
     * @param str 要转换的字符串
     * @param pattern 指定的格式
     * @return
     */
    public static Date convertToDate(String str, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 把日期转换为yyyy-MM-dd格式的字符串
     * @param date 要转换的日期
     * @return yyyy-MM-dd格式的字符串
     */
    public static String convertToString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
    public static String convertToString(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
}
