package mycode.mytest;

import java.util.HashMap;

public class MyTest01 {
    public static void main(String[] args) {
        //
        test();
    }

    public static void test(){
        //
        int[] arr= {1,2,4,6,8,9,3,5,7,10};
        int sum = 17;

        //获取数组中相加和为sum的数的下标
        int[] indexArr = getIndexOfNum(arr,sum,2);

        // 循环输出
        System.out.println("数组下标为：");
        for (int index: indexArr) {
            System.out.print(index);
            System.out.print(",");
        }
        System.out.println();
    }

    public static int[] getIndexOfNum(int[] arr,int sum,int type){
        //声明
        HashMap<Integer, Integer> record = new HashMap<>(arr.length);
        int num = 0;

        //循环数组
        for (int i=0; i < arr.length; i++) {
            record.put(arr[i],i);
            num = sum - arr[i];
            if(record.containsKey(num)){
                return new int[]{record.get(num),i};
            }
        }
        return new int[]{-1};
    }
}
