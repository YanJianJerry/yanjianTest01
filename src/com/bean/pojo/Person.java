package com.bean.pojo;

import com.alibaba.fastjson.annotation.JSONField;

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
