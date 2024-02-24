package com.Object.Arrays;


import java.util.Arrays;

public class Arraydemo01 {
    public static void main(String[] args) {
        //数组的初始化
        //静态初始化
        int[] a = {1, 2, 3};
        //Man[] mans = {new Man(), new Man()};

        //动态初始化
        int[] b = new int[10];
        b[0] = 12;

        System.out.println(a[1]);
        System.out.println(b[0]);

        int sum = 0;
        for (int j : a) {
            System.out.println(j);
            sum += j;
        }
        System.out.println("sum=" + sum);


        //测试reverse
        int[] reverse = reverse(a);
        System.out.println(reverse[0]+" "+reverse[1]+" "+reverse[2]);
        printArrays(reverse);

        //排序
        //String str = "12;4;9;2;1000;100;0;01";
        String str = "12*4*9*2*1000*100*0*01";
        //String str = "";
        String splitCode = "\\*";//分割符
        String joinCode = "*";
        //String[] arraySort = {"3","10","9","12"};
        String[] arraySort= str.split(splitCode,-1);
        System.out.println("数组长度："+arraySort.length);
        if (arraySort.length > 1) {
            int[] arrayInt =new int[arraySort.length];
            for (int i = 0; i < arraySort.length; i++) {
                arrayInt[i] = Integer.parseInt(arraySort[i]);
            }

            //排序
            Arrays.sort(arrayInt);

            StringBuilder str2 = new StringBuilder();
            for (int i = 0; i < arrayInt.length; i++) {
                if(i == arrayInt.length-1){
                    str2.append(arrayInt[i]);
                }else{
                    str2.append(arrayInt[i]+joinCode);
                }
            }
            System.out.println("排序结果："+str2.toString());
        }




    }

    //反转数组
    public static int[] reverse(int[] arrays) {
        int[] result = new int[arrays.length];

        //反转操作
        for (int i = 0, j = result.length - 1; i < arrays.length; i++, j--) {
            result[j] = arrays[i];
        }
        return result;
    }

    //打印数组
    public static void printArrays(int[] arrays){
        //没有下标的for遍历数组
        for (int i : arrays) {
            System.out.println(i);
        }

    }
}
