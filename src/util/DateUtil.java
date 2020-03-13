package util;

import java.util.*;

/**
 * @author lilei
 * create at 2020/2/13 13:46
 */

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class DateUtil {

    public static final long MILLIS_PER_SECOND = 1000;

    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;

    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

    public static final long MILLIS_PER_WEEK = 7 * MILLIS_PER_DAY;

    public static final long AS_ONE_DAY = 2 * MILLIS_PER_HOUR;

    //获得两个日期的相隔天数
    public static long getDate(Date one, Date two) {
        long time = Math.abs(one.getTime() - two.getTime());
        return getDate(time);
    }

    //获得两个日期的相隔天数
    public static long getDate(long time) {
        if (time % MILLIS_PER_DAY <= AS_ONE_DAY) {
            return time / MILLIS_PER_DAY;
        }
        return time / MILLIS_PER_DAY + 1;
    }

    //取得两个日期中的较大值
    public static Date getMax(Date one, Date two) {
        if (two.after(one)) {
            return two;
        } else {
            return one;
        }
    }

    //取得两个日期中的较小值
    public static Date getMin(Date one, Date two) {
        if (two.after(one)) {
            return one;
        } else {
            return two;
        }
    }


    public static Calendar toCalendar(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    //date是否在某个时间段内
    public static boolean isBetween(Date one, Date two, Date date) {
        Date max = getMax(one, two);
        Date min = getMin(one, two);
        return date.after(min) && date.before(max);
    }

    //两个时段中经过的零点个数
    public static long getZero(Date one, Date two) {
        Date max = getMax(one, two);
        Date min = getMin(one, two);
        return getDate(getPreZero(max).getTime() - getNextZero(min).getTime()) + 1;
    }

    //取得某个日期的前一个整点日期
    public static Date getPreZero(Date date) {
        return getPreZero(toCalendar(date));
    }

    public static Date getPreZero(Calendar calendar) {
        toZero(calendar);
        return calendar.getTime();
    }

    //取得某个日期的后一个整点日期
    public static Date getNextZero(Date date) {
        return getNextZero(toCalendar(date));
    }

    public static Date getNextZero(Calendar calendar) {
        if (isZero(calendar)) {
            return calendar.getTime();
        }
        toZero(calendar);
        tomorrow(calendar);
        return calendar.getTime();
    }

    //判断某个日期是零点吗
    public static boolean isZero(Calendar calendar) {
        return calendar.get(Calendar.HOUR) == 0 &&
                calendar.get(Calendar.MINUTE) == 0 &&
                calendar.get(Calendar.SECOND) == 0;
    }

    public static boolean isZero(Date date) {
        return isZero(toCalendar(date));
    }


    private static void toZero(Calendar calendar) {
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
    }


    //获得一个日期是一年中的第几周
    public static int getWeekOfYear(Date date) {
        return toCalendar(date).get(Calendar.WEEK_OF_MONTH);
    }


    //获取某个日期下一个最近的周一的日期
    public static Date getNextMonday(Date date) {
        return getNextMonday(toCalendar(date));
    }

    public static Date getNextMonday(Calendar calendar) {
        while (!isMonday(calendar)) {
            tomorrow(calendar);
        }
        return getPreZero(calendar);
    }

    public static Date getNextWeek(Calendar calendar) {
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        return calendar.getTime();
    }

    //当前日期后七天的日期
    public static Date getNextWeek(Date date) {
        return getNextWeek(toCalendar(date));
    }

    //判断一个日期时是否是周一
    public static boolean isMonday(Date date) {
        return isMonday(toCalendar(date));
    }

    public static boolean isMonday(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }


    public static Date yesterday(Date date) {
        return yesterday(toCalendar(date));
    }

    public static Date yesterday(Calendar calendar) {
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    public static Date tomorrow(Date date) {
        return tomorrow(toCalendar(date)).getTime();
    }

    public static Calendar tomorrow(Calendar calendar) {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar;
    }

    //获取两个日期之间的各个周一的日期
    public static List<Date> getBeginOfWeek(Date one, Date two) {
        Date max = getMax(one, two);
        Date min = getMin(one, two);
        List<Date> dates = new ArrayList<>();
        Date monday = getNextMonday(min);
        do {
            dates.add(monday);
            monday = getNextWeek(monday);
        } while (!monday.after(max));
        return dates;
    }

    //获取两个日期之间的各个整点的日期
    public static List<Date> getBeginOfDate(Date one, Date two) {
        Date max = getMax(one, two);
        Date min = getMin(one, two);
        List<Date> dates = new ArrayList<>();
        Date zero = getNextZero(min);
        do {
            dates.add(zero);
            zero = tomorrow(zero);
        } while (!zero.after(max));
        return dates;
    }

    //    获取两个日期之间的各个月的第一天的日期
    public static List<Date> getBeginOfMonth(Date one, Date two) {
        Date max = getMax(one, two);
        Date min = getMin(one, two);
        List<Date> dates = new ArrayList<>();
        Date month = getMonthFirst(min);
        do {
            dates.add(month);
            month = getNextMonth(month);
        } while (!month.after(max));
        return dates;
    }

    //判断一个日期是否是月首
    public static boolean isMonthFirst(Date date) {
        return isMonthFirst(toCalendar(date));
    }

    public static boolean isMonthFirst(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }

    //获得某个日期后的后一个月的日期
    public static Date getNextMonth(Calendar calendar) {
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    public static Date getNextMonth(Date date) {
        return getNextMonth(toCalendar(date));
    }

    //获得某个日期后一个月首的日期
    public static Date getMonthFirst(Calendar calendar) {
        while (!isMonthFirst(calendar)) {
            tomorrow(calendar);
        }
        return getPreZero(calendar);
    }

    public static Date getMonthFirst(Date date) {
        return getMonthFirst(toCalendar(date));
    }


    public static void main(String[] args) {
        System.out.println("当前日期到当前日期十周后的每个零点的日期：" + getBeginOfDate(new Date(), new Date(new Date().getTime() + MILLIS_PER_WEEK * 10)));
        System.out.println("当前日期到当前日期十周后的每个周一的日期：" + getBeginOfWeek(new Date(), new Date(new Date().getTime() + MILLIS_PER_WEEK * 10)));
        System.out.println("当前日期到当前日期十周后的每个月首的日期：" + getBeginOfMonth(new Date(), new Date(new Date().getTime() + MILLIS_PER_WEEK * 10)));
    }
}
