package com.reflection;

import com.bean.pojo.Person;

public class Reflection02 {
    public static void main(String[] args) {

    }

    public static void getClassName() throws ClassNotFoundException {
        //1.首先获得一个类
        Class c1 = Class.forName("com.bean.pojo.Person");

        Person person = new Person();
        Class c2 = person.getClass();

        //2.



        //3/


    }

}
