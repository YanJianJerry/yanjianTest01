package com.Object.Date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Author: yanjian
 * @Date: 2022/04/08 15:58
 * @Description:
 * @Param:
 * @Return:
 * @Version:
 */
public class GregorianCalendarDemo  {
    public static void main(String[] args) {

        //1、new GregorianCalendar() 创建日历对象
        GregorianCalendar calendar1 = new GregorianCalendar(2018,5,06,8,30,00);
        GregorianCalendar calendar2 = new GregorianCalendar(2018,06,06);

        //2、Calendar1.get() 获取日历对象中的值，注意：月份 0-11
        int year1 = calendar1.get(Calendar.YEAR);
        int month1 = calendar1.get(Calendar.MONTH);
        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
        int hour1 = calendar1.get(Calendar.HOUR);
        int minute1 = calendar1.get(Calendar.MINUTE);
        int second1 = calendar1.get(Calendar.SECOND);
        int dayOfDate = calendar1.get(Calendar.DATE);
        System.out.println("年："+year1);
        System.out.println("月："+month1+1);
        System.out.println("日："+day1);
        System.out.println("时："+hour1);
        System.out.println("分："+minute1);
        System.out.println("秒："+second1);
        System.out.println("时间："+dayOfDate);

        //3、Calendar3.set() 设置日历对象中的值
        GregorianCalendar calendar3 = new GregorianCalendar();
        calendar3.set(Calendar.YEAR,1996);
        calendar3.set(Calendar.MONTH,Calendar.OCTOBER);
        calendar3.set(Calendar.DAY_OF_MONTH,20);
        calendar3.set(Calendar.HOUR,23);
        calendar3.set(Calendar.MINUTE,30);
        calendar3.set(Calendar.SECOND,40);
        System.out.println(calendar3);

        //4、日历对象和时间对象的转化
        GregorianCalendar calendar4 = new GregorianCalendar();
        calendar4.setTime(new Date());
        Date date5 = calendar4.getTime();

        System.out.println("date5: "+date5);
        System.out.print("现在是");
        printCalendar(calendar4);

        //5、日期计算
        GregorianCalendar calendar5 = new GregorianCalendar();
        calendar5.setTime(new Date());

        calendar5.add(Calendar.MONTH,1);//加1个月
        calendar5.add(Calendar.DAY_OF_MONTH,-1);//减1天
        System.out.println("现在时间加一个月减一天为");
        printCalendar(calendar5);
    }

    static void printCalendar(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
        String week = ""+ ((dayOfWeek==0)?"日":dayOfWeek);

        System.out.printf("%d年%d月%d日，星期%s，%d时%d分%d秒",year,month,day,week,hour,minute,second);
        System.out.println();
    }
}
