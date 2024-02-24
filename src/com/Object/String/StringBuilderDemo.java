package com.Object.String;

/**
 * @Author: yanjian
 * @Date: 2022 04 03 14:04
 * @Description:
 * @Param:
 * @Return:
 * @Version:
 */
public class StringBuilderDemo {
    public static void main(String[] args) {
        //1、strBuilder.append(参数) :在后面追加字符串  return this;(不会产生新的对象)
        StringBuilder strBuilder = new StringBuilder("abc#ABC%123");
        for (int i = 0; i < 3; i++) {
            strBuilder.append((char)('a'+i));
        }
        System.out.println("循环追加结果为: "+strBuilder);

        strBuilder.append("Hello").append(", W").append("o").append('r').append('l').append('d');
        System.out.println("方法点链追加的结果为: "+strBuilder);

        //2、strBuilder.insert(位置,字符串参数): 指定位置插入字符串
        StringBuilder strBuilder2 = new StringBuilder("Java");
        strBuilder2.insert(0,"爱").insert(0,"我");
        strBuilder2.insert(6,"!");
        System.out.println("插入字符串的结果为: "+strBuilder2);

        //3、strBuilder.delete(from,to): 删除字符串 [)
        StringBuilder strBuilder3 = new StringBuilder();
        strBuilder3.append("abcABC123");
        strBuilder3.delete(3,6);
        System.out.println("删除子字符串结果为: "+strBuilder3);

        //4、strBuilder.deleteCharAt(位置): 删除字符串中某个位置的字符
        StringBuilder strBuilder4 = new StringBuilder("abcA2BC");
        strBuilder4.deleteCharAt(4).delete(0,3);
        System.out.println("删除字符结果为: "+strBuilder4);

        //5、strBuilder.reverse(): 字符串逆序
        StringBuilder strBuilder5 = new StringBuilder("abc");
        System.out.println("逆序的结果为: "+strBuilder5.reverse());

        //6、str：Builder.toString(): 转换成String输出
        StringBuilder strBuilder6 = new StringBuilder("abcABC123");
        System.out.println("转换成String输出: "+strBuilder6);
    }
}
