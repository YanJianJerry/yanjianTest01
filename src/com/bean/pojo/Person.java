package com.bean.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.UUID;

/**
 * @Author: yanjian
 * @CreateDate: 2023/11/15 15:49
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class Person {
    /**
     * 性别
     */
    public static String gender;
    /**
     * 发色
     */
    public String hairColor;
    /**
     * 年龄
     */
    private int age;
    /**
     * 姓名
     */
    @JSONField(ordinal=1,name = "personName")
    protected String name;
    /**
     * id
     */
    @JSONField(serialize=true,deserialize = true)
    private String id;
    /**
     * 工作
     */
    private Object job;

    public Person() {
        this.id = UUID.randomUUID().toString();
        this.name = "匿名";
        this.age = 18;
    }

    public Person(String name) {
        this.name = name;
        new Person();
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        Person.gender = gender;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getJob() {
        return job;
    }

    public void setJob(Object job) {
        this.job = job;
    }

    // 自我介绍
    public void introduce(){
        System.out.println("My name is "+this.name+",and I am "+this.age+" years old.");
    }

    @Override
    public String toString() {
        return "Person{" +
                "hairColor='" + hairColor + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", job=" + job +
                '}';
    }
}
