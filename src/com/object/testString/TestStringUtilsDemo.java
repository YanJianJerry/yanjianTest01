package com.object.testString;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;

/**
 * @Author: yanjian
 * @CreateDate: 2023/03/04 21:54
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class TestStringUtilsDemo {
    public static void main(String[] args) {
        //1.String join(Collection var0, String var1)
        String[] array= {"a","b","c"};
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
