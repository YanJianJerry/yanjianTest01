package com.yan.method;

import java.util.*;
public class TestMethod {
    public static void main(String[] args){
        test();
    }

    //加法
    public static int add(int a,int b){
        return a+b;
    }

    //浮点加法(方法重载)
    public static double add(double a,double b){
        return a+b;
    }

    //test方法
    public static void test(){
        Scanner scanner = new Scanner(System.in);
        int a=0;
        int b = 0;
        System.out.println("输入a");
        a = scanner.nextInt();
        System.out.println("输入b");
        b = scanner.nextInt();
        int sum = add(a,b);
        System.out.println(sum);

        double sumf = add(1.0,2.0);
        System.out.println(sumf);

        scanner.close();
    }
}


