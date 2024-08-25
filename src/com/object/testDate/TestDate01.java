package object.testDate;

import java.util.Date;

/**
 * 1. long类型存储时间戳
 * 2.
 * @author yanjian
 */
public class TestDate01 {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //
        test1();
    }

    /**
     * 基本方法的使用
     */
    public static void test1(){
        // 1.获取当前计算机时间
        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date();
        // Date = new Date(System.currentTimeMillis());
        System.out.println("1.当前时间");
        System.out.println("currentTimeMillis:"+currentTimeMillis);
        System.out.println("date:"+date);
        System.out.println("getTime:"+date.getTime());

        // 2. Date日期比较
        Date date1 = new Date(0L);
        System.out.println("date1:"+date1);
        System.out.println(date.equals(date1));
        System.out.println(date.before(date1));
        System.out.println(date.after(date1));

    }
}
