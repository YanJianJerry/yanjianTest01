package com.Object.Double;

import java.math.BigDecimal;

public class DoubleTest01 {

    public static void main(String[] args) {
        // 1.
        DoubleTest01 d1 = new DoubleTest01();
        d1.test01();
        d1.test02();

        // 2.

    }

    /**
     * 浮点数比较
     */
    private void test01(){

        float a =1.0f-0.9f;
        float b =0.9f-0.8f;
        float diff =1e-6f;
        Float aObj = Float.valueOf(a);
        Float bObj = Float.valueOf(b);
        System.out.println(diff);
        System.out.println(a==b);
        System.out.println(aObj.equals(bObj));
        if(Math.abs(a -b)<diff){
            System.out.println("true");
        }
    }

    /**
     *
     */
    private void test02() {

        BigDecimal a = new BigDecimal("1.0");

        BigDecimal b = new BigDecimal("0.9");

        BigDecimal c = new BigDecimal("0.8");

        BigDecimal x = a.subtract(b);

        BigDecimal y = b.subtract(c);

        if (x.equals(y)) {
            System.out.println("true");
        }
    }



}