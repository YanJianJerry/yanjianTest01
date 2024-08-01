package com.object.testArrays;

import java.util.ArrayList;
import java.util.List;

public class TestArray01 {
    public static void main(String[] args) {
        //
        //test01();

        //
        test02();
    }

    public static void test01(){
        String[] strArray01 = {"aaa","bbb","cccc"};
        System.out.println(strArray01[0]);
        strArray01 = new String[]{"1111"};
        System.out.println(strArray01[0]);
    }


    /**
     * 数组赋值测试
     */
    private static void test02(){
        List<String[]> list1 = new ArrayList<>();
        String[] arr1 = {"aaa","bbb"};
        String[] arr2 = new String[3];
        int[] arr3 = new int[]{1,3,4};

        //
        list1.add(arr1);
        System.out.println(list1);

        //
        arr1 = new String[]{"111","2222"};
        list1.add(arr1);
        System.out.print(list1);System.out.println(list1.get(0)[0]);

        //
        list1.get(0)[0] = "AAAAAAAAAA";
        System.out.print(list1);System.out.println(list1.get(0)[0]);

    }
}
