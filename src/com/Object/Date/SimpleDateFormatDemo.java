package com.Object.Date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: yanjian
 * @Date: 2022/04/08 15:05
 * @Description:
 * @Param:
 * @Return:
 * @Version:
 */
public class SimpleDateFormatDemo {
    public static void main(String[] args) throws ParseException {

        Date dateNow = new Date();

        //1、new SimpleDateFormat(),设置时间格式
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy年MM月dd日");
        System.out.println("时间格式: "+simpleDateFormat1);

        //2、根据设置的格式，时间字符串转为时间对象
        Date date1 = simpleDateFormat1.parse("2022-01-01 00:00:00");
        System.out.println("时间对象date1为: "+date1);


        //3、根据设置的格式，时间对象转为时间字符串
        Date date2 = new Date();
        String strDate2 = simpleDateFormat1.format(date2);
        System.out.println("字符串date2为: "+strDate2);
        System.out.println("现在的年月日为: "+simpleDateFormat2.format(new Date()));

        //4、其他格式
        DateFormat pattern1 = new SimpleDateFormat("现在是今年第w周，第D天！");
        System.out.println(pattern1.format(new Date()));
    }
}
