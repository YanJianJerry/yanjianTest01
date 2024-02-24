package com.syntax;

import java.math.BigDecimal;

/**
 * 继承 extends
 * 继承是面向对象的三大特性之一。使我们更容易实现，类的扩展和代码的复用
 * 主要作用：
 *  1. 代码重用，实现类的扩展
 *  2. 方便建模
 */


/**
 * 父类 超类 基类
 * 子类 派生类
 * Java的类中只有单继承（C++是多继承）
 * Java的接口有多继承
 * 子类继承父类，可以得到父类的全部属性和方法（除了构造器），但是不能直接访问父类的私有属性和方法
 *
 *
 */
public class Inherit {
    public static void main(String[] args) {
        //person1
        PersonInner person1 = new PersonInner();
        System.out.println(person1);
        person1.sleep();

        //student1
        StudentInner student1 = new StudentInner();
        System.out.println(student1);
        student1.sleep();
        System.out.println(student1 instanceof StudentInner);
        System.out.println(student1 instanceof PersonInner);
        System.out.println(student1 instanceof Object);

        //male1
        Male  male1 = new Male();
        System.out.println(male1);
        male1.sleep();
    }

    static class Male extends PersonInner{
        //成员属性
        String gender;

        //构造器
        public Male(){
            this.name = "匿名";
            this.height = 0;
            this.asset = new BigDecimal("0.0");
            this.gender = "男";
        }



        @Override
        public String toString() {
            return "Male{" +
                    "gender='" + gender + '\'' +
                    ", name='" + name + '\'' +
                    ", height=" + height +
                    ", asset=" + asset +
                    '}';
        }
    }
}

class PersonInner extends Object{
    //成员属性
    String name;
    int height;
    BigDecimal asset;
    String gender;

    //构造器
    public PersonInner(){
        this.name = "匿名";
        this.height = 0;
        this.asset = new BigDecimal("0.0");
    }

    //成员方法
    public void sleep(){
        System.out.println("sleeping...");
    }

    public PersonInner whoAreYou(String hello){
        return new PersonInner();
    }

    @Override
    public String toString() {
        return "person{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", asset=" + asset +
                ", gender=" + gender +
                '}';
    }
}

 class StudentInner extends PersonInner{
    //成员属性
    float scores;

    //构造器
    public StudentInner() {
        this.name = "匿名";
        this.height = 0;
        this.asset = new BigDecimal("0.0");
        this.scores = 0.0f;
    }

    public StudentInner(float scores) {
        this.scores = scores;
    }

    //成员方法
    public void study(){
        System.out.println("Studying");
    }

     @Override
     public StudentInner whoAreYou(String hello) {
         return new StudentInner();//返回值类型可以小于父类
     }

     @Override
    public String toString() {
        return "student{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", asset=" + asset +
                ", scores=" + scores +
                '}';
    }
}


