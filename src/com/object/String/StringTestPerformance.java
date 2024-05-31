package com.Object.String;

/**
 * @Author: yanjian
 * @Date: 2022/04/10 16:18
 * @Description:
 * @Param:
 * @Return:
 * @Version:
 */
public class StringTestPerformance {
    public static void main(String[] args) {

        String str = new String();
        /*使用String进行字符串循环拼接的效率*/
        //获取系统剩余内存空间（JVM虚拟机的内存空间）
        long freeMemoryOfJvmStart1 =  Runtime.getRuntime().freeMemory();
        //获取系统的当前时刻（毫秒数）
        long startTime1 = System.currentTimeMillis();

        //对str循环拼接
        for (int i = 0; i < 5000; i++) {
            str += i;
        }
        long freeMemoryOfJvmEnd1 =  Runtime.getRuntime().freeMemory();
        long endTime1 = System.currentTimeMillis();

        //计算内存空间以及时间的消耗
        long costMemory1 = freeMemoryOfJvmStart1 - freeMemoryOfJvmEnd1;
        long costTime1 = endTime1 - startTime1;


        //System.out.println(str);
        System.out.println("对String循环拼接消耗的内存为: "+costMemory1);
        System.out.println("对String循环拼接消耗的时间为: "+costTime1);

        StringBuilder stringBuilder = new StringBuilder();
        /* 使用StringBuilder对其进行字符串循环拼接*/
        long freeMemoryOfJvmStart2 = Runtime.getRuntime().freeMemory();
        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            stringBuilder.append(i);
        }
        long freeMemoryOfJvmEnd2 = Runtime.getRuntime().freeMemory();
        long endTime2 = System.currentTimeMillis();

       long costMemory2 = freeMemoryOfJvmStart2 - freeMemoryOfJvmEnd2;
       long costTime2 = endTime2 - startTime2;
        //System.out.println(stringBuilder);
        System.out.println("StringBuilder循环拼接消耗内存为: "+costMemory2);
        System.out.println("StringBuilder循环拼接消耗时间为: "+costTime2);
    }
}
