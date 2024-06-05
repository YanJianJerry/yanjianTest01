package com.object.testArrays;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Integer.*;

/**
 * @Author: yanjian
 * @CreateDate: 2022/12/24 17:40
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class TestArraySort {
    public static void main(String[] args) {

        //升序排序
        ArraySortAscending();

        //逆序排序
        ArraySortDescending();
    }

    //正序排序
    public static void ArraySortAscending() {
        //排序
        //String str = "12;4;9;2;1000;100;0;01";
        String str = "12*4*9*2*1000*100*0*01";
        //String str = "";
        String splitCode = "\\*";//分割符
        String joinCode = "*";
        //String[] arraySort = {"3","10","9","12"};

        //字符串分割为数组
        String[] arraySort = str.split(splitCode, -1);
        System.out.println("数组长度：" + arraySort.length);

        //排除空串的情况
        if (arraySort.length > 1) {
            //字符串转为int
            int[] arrayInt = new int[arraySort.length];
            for (int i = 0; i < arraySort.length; i++) {
                arrayInt[i] = parseInt(arraySort[i]);
            }

            //int排序
            Arrays.sort(arrayInt,0,8);

            //排好序再用拼接符拼接成字符串
            StringBuilder str2 = new StringBuilder();
            for (int i = 0; i < arrayInt.length; i++) {
                if (i == arrayInt.length - 1) {
                    str2.append(arrayInt[i]);
                } else {
                    str2.append(arrayInt[i]).append(joinCode);
                }
            }
            System.out.println("升序排序结果：" + str2.toString());
        }
    }

    //逆序排序
    public static void ArraySortDescending() {
        //声明变量
        String str = "12;4;9;2;1000;100;0;01";
        String splitCode = ";";//分割符
        String joinCode = ":";

        //
        String[] arraySort = str.split(splitCode, -1);
        System.out.println("数组长度：" + arraySort.length);
        //排除空串的情况
        if (arraySort.length > 1) {
            //字符串转为int
            Integer[] arrayInt = new Integer[arraySort.length];
            for (int i = 0; i < arraySort.length; i++) {
                arrayInt[i] = parseInt(arraySort[i]);
            }

            //int逆序排序
            Comparator<Integer> cmp = new MyComparatorDescending();
            Arrays.sort(arrayInt,cmp);

            //排好序再用拼接符拼接成字符串
            StringBuilder str2 = new StringBuilder();
            for (int i = 0; i < arrayInt.length; i++) {
                if (i == arrayInt.length - 1) {
                    str2.append(arrayInt[i]);
                } else {
                    str2.append(arrayInt[i]).append(joinCode);
                }
            }
            System.out.println("逆序排序结果：" + str2.toString());
        }
    }

    //
    //Comparator是一个接口，所以这里我们自己定义的类MyComparator要implents该接口,而不是extends Comparator
    //Comparable和Comparator都是用来实现对象的比较、排序
    //Comparator位于java.util包下，而Comparable位于java.lang包下
    //Comparable接口的实现是在类的内部（如 String、Integer已经实现了Comparable接口，自己就可以完成比较大小操作），
    //Comparator接口的实现是在类的外部（可以理解为Comparable是自已完成比较，Comparator是外部程序实现比较）
    //实现Comparable接口要重写compareTo方法, 在compareTo方法里面实现比较
    static class MyComparatorDescending implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            //如果o1小于o2，我们就返回正值，如果o1大于o2我们就返回负值，
            //这样颠倒一下，就可以实现反向排序了
            return o2.compareTo(o1);
//            if (o1 < o2) {
//                return 1;
//            } else if (o1 > o2) {
//                return -1;
//            } else {
//                return 0;
//            }
        }
    }

    //对于一个已定义的二位数组a进行如下规则排序,首先按照每一个对应的一维数组第一个元素进行升序排序（即a[][0]）,若第一个元素相等,则按照第二个元素进行升序排序（a[][1]
    static class MyComparatorTest01 implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[0]==o2[0]) {
                return o1[1]-o2[1];
                //o1[1]-o2[1]表示对于第二个元素进行升序排序，如果为o2[1]-o1[1]则表示为降序。
            }
            else {
                return o1[0]-o2[0];
            }
        }
    }

}
