package Object.Arrays;

public class ArrayTest01 {
    public static void main(String[] args) {
        //
        test01();
    }

    public static void test01(){
        String[] strArray01 = {"aaa","bbb","cccc"};
        System.out.println(strArray01[0]);
        strArray01 = new String[]{"1111"};
        System.out.println(strArray01[0]);
    }
}
