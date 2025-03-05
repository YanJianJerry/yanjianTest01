package com.reflection;

import com.bean.pojo.Person;
import com.bean.pojo.TeacherJob;
import com.object.myObjects.StudentObj;

import java.lang.reflect.InvocationTargetException;

public class Reflection02 {
    public static void main(String[] args) {
        try {
            //
            test1();

            //
            TeacherJob teacherJob = new TeacherJob();
            test2(teacherJob,"introduce");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //
    public static void getClassName() throws ClassNotFoundException {
        //1.首先获得一个类
        Class c1 = Class.forName("com.bean.pojo.Person");

        //2.


        //3.


    }

    //
    public static void test1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //
        Class aClass = Class.forName("com.object.myObjects.StudentObj");

        //
        String name1 = ((StudentObj) aClass.newInstance()).getName();
        System.out.println(name1);

        StudentObj obj1 = ((StudentObj)aClass.newInstance());
        StudentObj obj = ((StudentObj)aClass.getConstructor(String.class).newInstance("Jerry"));
        String name2 = (String) aClass.getMethod("getName").invoke(obj);
        aClass.getMethod("setName",String.class).invoke(obj,"Tom");
        System.out.println(name2);
        System.out.println(obj.getName());
    }

    //
    public static void test2(Person obj,String method) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        obj.getClass().getMethod(method).invoke(obj);
    }


}
