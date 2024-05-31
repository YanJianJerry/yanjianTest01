package com.Object.String;


import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;

public class StringTest {
    public static void main(String[] args) {
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
}