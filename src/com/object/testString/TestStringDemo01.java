package com.object.testString;


import com.common.publicmethod.PM;
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;

/**
 *
 public static void test02(){
 // 1、定义

 // 2、赋值

 // 3、处理

 // 4、结果

 }
 */
public class TestStringDemo01 {
    public static void main(String[] args) {
        // StringUtils
//        test01();

        // split
        test02();
    }

    // StringUtils
    public static void test01(){
        String[] array= {"a","b","c"};
        String str1 = "a" + "b";
        ArrayList list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        //String jojnStr =  StringUtils.join(Arrays.asList(array),"-");
        String jojnStr =  StringUtils.join(list,"-");
        System.out.println(jojnStr);
        //
    }

    public static void test02(){
        // 1、定义
        String s1 = "111??aaa??%%222??bbb?? ";
        String separator = "\\?\\?";

        // 2、赋值

        // 3、处理
        String[] arr1 = s1.split("%%",-1);
        String[] arr2 = arr1[0].split(separator,-1);

        // 4、结果
        PM.toString(arr1);
        PM.toString(arr2);
        PM.toString(arr1[1].split(separator,-1));
        PM.toString(arr2[2]);
    }
}