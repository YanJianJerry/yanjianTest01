package com.common.util.apache.commons.lang;

import org.apache.commons.lang3.StringUtils;

public class TestStringUtill {

    public static void main(String[] args) {

    }

    /**
     * StringUtils
     * 空值判断（最核心）
     * Java 原生的 String.isEmpty() 无法处理 null（会报空指针），而 StringUtils 完美解决这个问题
     */
    public static void test01(){
        String str1 = null;
        String str2 = "";       // 空字符串
        String str3 = "   ";    // 全空格字符串
        String str4 = "hello";  // 正常字符串

        // 1. isEmpty：判断是否为 null 或 空字符串（""）
        System.out.println(StringUtils.isEmpty(str1));

        // 2. isBlank：判断是否为 null、空字符串 或 全空格（开发中更常用）
        System.out.println(StringUtils.isBlank(str3));  // true


    }

    /**
     * StringUtils
     * 字符串拼接（避免 null 变成 "null" 字符串）
     * 原生拼接 null 会得到 "null"，而 StringUtils 会自动处理
     */
    public static void test02(){
        String a = "Hello";
        String b = null;
        String c = "World";

        // 原生拼接：Hello null World
        String nativeJoin = a + " " + b + " " + c;
        // StringUtils拼接：Hello  World（自动忽略 null）
        String utilsJoin = StringUtils.join(a, " ", b, " ", c);
        // 更优雅的拼接（指定分隔符）：Hello|World
        String utilsJoinWithDelimiter = StringUtils.joinWith("|", a, b, c);

        System.out.println(nativeJoin);          // Hello null World
        System.out.println(utilsJoin);           // Hello  World
        System.out.println(utilsJoinWithDelimiter); // Hello|World
    }
}
