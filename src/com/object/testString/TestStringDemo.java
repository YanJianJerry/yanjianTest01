package com.object.testString;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author yanjian
 */
public class TestStringDemo {
    //定义全局变量
    static String globalString;
    static boolean flag;

    public static void main(String[] args) {
        System.out.println("定义的全局变量globalString的默认值 is " + globalString);
        System.out.println("定义的全局布尔变量flag的默认值 is : " + flag);
        //字符串的定义==================================
        //字符串定义方式一: 使用字面量，从常量池中找（没有则在常量池中创建）
        String s1 = "abcd";

        //字符串定义方式二: new方式，开辟堆空间且栈指向堆，堆从常量池中找，有则引用地址
        String s2 = new String("abcd");

        //字符串定义方式三
        String s3 = new String();
        s3 = "abcd";

        /*
         * 字符串String的常用方法================================
         *
         *
         */
        //定义必要的局部变量
        String str = new String("HelloWorld");
        String str1 = new String();
        String str2;

        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        boolean flag5 = false;


        //1、str.equals() 比较字符串：严格区分大小写
        //str.equalsIgnoreCase() 比较字符串，忽略大小写
        flag1 = "helloWorld".equals(str);
        flag2 = str.equalsIgnoreCase("helloWorld");
        System.out.println("字符串比较flag1 is : " + flag1);
        System.out.println("字符串比较flag2 is : " + flag2);

        //2、str.length()  获取字符串长度
        int len = 0;
        len = str.length();
        System.out.println("length of str is : " + len);

        //3、字符串大小写转化
        str = str.toUpperCase();
        System.out.println("str转为全大写: " + str);
        str = str.toLowerCase();
        System.out.println("str转为全小写: " + str);

        //4、str.indexOf(String or int); 查找字符(串)在str中的位置index，找不到则返回-1
        int position = 0;
        int position1 = 0;
        int position2 = 0;
        position = str.indexOf("llo");

        position1 = str.indexOf(65);// position1 = str.indexOf('a');
        position2 = str.lastIndexOf('o');
        System.out.println("llo在str中的位置为: " + position);
        System.out.println("a在str中的位置为: " + position1);
        System.out.println("最后面哪个o在str中的位置为: " + position2);

        //5、str1.trim(); 去除首尾空格
        str1 = "   HelloWorld   ";
        len = str1.length();
        System.out.println("str1的实际长度 : " + len);
        str1 = str1.trim();
        len = str1.length();
        System.out.println("str1的去首尾空格的长度 : " + len);

        //6、 str.substring(int); str.substring(int,int); 截取字符串：[)
        str = "HelloWorld";
        str2 = str.substring(1);
        String str3 = str.substring(1, 2);
        System.out.println("str2为: " + str2);
        System.out.println("str3为: " + str3);

        //7、str.split() 字符串拆分：注意分割符在字符串最后一位时，可能无法识别
        String str4 = "abc;ABC;123";
        String[] sku = str4.split(";");
        for (String s :
                sku) {
            System.out.println("str4通过;的拆分: " + s);
        }
        //有些特殊字符如，* . / | $ 用于分割时，需要用\\ 进行转义 (    [     {    /    ^    -    $     ¦    }    ]    )    ?    *    +    .
        String str5 = "abc\\ABC";
        String[] split = str5.split("\\\\");
        for (String s : split) {
            System.out.println("str5通过\\\\拆分为: " + s);
        }

        //StringTokenizer拆分字符串
        String str6 = "abc|ABC";
        StringTokenizer token = new StringTokenizer(str6,"|");
        while(token.hasMoreElements()){
            System.out.println("str6通过|拆分为: "+token.nextElement());
        }

        //9、str.intern()    str由指向堆变为指向常量池
        String str7 = "hello";
        String str8 = new String("hello");
        str8 = str8.intern();
        System.out.println(str7.equals(str8));


        //10、str.charAt(index) 从字符串中根据索引取出字符
       String str9 = "HelloWorld";
        char c1 = str.charAt(0);
        System.out.println("c1为: "+c1);
        char[] chars = new char[str9.length()];
        char[] chars1 = str9.toCharArray();
        for (int i = 0; i < str9.length(); i++) {
            chars[i] = str.charAt(i);
            System.out.print("\t "+chars1[i]);
        }
        System.out.println("");
        for (char c :
                chars) {
            System.out.print("\t "+c);
        }
        System.out.println();

        //11、str.replace(target,replacement) 替换字符串中的字符或子字符串
        String str11 = "HelloWorldA";
        str11 = str11.replace("Hello","Welcome");
        str11 = str11.replaceFirst("World","BigWorld");
        str11 = str11.replace('A','!');
        System.out.println("替换字符串的结构为: "+str11);

        String str11_1 = "1000+100+12+9+4+2+1+0";
        str11_1 = str11_1.replaceAll("/+","$");
        System.out.println("替换字符串后为: "+str11_1);

        String str11_2 = "          123,456,789         ";
        str11_2 = str11_2.replace(",",":;").trim();
        System.out.println("str11_2: "+str11_2);

        //12、str12.contains(String);
        String str12 = "HelloWorld";
        boolean flag12 = str12.contains("World");
        System.out.println("12: "+flag12);

        //13、正则
        // 验证：boolean matches(String regex)
        // 拆分: String[] split(String regex)
        // 替换： String replaceAll(String regex, String replacement)
        String str13_1 = "1333";
        String str13_2 = "HelloWorld";
        String str13_3 = "123_abcABC";
        String str13_4 = "123|AAA$abc|AAA$ABC";
        String str13_5 = "123|AAA$abc|AAA$ABC";

        String regex1 = "\\d+";
        String regex2 = "[a-zA-Z]+";
        String regexp3 = "\\w{6,}";
        String regexp4 = "\\|(A{3})\\$";
        String regexp5 = "[|$]"; //"\\||\\$"

        boolean flag13_1 = str13_1.matches(regex1);
        boolean flag13_2 = str13_2.matches(regex2);
        boolean flag13_3 = str13_3.matches(regexp3);
        str13_4 = str13_4.replaceAll(regexp4,"_");
        String[] array13 = str13_5.split(regexp5,-1);

        System.out.println("13_1: "+flag13_1);
        System.out.println("13_2: "+flag13_2);
        System.out.println("13_3: "+flag13_3);
        System.out.println("13_4: "+str13_4);
        for (String s : array13){
            System.out.println("array13为: "+s);
        }

        //14 String.join(,); 数组或list转为String并有拼接符
        String[] arrayStr14 = new String[]{"abc","123","ABC"};
        List<String> list14 = new ArrayList<String>();
        list14.add("yan");list14.add("jian");

        String str14_1 = String.join("_",arrayStr14);
        String str14_2 = String.join("_",list14);
        System.out.println("join Test str14_1: "+str14_1);
        System.out.println("join Test str14_2: "+str14_2);
        /*
         * ================================
         *
         *
         */

    }
}
