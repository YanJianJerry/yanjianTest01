package com.object.testCollection.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TestCaseInsensitiveMap {
    public static void main(String[] args) {
        TestCaseInsensitiveMap.test1();

        TestCaseInsensitiveMap.test2();
    }

    public static void test1() {
        Map<String, String> map = new HashMap<>();
        map.put("Name", "Jerry");
        map.put("name", "Yan");
        map.put("age", "18");

        Map<String, String> caseInsensitiveMap = new CaseInsensitiveMap<>(map);
        System.out.println(caseInsensitiveMap);
        System.out.println(caseInsensitiveMap.get("NAME"));
    }

    /**
     * TreeMap实现
     * 不支持 null key（TreeMap 不允许）
     * key 保留原始大小写，但查找时忽略大小写
     * 结果是有序的（按字典序）
     */
    public static void test2() {
        Map<String, String> map = new HashMap<>();
        map.put("Name", "Jerry");
        map.put("name", "Yan");
        map.put("age", "18");

        Map<String, String> caseInsensitiveMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        caseInsensitiveMap.putAll(map);
        System.out.println(caseInsensitiveMap);
        System.out.println(caseInsensitiveMap.get("NAME"));
    }
}
