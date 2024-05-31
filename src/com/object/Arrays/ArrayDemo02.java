package com.Object.Arrays;

import java.util.Arrays;

public class ArrayDemo02 {
    public static void main(String[] args) {
        int[] a = {2,3,6,5,7,4,1};
        sortBubble(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sortBubble(int[] array) {
        int temp = 0;

        //外层循环，判断要走多少次
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = false;
            //内层找出此次循环中的最大值
            for (int j = 0; j < array.length - 1 - i; j++) {
                if(array[j] > array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    flag = true;
                }
            }

            //如果第一次循环遍历没有产生交换，说明已经排好序了，不必后续循环
            if(flag==false) {
                break;
            }
        }
        return;

    }
}
