package com.tools.fastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import com.bean.NewInstance;
import com.bean.pojo.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bean.StringValue.PERSON;
/**
 * @Author: yanjian
 * @CreateDate: 2023/11/15 15:28
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class FastJsonTest01 {
    public static void main(String[] args) {
        //1.序列化 反序列化 String and JavaBean
//        test01();

        //2.String 转 JSON 以及JSONArray
//        test02();

        //3.
//        test03();
        //4.
//        test04();

        //5.
        test05();

        //6.
//        test06();

        //7.
        test07();
    }

    /**
     * 序列化   对象 转为 JSON格式字符串
     * 反序列化  JSON格式字符串 转为 对象
     */
    public static void test01() {
        //获取对象实例
        NewInstance newInstance = new NewInstance();
        Person personObj = (Person) newInstance.getPerson(PERSON, "yanjian");

        //序列化
        String personStr = JSON.toJSONString(personObj);
        //反序列化
        Person personObj2 = JSON.parseObject(personStr, Person.class);

        System.out.println(personStr);
        System.out.println(personObj2.toString());
    }

    /**
     *
     */
    public static void test02() {
        //获取对象实例
        NewInstance newInstance = new NewInstance();
        Person personObj = (Person) newInstance.getPerson(PERSON, "yanjian");

        //序列化
        String personJsonStr = JSON.toJSONString(personObj);
        System.out.println(personJsonStr);

        //json字符串 转为 JSON对象
        JSONObject jsonObj = JSON.parseObject(personJsonStr);
        System.out.println(jsonObj.toString());
        System.out.println(jsonObj.toJSONString());

        //json数组 字符串 转为 JSON数组对象
        JSONArray jsonArr = JSON.parseArray("[" + personJsonStr + "]");
        System.out.println(jsonArr.toString());
        System.out.println(jsonArr.toJSONString());

        //json数组 字符串 转为 对象List
        List<Person> personList = JSON.parseArray(String.valueOf(jsonArr), Person.class);
        System.out.println(personList.toString());
    }

    /**
     * Map 转 JSON对象
     * 其实JSONObject 就是一个Map
     */
    public static void test03() {
        NewInstance newInstance = new NewInstance();
        Map<String, Object> map1 = newInstance.getTestMap1();

        //Map 转  JSON对象
        JSONObject jsonObj = new JSONObject(map1);
        System.out.println(jsonObj.toJSONString());
        System.out.println(jsonObj);
        //"{\"gender\":\"male\",\"Id\":\"001\",\"Name\":\"Bob\"}"

        //JSON对象 转 Map
        Map<String, Object> map2 = jsonObj;
        System.out.println(map2);
    }

    /**
     * Object[] 转json字符串 转List
     *
     */
    public static void test04(){
        NewInstance newInstance = new NewInstance();
        Object[] persons = newInstance.getPerson();

        //Object[] 转json字符串 转List
        String personsStr = JSON.toJSONString(persons);
        List<Person> personsList =  JSON.parseArray(personsStr, Person.class);
        System.out.println(personsStr);
        System.out.println(personsList);
        System.out.println(JSON.toJSONString(personsList));

        //

    }

    /**
     * List<Map> 转 json
     *
     */
    public static void test05(){
        // 声明变量
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        ArrayList arrayList = new ArrayList();

        // 变量赋值
        map1.put("name","Jerry");
        map1.put("skill","Java");

        map2.put("name","Tom");
        map2.put("skill","Python");

        arrayList.add(map1);
        arrayList.add(map2);

        // 执行方法
        String jsonStr = JSON.toJSONString(arrayList);
        System.out.println(jsonStr);

    }

    /**
     * 包含数组的json字符串转对象
     */
    public static void test06(){
        //
        String jsonstr1 = "{\"messge\":\"SUCCES\",\"data\":[\"2014-02-12\",\"2020-01-01\"]}";

        //
        JSONObject jsonObj1 = JSON.parseObject(jsonstr1);
        System.out.println(jsonObj1.get("data"));

        //
        JSONArray jsonArr1 = JSON.parseArray(jsonObj1.get("data").toString());
        System.out.println(jsonArr1.get(0));
    }

    /**
     * 校验字符串是否为JSON格式
     */
    public static void test07(){
        // 定义变量
        String jsonStr = "rqwerqerq";
        String jsonStr1 = "[{\"skill\":\"Java\",\"name\":\"Jerry\"},{\"skill\":\"Python\",\"name\":\"Tom\"}]";
        String jsonStr2 = "{\"name\":\"John\", \"age\":30}";
        boolean isValid;

        //
        JSONValidator validator = JSONValidator.from(jsonStr2);
        isValid = validator.validate();
        System.out.println(isValid);

        //
        boolean isJson = JSON.isValid(jsonStr);
        System.out.println(isJson);
    }
}
