package com.reflection;

import com.bean.NewInstance;
import com.bean.pojo.Person;

import java.util.Map;

import static com.bean.StringValue.PERSON;

/**
 * @Author: yanjian
 * @CreateDate: 2023/11/16 10:21
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class ReflectionTest {
    public static void main(String[] args) {
        test01();
    }

    public static void test01(){
        Person.gender = "male";
        //创建实例bean
        NewInstance newInstance = new NewInstance();
        com.bean.pojo.Person person = (com.bean.pojo.Person)newInstance.getPerson(PERSON);
        ReflectionUtils reflectionUtils = new ReflectionUtils();

        //反射 bean to map
        long s1 = System.currentTimeMillis();
        Map<String, Object> dataMap = reflectionUtils.beanToMap(person);
        long s2 = System.currentTimeMillis();
        System.out.println("花费时间毫秒："+(s2-s1));
        System.out.println(dataMap.toString());
    }
}
