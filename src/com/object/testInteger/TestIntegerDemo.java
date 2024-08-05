package com.object.testInteger;

import javax.script.ScriptContext;
import java.util.Scanner;

/**
 * @Author: yanjian
 * @CreateDate: 2023/06/08 20:40
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class TestIntegerDemo {
    public static void main(String[] args) {

        //1.int转String
        test01();

        //
        test02();

    }

    //1.int转String
    private static void test01(){
        int intValue1 = 123;
        String strValue1_1 = String.valueOf(intValue1);
        String strValue1_2 = Integer.toString(intValue1);
        String strValue1_3 = intValue1 + "";

        System.out.println(strValue1_1+strValue1_2+strValue1_3);
    }

    //2.Sting转int
    private static void test02(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String text = scanner.next();
            if (!("q".equalsIgnoreCase(text) || "exit".equalsIgnoreCase(text))){
                String regex2 = "^-?\\d+(\\.\\d+)?$";
                // 当输入的字符串不是有理数
                if (!text.matches(regex2)){
                    System.out.println("请输入有理数！");
                    continue;
                }
                // 先转double再转int，防止 1.000 这种情况报错
                double qtyDouble = Double.parseDouble(text);
                int qtyInt = (int)qtyDouble;
                System.out.println(qtyInt);
            }else {
                break;
            }
        }
    }
}



