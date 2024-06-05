package com.object.testPattern;

/**
 * @Author: yanjian
 * @CreateDate: 2022/12/25 21:30
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class RegExpTest {
    public static void main(String[] args) {
        //1.
        Test01();
        System.out.println("===========================");
        //2.
        Test02();
    }

    //1.
    public static void Test01(){
        String str1 = "123.12";
        String str2 = "123.123";

        String regex1 = "^(([1-9]\\d*)|(0))(\\.\\d{1,2})?$";//最多保留2位小数
        String regex2 = "^(([^0][0-9]+|0)\\.([0-9]{1,2})$)|^([^0][0-9]+|0)$";//最多保留2位小数

        boolean flag1 = str1.matches(regex1);
        boolean flag2 = str2.matches(regex2);

        System.out.println("1.flag1: "+flag1);
        System.out.println("1.flag2: "+flag2);
    }

    //2.
    public static void Test02(){
            String strQty1 = "10";

            String regex1 = "^[1-9]\\d*|0$";//正整数校验
            String regex2 = "^[1-9]\\d*|0$";
            boolean flag1 = strQty1.matches(regex1);

            System.out.println("2.flag1: "+flag1);
    }
}
