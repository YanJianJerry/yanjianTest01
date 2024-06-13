package mycode.math;

import java.math.BigDecimal;

/**
 * @author yanjian
 */
public class TestMath01 {
    public static void main(String[] args) {
        //
        test00();
        test01();
    }
    /**
     * 0e-8 格式转换
     */
    public static void test00(){
        BigDecimal bigDecimal_Zero = new BigDecimal("0.0000000000000000000");
        System.out.println(bigDecimal_Zero);
        System.out.println(Double.parseDouble(bigDecimal_Zero.toString()));
        System.out.println(bigDecimal_Zero.stripTrailingZeros().toString());
    }

    /**
     * 浮点数取模运算
     */
    public static void test01(){
        double qty1= 10.3;
        double result = qty1%5;
        System.out.println("取模后的值为："+result);
    }

    //
}
