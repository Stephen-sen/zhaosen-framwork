package com.zhaosen.util;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;

public class DateUtil {
    private static SimpleDateFormat fullSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat fullSimpleDateFormat2 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    private static SimpleDateFormat DateFormatyyyyMMNew = new SimpleDateFormat("yyyyMM");
    private static SimpleDateFormat DateFormatyyMMdd = new SimpleDateFormat("yyMMdd");
    private static SimpleDateFormat DateFormatyyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat DateFormatyyyy = new SimpleDateFormat("yyyy");
    private static SimpleDateFormat DateFormatyyyyMM = new SimpleDateFormat("yyyy-MM");
    private static SimpleDateFormat DateFormatssmmHHddMM = new SimpleDateFormat("ss mm HH dd MM ?");
    private static SimpleDateFormat DateFormatssmmHHddMMyyyy = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
    private static SimpleDateFormat DateFormatyyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
    public static String DATE_FORMAT_yyyyMMdd = "yyyy-MM-dd";
    public static String DATE_FORMAT_MMdd = "MM-dd";
    public static String DATE_FORMAT_yyyyMMddhhmmss = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_FORMAT_yyyyMMdd1 = "yyyy/MM/dd";
    public static String DATE_FORMAT_MMdd1 = "MM/dd";
    public static String DATE_FORMAT_yyMMdd1 = "yy/MM/dd";
    public static String DATE_FORMAT_yyyyMMddhhmmss1 = "yyyy/MM/dd HH:mm:ss";
    private static Date rightNow = null;

    public DateUtil() {
    }

    public static String convertDateToString(Date source, String pattern) {
        if(StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd";
        }

        FastDateFormat format = FastDateFormat.getInstance(pattern);
        return format.format(source);
    }

    public static Date convertStringToDate(String source, String pattern) {
        if(StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd";
        }

        Date date = null;

        try {
            date = DateUtils.parseDate(source, new String[]{pattern});
        } catch (ParseException var4) {
            ;
        }

        return date;
    }

    public static Timestamp getTimestampFromDate(Date date) {
        String dt = getByFullString(date);
        Timestamp ts = getTimestampByTimeString(dt);
        return ts;
    }

    public static String getTimestampString(Timestamp convertDate) {
        String t1 = fullSimpleDateFormat.format(convertDate);
        return t1;
    }

    public static Date getTimeStringByFullString(String convertDate) {
        Date timeString = null;

        try {
            timeString = fullSimpleDateFormat.parse(convertDate);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return timeString;
    }

    public static Timestamp getTimestampByTimeString(String convertDate) {
        Timestamp t1 = Timestamp.valueOf(convertDate);
        return t1;
    }

    public static String getSystemTimeByFullString() {
        rightNow = new Date();
        return fullSimpleDateFormat.format(rightNow);
    }

    public static String getSystemTimeByFullString2() {
        rightNow = new Date();
        return fullSimpleDateFormat2.format(rightNow);
    }

    public static String getByFullString(Date date) {
        return fullSimpleDateFormat.format(date);
    }

    public static String getyyyyMMddStr(Date date) {
        return DateFormatyyyyMMdd.format(date);
    }

    public static String getyyMMddStr(Date date) {
        return DateFormatyyMMdd.format(date);
    }

    public static String getSystemDateByyyMMdd() {
        rightNow = new Date();
        return DateFormatyyMMdd.format(rightNow);
    }

    public static String getSystemDateByYM() {
        return DateFormatyyyyMMNew.format(new Date());
    }

    public static String getSystemDateByyyyyMMdd() {
        rightNow = new Date();
        return DateFormatyyyyMMdd.format(rightNow);
    }

    public static String getSystemDateByyyyyMMddHHmmss() {
        rightNow = new Date();
        return DateFormatyyyyMMddHHmmss.format(rightNow);
    }

    public static String getSystemDateByyyyy() {
        rightNow = new Date();
        return DateFormatyyyy.format(rightNow);
    }

    public static String getSystemDateByyyyyMM() {
        rightNow = new Date();
        return DateFormatyyyyMM.format(rightNow);
    }

    public static String getSystemDateByyyyyMM(Date date) {
        rightNow = date;
        return DateFormatyyyyMM.format(rightNow);
    }

    public static String getDateByssmmHHddMM(Date date) {
        return DateFormatssmmHHddMM.format(date);
    }

    public static String getDateByssmmHHddMMyyyy(Date date) {
        return DateFormatssmmHHddMMyyyy.format(date);
    }

    public static Date getDayAfter(Date source, char timeUnit, int interval) {
        Calendar dest = null;
        dest = Calendar.getInstance();
        dest.setTime(source);
        switch(timeUnit) {
        case 'M':
            dest.add(2, interval);
            break;
        case 'd':
            dest.add(7, interval);
            break;
        case 'h':
            dest.add(10, interval);
            break;
        case 'm':
            dest.add(12, interval);
            break;
        case 's':
            dest.add(13, interval);
            break;
        case 'y':
            dest.add(1, interval);
            break;
        default:
            dest.add(7, interval);
        }

        return dest.getTime();
    }

    public static Date getSystemTime() {
        rightNow = new Date();
        return rightNow;
    }

    public static boolean DateAfter(Timestamp time1, Timestamp time2) {
        return time1.after(time2);
    }

    public static boolean isValidDate(int year, int month, int day) {
        if(2 == month) {
            if(2008 == year) {
                if(day > 29) {
                    return false;
                }
            } else if(day > 28) {
                return false;
            }
        } else if((4 == month || 6 == month || 9 == month || 11 == month) && day > 30) {
            return false;
        }

        return true;
    }

    public static int convertStrToMin(String str) {
        float fValue = Float.parseFloat(str);
        float tempValue = fValue * 60.0F;
        int min1 = Math.round(tempValue);
        return min1;
    }

    public static boolean isEqualTimeStr(String str, Timestamp ts) {
        boolean flag = false;
        if(str == null) {
            return false;
        } else if("".equals(str)) {
            return false;
        } else if(ts == null) {
            return false;
        } else {
            String tsStr = getDateByssmmHHddMM(ts);
            if(str.equals(tsStr)) {
                flag = true;
            }

            return flag;
        }
    }

    public static Date getTimeStringByssmmHHddMM(String str) {
        Date timeString = null;

        try {
            timeString = DateFormatssmmHHddMM.parse(str);
        } catch (ParseException var3) {
            ;
        }

        return timeString;
    }

    public static int judgeSmS(String smsTimeStr, Timestamp overtime, int num) {
        Date smsDate = getTimeStringByssmmHHddMM(smsTimeStr);
        String overtimeStr = getDateByssmmHHddMM(overtime);
        Date overtime2 = getTimeStringByssmmHHddMM(overtimeStr);
        Date overtime3 = getDayAfter(overtime2, 'm', -num);
        int i1 = overtime3.compareTo(smsDate);
        return i1;
    }

    public static boolean isWeekend(String theDate) {
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            d = sdf.parse(theDate);
        } catch (ParseException var4) {
            ;
        }

        GregorianCalendar c = new GregorianCalendar(TimeZone.getTimeZone("EST"), Locale.US);
        c.setTime(d);
        return c.get(7) < 6;
    }

    public static boolean filterTaskDate(String date) {
        boolean flag = true;
        String value = null;
        Properties properties = new Properties();

        try {
            properties.load(DateUtils.class.getResourceAsStream("/com/zhengxin/eoms/wsm/util/calendar.properties"));
            value = properties.getProperty(date);
        } catch (IOException var5) {
            ;
        }

        if(value == null) {
            flag = isWeekend(date);
        } else if(value.equals("1")) {
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }

    public static String[] quarterFortess(int year, int quarter) {
        String[] result = new String[]{"", ""};
        String[] qmap = new String[]{"0", "0"};
        switch(quarter) {
        case 1:
            qmap[0] = "01";
            qmap[1] = "03";
            break;
        case 2:
            qmap[0] = "04";
            qmap[1] = "06";
            break;
        case 3:
            qmap[0] = "07";
            qmap[1] = "09";
            break;
        case 4:
            qmap[0] = "10";
            qmap[1] = "12";
        }

        String edgeDay = "-31";
        if(qmap[1].equals("06") || qmap[1].equals("09")) {
            edgeDay = "-30";
        }

        String btime = String.valueOf(year) + "-" + qmap[0] + "-01 00:00:00";
        String etime = String.valueOf(year) + "-" + qmap[1] + edgeDay + " 23:59:59";
        result[0] = btime;
        result[1] = etime;
        return result;
    }

    public static int getWorkDays(Date dateFrom, Date dateTo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int workdays = 0;

        try {
            dateFrom = dateFormat.parse(convertDateToString(dateFrom, "yyyy-MM-dd"));
            dateTo = dateFormat.parse(convertDateToString(dateTo, "yyyy-MM-dd"));
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateTo);
            cal.add(5, 1);

            for(dateTo = cal.getTime(); dateFrom.before(dateTo); cal = null) {
                cal = Calendar.getInstance();
                cal.setTime(dateFrom);
                String date = convertDateToString(dateFrom, "yyyy-MM-dd");
                if(filterTaskDate(date)) {
                    ++workdays;
                }

                cal.add(5, 1);
                dateFrom = cal.getTime();
            }
        } catch (Exception var6) {
            ;
        }

        System.out.println("工作天数是:" + workdays);
        return workdays;
    }

    public static int getDaysOfMonth(int year, int month) {
        byte days = 0;
        if(year != 0 && month != 0) {
            if(month == 2) {
                if((year % 4 != 0 || year % 100 == 0) && year % 400 != 0) {
                    days = 28;
                } else {
                    days = 29;
                }
            } else if(month != 1 && month != 3 && month != 5 && month != 7 && month != 8 && month != 10 && month != 12) {
                days = 30;
            } else {
                days = 31;
            }
        }

        return days;
    }

    public static String convertStrtime(String time1) {
        String tempStr1 = time1.substring(0, 4);
        String tempStr2 = time1.substring(4, 6);
        String tempStr3 = time1.substring(6, 8);
        String strTime = tempStr1 + "-" + tempStr2 + "-" + tempStr3 + time1.substring(8);
        return strTime;
    }

    public static String convertEoms2Crm(String time1) {
        String[] date = time1.split("-");
        String d = "";

        for(int i = 0; i < date.length; ++i) {
            d = d.concat(date[i]);
        }

        return d;
    }

    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(7);
        return week;
    }

    public static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(5);
        return week;
    }

    public static int getCHNDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer week = Integer.valueOf(calendar.get(7));
        int chnWeek1;
        if(week.equals(Integer.valueOf(1))) {
            chnWeek1 = 7;
        } else {
            chnWeek1 = week.intValue() - 1;
        }

        return chnWeek1;
    }

    public static String getWeekCHNStr(int week) {
        switch(week) {
        case 1:
            return "一";
        case 2:
            return "二";
        case 3:
            return "三";
        case 4:
            return "四";
        case 5:
            return "五";
        case 6:
            return "六";
        case 7:
            return "日";
        default:
            return "一";
        }
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(2);
        return month;
    }

    public static int getCNMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(2) + 1;
        return month;
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(1);
        return year;
    }

    public static Date getFirstDayOfWeek(Date date) {
        Integer week = Integer.valueOf(getDayOfWeek(date));
        new Date();
        Date firstDay;
        if(week.equals(Integer.valueOf(1))) {
            firstDay = getDayAfter(date, 'd', -6);
        } else {
            firstDay = getDayAfter(date, 'd', 2 - week.intValue());
        }

        return firstDay;
    }

    public static Date getLastDayOfWeek(Date date) {
        Integer week = Integer.valueOf(getDayOfWeek(date));
        new Date();
        Date firstDay;
        if(week.equals(Integer.valueOf(0))) {
            firstDay = date;
        } else {
            firstDay = getDayAfter(date, 'd', 8 - week.intValue());
        }

        return firstDay;
    }

    public static Date getFirstDayOfMonth(Date date) {
        int year = getYear(date);
        int month = getMonth(date) + 1;
        String str = String.valueOf(year) + "-" + month + "-01" + " 00:00:00";
        return getTimeStringByFullString(str);
    }

    public static Date getLastDayOfMonth(Date date) {
        int year = getYear(date);
        int month = getMonth(date) + 1;
        int days = getDaysOfMonth(year, month);
        String str = String.valueOf(year) + "-" + month + "-" + days + " 00:00:00";
        return getTimeStringByFullString(str);
    }

    public static String formatWeekStr(String week) {
        switch(Integer.parseInt(week)) {
        case 1:
            return "一";
        case 2:
            return "二";
        case 3:
            return "三";
        case 4:
            return "四";
        case 5:
            return "五";
        case 6:
            return "六";
        case 7:
            return "日";
        default:
            return "";
        }
    }

    public static int compareDate(Date dt1, Date dt2) {
        try {
            return dt1.getTime() > dt2.getTime()?1:(dt1.getTime() < dt2.getTime()?-1:0);
        } catch (Exception var3) {
            var3.printStackTrace();
            return 0;
        }
    }

    public static int getDaysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / 86400000L;
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static int getDaysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / 86400000L;
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static void main(String[] arg) {
        System.out.println(getDayOfWeek(new Date()));
    }
}
