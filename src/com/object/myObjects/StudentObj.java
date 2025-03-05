package com.object.myObjects;

/**
 * @Author: yanjian
 * @CreateDate: 2022/05/01 17:39
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class StudentObj {
    private String name;
    private int age;

    //无参构造器，new Student(); 的时候被调用
    public StudentObj(){
        name = "匿名";
        age = 0;
    }

    //含参构造器
    public StudentObj(String name) {
        this.name = name;
        this.age = 18;
    }

    //含参构造器
    public StudentObj(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //重写toString()方法
    public String toString(){
        return this.getName()+" -- "+this.getAge();
    }
}
