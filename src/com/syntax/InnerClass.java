package com.syntax;

/**
 * @Author: yanjian
 * @CreateDate: 2023/07/28 23:34
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */

/**
 * 内部类
 * 成员内部类：非静态内部类、静态内部类
 * 匿名内部类
 * 局部内部类
 *
 * 特点：
 * 1.实现了更好的封装。内部类只能让外部类访问，同包的其他类无法直接访问
 * 2.内部类可以直接访问外部类的私有属性，内部类被当成其外部类的成员。单外部类不能直接访问，内部类的属性。
 * 3.内部类是一个编译时概念，编译后会形成两个类，Outer.class 和 Outer$Inner.class。所以内部类是相对独立的存在。
 * 4.内部类的成员名和方法名可以和外部类相同
 */
public class InnerClass {

}


