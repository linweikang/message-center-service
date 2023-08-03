package net.sitir.message.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 日期处理
 * @author linweikang
 * @date 2023/8/3
 */
public class DateUtils {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss:SSS";

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String YYYYMMDD = "yyyyMMdd";

    public static final String HHmmss = "HHmmss";

    public static final String YYYYMM = "yyyyMM";

    public static final String YYMMDD = "yyMMdd";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    private DateUtils() {
    }

    /**
     * 从给定的date，加上hour小时 求指定date时间后hour小时的时间
     *
     * @param date 指定的时间
     * @param hour 多少小时后
     * @return
     */
    public static Date addExtraHour(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return cal.getTime();
    }

    /**
     * 获取两个时间相差的天数
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 相差的天数
     */
    public static Long getBetweenDays(LocalDateTime startTime, LocalDateTime endTime){
        if (startTime == null || endTime == null){
            return 0L;
        }
        Duration duration = Duration.between(startTime, endTime);
        long days = duration.toDays(); //相差的天数
        /*long hours = duration.toHours();//相差的小时数
        long minutes = duration.toMinutes();//相差的分钟数
        long millis = duration.toMillis();//相差毫秒数
        long nanos = duration.toNanos();//相差的纳秒数*/
        return days;
    }

    /**
     * 获取当前时间与给定时间的相差天数
     * @param time 给定时间
     * @return 相差的天数
     */
    public static Long getBetweenDays(LocalDateTime time){
        if (time == null){
            return 0L;
        }
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(time, now);
        long days = duration.toDays(); //相差的天数
        return days;
    }

    /**
     * 从给定的date，加上increase天
     *
     * @param date
     * @param increase
     * @return
     */
    public static Date increaseDay2Date(Date date, int increase) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.DAY_OF_MONTH, increase);
        return cal.getTime();
    }

    /**
     * 把字符串日期默认转换为yyyy-mm-dd格式的Data对象
     *
     * @param strDate
     * @return
     */
    public static Date format(String strDate, String format) {
        Date d = null;
        if (null == strDate || "".equals(strDate))
            return null;
        else
            try {
                d = getFormatter(format).parse(strDate);
            } catch (ParseException pex) {
                return null;
            }
        return d;
    }

    /**
     * Date类型时间转化为LocalDateTime类型
     * @param date
     * @return
     */
    public static LocalDateTime getLocalDateTimeFromDate(Date date) {
        LocalDateTime localDateTime = date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        return localDateTime;
    }

    /**
     * LocalDateTime类型转化为Date类型时间
     * @param localDateTime
     * @return
     */
    public static Date getDateFromLocalDateTime(LocalDateTime localDateTime) {
        Date date = Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
        return date;
    }


    /**
     * 判断两个时间的间隔，返回秒数
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    public static int calLastedTime(String startDateStr, String endDateStr) {
        long end = format(endDateStr, YYYY_MM_DD_HH_MM_SS).getTime();
        long start = format(startDateStr, YYYY_MM_DD_HH_MM_SS).getTime();
        int interval = (int)((end - start) / 1000);
        return interval;
    }

    public static List<String> internalTime(String startDateStr, String endDateStr, int internalNumber) {
        List<String> returnStr = new ArrayList<>();
        long end = format(endDateStr, YYYY_MM_DD_HH_MM_SS).getTime();
        long start = format(startDateStr, YYYY_MM_DD_HH_MM_SS).getTime();
        long intervalAll = (long)((end - start));
        long space = intervalAll / internalNumber;
        for(int i = 0; i < internalNumber; i++) {
            returnStr.add(format(new Date(start + space * (i + 1)), YYYY_MM_DD_HH_MM_SS));
        }
        return returnStr;
    }

    public static int internalTimeReturnLocation(String startDateStr, String endDateStr, int internalNumber, String checkDateStr) {
        int location = -1;
        List<String> returnStr = internalTime(startDateStr, endDateStr, internalNumber);
        for(int i = 0; i < returnStr.size(); i++) {
            if(checkDateStr.compareTo(startDateStr) > 0 && checkDateStr.compareTo(returnStr.get(i)) < 0) {
                location = i;
                break;
            }
        }
        return location;
    }

    /**
     * 获取一个简单的日期格式化对象
     *
     * @return 一个简单的日期格式化对象
     */
    private static SimpleDateFormat getFormatter(String parttern) {
        return new SimpleDateFormat(parttern);
    }


    /**
     * 获取当前日期
     *
     * @return 一个包含年月日的<code>Date</code>型日期
     */
    public static synchronized Date getCurrDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /*public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        if (localDateTime == null){
            return "";
        }
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }*/

    /**
     * LocalDateTime类型转化为指定格式的字符串时间类型-yyyy年MM月dd日
     * @param localDateTime 时间
     * @return 字符串
     */
    public static String formatLocalDateTimeStr(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return "";
        }
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        String localDateTimeStr = year + "年" + month + "月" + day + "日";
        return localDateTimeStr;
    }

    /**
     * LocalDateTime类型转化为指定格式的字符串时间类型-默认为yyyy－MM－dd hh:mm:ss
     * @param localDateTime 时间
     * @param pattern 格式
     * @return 字符串
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        if (localDateTime == null) {
            return "";
        }
        if (StringUtils.isBlank(pattern)){
            pattern = YYYY_MM_DD_HH_MM_SS;
        }
        String localDateTimeStr = localDateTime.format(DateTimeFormatter.ofPattern(pattern));
        return localDateTimeStr;
    }

    /**
     * LocalDateTime类型转化为指定格式(YYYY-MM-DD)的字符串时间类型
     * @param localDateTime 时间
     * @return 字符串
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return "";
        }
        String localDateTimeStr = localDateTime.format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
        return localDateTimeStr;
    }

    public static String getCurrDateYYMMDDStr() {
        return format(getCurrDate(), YYMMDD);
    }

    /**
     * 获取当前完整时间,样式: yyyyMMddhhmmss
     *
     * @return 一个包含年月日时分秒的<code>String</code>型日期。yyyyMMddhhmmss
     */
    public static String getCurrDateTime14Str() {
        return format(getCurrDate(), YYYYMMDDHHMMSS);
    }

    /**
     * 获取当前完整时间,样式: yyyyMMddhhmmss
     *
     * @return 一个包含年月日时分秒的<code>String</code>型日期。yyyyMMddhhmmss
     */
    public static String getCurrDateTime14StrAfterHout(int hour) {
        Date afterDate = addExtraHour(new Date(), hour);
        return format(afterDate, YYYYMMDDHHMMSS);
    }

    /**
     * 获取当前完整时间,样式: yyyy－MM－dd hh:mm:ss
     *
     * @return 一个包含年月日时分秒的<code>String</code>型日期。yyyy-MM-dd hh:mm:ss
     */
    public static String getCurrDateTimeStr() {
        return format(getCurrDate(), YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay YYYY_MM_DD_HH_MM_SS 格式
     * @param formatStr    日期类型
     * @return
     */
    public static String getSpecifiedDayBefore(String specifiedDay, String formatStr) {// 可以用new
        // Date().toLocalString()传递参数
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat(formatStr).format(c.getTime());
        return dayBefore;
    }

    public static long getTimeStampBy14DateStr(String dateStr) {// 可以用new
        Date date = null;
        try {
            date = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public static String get14DateStrByTimeStampLong(long timeStamp) {// 可以用new
        Date date = new Date(timeStamp);
        try {
            return format(date, YYYY_MM_DD_HH_MM_SS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将指定格式的时间字符串转换为LocalDateTime-默认为yyyy－MM－dd hh:mm:ss
     * @param dateTime 时间字符串
     * @param format 指定格式
     * @return LocalDateTime
     */
     public static LocalDateTime toLocalDataTime(String dateTime, String format){
        if (StringUtils.isBlank(dateTime)){
            return null;
        }
        if (StringUtils.isBlank(format)){
            format = YYYY_MM_DD_HH_MM_SS;
        }

        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        LocalDateTime ldt = LocalDateTime.parse(dateTime, df);
        return ldt;
     }

    /**
     * 将时间字符串转换为LocalDateTime-只支持yyyy－MM－dd hh:mm:ss 和 yyyy－MM－dd 格式
     * @param dateTime 时间字符串
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDataTime(String dateTime){
        if (StringUtils.isBlank(dateTime)){
            return null;
        }

        LocalDateTime ldt;
        if (dateTime.length() > 10){
            String format = YYYY_MM_DD_HH_MM_SS;
            DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
            ldt = LocalDateTime.parse(dateTime, df);
        }else {
            String format = YYYY_MM_DD;
            DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
            ldt = LocalDate.parse(dateTime, df).atStartOfDay();
        }
        return ldt;
    }

    /**
     * 获取指定时间的00:00:00
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getLocalDateTimeForBegin(LocalDateTime localDateTime) {
        LocalDateTime begin = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
        return begin;
    }

    /**
     * 获取指定时间的23:59:59
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getLocalDateTimeForEnd(LocalDateTime localDateTime) {
        LocalDateTime end = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX);
        return end;
    }

    /**
     * 时间戳(毫秒)转化为LocalDateTime格式
     * @param timestamp
     * @return
     */
    public static LocalDateTime getLocalDateTimeFromTimestamp(Long timestamp) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timestamp/1000, 0, ZoneOffset.ofHours(8));
        return localDateTime;
    }

    /**
     * LocalDateTime格式转化为时间戳(毫秒)
     * @param localDateTime
     * @return
     */
    public static Long getTimestampFromLocalDateTime(LocalDateTime localDateTime) {
        Long timestamp = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return timestamp;
    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay YYYY_MM_DD_HH_MM_SS 格式
     * @param formatStr    日期类型
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay, String formatStr) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat(formatStr).format(c.getTime());
        return dayAfter;
    }

    /**
     * 获取本周第一天的日期
     *
     * @return
     */
    public static final String getWeekFirstDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
        cal.add(Calendar.DATE, -day_of_week);
        return sdf.format(cal.getTime());
    }

    /**
     * 获取当前月的第一天
     *
     * @return
     */
    public static final String getCurrentMonthFirstDay() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 当前月的第一天
        cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
        Date beginTime = cal.getTime();
        return sdf.format(beginTime);
    }

    /**
     * 功能：获取指定月份的第一天<br/>
     *
     * @version 2017年6月20日 下午5:26:39 <br/>
     */
    public static final String getStartDayWithMonth(String month) throws ParseException {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat mf = new SimpleDateFormat("yyyy-MM");
        Date date = mf.parse(month);
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 0);// 因为格式化时默认了DATE为本月第一天所以此处为0
        return sdf.format(calendar.getTime());
    }

    /**
     * 功能：获取指定月份的最后一天<br/>
     *
     * @version 2017年6月20日 下午5:26:54 <br/>
     */
    public static final String getEndDayWithMonth(String month) throws ParseException {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat mf = new SimpleDateFormat("yyyy-MM");
        Date date = mf.parse(month);
        calendar.setTime(date);
        calendar.roll(Calendar.DATE, -1);// api解释roll()：向指定日历字段添加指定（有符号的）时间量，不更改更大的字段
        return sdf.format(calendar.getTime());
    }

    public static final String formatYearMonthDay(String dateStr) throws ParseException {
        if (StringUtils.isNotBlank(dateStr)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateStr);
            return sdf.format(date);
        } else {
            return "";
        }
    }

    /**
     * 功能：<br/>
     * 根据时间 yyyy-MM-dd 获取该日期是本月第几周
     *
     * @version 2017年7月28日 上午11:10:44 <br/>
     */
    public static final int getWeekIndexOfMonth(String dateStr) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        return weekOfMonth;
    }

    public static void main(String args[]) throws ParseException {
        // System.out.println(DateUtils.getWeekFirstDay());
        // System.out.println(DateUtils.getCurrentMonthFirstDay());
        // System.out.println(DateUtils.getYesterdayStart());
        /*SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy HH:mm:ss",Locale.US);
        Date date =sdf.parse("19 Apr 93 18:51:33");
        System.out.println(internalTime("1992-08-06 15:47:30", "1993-04-27 16:30:51", 5));
        System.out.println(internalTimeReturnLocation("1992-08-06 15:47:30", "1993-04-27 16:30:51",
                5, "1992-12-28 11:07:10"));*/

        LocalDateTime time = LocalDateTime.of(2023, 10, 31, 10, 20, 30);
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(time, now);
        long days = duration.toDays(); //相差的天数
        System.out.println(days);

    }

}
