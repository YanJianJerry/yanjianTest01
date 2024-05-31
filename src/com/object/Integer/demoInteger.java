package com.Object.Integer;

/**
 * @Author: yanjian
 * @CreateDate: 2023/06/08 20:40
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class demoInteger {
    public static void main(String[] args) {

        //1.int转String
        Test01();

        //
    }

    //1.int转String
    private static void Test01(){
        int intValue1 = 123;
        String strValue1_1 = String.valueOf(intValue1);
        String strValue1_2 = Integer.toString(intValue1);
        String strValue1_3 = intValue1 + "";

        System.out.println(strValue1_1+strValue1_2+strValue1_3);
    }

    //2.
}



