package com.syntax;

/**
 * 静态变量（类变量）、静态方法（类方法）
 * 1. static的属性和方法属于类（JVM中独一份的）
 * 2. 静态成员的声明周期和类相同，整个程序执行期间都有效
 * 3. 静态成员为该类的公共变量，属于类，被该类的所有实例共享，在类载入时初始化
 * 4. 一般用 类名.类属性/方法 来调用
 * 5. 在static 方法中不可直接访问非 static 成员 （static不能访问this）
 */

/**
 * 静态初始化块
 * 用于类的初始化操作，初始化类的属性
 * 静态初始化块，类加载的时候调用。不能方位非static成员
 * （构造方法是用于对象的简单属性的初始化）
 *
 * 静态初始化块的执行顺序
 * 从Object类开始，逐步执行子类的静态初始化块（父类 -> 子类）
 * （构造器也是如此）
 */
public class Static extends Object{

    static int count;//静态属性 属于类 独一份
    int index;

    //静态初始化块，类加载的时候调用。不能方位非static成员
    static {
        count = 0;
        print("静态初始化块");
    }

    public static void main(String[] args) {
        print("aaa");
        print("bbb");
        print("ccc");
        print("111", "ddd");

        /* 非static方法，需要new一个对象 */
        //print();//直接调用非静态成员，编译报错
        Static print = new Static();
        print.print();
        print.print();
    }

    //
    public static void print(String desc, String s) {
        System.out.println(desc + ": " + s);
    }

    //
    public static void print(String s) {
        System.out.println("输出" + count + ": " + s);
        count++;
        //index ++;
    }

    public void print() {
        System.out.println("输出" + index + "：public void print()");
        index++;
    }
}
