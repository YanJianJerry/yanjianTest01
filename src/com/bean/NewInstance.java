package com.bean;

import com.bean.pojo.Person;
import com.bean.pojo.TeacherJob;

import java.util.HashMap;
import java.util.Map;

import static com.bean.StringValue.PERSON;

/**
 * @Author: yanjian
 * @CreateDate: 2023/11/15 17:03
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class NewInstance {

    /**
     * 获取实例化Person
     * @param className 类名
     * @param args 后续变量
     * @return Person
     */
    public Object getPerson(String className, Object... args){
        if(PERSON.equalsIgnoreCase(className)){
            TeacherJob t = new TeacherJob();
            t.setSchool("中学");
            t.setSubject("数学");

            Person p1 = new Person();
            if(args.length==1){
                p1.setName(args[0].toString());
                p1.setJob(t);
            }else if(args.length== 2){
                p1.setName(args[0].toString());
                p1.setJob(args[1]);
            }else{
                p1.setJob(t);
                p1.setName("Jerry");
            }
            p1.setAge(18);
            p1.setId("10001");
            p1.setHairColor("black");

            return  p1;
        }
        return null;
    }

    /**
     * 获取实例化 Person[]
     * @return Person[]
     */
    public Object[] getPerson(){
        TeacherJob t1 = new TeacherJob();
        t1.setSchool("中学");
        t1.setSubject("语文");
        TeacherJob t2 = new TeacherJob();
        t2.setSchool("中学");
        t2.setSubject("数学");
        TeacherJob t3 = new TeacherJob();
        t3.setSchool("中学");
        t3.setSubject("体育");

        Person p1 = new Person();
        p1.setJob(t1);
        p1.setName("Jerry");
        p1.setAge(18);
        p1.setId("10001");
        p1.setHairColor("black");
        Person p2 = new Person();
        p2.setJob(t2);
        p2.setName("Bob");
        p2.setAge(22);
        p2.setId("10002");
        p2.setHairColor("brown");
        Person p3 = new Person();
        p3.setJob(t3);
        p3.setName("Tim");
        p3.setAge(26);
        p3.setId("10003");
        p3.setHairColor("yellow");

        return new Person[]{p1,p2,p3};
    }

    /**
     * 获取测试 Map1
     * @return Map
     */
    public Map<String,Object> getTestMap1(){
        //初始化
        HashMap<String, Object> map = new HashMap<>(10);

        //赋值
        map.put("Name","Bob");
        map.put("Id","001");
        map.put("gender","male");

        //处理

        //返回
        return map;
    }

}
