package com.syntax;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 构造器（用来初始化对象）
 * 1.构造器通过new关键字调用
 * 2.返回对象但是没有返回类型，也不能用return
 * 3.如果没有定义构造器，编译器会自动构造一个无参构造方法。如果已定义则不会自动添加
 * 4.构造器的方法名必须和类名一致
 */
public class Constructor {
    //
    long a = 2_099_999_999;
     int b;
    short c =  12345;
    BigDecimal bigDecimal = new BigDecimal(111);

    Constructor(){
        this.a = 0;
        b = 1234567890;
    }

    public Constructor(long a) {
        this.a = a;
    }

    public Constructor(long a, int b, short c, BigDecimal bigDecimal) {
        this.a = a;//this指对象本身，创建了的对象的地址
        this.b = b;
        this.c = c;
        this.bigDecimal = bigDecimal;
    }

    Constructor(long a, int b){
        this.a = 1;
        this.bigDecimal = new BigDecimal(a+b);
        this.b = b;
    }

    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public short getC() {
        return c;
    }

    public void setC(short c) {
        this.c = c;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    @Override
    public String toString() {
        return "Constructor{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", bigDecimal=" + bigDecimal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Constructor)) return false;
        Constructor that = (Constructor) o;
        return getA() == that.getA() && getB() == that.getB() && getC() == that.getC() && getBigDecimal().equals(that.getBigDecimal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getA(), getB(), getC(), getBigDecimal());
    }

//    public String toString(){
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(this.a).append("-").append(this.b).append("-");
//        stringBuilder.append(this.c).append("-").append(this.bigDecimal.toString());
//        return stringBuilder.toString();
//    }

    public void consolePrint(String s){
        System.out.println(s);
        System.out.println("a:"+this.a);
        System.out.println("b:"+this.b);
        System.out.println("c:"+this.c);
        System.out.println("bigDecimal:"+this.bigDecimal.toString());
    }

    public static void main(String[] args) {
        Constructor constructor1 = new Constructor();
        Constructor constructor2 = new Constructor(123000,456);

        constructor1.consolePrint("============");
        constructor2.consolePrint("###########");
        System.out.println("----------------");
        constructor1.consolePrint(constructor1.toString());
        System.out.println(constructor1);
        System.out.println(constructor2);
    }
}
