package com.object.testDate;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyDateUse {
    public static void main(String[] args) {
//        oldUse1();
        newUse1();
        newUse2();
        newUse3();
    }

    /**
     * 推荐1
     */
    static void newUse1(){
        // 获取当前日期
        LocalDate now1 = LocalDate.now(); // 获取当前日期（不含时间）
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now1.format(formatter1);
        System.out.println(formattedDate);
        System.out.println(now1.format(DateTimeFormatter.ISO_LOCAL_DATE));

        // 获取当前时间
        LocalDateTime now2 = LocalDateTime.now();
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        System.out.println(now2.format(formatter2));
        System.out.println(now2.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(now1.format(DateTimeFormatter.ISO_DATE));

        // 指定时区
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        System.out.println(now.format(formatter));
    }

    /**
     * 推荐2
     */
    static void newUse2(){
        // 计算两个日期之间的天数差
        // 计算年龄
        LocalDate birthDate = LocalDate.of(1996, 6, 14);
        LocalDate now = LocalDate.now();
//        long daysBetween = ChronoUnit.DAYS.between(birthDate, now);
        Period period = Period.between(birthDate, now);
        System.out.println("年龄："
                + period.getYears() + "年 "
                + period.getMonths() + "月 "
                + period.getDays() + "天");

        // 计算倒计时天数
        LocalDate targetDate = LocalDate.of(2026, 5, 15);
        long daysBetween = ChronoUnit.DAYS.between(now, targetDate);
        System.out.println("距离" + targetDate + "还有" + daysBetween + "天");

        // 计算两个 日期时间 之间的精确时间差
        LocalDateTime start = LocalDateTime.of(2022, 3, 22, 9, 30, 0);
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("时间差：" + duration.toDays() + "天 "
                + duration.toHours() % 24 + "小时 "
                + duration.toMinutesPart() % 60 + "分 "
                + duration.getSeconds() % 60 + "秒 ");

        //
    }

    /**
     * 推荐3 加上时区
     */
    static void newUse3(){
        // 获取系统默认时区的当前时间（自动处理夏令时）
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("当前系统时间（含时区和DST）: " + now);

        // 或指定特定时区（如纽约，会自动判断是 EST 还是 EDT）
        ZonedDateTime nyTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("纽约当前时间: " + nyTime);


        // 创建时区
        ZoneId ny = ZoneId.of("America/New_York");

        // 冬季时间（标准时间 EST, UTC-5）
        ZonedDateTime winter = ZonedDateTime.of(2025, 11, 1, 12, 0, 0, 0, ny);
        // 夏季时间（夏令时 EDT, UTC-4）
        ZonedDateTime summer = ZonedDateTime.of(2025, 7, 1, 12, 0, 0, 0, ny);

        Duration diff = Duration.between(winter, summer);
        System.out.println("相差小时数: " + diff.toHours()); // 精确计算，自动处理 DST 偏移变化

        // 假设你有一个“纽约本地时间”字符串
        LocalDateTime local = LocalDateTime.parse("2025-03-09T02:30:00"); // 注意：这是 DST 切换日！

        // 将其解释为纽约时间（自动处理歧义或跳过）
        ZonedDateTime zdt = local.atZone(ZoneId.of("America/New_York"));

        System.out.println(zdt);
    }


    /**
     * 旧版使用1
     */
    static void oldUse1(){
        Date date1 = new Date();

        System.out.println("现在时间date1为: "+date1);
        System.out.println("现在的毫秒数date1: "+date1.getTime());

        // 格式化
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("当前时间格式化输出："+simpleDateFormat1.format(date1));

        // 解析
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date2 = simpleDateFormat2.parse("2022-01-01 00:00:00");
            System.out.println("时间对象date2为: "+date2);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 日历
        Calendar calendar = new GregorianCalendar();
        System.out.println("日历对象calendar为: "+ calendar);
        System.out.println("时间: "+ calendar.getTime());
        System.out.println("年: "+ calendar.get(Calendar.YEAR));
    }

    /**
     * 旧版使用2
     */
    static void oldUse2(){
        // 计算两个日期之间的天数差
        Date date1 = new Date(124, Calendar.JANUARY, 1); // 2024-01-01 (年份从1900开始)
        Date date2 = new Date(124, Calendar.JANUARY, 15); // 2024-01-15

        long timeDiff = date2.getTime() - date1.getTime(); // 毫秒差

        long daysDiff = timeDiff / (1000 * 60 * 60 * 24);
        long hoursDiff = timeDiff / (1000 * 60 * 60);
        long minutesDiff = timeDiff / (1000 * 60);

        System.out.println("天数差: " + daysDiff);   // 14
        System.out.println("小时差: " + hoursDiff);  // 336


        // Calendar方式
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2024, Calendar.JANUARY, 1);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(2024, Calendar.JANUARY, 15);

        long timeInMillis1 = cal1.getTimeInMillis();
        long timeInMillis2 = cal2.getTimeInMillis();

        long diffInMillis = timeInMillis2 - timeInMillis1;
        long daysDiff2 = diffInMillis / (24 * 60 * 60 * 1000);

        System.out.println("天数差: " + daysDiff2); // 14
    }
}
