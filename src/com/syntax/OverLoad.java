package com.syntax;

import java.math.BigDecimal;

/**
 * @Author: yanjian
 * @CreateDate: 2023/06/27 2:17
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
/*
    构成重载(形参列表不同)：参数类型不同，参数顺序不同，参数个数不同
 */
public class OverLoad {
    public static void main(String[] args) {
        OverLoad overLoad = new OverLoad();
        String str = overLoad.add(1,2.4);
        System.out.println(str);

        //
        overLoad.test();

    }

    private String add() {
        return "";
    }

    private String add(int a) {
        return Integer.toString(a);
    }

    private String add(double a) {
        return Double.toString(a);
    }

    private String add(int a, double b) {
        return Double.toString(a + b);
    }

    private String add(double a, int b) {
        return Double.toString(a + b);
    }

    private String add(String a,String b,String... args){
        BigDecimal qty = new BigDecimal(a).add(new BigDecimal(b));

        for (String arg : args){
            qty = qty.add(new BigDecimal(arg));
        }

        return qty.toString();
    }

    //
    private void test() {
        System.out.println(add("1.2","11.333","10","20","100"));
    }
}
