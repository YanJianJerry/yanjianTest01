package test.learning;

/*
* 增强for循环，主要用来遍历数组
 */

public class TestFor02 {
    public static void main(String[] args) {
        int[] numbers = {10,20,30,40,50};

        //遍历数组的元素
        for(int x:numbers){
            System.out.println(x);
        }

        //
        for(int i = 0;i<numbers.length;i++){
            System.out.println(numbers[i]);
        }
    }
}
