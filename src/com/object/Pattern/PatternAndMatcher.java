package com.Object.Pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: yanjian
 * @CreateDate: 2022/12/25 21:42
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class PatternAndMatcher {
    public static void main(String[] args) {
        //1.
        //Test01();

        //2.
        PatternTest();
    }

    //1.正则截取字符串
    public static void Test01(){
        String str1 = "=001.32=001.32=001.32";

        String strReg = "=\\d{3}\\.\\d{2}";

        Pattern pattern = Pattern.compile(strReg);
        Matcher matcher = pattern.matcher(str1);

        if (matcher.find()) {
            System.out.println("if截断："+matcher.group());
        }

        while (matcher.find()) {
            System.out.println("whileLoop: "+matcher.group());
        }
    }

    //2.
    public static void PatternTest(){
        String str1 = "abc123ABC!@#$%^&*()~':;\"\"''<>/?,[]{}\\-_=+456";
        String str2 = "abc.a1A|123.1aA|ABC";
        String str3 = "abc123ABC!@#$%^&*()~':;\"\"''<>/?,[]{}\\-_=+456";

        String regex1 = "\\W";
        String regex2 = "\\.(\\w{3})\\|";
        String regex3 = "\\d+";

        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        Pattern pattern3 = Pattern.compile(regex3);

        Matcher matcher1 = pattern1.matcher(str1);
        //Matcher matcher2 = pattern2.matcher(str2);
        Matcher matcher3 = pattern3.matcher(str3);

        str1 = matcher1.replaceAll("");
        String[] array2 = pattern2.split(str2);

        System.out.println("str1: "+str1);

        for (String s : array2){
            System.out.println("array2 is: "+s);
        }

        while(matcher3.find()){
            System.out.println("group(): "+matcher3.group());
            System.out.println("start(): "+matcher3.start());
            System.out.println("end(): "+matcher3.end());
        }
    }

    //3.
    public static void MatcherTest(){
        String str1 = "";
        String str2 = "";
        String str3 = "";

        String regex1 = "";
        String regex2 = "";
        String regex3 = "";

        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        Pattern pattern3 = Pattern.compile(regex3);

        Matcher matcher1 = pattern1.matcher(str1);
        Matcher matcher2 = pattern2.matcher(str2);
        Matcher matcher3 = pattern3.matcher(str3);
    }

    //4.

}
