package com.reflection;

public class CreateClass {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Student();
        System.out.println("这个人是："+person.name);

        //1.通过对象获得Class类
        Class<?> c1 = person.getClass();
        System.out.println(c1.hashCode());

        //2.Class.forName
        Class<?> c2 = Class.forName("com.reflection.Student");
        System.out.println(c2.hashCode());

        //3.类名.class
        Class<Student> c3 = Student.class;
        System.out.println(c3.hashCode());

        //4.基本内置类型的包装类都有一个Type属性
        Class<Integer> c4 = Integer.TYPE;
        System.out.println(c4);

        //通过Class类获取父类
        Class<?> c5 = c1.getSuperclass();
        System.out.println(c5);
    }
}

class Person{
    public String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person{
    public Student() {
        this.name = "学生";
    }

    public Student(String name) {
        this.name = "学生";
    }
}

class Teacher extends Person{
    public Teacher() {
        super.name = "教师";
    }

    public Teacher(String name) {
        this.name = "老师";
    }
}