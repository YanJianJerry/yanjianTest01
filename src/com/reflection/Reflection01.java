package com.reflection;

import javax.xml.bind.Element;

public class Reflection01 {
    public static void main(String[] args) throws ClassNotFoundException {
        //通过反射获取类的class对象
        Class aclass = Class.forName("com.reflection.User");
        System.out.println(aclass);
        System.out.println(aclass.hashCode());

        //一个类在内存中只有一个class对象
        //一个类被加载后，类的整个结构都会被封装在Class对象中
        Class bclass = Class.forName("com.reflection.User");
        System.out.println(bclass.hashCode());


        //所有类型的class
        Class c1 = Object.class;// 类
        Class c2 = Comparable.class;// 接口
        Class c3 = String[].class;// 一维数组
        Class c4 = int[][].class;// 二维数组
        Class c5 = Override.class;// 注解
        Class c6 = Element.class;//enum 枚举
        Class c7 = Integer.class;// 基本数据类型
        Class c8 = Void.class;// void
        Class<?> c9 = Class.class;// class

        System.out.println("=====================");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        System.out.println(c9);
        System.out.println("==========================");

        //只要元素类型与维度一致，就是同一个class
        Class c3_1 = String[].class;
        Class c4_1 = int[].class;
        System.out.println(c3.hashCode());
        System.out.println(c3_1.hashCode());
        System.out.println(c4.hashCode());
        System.out.println(c4_1.hashCode());
    }
}

//实体类：pojo entity
class User{
    private String name;
    private int id;
    private int age;

    public User(){

    }

    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    private void init(){
        this.name = "张三";
        this.age = 18;
        this.id = 1;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

