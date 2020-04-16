package com.rainbow.common.util;

import com.rainbow.common.model.HMS;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * 日期转换工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String FORMAT_FULL_STANDARD = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String FORMAT_FULL_STANDARD_0 = "yyyy-MM-dd HH:00:00";
    /**
     * yyyyMMddHHmmss
     */
    public static final String FORMAT_FULL_ONLYNUM = "yyyyMMddHHmmss";
    /**
     * yyyyMMddHHmm
     */
    public static final String FORMAT_MINUTE_ONLYNUM = "yyyyMMddHHmm";
    /**
     * yyyy-MM-dd
     */
    public static final String FORMAT_DATE_STANDARD = "yyyy-MM-dd";

    /**
     * yyyyMMdd
     */
    public static final String FORMAT_DATA_ONLYNUM = "yyyyMMdd";
    /**
     * HH:mm:ss
     */
    public static final String FORMAT_TIME_STANDARD = "HH:mm:ss";
    /**
     * HHmmss
     */
    public static final String FORMAT_TIME_ONLYNUM = "HHmmss";

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
            "yyyy.MM.dd HH:mm", "yyyy.MM", "HH:mm"
    };

    /**
     * 格式化日期
     *
     * @param ldt
     * @param format
     * @return
     */
    public static String dataFormat(final LocalDateTime ldt, final String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return ldt.format(formatter);
    }

    // 格式"2012-12-06"转为date
    public static Date stringToDate(String dateString) {
        Date date = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_STANDARD);
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }

    /*
     * 将时间转换为时间戳
     */
    public static long dateToStamp(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }

    /**
     * 获取当前完整日期时间yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrFullDataStandard() {
        return dataFormat(LocalDateTime.now(), FORMAT_FULL_STANDARD);
    }

    /**
     * 获取当前完整日期时间yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrFullDataStandardZero() {
        return dataFormat(LocalDateTime.now(), FORMAT_FULL_STANDARD_0);
    }

    /**
     * 获取当前数字日期时间yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrFullDateOnlyNum() {
        return dataFormat(LocalDateTime.now(), FORMAT_FULL_ONLYNUM);
    }

    /**
     * 获取当前完整日期yyyy-MM-dd
     *
     * @return
     */
    public static String getCurrDataStandard() {
        return dataFormat(LocalDateTime.now(), FORMAT_DATE_STANDARD);
    }

    /**
     * 获取当前数字日期yyyyMMdd
     *
     * @return
     */
    public static String getCurrDateOnlyNum() {
        return dataFormat(LocalDateTime.now(), FORMAT_DATA_ONLYNUM);
    }

    /**
     * 获取当前数字日期yyyyMMddHHmm
     *
     * @return
     */
    public static String getCurrMinuteDateOnlyNum() {
        return dataFormat(LocalDateTime.now(), FORMAT_MINUTE_ONLYNUM);
    }

    /**
     * 获取当前完整时间HH:mm:ss
     *
     * @return
     */
    public static String getCurrTimeStandard() {
        return dataFormat(LocalDateTime.now(), FORMAT_TIME_STANDARD);
    }

    /**
     * 获取当前数字时间HHmmss
     *
     * @return
     */
    public static String getCurrTimeOnlyNum() {
        return dataFormat(LocalDateTime.now(), FORMAT_TIME_ONLYNUM);
    }

    /**
     * yyyy-MM-dd格式string转换LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate getLocalDateByString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_STANDARD);
        return LocalDate.parse(date, formatter);
    }

    /**
     * string转换LocalDate
     *
     * @return
     */
    public static LocalDate getLocalDateByStringAndPattern(String datetime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(datetime, formatter);
    }

    /**
     * yyyy-MM-dd HH:mm:ss格式string转换LocalDateTime
     *
     * @param datetime
     * @return
     */
    public static LocalDateTime getLocalDateTimeByString(String datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_FULL_STANDARD);
        return LocalDateTime.parse(datetime, formatter);
    }

    public static LocalDateTime getLocalFullDateTimeByString(String datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_FULL_ONLYNUM);
        return LocalDateTime.parse(datetime, formatter);
    }

    public static LocalDateTime getLocalFullDateTimeByPattern(String datetime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(datetime, formatter);
    }

    /**
     * 获取当前毫秒数
     *
     * @return
     */
    public static Long getCurrMilli() {
        Instant instant = Instant.now();
        return instant.toEpochMilli();
    }

    public static Long getCurrDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = formatter.parse(getCurrDataStandard());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static Long getLongByString(String datetime) {
        return Timestamp.valueOf(DateUtils.getLocalDateTimeByString(datetime)).getTime();
    }

    public static Long getLongByDateString(String date) {
        date = date + " 00:00:00";
        return Timestamp.valueOf(DateUtils.getLocalDateTimeByString(date)).getTime();
    }

    public static Long getLongByLdt(LocalDateTime ldt) {
        return Timestamp.valueOf(ldt).getTime();
    }

    public static Long getStartOfDay(LocalDateTime now) {
        LocalDate ld = now.toLocalDate();
        LocalDateTime ldt = ld.atStartOfDay();
        return getLongByLdt(ldt);
    }

    public static Long getStartOfDay(LocalDate localDate) {
        ZonedDateTime zdt = localDate.atStartOfDay(ZoneId.systemDefault());
        Date date = Date.from(zdt.toInstant());
        return date.getTime();
    }


    public static Long getEndOfDay(LocalDate localDate) {
        return DateUtils.getLongByLdt(LocalDateTime.of(localDate, LocalTime.MAX));
    }

    public static Long getEndOfDay(LocalDateTime now) {
        LocalDate ld = now.toLocalDate();
        ld = ld.plusDays(1l);
        LocalDateTime ldt = ld.atStartOfDay();
        return getLongByLdt(ldt);
    }

    public static Long getEndOfDay(Long now) {
        Date nowDate = new Date(now);
        Calendar nowC = Calendar.getInstance();
        nowC.setTime(nowDate);
        nowC.add(Calendar.DAY_OF_MONTH, 1);
        nowC.set(Calendar.HOUR_OF_DAY, 0);
        nowC.set(Calendar.MINUTE, 0);
        nowC.set(Calendar.SECOND, 0);
        nowC.set(Calendar.MILLISECOND, 0);
        return nowC.getTime().getTime();
    }

    public static Long getNMonthDate(Long date, int n) {
        Date d = new Date(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.MONTH, n);
        return calendar.getTime().getTime();
    }

    public static String formatByDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String format(long time, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(time);
    }

    /**
     * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
     * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 相差天数
     *
     * @param date
     * @param otherDate
     * @return
     */
    public static int differentDaysByMillisecond(Long date, Long otherDate) {
        long time = date - otherDate;
        int num = (int) (time / (24 * 60 * 60 * 1000));
        return num;
    }

    /**
     * 相隔天数
     *
     * @param date
     * @param otherDate
     * @return
     */
    public static long differentDaysBySeparated(Long date, Long otherDate) {

        String dateStr = DateUtils.format(date, DateUtils.FORMAT_DATE_STANDARD);
        String otherDateStr = DateUtils.format(otherDate, DateUtils.FORMAT_DATE_STANDARD);

        long beforeTime = parseDate(dateStr).getTime();
        long afterTime = parseDate(otherDateStr).getTime();

        return (beforeTime - afterTime) / (1000 * 60 * 60 * 24);

    }


    /**
     * 指定日期加上天数后的日期
     *
     * @param num     为增加的天数
     * @param newDate 创建时间
     * @return
     * @throws ParseException
     */
    public static String plusDay(final int num, String newDate, final String format) throws ParseException {
        SimpleDateFormat formats = new SimpleDateFormat(format);
        Date currdate = formats.parse(newDate);
        System.out.println("现在的日期是：" + currdate.getTime());
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        currdate = ca.getTime();
        String enddate = formats.format(currdate);
        System.out.println("增加天数以后的日期：" + enddate);
        return enddate;
    }

    /**
     * 当前日期加上天数后的日期
     *
     * @param num 为增加的天数
     * @return
     */
    public static String now_plusDay(final int num, final String format) {
        Date d = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        // String currdate = formatDate.format(d);

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        d = ca.getTime();
        String enddate = formatDate.format(d);
        // System.out.println("增加天数以后的日期：" + enddate);
        return enddate;
    }

    // 时间戳转date
    public static Date strToDate(String dateStr) {
        // String dateStr= "201603261407";
        Date date = new Date();
        // 注意format的格式要与日期String的格式相匹配
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            date = sdf.parse(dateStr);
            System.out.println(date.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    // 判断某个日期时间+指定秒数，已超过now
    public static boolean someDateExpireNow(Date someDATE, int second) {
        Long now = System.currentTimeMillis();
        // 如果毫秒数小于now的毫秒数，就是过期
        if (someDATE.getTime() + second < now) {
            return true;
        }
        return false;
    }

    // 判断某个日期时间已超过now
    public static boolean someDateExpireNow(Date someDATE, String SimpleDateFormat) {
        Long now = System.currentTimeMillis();
        SimpleDateFormat fmt = new SimpleDateFormat(SimpleDateFormat); // 可以方便地修改日期格式
        String now_time = fmt.format(now);
        String some_date = fmt.format(someDATE);
        // System.out.println("now-------"+now.getTime());
        // System.out.println("some_time-------"+someDATE.getTime());
        // System.out.println("now_time-------"+now_time);
        // System.out.println("some_date-------"+some_date);
        // 如果毫秒数小于now的毫秒数，就是过期
        if (someDATE.getTime() < now) {
            return true;
        }
        return false;
    }

    // 判断某个日期时间已超过now
    public static boolean someDateExpireNow(Date someDATE) {
        Long now = System.currentTimeMillis();
        // 如果毫秒数小于now的毫秒数，就是过期
        if (someDATE.getTime() < now) {
            return true;
        }
        return false;
    }

    /**
     * @Description: 返回当前时间加上指定毫秒后的时间字符串
     */
    public static String getDayByAdd(Long mills, String format) {
        LocalDateTime ldt = LocalDateTime.now();
        ldt = ldt.plusSeconds(mills / 1000);
        return dataFormat(ldt, "yyyy-MM-dd");
    }

    /**
     * 判断当前日期是星期几<br>
     * <br>
     *
     * @param pTime 修要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
    public static int dayForWeek(String pTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 判断当前日期是星期几<br>
     * <br>
     *
     * @param pTime 修要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
    public static int dayForWeek(Date pTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(pTime);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 判断某一时间是否在一个区间内
     *
     * @param sourceTime 时间区间,半闭合,如[10:00-20:00)
     * @param curTime    需要判断的时间 如10:00
     * @return
     * @throws IllegalArgumentException
     */
    public static boolean isInTime(String sourceTime, String curTime) {
        if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
            throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
        }
        if (curTime == null || !curTime.contains(":")) {
            throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
        }
        String[] args = sourceTime.split("-");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            long now = sdf.parse(curTime).getTime();
            long start = sdf.parse(args[0]).getTime();
            long end = sdf.parse(args[1]).getTime();
            if (args[1].equals("00:00")) {
                args[1] = "24:00";
            }
            if (end < start) {
                if (now >= end && now < start) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (now >= start && now < end) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
        }

    }

    /**
     * @Description: 获取本周的开始时间
     */
    public static Long getStartOfWeek() {
        int mondayPlus;
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            mondayPlus = 0;
        } else {
            mondayPlus = 1 - dayOfWeek;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday) + " 00:00:00";
        System.out.println("工具类获取周一：" + preMonday);
        Date startDate = null;
        try {
            startDate = parseDate(preMonday, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate.getTime();
    }

    public static int getYMDhms(String x) {
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        int year = calendar2.get(Calendar.YEAR);
        int month = calendar2.get(Calendar.MONTH);
        int day = calendar2.get(Calendar.DAY_OF_MONTH);
        int hour = calendar2.get(Calendar.HOUR_OF_DAY);// 24小时制
        // int hour = calendar2.get(Calendar.HOUR);//12小时制
        int minute = calendar2.get(Calendar.MINUTE);
        int second = calendar2.get(Calendar.SECOND);
        switch (x) {
            case "Y":
                return year;
            case "M":
                return month;
            case "D":
                return day;
            case "h":
                return hour;
            case "m":
                return minute;
            case "s":
                return second;
            default:
                return hour;
        }
    }

    /**
     * Long 时间戳转换LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate formatLocalDate(Long date) {
        return Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * @param date
     * @return Long 时间戳转换LocalDateTime
     */
    public static LocalDateTime formatLocalDateTime(Long date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault());
    }

    /**
     * 判断时间相等  时间到日
     *
     * @param
     * @return
     */
    public static boolean equalsLocalDate(Long date1, Long date2) {
        return formatLocalDate(date1).equals(formatLocalDate(date2));
    }

    /**
     * @param leftStartDate  开始时间
     * @param leftEndDate    结束时间
     * @param rightStartDate 比较的时间段
     * @param rightEndDate   比较的时间段
     * @return true  存在 false 不存在
     */
    public static boolean isOverlap(Long leftStartDate, Long leftEndDate, Long rightStartDate, Long rightEndDate) {

        return
                ((leftStartDate >= rightStartDate))
                        && leftStartDate < rightEndDate
                        ||
                        ((leftStartDate > rightStartDate)
                                && leftStartDate <= rightEndDate)
                        ||
                        ((rightStartDate >= leftStartDate)
                                && rightStartDate < leftEndDate)
                        ||
                        ((rightStartDate > leftStartDate)
                                && rightStartDate <= leftEndDate);

    }


    public static void main(String[] args) {

        // Long w = System.currentTimeMillis();
        // Long s = Long.valueOf("1492704000000");

        // System.out.println(differentDaysBySeparated(w, s));
        // LocalDateTime ldt = LocalDateTime.now();
        // ldt = ldt.plusSeconds(60*60*24);
        // System.out.println(dataFormat(ldt, "yyyy-MM-dd HH:mm:ss"));
        //
        System.out.println(getCurrFullDataStandardZero());

        try {
            System.out.println(dateToStamp(getCurrFullDataStandardZero()));
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //
        //
        // System.out.println(DateUtils.isInTime("20:00-01:00", "00:00:00"));
        // System.out.println(DateUtils.isInTime("20:00-01:00", "00:00"));
        // System.out.println(DateUtils.isInTime("20:00-01:00", "03:00"));
        // System.out.println();
        //
        // System.out.println(DateUtils.isInTime("20:00-23:00", "03:00"));
        // System.out.println(DateUtils.isInTime("20:00-23:00", "22:59"));
        // System.out.println(DateUtils.isInTime("20:00-23:00", "18:00"));
        // System.out.println(DateUtils.isInTime("20:00-23:00", "20:00"));
        // System.out.println(DateUtils.isInTime("20:00-23:00", "23:00"));
        // System.out.println(getStartOfWeek());

        try {
            System.out.println(DateUtils.dateToStamp("2017-11-08 00:00:00"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        Calendar calendar = Calendar.getInstance();
        calendar.set(0, 0, 0, 8, 0, 0);

        System.out.println(calendar.getTimeInMillis());

        // Calendar.
        //  System.out.println(  DateUtils.getFragmentInMilliseconds(


        System.out.println(TimeUnit.MILLISECONDS.convert(12, TimeUnit.HOURS));
        System.out.println(TimeUnit.HOURS.convert(43200000, TimeUnit.MILLISECONDS));

        Long leftStartDate = 9L;
        Long leftEndDate = 12L;

        Long rightStartDate = 13L;
        Long rightEndDate = 14L;
        System.out.println("asssss" + isOverlap(leftStartDate, leftEndDate, rightStartDate, rightEndDate));
    }

    public static void calculateTime(long seconds) {
        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - TimeUnit.SECONDS.toHours(TimeUnit.SECONDS.toDays(seconds));
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toMinutes(TimeUnit.SECONDS.toHours(seconds));
        long second = TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.SECONDS.toSeconds(TimeUnit.SECONDS.toMinutes(seconds));
        System.out.println("Day" + day + " Hour" + hours + " Minute" + minute + " Seconds" + second);
    }


    public static HMS convertMills2HMS(Long milliSeconds) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        HMS hms = new HMS();
        second = milliSeconds.intValue() / 1000;

        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        hms.setHour(hour);
        hms.setMinute(minute);
        hms.setSecond(second);
        return hms;
    }


}