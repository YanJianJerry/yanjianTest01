package com.Object.Date;

import java.util.Date;

/**
 * @Author: yanjian
 * @Date: 2022/04/07 23:53
 * @Description:
 * @Param:
 * @Return:
 * @Version:
 */
public class DateDemo {
    public static void main(String[] args) {

        //1、用long长整型存时间的毫秒数，long的最大值
        long longMaxValue = Long.MAX_VALUE;
        long longYearMaxValue = longMaxValue/(1000L*3600*24*365);
        System.out.println("long毫秒数表示最大的年份: "+longYearMaxValue);

        //2、现在的毫秒数
        long now = System.currentTimeMillis();
        System.out.println("现在的毫秒数: "+now);
        
        //3、Date() 没有传参代表当前的时刻
        Date date1 = new Date();
        System.out.println("现在的时间date1为: "+date1);
        System.out.println("现在的毫秒数date1: "+date1.getTime());

        //4、Date(参数)
        Date date2 = new Date(1000L * 3600 * 365 * 100);
        System.out.println("传参毫秒数date2的时间: "+date2);

    }
}
